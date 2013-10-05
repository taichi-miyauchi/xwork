package xwork.flow;

import xwork.ChildItem;
import xwork.WorkData;
import xwork.WorkDataManager;
import xwork.WorkResult;
import xwork.flow.model.CaseModel;
import xwork.flow.model.FlowModel;
import xwork.flow.model.ItemModel;
import xwork.flow.model.JobModel;
import xwork.job.IJobProcess;
import xwork.job.model.Job;

/**
 * WorkFlowサービス.
 * WorkFlow定義に従って各ジョブ処理をコールします。
 * 
 * @author taichi
 */
public class WorkFlowService implements Runnable  {

	/** 実行フラグ */
	private boolean isRun = true;
	
	/**
	 * コンストラクタ.
	 */
	public WorkFlowService() {
	}
	
	/**
	 * スレッド実行.
	 */
	@Override
	public void run() {
		while (this.isRun) {
			try {
				// ワークフローイベント取得
				WorkFlowEvent event = WorkFlowEventQueue.get();
				
				// イベントが無い場合は少しだけ待つ(データが無いのにすぐループする必要がないため)
				if (event == null) {
					Thread.sleep(10L);
					continue;
				}
				
				System.out.println("[EVENT] " + event.getEventID() + " WorkID:" + event.getWorkID() 
						+ ", FlowName:" + event.getFlowName() + ",ItemID:" + event.getItemID()
						+ ", JobName:" + event.getJobName() + ",JobID:" + event.getJobID());
								
				// 作業データ取得
				WorkData data = WorkDataManager.get(event.getWorkID());

				// ジョブ名の取得
				String jobName = event.getJobName();
				
				// WorkIDのみのイベントの場合（フローの最初）
				// ジョブが指定されていないので、最初のジョブを呼出す
				if (event.getJobName() == null) {
					JobModel jobModel = data.getWorkFlow().getJobList().get(0);
					jobName = jobModel.getName();
				} 
				
				// ジョブプロセスの生成
				Class<?> cls = Class.forName("xwork.job.process." + jobName + "JobProcess"); 
				IJobProcess process = (IJobProcess)cls.newInstance();
				
				// イベント毎の処理
				switch (event.getEventID()) {
				case START :
					
					// Jobプロセス開始（ジョブの生成）
					process.start(data, event);
					break;
					
				case FINISH :
					
					// Jobプロセス完了
					process.finish(data, event);
					
					// 処理対象ジョブ
					Job targetJob = data.getJob(event.getJobID());
					
					/** 次ジョブへの遷移処理 */
					// ジョブ定義取得
					JobModel jobConfig = null;
					if (event.getFlowName() != null) {
						jobConfig = data.getWorkFlow().getFlowModel(event.getFlowName()).getJobModel(targetJob.getJobName());
					} else {
						jobConfig = data.getWorkFlow().getJobModel(targetJob.getJobName());
					}
					CaseModel cmodel = jobConfig.switchModel.getCase(targetJob.getResultStatus());
					if (cmodel != null) {
												
						// 通常分岐の場合
						if (cmodel.getTo() != null) {
							
							// 作業プロセスキューに登録（処理開始）※直接処理をコールしても良い
							if ("#end".equals(cmodel.getTo())) {
								
								// 子要素のフローの場合
								if (event.getFlowName() != null) {
									System.out.println("子Flow終了");
									
									// flow定義を取得し、<flow to="a">のaを取得する
									String nextJobName = data.getWorkFlow().getFlowModel(targetJob.getFlowName()).getTo();
									
									ChildItem childItem = data.getChildItem(targetJob.getItemID());
									childItem.setStatus("Finish");
									
									// ChildItemの完了処理
									// 最終ジョブの結果を結果として設定する
									Job lastJob = childItem.getLastJob();
									
									// TODO:ChildItemの結果もItems対応する
									childItem.setResult(lastJob.getResultList().get(0).getContent());
									
									
									// 子要素フローすべて完了かチェック
									boolean isAllFinish = true;
									for (ChildItem child : data.getItemList()) {
										if (!"Finish".equals(child.getStatus())) {
											isAllFinish = false;
											break;
										}
									}
									// 全ての子要素のフローが完了した場合に、親ジョブをコールする
									if (isAllFinish) {
										// 次ジョブの開始イベント登録
										WorkFlowEventQueue.put(new WorkFlowEvent(WorkFlowEvent.EventID.START, data.getWorkID(), null, null, nextJobName, null));
									}
									
								} 
								// 親要素のフローの場合
								else {
									
									// WorkDataの完了処理
									// 最終ジョブの結果を結果として設定する
									Job lastJob = data.getLastJob();
									WorkResult wret = new WorkResult();
									wret.setItemList(lastJob.getResultList().get(0).getItems());
									data.setWorkResult(wret);
									
									// WorkDataのステータスを完了とする
									// TODO:とりあえずresultが設定された場合を完了とみなす
								}
								
							} else {
								// 次ジョブの開始イベント登録
								WorkFlowEventQueue.put(new WorkFlowEvent(WorkFlowEvent.EventID.START, data.getWorkID(), targetJob.getFlowName(), targetJob.getItemID(), cmodel.getTo(), null));
							}
						
						// 子項目別分岐の場合
						} else {
							
							// トリマージョブ結果(子要素)の項目数ループし、次フローを決定し、ジョブを設定
							for(ChildItem child : data.getItemList()) {
								
								// フローの決定(子項目名からフローを検索)
								ItemModel imodel = cmodel.getItemModel(child.getName());
								
								FlowModel childFlow = data.getWorkFlow().getFlowModel(imodel.getTo());

								// サブフローの先頭ジョブ
								String nextJobName = childFlow.getJobList().get(0).getName();

								// 作業プロセスキューに登録（処理開始）※直接処理をコールしても良い
								if ("#end".equals(cmodel.getTo())) {
									System.out.println("Flow終了");
								} else {
									WorkFlowEventQueue.put(new WorkFlowEvent(WorkFlowEvent.EventID.START, data.getWorkID(), imodel.getTo(), child.getId(), nextJobName, null));
								}
							}
							
						}
						
					}
					break;
					
				default:
					break; 
				}
				
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	

	/**
	 * 実行フラグ取得.
	 * @return 
	 */
	public boolean isRun() {
		return isRun;
	}
	/**
	 * 実行フラグ設定.
	 * @param isRun
	 */
	public void setRun(boolean isRun) {
		this.isRun = isRun;
	}
}

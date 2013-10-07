package xwork.job.process;

import xwork.ChildItem;
import xwork.WorkData;
import xwork.core.model.Item;
import xwork.flow.WorkFlowEvent;
import xwork.flow.WorkFlowEventQueue;
import xwork.job.IJobProcess;
import xwork.job.JobManager;
import xwork.job.model.Job;
import xwork.job.model.JobRequest;
import xwork.job.model.JobResult;

/**
 * 全子要素結果チェック処理.
 * 
 * [JOB-IF]
 * <job>
 *   <request>
 *      <item>エントリA結果</item>
 *      <item>エントリB結果</item>
 *    </request>
 *    
 *    <result>
 *      <status>Match/UnMatch</status>
 *      <item>エントリ結果(最終)</item> ※これは一致(Match)した場合に設定する
 *    </result>
 * </job>
 * 
 * @author taichi
 */
public class AllItemCheckJobProcess implements IJobProcess {
	
	/**
	 * ジョブの作成.
	 * @param workData
	 * @return
	 */
	private Job createJob(WorkData workData, WorkFlowEvent event) {

		// ジョブの取得
		Job job = new Job("AllItemCheck");
		job.setWorkID(workData.getWorkID());
		job.setFlowName(event.getFlowName());
		
		// 要求データ作成
		JobRequest req = new JobRequest();
		
		// 子項目ごとの結果をリクエストに纏める
		for(ChildItem child : workData.getItemList()) {
			System.out.println(child.getName() + ":" + child.getResult());
			req.addItem(new Item(child.getName(), child.getResult()));
		}
		
		job.setRequest(req);		
		return job;
	}
	
	/**
	 * ジョブ開始処理.
	 * ・ジョブの作成し、　※メソッドを分けるべき？
	 * ・複数のエントリ結果の照合を行う。
	 * 
	 */
	public void start(WorkData workData, WorkFlowEvent event) {
		System.out.println("■AllItemCheck#start()■");

		// 要求ジョブの作成
		Job job = createJob(workData, event);
		
		// 結果の作成
		JobResult result = new JobResult();
		for (ChildItem child : workData.getItemList()) {
			result.addItem(child.getName(), child.getResult());
		}
		job.add(result);
				
		// 作業データに当ジョブを追加　⇒　これはどこかで永続化する必要がある
		workData.getJobList().add(job);
		
		// ジョブの登録
		JobManager.regist(job);
		
		// 作業プロセスキューに登録（処理開始）
		WorkFlowEventQueue.put(new WorkFlowEvent(WorkFlowEvent.EventID.FINISH, workData.getWorkID(), job.getFlowName(), job.getItemID(), job.getJobName(), job.getJobID()));
	}	
	
	/**
	 * ジョブ完了通知.
	 * @param data
	 */
	public void finish(WorkData data, WorkFlowEvent event) {
		System.out.println("■AllItemCheck#finish()■");
		// 処理なし
	}
}

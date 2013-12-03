package xwork.job.process;

import java.util.List;

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
 * エントリ結果チェック処理.
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
public class EntryCheckJobProcess implements IJobProcess {
	
	/**
	 * ジョブの作成.
	 * @param workData
	 * @return
	 */
	private Job createJob(WorkData workData, WorkFlowEvent event) {

		// ジョブの取得
		Job job = new Job("EntryCheck");
		job.setWorkID(workData.getWorkID());
		job.setFlowName(event.getFlowName());
		job.setItemID(event.getItemID());
		job.setParentItemID(event.getParentID());
		
		// 要求データ作成
		JobRequest req = new JobRequest();

		List<Job> jobList = workData.getFlow(event.getItemID()).getJobList();
		
		// 前回ジョブ(EntryJob) (ReEntryJob) 		
		// 過去ジョブ(Entry/ReEntry)を遡り要求データを作成する
		// 前回ジョブ(EntryJob) まで遡って、EntryJobの結果とReEntryJobの結果をリクエストに設定する。
		for (int i=0; i<jobList.size(); i++) {
			Job preJob = jobList.get(jobList.size() - (i+1));
			if ("Entry".equals(preJob.getJobName()) || "ReEntry".equals(preJob.getJobName())) {
				for (JobResult ret : preJob.getResultList()) {
					req.addItem(ret.getContent());
				}
			}
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
		System.out.println("■EntryCheck#start()■");

		// 要求ジョブの作成
		Job job = this.createJob(workData, event);
	
		//
		// エントリジョブ結果の照合処理
		// ・複数のエントリ結果の中で同一のエントリがあるか
		//
		// 結果の設定　最初は不一致としておく
		job.setResultStatus("UNMATCH");
		List<Item> items = job.getRequest().getItems();
		for (int i=0; i<items.size(); i++) {
			String baseText = items.get(i).getValue();
			for (int j=0; j<items.size(); j++) {
				if (i == j) continue;	// 基準と同じ場合はスルー
				if (baseText.equals(items.get(j).getValue())) {
					job.setResultStatus("MATCH");
					JobResult ret = new JobResult();
					ret.setContent(baseText);
					//ret.setStatus("Match");
					job.add(ret);
					break;
				}
			}
			if ("MATCH".equals(job.getResultStatus())) {
				break;
			}
		}
		
		// 作業データに当ジョブを追加　⇒　これはどこかで永続化する必要がある
		workData.getFlow(event.getItemID()).addJob(job);
		
		// ジョブの登録
		JobManager.regist(job);
		
		// 作業プロセスキューに登録（処理開始）
		WorkFlowEventQueue.put(
				new WorkFlowEvent(
						WorkFlowEvent.EventID.FINISH, 
						workData.getWorkID(), 
						job.getFlowName(), 
						job.getItemID(), 
						job.getParentItemID(),	// parentID
						job.getJobName(), 
						job.getJobID()));
	}	
	
	/**
	 * ジョブ完了通知.
	 * @param data
	 */
	public void finish(WorkData data, WorkFlowEvent event) {
		System.out.println("■EntryCheck#finish()■");
		// 処理なし
	}
}

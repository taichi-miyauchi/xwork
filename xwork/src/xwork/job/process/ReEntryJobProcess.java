package xwork.job.process;

import java.util.List;

import xwork.WorkData;
import xwork.flow.WorkFlowEvent;
import xwork.job.IJobProcess;
import xwork.job.JobManager;
import xwork.job.JobQueueManager;
import xwork.job.model.Job;
import xwork.job.model.JobRequest;
import xwork.job.model.JobResult;

/**
 * エントリジョブ処理.
 * @author 太一
 */
public class ReEntryJobProcess implements IJobProcess {
	
	/**
	 * ジョブ開始処理.
	 * WorkDataを元にWorkFlowに従ってエントリジョブを作成する。
	 * 
	 * <job>
	 *   <requst>
	 *   	<item><img>(base64)</img><item>
	 *      <item><text>エントリ結果１</text></item>	
	 *      <item><text>エントリ結果２</text></item>
	 *   </request>
	 *   <result>
	 *      <status>Entry/不明/キャンセル</status>
	 *   	 <item>エントリ結果3</item>
	 *   </result>
	 * </job>
	 */
	public void start(WorkData workData, WorkFlowEvent event) {
		System.out.println("■ReEntry#start()■");

		// ジョブの取得
		Job job = new Job("ReEntry");
		job.setWorkID(workData.getWorkID());
		job.setFlowName(event.getFlowName());
		job.setItemID(event.getItemID());

		// ジョブリクエスト
		JobRequest req = new JobRequest();
		
		// 前回ジョブ(EntryJob) まで遡って、EntryJobの結果とReEntryJobの結果をリクエストに設定する。
		List<Job> jobList = workData.getFlow(event.getItemID()).getJobList();
		for (int i=0; i<jobList.size(); i++) {
			Job preJob = jobList.get(jobList.size() - (i+1));
			if ("Entry".equals(preJob.getJobName()) || "ReEntry".equals(preJob.getJobName())) {
				for (JobResult ret : preJob.getResultList()) {
					req.addItem(ret.getContent());
				}
			}
		}		
		job.setRequest(req);
		
		// 作業データの更新
		workData.addJob(job);
		
		// ジョブ登録
		JobManager.regist(job);
		// 1つ
		JobQueueManager.put(job);
	}
	
	/**
	 * ジョブ完了通知.
	 * @param data
	 */
	public void finish(WorkData data, WorkFlowEvent event) {
		System.out.println("□ReEntry#finish()□");
		// 完了したジョブ内容をWorkDataに反映 ※このあたりの処理は共通化対象
		//String jobID = job.getID();

		Job job = data.getFlow(event.getItemID()).getCurrentJob();
		if (job.getResultList().size() >= 1) {
			job.setStatus("FINISH");			// ジョブ進捗状態＝完了設定　TODO:これは上位で設定すべき
			job.setResultStatus("FINISH");	// 完了設定
			System.out.println("完了！！！");
		}
	}
}

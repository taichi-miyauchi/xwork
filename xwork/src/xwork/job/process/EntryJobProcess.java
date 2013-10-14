package xwork.job.process;

import xwork.WorkData;
import xwork.core.model.Item;
import xwork.flow.WorkFlowEvent;
import xwork.job.IJobProcess;
import xwork.job.JobManager;
import xwork.job.JobQueueManager;
import xwork.job.model.Job;
import xwork.job.model.JobRequest;

/**
 * エントリジョブ処理.
 * @author 太一
 */
public class EntryJobProcess implements IJobProcess {
	
	/**
	 * ジョブ開始処理.
	 * WorkDataを元にWorkFlowに従ってエントリジョブを作成する。
	 */
	public void start(WorkData workData, WorkFlowEvent event) {
		System.out.println("■EntryJob#start()■");
				
		// エントリジョブの作成
		Job job = new Job("Entry");
		job.setWorkID(workData.getWorkID());
		job.setItemID(event.getItemID());
		job.setParentItemID(event.getParentID());
		
		// 要求データの作成 TODO:プロセス内では、リクエストの生成だけで良いのでは？　Jobは上位でも
		JobRequest req = new JobRequest();
		
		// 処理対象データ取得
		Item item = workData.getItem(event.getItemID());
		req.setContent(item.getValue());
		job.setRequest(req);
		
		// 作業データの更新
		workData.addJob(job);
		
		// エントリJobデータを登録
		JobManager.regist(job);
		
		// 作業者2人に依頼
		JobQueueManager.put(job);
		JobQueueManager.put(job);
	}
	
	/**
	 * ジョブ完了通知.
	 * @param data
	 */
	public void finish(WorkData data, WorkFlowEvent event) {
		System.out.println("□EntryJob#finish()□");
		// 完了したジョブ内容をWorkDataに反映 ※このあたりの処理は共通化対象
		//String jobID = job.getID();

		Job job = data.getJob(event.getJobID());
		if (job.getResultList().size() >= 2) {
			job.setStatus("FINISH");				// ジョブ進捗状態＝完了設定　TODO:これは上位で設定すべき
			job.setResultStatus("FINISH");		// 完了設定
			System.out.println("完了！！！");
		}
	}
}

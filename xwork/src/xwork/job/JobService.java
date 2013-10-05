package xwork.job;

import xwork.WorkData;
import xwork.WorkDataManager;
import xwork.flow.WorkFlowEvent;
import xwork.flow.WorkFlowEventQueue;
import xwork.job.model.Job;
import xwork.job.model.JobResult;


/**
 * JOBをWorkerに分配するサービス.
 * 
 * @author 太一
 */
public class JobService {

	/**
	 * ジョブ取得.
	 * @return
	 */
	public Job get() {
		return JobQueueManager.get();
	}
	
	/**
	 * ジョブ納品
	 * @param job
	 */
	public void submit(Job job) {
		/*デバッグ*/
		for (JobResult ret : job.getResultList()) {
			System.out.println("[ジョブ結果]" + ret.getContent());
		}
		/*デバッグ*/
		
		// ジョブ結果の保存
		JobManager.update(job);
		
		// 作業データの更新
		WorkData workData = WorkDataManager.get(job.getWorkID());
		// 現時点では同一インスタンスで以下の処理は不要
//		for (JobResult ret : job.getResultList()) {
//			workData.getCurrentJob().add(ret);			
//		}
		
		// WorkFlow通知
		// 作業プロセスキューに登録（ジョブ完了）
		WorkFlowEventQueue.put(
				new WorkFlowEvent(WorkFlowEvent.EventID.FINISH, workData.getWorkID(), job.getFlowName(), job.getItemID(), job.getJobName(), job.getJobID()));
		
	}
}

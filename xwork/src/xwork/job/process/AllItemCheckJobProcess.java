package xwork.job.process;

import xwork.WorkData;
import xwork.core.model.Flow;
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
 *      <item id="1" name="名前"><value>名前</value></item>
 *      <item id="2" name="TEL"><value>電話番号</value></item>
 *    </request>
 *    
 *    <result>
 *      <status>OK/NG</status>
 *      <item id="" name="">
 *      	<value>名前</value>
 *      </item>
 *      <item id="" name="">
 *      	<value>名前</value>
 *      </item>*      
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
		job.setParentItemID(event.getParentID());
		
		// 要求データ作成
		JobRequest req = new JobRequest();
		
		// 子項目ごとの結果をリクエストに纏める
		for(Flow flow : workData.getFlowList(event.getItemID())) {
			System.out.println(flow.getName() + ":" + flow.getResult().getValue());
			req.addItem(new Item(flow.getName(), flow.getResult().getValue()));
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
		for (Flow child : workData.getFlowList()) {
			result.addItem(child.getItemID(), child.getName(), child.getResult().getValue());
		}
		job.add(result);
				
		// 作業データに当ジョブを追加　⇒　これはどこかで永続化する必要がある
		workData.addJob(job);
		
		// ジョブの登録
		JobManager.regist(job);
		
		// 作業プロセスキューに登録（処理開始）
		WorkFlowEventQueue.put(new WorkFlowEvent(WorkFlowEvent.EventID.FINISH, workData.getWorkID(), job.getItemID(), event.getParentID(), job.getJobName(), job.getJobID()));
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

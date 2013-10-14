package xwork.job.process;

import xwork.WorkData;
import xwork.core.model.Flow;
import xwork.core.model.Item;
import xwork.flow.WorkFlowEvent;
import xwork.job.IJobProcess;
import xwork.job.JobManager;
import xwork.job.JobQueueManager;
import xwork.job.model.Job;
import xwork.job.model.JobRequest;
import xwork.job.model.JobResult;

/**
 * トリムジョブ処理.
 * 
 * [JOB-IF]
 * <job type="Trim">
 *   <request>
 *      <item><img>aadfasfd</img></item>
 *    </request>
 *    
 *    <result>
 *      <status>Trimmed/NoImage/NoItem</status>
 *      <item name="項目名" type="polygon/rectangle">
 *      	<property name="言語" value="日本語"/>
 *      	<value><point x="1" y="1"/><point x="2" y="2"/></value>
 *      	<value><rect x="1" y="1" width="100" height="100"/></value>
 *      </item>
 *      <item name="項目名">
 *      	<property name="言語" value="日本語"/>
 *      	<value><point x="1" y="1"/><point x="2" y="2"/></value>
 *      	<value><rect x="1" y="1" width="100" height="100"/></value> *      
 *      </item>
 *    </result>
 * </job>
 * 
 *  * @author 太一
 */
public class TrimJobProcess implements IJobProcess {
	
	/**
	 * ジョブ開始処理.
	 * WorkDataを元にWorkFlowに従ってエントリジョブを作成する。
	 */
	public void start(WorkData workData, WorkFlowEvent event) {
		System.out.println("■TrimmerJob#start()■");

		// 処理対処の項目データ取得
		Item item = workData.getItem(event.getItemID());
		
		// エントリジョブの作成
		Job job = new Job("Trim");
		job.setWorkID(workData.getWorkID());
		job.setItemID(event.getItemID());
		job.setParentItemID(event.getParentID());
		
		JobRequest req = new JobRequest();
		//req.addItem(item.getValue());
		req.setContent(item.getValue());
		job.setRequest(req);
		
		// 作業データの更新
		workData.addJob(job);
		
		// エントリJobデータを登録
		JobManager.regist(job);
		
		// 作業者1人に依頼
		JobQueueManager.put(job);			
	}
	
	/**
	 * ジョブ完了通知.
	 * @param data
	 */
	public void finish(WorkData data, WorkFlowEvent event) {
		System.out.println("□TrimmerJob#Finish()□");
		// 完了したジョブ内容をWorkDataに反映 ※このあたりの処理は共通化対象
		//String jobID = job.getID();

		// 対象のジョブを取得
		Job job = data.getJob(event.getJobID());
		JobResult ret = job.getResultList().get(0);
		for (Item item : ret.getItems()) {
			// TODO:画像分割処理
			item.getRect();
			
			// WorkDataに子要素登録
			//  →これにより、子要素のフローが実行される。
			Flow flow = new Flow();
			flow.setPID(event.getItemID());
			flow.setItemID(item.getId());
			flow.setName(item.getName());
			flow.setRequest(item);
			data.getFlowList().add(flow);			
		}
		
		job.setStatus("FINISH");			// ジョブ進捗状態＝完了設定　TODO:これは上位で設定すべき
		job.setResultStatus("Trimmed");	// 
	}
}

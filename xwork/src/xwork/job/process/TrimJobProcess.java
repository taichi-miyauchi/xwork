package xwork.job.process;

import xwork.ChildItem;
import xwork.WorkData;
import xwork.WorkDataManager;
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
 *      <item name="項目名">
 *      	<property name="言語" value="日本語"/>
 *      	<polygon><point x="1" y="1"/><point x="2" y="2"/></polygon>
 *      	<rect x="1" y="1" width="100" height="100"/>
 *      </item>
 *      <item name="項目名">
 *      
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
		
		// 要求データ取得
		String content = workData.getWorkRequest().getContent();

		// エントリジョブの作成
		Job job = new Job("Trim");
		job.setWorkID(workData.getWorkID());
		job.setFlowName(event.getFlowName());
		job.setItemID(event.getItemID());
		
		JobRequest req = new JobRequest();
		req.setContent(content);
		job.setRequest(req);
		
		// 作業データの更新
		WorkDataManager.get(workData.getWorkID()).getJobList().add(job);
		
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

		Job job = data.getCurrentJob();
		JobResult ret = job.getResultList().get(0);
		for (xwork.cmn.model.Item item : ret.getItems()) {
			// TODO:画像分割処理
			item.getRect();
			
			// WorkDataに子要素登録
			//  →これにより、子要素のフローが実行される。
			ChildItem child = new ChildItem();
			child.setId(item.getName());
			child.setName(item.getName());
			child.setContent(item.getValue());
			data.getItemList().add(child);
		}
		
		job.setStatus("FINISH");			// ジョブ進捗状態＝完了設定　TODO:これは上位で設定すべき
		job.setResultStatus("Trimmed");	// 
	}
}

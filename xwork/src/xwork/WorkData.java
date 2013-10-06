package xwork;

import java.util.ArrayList;
import java.util.List;

import xwork.flow.model.WorkFlowModel;
import xwork.job.model.Job;

/**
 * 作業データ.
 * <WorkData>
 * 	  <Request id="" type="名刺デジタル化"> ※ユーザからの要求
 *      <item id=""><value type="img">画像(Base64)</value></item>
 *      <item id="">
 *   </Request>
 *   
 *   <Result status="">
 *   
 *   </Result>
 * </WorkData>
 * 
 * @author taichi
 */
public class WorkData {

	/** 作業ID */
	private String workID = null;
	/** ワークフロー */
	private WorkFlowModel workFlow = null;
	/** 作業依頼データ */
	private WorkRequest request = null;
	/** 作業結果データ */
	private WorkResult result = null;
	/**
	 *  ジョブデータ(進捗データ) 
	 */
	private List<Job> jobList = new ArrayList<Job>();
	/**
	 * 子要素フローデータ(子要素の進捗データ）
	 */
	private List<ChildItem> itemList = new ArrayList<ChildItem>();
	
	
	/**
	 * コンストラクタ
	 */
	public WorkData()  {
	}
	/**
	 * コンストラクタ
	 * @param entry
	 */
	public WorkData(WorkRequest request) {
		this.request = request;
	}
	
	/**
	 * 依頼内容のチェック処理
	 */
	public void check() {
		
	}
	
	@Override
	public String toString() {
		String str = "";
		
		return str;
	}
	
	/**
	 * 指定JobIDのJobデータ取得.
	 * 
	 * @param jobID ジョブID
	 * @return
	 */
	public Job getJob(String jobID) {
		for (Job job : this.jobList) {
			if (jobID.equals(job.getJobID())) {
				return job;
			}
		}
		// 子項目からも検索
		Job job = null;
		for (ChildItem child : this.itemList) {
			job = child.getJob(jobID);
			if (job != null) {
				return job;
			}
		}
		return null;
	}
	
	/**
	 * 最後のジョブ取得.
	 * @return
	 */
	public Job getLastJob() {
		if (this.jobList.size() == 0) {
			// ジョブが１つもない
			return null;			
		} else {
			return this.jobList.get(this.jobList.size()-1);
		}
	}
	
	/**
	 * 現在ジョブ取得.
	 * @return
	 */
	public Job getCurrentJob() {
		if (this.jobList.size() == 0) {
			// ジョブが１つもない
			return null;			
		} else {
			return this.jobList.get(this.jobList.size()-1);
		}
	}

	/**
	 * １つ前のジョブ取得
	 * @return
	 */
	public Job getPreviousJob() {
		if (this.jobList.size() == 0) {
			return null;
		} else {
			return this.jobList.get(this.jobList.size() - 2);
		}
	}
	
	public ChildItem getChildItem(String name) {
		for (ChildItem item : this.itemList) {
			if (name.equals(item.getName())) {
				return item;
			}
		}
		return null;
	}
	
	public String getWorkID() {
		return this.workID;
	}
	public void setWorkID(String workID) {
		this.workID = workID;
	}
	
	public WorkRequest getWorkRequest() {
		return this.request;
	}
	
	public WorkResult getWorkResult() {
		return this.result;
	}
	public void setWorkResult(WorkResult result) {
		this.result = result;
	}
	
	public List<Job> getJobList() {
		return this.jobList;
	}
	
	public List<ChildItem> getItemList() {
		return this.itemList;
	}
	public void setItemList(List<ChildItem> itemList) {
		this.itemList = itemList;
	}
	
	public WorkFlowModel getWorkFlow() {
		return this.workFlow;
	}
	public void setWorkFlow(WorkFlowModel workFlow) {
		this.workFlow = workFlow;
	}
}

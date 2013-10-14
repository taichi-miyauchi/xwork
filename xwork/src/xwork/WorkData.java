package xwork;

import java.util.ArrayList;
import java.util.List;

import xwork.core.model.Flow;
import xwork.core.model.Item;
import xwork.flow.model.WorkFlowModel;
import xwork.job.model.Job;

/**
 * 作業データ.
 *
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
	/** フローデータ */
	private List<Flow> flowList = new ArrayList<Flow>();
	/** 作業結果データ */
	private WorkResult result = null;
	
	
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
	
//	public JobModel getJobModel(String flowName, String jobName) {
//		
//	}
	
	/**
	 * 指定ItemIDのItemデータ取得.
	 * 
	 * @param itemID
	 * @return
	 */
	public Item getItem(String itemID) {
		for (Item item : this.request.getItems()) {
			if (itemID.equals(item.getId())) {
				return item;
			}
		}
		for (Flow flow : this.flowList) {
			if (itemID.equals(flow.getItemID())) {
				return flow.getRequeset();
			}			
		}
		return null;
	}
	
	/**
	 * 指定JobIDのJobデータ取得.
	 * 
	 * @param jobID ジョブID
	 * @return
	 */
	public Job getJob(String jobID) {
		for (Flow flow : this.flowList) {
			for (Job job : flow.getJobList()) {
				if (jobID.equals(job.getJobID())) {
					return job;
				}				
			}
		}
		return null;
	}
	
	/**
	 * ジョブ追加.
	 * 
	 * @param job
	 */
	public void addJob(Job job) {
		Flow flow = getFlow(job.getItemID());
		// まだフローデータが作成されていない場合作成する。
		if (flow == null) {
			flow = new Flow();
			flow.setItemID(job.getItemID());
			this.flowList.add(flow);
		}
		flow.addJob(job);
	}
	
	
	public Flow getFlow(String itemID) {
		for (Flow flow : this.flowList) {
			if (itemID.equals(flow.getItemID())) {
				return flow;
			}				
		}
		return null;
	}
	
	/**
	 * 子項目のフローリスト取得.
	 * 
	 * @param pid
	 * @return
	 */
	public List<Flow> getFlowList(String pid) {
		List<Flow> flows = new ArrayList<Flow>();
		for (Flow flow : this.flowList) {
			if (pid.equals(flow.getPID())) {
				flows.add(flow);
			}
		}
		return flows;
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
	
	public WorkFlowModel getWorkFlow() {
		return this.workFlow;
	}
	public void setWorkFlow(WorkFlowModel workFlow) {
		this.workFlow = workFlow;
	}
	
	public List<Flow> getFlowList() {
		return this.flowList;
	}
	public void setFlowList(List<Flow> flowList) {
		this.flowList = flowList;
	}
}

package xwork.core.model;

import java.util.ArrayList;
import java.util.List;

import xwork.job.model.Job;

/**
 * フローデータ.
 * 項目毎のワークフローの状態を保持する。
 * 
 * @author taichi
 */
public class Flow {
	
	private String id = null;
	private String status = null;
	private String itemID = null;
	
	private Item request = null;
	private List<Job> jobList = new ArrayList<Job>();
	private Item result = null;
	
	/**
	 * コンストラクタ.
	 */
	public Flow() {
		
	}
	
	/**
	 * ジョブ追加.
	 * @param job
	 */
	public void addJob(Job job) {
		this.jobList.add(job);
	}
	
	
	public String getID() {
		return this.id;
	}
	public void setID(String id) {
		this.id = id;
	}
	
	public String getStatus() {
		return this.status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getItemID() {
		return this.itemID;
	}
	public void setItemID(String itemID) {
		this.itemID = itemID;
	}
	
	public Item getRequeset() {
		return this.request;
	}
	public void setRequest(Item request) {
		this.request = request;
	}
	
	public List<Job> getJobList() {
		return this.jobList;
	}
	public void setJobList(List<Job> jobList) {
		this.jobList = jobList;
	}
 	
	public Item getResult() {
		return this.result;
	}
	public void setResult(Item result) {
		this.result = result;
	}
 }

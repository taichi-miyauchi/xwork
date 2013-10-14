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
	private String pid = null;
	private String name = null;
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
	
	
	
	public String getID() {
		return this.id;
	}
	public void setID(String id) {
		this.id = id;
	}
	
	public String getPID() {
		return this.pid;
	}
	public void setPID(String pid) {
		this.pid = pid;
	}
	
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
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
	public void setResultValue(String value) {
		this.result = new Item();
		this.result.setValue(value);
	}
 }

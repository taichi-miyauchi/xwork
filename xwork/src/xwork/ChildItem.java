package xwork;

import java.util.ArrayList;
import java.util.List;

import xwork.job.model.Job;


/**
 * 子項目フロー.
 * 
 * @author 太一
 */
public class ChildItem {
	
	private String id = null;
	private String name = null;
	/**
	 * ステータス.
	 * 処理中/完了
	 */
	private String status = null;
	/** 
	 * 子項目の実体 
	 * ※現時点では、トリマーの子画像を想定
	 */
	private String content = null;
	/** 
	 * ジョブデータ(進捗データ) 
	 */
	private List<Job> jobList = new ArrayList<Job>();
	/**
	 * 作業結果.
	 */
	private String result = null;
	
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
		return null;
	}		
	
	/**
	 * 最後のジョブを取得.
	 * @return
	 */
	public Job getLastJob() {
		return this.jobList.get(this.jobList.size() - 1);
	}
	
	public Job getCurrentJob() {
		if (this.jobList.size() == 0) {
			// ジョブが１つもない
			// workflowから最初のジョブ(入れ物だけ)を作成する？
			return null;//workFlow.getJobList().get(0);
			
		} else {
			return this.jobList.get(this.jobList.size()-1);
		}
	}
	
	public Job getPreviousJob() {
		if (this.jobList.size() == 0) {
			return null;
		} else {
			return this.jobList.get(this.jobList.size() - 2);
		}
	}
	
	
	
	public String getId() {
		return this.id;
	}
	public void setId(String id) {
		this.id = id;
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
	
	public String getContent() {
		return this.content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public List<Job> getJobList() {
		return this.jobList;
	}
	public void setJobList(List<Job> jobList) {
		this.jobList = jobList;
	}
	
	public String getResult() {
		return this.result;
	}
	
	public void setResult(String result) {
		this.result = result;
	}
}

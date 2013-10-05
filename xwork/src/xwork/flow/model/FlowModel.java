package xwork.flow.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * Flow定義.
 * サブフロー
 * 
 * @author taichi
 */
@XmlAccessorType(XmlAccessType.NONE)
public class FlowModel {

	/**  Flow名 */
	@XmlAttribute(name="name")
	private String name = null;
	/** ジョブリスト */
	@XmlElement(name="job")
	private List<JobModel> jobList = new ArrayList<JobModel>();
	/** 
	 * 遷移先.
	 * フロー完了後の遷移先ジョブ名
	 */
	@XmlAttribute(name="to")
	private String to = null;
	
	/**
	 * Flow名取得.
	 * @return
	 */
	public String getName() {
		return this.name;
	}
	/**
	 * Flow名設定.
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	public String getTo() {
		return this.to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	
	/**
	 * ジョブリスト取得
	 * @return
	 */
	public List<JobModel> getJobList() {
		return this.jobList;
	}
	
	/**
	 * ジョブモデル取得
	 * @param jobName
	 * @return 
	 */
	public JobModel getJobModel(String jobName) {
		for (JobModel job : jobList) {
			if (job.getName().equals(jobName)) {
				return job;
			}
		}
		return null;
	}	
}

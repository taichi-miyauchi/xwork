package xwork.flow.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * WorkFlowModel
 * @author 太一
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name="workflow")
public class WorkFlowModel {

	/** FlowID */
	@XmlAttribute(name="id")
	private String id = null;
	/**  Flow名 */
	@XmlAttribute(name="name")
	private String name = null;
	/** ジョブリスト */
	@XmlElement(name="job")
	private List<JobModel> jobList = new ArrayList<JobModel>();
	/** サブフローリスト */
	@XmlElement(name="flow")
	private List<FlowModel> flowList = new ArrayList<FlowModel>();
	
	/**
	 * WorkFlowID取得.
	 * @return
	 */
	public String getID() {
		return this.id;
	}
	public void setID(String id) {
		this.id = id;
	}
	
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
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
		for (JobModel job : this.jobList) {
			if (job.getName().equals(jobName)) {
				return job;
			}
		}
		return null;
	}
	
	public List<FlowModel> getFlowList() {
		return this.flowList;
	}
	public FlowModel getFlowModel(String flowName) {
		for (FlowModel flow : this.flowList) {
			if (flow.getName().equals(flowName)) {
				return flow;
			}
		}
		return null;
	}
}

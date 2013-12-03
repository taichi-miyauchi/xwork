package xwork.flow;


/**
 * ワークフローイベント.
 * 
 * @author taichi
 */
public class WorkFlowEvent {

	public enum EventID {
		START,
		FINISH,
	}
	
	/** 
	 * イベントID 
	 */
	private EventID eventID = EventID.START;
	
	/** 
	 * イベント対象の作業ID.
	 */
	private String workID = null;
	/**
	 * フロー名.
	 */
	private String flowName = null;
	/**
	 * 親項目ID.
	 * トリマー後の子項目のフロー時に設定することを想定
	 */
	private String parentID = null;
	/** 
	 * イベント対象の項目ID.
	 */
	private String itemID = null;
	/** 
	 * ジョブ名.
	 */
	private String jobName = null;
	/** 
	 * イベント対象のジョブID.
	 */
	private String jobID = null;
	
	/**
	 * コンストラクタ.
	 */
	public WorkFlowEvent() {
	}
	public WorkFlowEvent(EventID eventID, String workID, String itemID, String jobID) {
		this.eventID = eventID;
		this.workID = workID;
		this.itemID = itemID;
		this.jobID = jobID;
	}
	/**
	 * コンストラクタ.
	 * @param eventID イベントID
	 * @param workID WorkID
	 * @param flowName フロー名
	 * @param itemID アイテムID
	 * @param parentID 親アイテムID
	 * @param jobName ジョブ名
	 * @param jobID ジョブID
	 */
	public WorkFlowEvent(EventID eventID, String workID, String flowName, String itemID, String parentID, String jobName, String jobID) {
		this.eventID = eventID;
		this.workID = workID;
		this.flowName = flowName;
		this.parentID = parentID;
		this.itemID = itemID;
		this.jobName = jobName;
		this.jobID = jobID;
	}
	
	public EventID getEventID() {
		return this.eventID;
	}
	
	public String getWorkID() {
		return workID;
	}
	public void setWorkID(String workID) {
		this.workID = workID;
	}
	
	public String getFlowName() {
		return this.flowName;
	}
	public void setFlowName(String flowName) {
		this.flowName = flowName;
	}
	
	public String getParentID() {
		return this.parentID;
	}
	public void setParentID(String parentID) {
		this.parentID = parentID;
	}
	
	public String getItemID() {
		return this.itemID;
	}
	public void setItemID(String itemID) {
		this.itemID = itemID;
	}
	
	public String getJobName() {
		return this.jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	
	public String getJobID() {
		return this.jobID;
	}
	public void setJobID(String jobID) {
		this.jobID = jobID;
	}
}

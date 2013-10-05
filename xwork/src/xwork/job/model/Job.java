package xwork.job.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * ジョブデータ.
 * 
 * <job workID="" jobID="" jobName="" status="">
 *   -- 要求 --
 *   <request>
 *      <item><img>バイナリ</img></item>
 *      <item><text>エントリ1</text></item>
 *      <item><text>エントリ2</text></item>
 *   </requset>
 *   
 *   -- 結果 --
 *   <result>  ※ジョブ毎の結果
 *     <status>Entry</status>
 *     <text>ああああ</text>
 *   </result>
 *   <result>
 *     <status>不明</status>
 *   </result>
 * </job>
 * 
 * @author 太一
 */
public class Job {
	
	/** WorkID. 作業ID */
	private String workID = null;
	/** 
	 * Flow名.
	 * サブフロー時に設定される。
	 */
	private String flowName = null;
	/** 
	 * ItemID.
	 * 項目(Item)毎のフローの場合に使用。
	 */
	private String itemID = null;
	/**
	 *  JobID（ジョブ単位でユニークな値）
	 */
	private String jobID = null;
	// ジョブ名
	private String jobName = null;
	// ジョブステータス（処理待ち・処理中・完了・異常）
	private String status = null;
	
	
	// 要求データ
	private JobRequest request = null;
	
	// 結果ステータス
	private String resultStatus  = null;
	// 結果
	private List<JobResult> resultList = new ArrayList<JobResult>();
	
	/**
	 * コンストラクタ.
	 */
	public Job() {
		// JobIDの発番
		this.jobID = UUID.randomUUID().toString();
	}
	/**
	 * コンストラクタ.
	 * @param name ジョブ名
	 */
	public Job(String name) {
		this();
		this.jobName = name;
	}
	
	// 作業ID
	public String getWorkID() {
		return this.workID;
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
	
	// 項目ID
	public String getItemID() {
		return this.itemID;
	}
	public void setItemID(String itemID) {
		this.itemID = itemID;
	}
	
	// ジョブ名
	public String getJobName() {
		return this.jobName;
	}
	public void setJobName(String name) {
		this.jobName = name;
	}
	
	// ジョブID
	public String getJobID() {
		return this.jobID;
	}
	public void setJobID(String id) {
		this.jobID = id;
	}
	
	// ジョブステータス
	public String getStatus() {
		return this.status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	// リクエスト
	public JobRequest getRequest() {
		return this.request;
	}
	public void setRequest(JobRequest request) {
		this.request = request;
	}
	
	// 結果ステータス
	public String getResultStatus() {
		return this.resultStatus;
	}
	public void setResultStatus(String status) {
		this.resultStatus = status;
	}
	
	// 結果
	public void add(JobResult result) {
		this.resultList.add(result);
	}
	public List<JobResult> getResultList() {
		return this.resultList;
	}
}
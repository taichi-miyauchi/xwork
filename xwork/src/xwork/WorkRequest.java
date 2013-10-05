package xwork;

import java.util.List;

/**
 * 作業要求データ.
 * @author 太一
 */
public class WorkRequest {

	// アクセストークン
	private String aceessToken = null;
	// 仕事ID
	private String workID = null;
	// 仕事種別ID
	private String workTypeID = null;
	// 仕事内容
	private List<WorkItem> workItemList = null;
	private String content = null;
	
	
	public String getAceessToken() {
		return aceessToken;
	}
	public void setAceessToken(String aceessToken) {
		this.aceessToken = aceessToken;
	}
	
	public String getWorkID() {
		return this.workID;
	}
	public void setWorkID(String workID) {
		this.workID = workID;
	}
	public String getWorkTypeID() {
		return this.workTypeID;
	}
	public void setWorkTypeID(String workTypeID) {
		this.workTypeID = workTypeID;
	}
	
	public String getContent() {
		return this.content;
	}
	public void setConetnt(String content) {
		this.content = content;
	}
	
	public List<WorkItem> getWorkItemList() {
		return this.workItemList;
	}
	public void setWorkItemList(List<WorkItem> workItemList) {
		this.workItemList = workItemList;
	}
	

}

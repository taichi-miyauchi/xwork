package xwork;

import java.util.List;

public class WorkEntry {

	// アクセストークン
	private String aceessToken = null;
	// 仕事ID(仕事の種類)
	private String workID = null;
	// 仕事内容
	private List<WorkItem> workItemList = null;
	
	
	
	public String getAceessToken() {
		return aceessToken;
	}
	public void setAceessToken(String aceessToken) {
		this.aceessToken = aceessToken;
	}
	public String getWorkID() {
		return workID;
	}
	public void setWorkID(String workID) {
		this.workID = workID;
	}
	public List<WorkItem> getWorkItemList() {
		return this.workItemList;
	}
	public void setWorkItemList(List<WorkItem> workItemList) {
		this.workItemList = workItemList;
	}
	

}

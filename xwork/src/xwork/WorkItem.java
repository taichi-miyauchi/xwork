package xwork;

import java.util.UUID;

/**
 * 仕事内容
 * @author 太一
 */
public class WorkItem {
	
	private String workID = null;
	private UUID id = null;
	private String name = null;
	private String content = null;

	
	
	public String getWorkID() {
		return workID;
	}
	public void setWorkID(String workID) {
		this.workID = workID;
	}
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getContent() {
		return this.content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}

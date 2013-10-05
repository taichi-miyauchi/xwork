package xwork;

import java.util.ArrayList;
import java.util.List;

import xwork.cmn.model.Item;

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
	// 仕事項目
	private List<Item> items = new ArrayList<Item>();
	private String content = null;
	
	/**
	 * 項目追加.
	 * @param item
	 */
	public void addItem(Item item) {
		this.items.add(item);
	}
	
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
	
	public List<Item> getWorkItemList() {
		return this.items;
	}
	public void setWorkItemList(List<Item> items) {
		this.items = items;
	}
	

}

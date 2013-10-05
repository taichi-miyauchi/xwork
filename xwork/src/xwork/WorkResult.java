package xwork;

import java.util.List;

import xwork.cmn.model.Item;

/**
 * 作業結果.
 * @author taichi
 */
public class WorkResult {
	
	/**
	 * 結果内容
	 */
	private String content = null;
	/**
	 * 結果項目リスト.
	 */
	private List<Item> itemList = null;
	
	public String getContent() {
		return this.content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public List<Item> getItemList() {
		return this.itemList;
	}
	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}
}

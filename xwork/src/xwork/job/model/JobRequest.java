package xwork.job.model;

import java.util.ArrayList;
import java.util.List;

import xwork.cmn.model.Item;

/**
 * ジョブ 要求データ
 * @author taichi
 */
public class JobRequest {

	private String content = null;
	
	// 要求項目
	private List<Item> items = new ArrayList<Item>();
	
	public String getContent() {
		return this.content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	/**
	 * 要求項目追加.
	 * @param item Item
	 */
	public void addItem(Item item) {
		this.items.add(item);
	}
	public void addItem(String content) {
		this.items.add(new Item(content));
	}
	
	/**
	 * 要求項目リスト取得.
	 * @return
	 */
	public List<Item> getItems() {
		return this.items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}
}

package xwork.cmn.model;

import java.util.ArrayList;
import java.util.List;

/**
 * [共通モデル] リクエスト.
 * <request>
 *   <item>
 *      <text>ああああ</text>
 *   </item>
 *   <item>
 *      <text>いいい</text>
 *   </item>
 * </request>
 * @author taichi
 */
public class Request {

	// 要求項目リスト
	private List<Item> items = new ArrayList<Item>();
	
	/**
	 * 要求項目リスト取得
	 * @return List<Item>
	 */
	public List<Item> getItems() {
		return this.items;
	}
	/**
	 * 要求項目リスト設定
	 * @param items List<Item>
	 */
	public void setItems(List<Item> items) {
		this.items  = items;
	}
}

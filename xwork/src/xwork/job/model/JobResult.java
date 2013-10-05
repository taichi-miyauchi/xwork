package xwork.job.model;

import java.util.ArrayList;
import java.util.List;

import xwork.cmn.model.Item;

/**
 * ジョブ 結果データ.
 * 
 * <result>
 *   <status>Finish/UnMatch/Matchなど</status>
 *   <content>結果</content>
 *   <item>
 *     <text>あああ</text>
 *   </item>
 * </resutl>
 * 
 * @author 太一
 */
public class JobResult {

	// 結果ステータス
	private String status = null;
	// 結果内容
	private String content = null;
	// 結果項目(子要素)
	private List<Item> items = new ArrayList<Item>();
	
	
	/**
	 * 結果ステータス取得.
	 * @return
	 */
	public String getStatus() {
		return this.status;
	}
	/**
	 * 結果ステータス設定.
	 * @param status 結果ステータス
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	/**
	 * 結果項目リスト取得
	 * @return
	 */
	public List<Item> getItems() {
		return this.items;
	}
	/**
	 * 結果項目リスト設定
	 * @param items
	 */
	public void setItems(List<Item> items) {
		this.items = items;
	}
	
	public void addItem(String name, String text) {
		this.items.add(new Item(name,text));
	}
	
	
	public String getContent() {
		return this.content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}

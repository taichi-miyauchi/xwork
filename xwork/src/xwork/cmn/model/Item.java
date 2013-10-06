package xwork.cmn.model;

import java.util.ArrayList;
import java.util.List;

/**
 * [共通モデル] Item.
 * <item id="ＩＤ" name="項目名" type="text,image">
 *   <value>String/<rect x="1" y="1" w="10" h="10"/>/byte[]</value>
 *   <property name="" value=""/>
 *   <item></item>
 *   <item></item>
 * </item>
 * @author taichi
 */
public class Item {

	/** ID */
	private String id = null;
	/** 項目名 */
	private String name = null;
	/** 項目種別 */
	private String type= null;
	/** 値 */
	private String value = null;
	/** 矩形 */
	private Rect rect = null;
	/** 子要素 */
	private List<Item> items = new ArrayList<Item>();
	
	
	/**
	 * コンストラクタ.
	 */
	public Item() {
	}
	/**
	 * コンストラクタ.
	 * @param id
	 * @param name
	 * @param value
	 */
	public Item(String id, String name, String value) {
		this.id = id;
		this.name = name;
		this.value = value;
	}
	/**
	 * コンストラクタ.
	 * @param value 値
	 */
	public Item(String value) {
		this.value = value;
	}
	/**
	 * コンストラクタ.
	 * @param name 項目名
	 * @param value 値
	 */
	public Item(String name, String value) {
		this.name = name;
		this.value = value;
	}
	
	/**
	 * 文字列化
	 */
	@Override
	public String toString() {
		String str = "";
		str = "name:" + name + ",value:" + value;
		return str;
	}
	
	public String getId() {
		return this.id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getType() {
		return this.type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public String getValue() {
		return this.value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	public Rect getRect() {
		return this.rect;
	}
	public void setRect(Rect rect) {
		this.rect = rect;
	}
	
	public void addItem(Item item) {
		this.items.add(item);
	}
	public List<Item> getItems() {
		return this.items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}	
}

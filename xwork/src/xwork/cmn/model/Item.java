package xwork.cmn.model;

/**
 * [共通モデル] Item.
 * <item name="項目名">
 *   <text>String</text>
 *   <img>byte[]</img>
 *   <rect x="1" y="1" w="10" h="10"/>
 * </item>
 * @author taichi
 */
public class Item {

	/** 項目名 */
	private String name = null;
	/** テキスト */
	private String text = null;
	/** 矩形 */
	private Rect rect = null;
	
	/**
	 * コンストラクタ.
	 */
	public Item() {
	}
	/**
	 * コンストラクタ.
	 * @param text テキスト
	 */
	public Item(String text) {
		this.text = text;
	}
	public Item(String name, String text) {
		this.name = name;
		this.text = text;
	}
	
	/**
	 * 文字列化
	 */
	@Override
	public String toString() {
		String str = "";
		str = "name:" + name + ",text:" + text;
		return str;
	}
	
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getText() {
		return this.text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	public Rect getRect() {
		return this.rect;
	}
	public void setRect(Rect rect) {
		this.rect = rect;
	}
}

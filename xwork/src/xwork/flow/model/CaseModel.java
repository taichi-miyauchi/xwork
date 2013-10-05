package xwork.flow.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * [WorkFlow] Case定義.
 * 
 * @author taichi
 */
@XmlAccessorType(XmlAccessType.NONE)
public class CaseModel {
	
	/** ケース名 */
	@XmlAttribute(name="name")
	private String name = null;
	/** 次ジョブ名 */
	@XmlAttribute(name="to")
	private String to = null;
	/** 項目遷移リスト */
	@XmlElement(name="item")
	private List<ItemModel> itemList = new ArrayList<ItemModel>();
	
	
	/**
	 * コンストラクタ
	 */
	public CaseModel() {
	}
	public CaseModel(String name, String to) {
		this.name = name;
		this.to= to;
	}
	
	/**
	 * ケース名取得
	 * @return ケース名
	 */
	public String getName() {
		return this.name;
	}
	/**
	 * ケース名設定
	 * @param name ケース名
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 次ジョブ名取得
	 * @return 次ジョブ名
	 */
	public String getTo() {
		return this.to;
	}
	/**
	 * 次ジョブ名設定
	 * @param to 次ジョブ名
	 */
	public void setTo(String to) {
		this.to = to;
	}
	
	public List<ItemModel> getItemList() {
		return this.itemList;
	}
	public ItemModel getItemModel(String itemName) {
		for(ItemModel item : this.itemList) {
			if (item.getEqual() == null) {
				return item;
			}
			if (item.getEqual().indexOf(itemName) != -1) {
				return item;
			}
		}
		// 該当項目が無い場合は、最後に定義しているデータを
		return this.itemList.get(this.itemList.size() - 1);
	}
}

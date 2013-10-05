package xwork.flow.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

/**
 * パラメータ定義.
 * 
 * @author taichi
 */
@XmlAccessorType(XmlAccessType.NONE)
public class ParamModel {
	
	/** パラメータ名 */
	@XmlAttribute(name="name")
	public String name = null;
	
	/** パラメータ値 */
	@XmlAttribute(name="value")
	public String value = null;
	
	/**
	 * コンストラクタ
	 */
	public ParamModel() {
	}
	public ParamModel(String name, String value) {
		this.name = name;
		this.value = value;
	}
	
}

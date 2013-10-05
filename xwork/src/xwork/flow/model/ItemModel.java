package xwork.flow.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

/**
 * ItemModel.
 * item毎の条件分岐を定義する。
 * <item target="xxx" 
 *            equals="xxx,xxx,xxx"
 *            to="次ジョブ名"
 * 
 * @author taichi
 */
@XmlAccessorType(XmlAccessType.NONE)
public class ItemModel {

	
	@XmlAttribute(name="target")
	private String target = null;
	@XmlAttribute(name="equal")
	private String equal = null;
	@XmlAttribute(name="to")
	private String to = null;
	
	
	public String getTarget() {
		return this.target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	
	public String getEqual() {
		return this.equal;
	}
	public void setEqual(String equal) {
		this.equal = equal;
	}
	
	public String getTo() {
		return this.to;
	}
	public void setTo(String to) {
		this.to = to;
	}
}

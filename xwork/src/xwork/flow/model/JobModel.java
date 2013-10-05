package xwork.flow.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * [WorkFlow]  ジョブ定義.
 * 
 * @author taichi
 */
@XmlAccessorType(XmlAccessType.NONE)
public class JobModel {
	
	/** ジョブ名 */
	@XmlAttribute(name="name")
	private String name = null;
	/** コマンド */
	@XmlAttribute(name="cmd")
	private String cmd = null;
	
	/** パラメータ */
	@XmlElement(name="param", required=false)
	private List<ParamModel> params = new ArrayList<ParamModel>();
	
	/** 分岐 */
	@XmlElement(name="switch", required=false)
	public SwitchModel switchModel = new SwitchModel();
	
	/** 親要素 */
	private FlowModel parent = null;
	
	
	/**
	 * コンストラクタ.
	 */
	public JobModel() {
	}
	/**
	 * コンストラクタ.
	 * @param name 
	 */
	public JobModel(String name) {
		this.name = name;
	}
	
	public FlowModel getParent() {
		return this.parent;
	}
	public void setParent(FlowModel parent) {
		this.parent = parent;
	}
	
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getCmd() {
		return this.cmd;
	}
	public void setCmd(String cmd) {
		this.cmd = cmd;
	}
	
	public void addParam(ParamModel param) {
		this.params.add(param);
	}
	public List<ParamModel> getParams() {
		return this.params;
	}
	
	
}

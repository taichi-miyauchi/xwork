package xwork.flow.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * [WorkFlow] Switch定義.
 * 
 * @author taichi
 */
@XmlAccessorType(XmlAccessType.NONE)
public class SwitchModel {
	
	@XmlElement(name="case")
	private List<CaseModel> cases = new ArrayList<CaseModel>();
	
	/**
	 * ケース取得
	 * @param name ケース名
	 * @return CaseModel
	 */
	public CaseModel getCase(String name) {
		for(CaseModel c : cases) {
			// 名称未設定はデフォルト扱い
			if (c.getName() == null) {
				return c;
			}
			if (c.getName().equalsIgnoreCase(name)) {
				return c;
			}
		}
		return null;
	}
	
	public List<CaseModel> getCases() {
		return this.cases;
	}
	public void setCases(List<CaseModel> cases) {
		this.cases = cases;
	}
}

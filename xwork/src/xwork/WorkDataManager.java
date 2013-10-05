package xwork;

import java.util.HashMap;
import java.util.Map;

import xwork.flow.WorkFlowModelManager;

/**
 * 作業データ管理者.
 * 
 * @author taichi
 */
public class WorkDataManager {

	/**
	 * 作業データのストレージ
	 * ※本来は永続化対象となり、永続化のマネージャ経由となる
	 */
	private static Map<String, WorkData> works = new HashMap<String, WorkData>();
	
	/**
	 * WorkProcess作成
	 * @param entry
	 * @return
	 */
	public static WorkData create(WorkRequest entry) {
		
		// 作業データの作成
		WorkData data = new WorkData(entry);

		// 処理IDの発行
		data.setWorkID("1");
		
		return data;
	}
	
	/**
	 * 登録
	 * @param process
	 */
	public static void regist(WorkData data) {
		works.put(data.getWorkID(), data);
	}
	
	/**
	 * 返す
	 * @return
	 */
	public static WorkData get(String dataID) {
		WorkData data = works.get(dataID);
		data.setWorkFlow(WorkFlowModelManager.table.get("digitize"));
		return data;
	}
}

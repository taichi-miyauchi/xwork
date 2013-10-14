package xwork;

import xwork.core.model.Item;
import xwork.flow.WorkFlowEvent;
import xwork.flow.WorkFlowEventQueue;

/**
 * 仕事申し込みサービス
 * @author 太一
 */
public class WorkEntryService {

		/**
		 * 仕事の申し込み
		 * @param req 作業申込み
		 * @param res 申込み結果
		 */
		public void entry(WorkRequest req, WorkEntryResult res) {
			/*
			// 認可 
			// アクセストークンが有効かチェックする
			try {
				AccessTokenManager amng = new AccessTokenManager();
				amng.auth(req.getAceessToken());	// checkPermission
			} catch (AccessTokenException ex) {
				//
			}
			*/
			
			// 基本的な内容チェック
			//req.check();
			
			// 作業データの作成
			WorkData data = WorkDataManager.create(req);
			
			// 作業データを登録（処理開始）
			WorkDataManager.regist(data);

			// 作業プロセスキューに登録（処理開始）⇒ WorkFlowServiceが処理する
			//WorkFlowEventQueue.put(new WorkFlowEvent(WorkFlowEvent.EventID.START, data.getWorkID(), null, null));
			// 子項目(item)単位のイベントキューを作成する
			for (Item item : data.getWorkRequest().getItems()) {
				WorkFlowEventQueue.put(new WorkFlowEvent(WorkFlowEvent.EventID.START, data.getWorkID(), item.getId(), null));
			}
						
			// 受付結果を設定
			res.setStatus("OK");			
		}
		
		public WorkResult receipt(String workID) {
			WorkData data = WorkDataManager.get(workID);
			return data.getWorkResult();
		}
}

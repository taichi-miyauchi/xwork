package xwork.job.process;

import xwork.WorkData;
import xwork.flow.WorkFlowEvent;
import xwork.job.IJobProcess;

/**
 * エントリ完了処理.
 * 
 * @author taichi
 */
public class EntryFinishJobProcess implements IJobProcess {
	
	/**
	 * ジョブ開始処理.
	 * WorkDataを元にWorkFlowに従ってエントリジョブを作成する。
	 */
	public void start(WorkData workData, WorkFlowEvent event) {
		System.out.println("- Job#Start EntryFinish");

		// ジョブの取得
		//Job job = workData.getFlow(event.getItemID()).getCurrentJob();

		System.out.println("********************************");
		System.out.println("  フロー完了");
		System.out.println("********************************");
		// WorkData完了
		// WorkDataの結果を設定し完了
		
	}
	
	/**
	 * ジョブ完了通知.
	 * @param data
	 */
	public void finish(WorkData data, WorkFlowEvent event) {
		System.out.println("- Job#Finish EntryFinish");
		// 処理なし
	}
}

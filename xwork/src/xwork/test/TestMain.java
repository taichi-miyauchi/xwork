package xwork.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.xml.bind.JAXB;

import xwork.WorkEntryResult;
import xwork.WorkEntryService;
import xwork.WorkRequest;
import xwork.WorkResult;
import xwork.cmn.model.Item;
import xwork.flow.WorkFlowModelManager;
import xwork.flow.WorkFlowService;
import xwork.flow.model.WorkFlowModel;
import xwork.job.JobService;
import xwork.job.model.Job;
import xwork.job.model.JobResult;

/**
 * テストクラス.
 * 
 * @author taichi
 */
public class TestMain {

	// メイン関数
	public static void main(String[] args) {
		
		// テストクラスの起動
		TestMain test = new TestMain();
		
		// Work依頼
		test.addWork();
		
		try {
			BufferedReader console =
		            new BufferedReader(new InputStreamReader(System.in), 1);
	
			while (true) {
				String cmd = console.readLine();
				switch (cmd) {
				case "GET" :
						// JOB取得
						test.getJob(console);
						break;
				case "result" :
						// 結果問い合わせ
						test.getResult(console);
						break;
				case "END" : 
						System.exit(0);
						break;
				default :
						break;
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * コンストラクタ
	 */
	private TestMain() {

		// WorkFlow定義のロード
		WorkFlowModel workFlow = JAXB.unmarshal(new java.io.File("src\\flow2.xml"), WorkFlowModel.class);
		
		// WorkFlow定義管理に登録
		WorkFlowModelManager.table.put("digitize",workFlow);
		
		// WorkFlow処理の開始
		new Thread(new WorkFlowService()).start();
		
	}
	
	/**
	 * 仕事の追加
	 */
	private void addWork() {
		System.out.println("WORK依頼");
		// デジタル化処理依頼
		WorkEntryService entryService = new WorkEntryService();
		
		WorkRequest request = new WorkRequest();
		request.setWorkTypeID("digitize-trim");
		request.setConetnt("あいうえお");
		
		WorkEntryResult result = new WorkEntryResult();
		
		// 処理依頼
		entryService.entry(request, result);
		
		System.out.println("[結果]" + result.getStatus());		
	}
	
	/**
	 * ジョブ取得
	 */
	private void getJob(BufferedReader console) throws IOException {
		
		JobService service = new JobService();
		Job job = service.get();
		if (job == null) {
			System.out.println(" X ジョブなし");
		} else {
			JobResult ret = new JobResult();

			System.out.println(" ----------------------------------------------------");
			System.out.println(" - JobName:" + job.getJobName());
			System.out.println(" - Content:" + job.getRequest().getContent());
			System.out.println(" ----------------------------------------------------");
			if ("Trim".equals(job.getJobName())) {
				// トリム結果の設定
				ret.addItem("Name", "宮内太一");
				ret.addItem("TEL", "090-1970-0895");
				//rect.setX(Long.parseLong(console.readLine()));

			} else {
				// エントリ結果の設定
				String entry = console.readLine();
				ret.setContent(entry);
			}
			job.add(ret);
			service.submit(job);			
		}
	}
	
	private void getResult(BufferedReader console) throws IOException {
		
		// デジタル化処理依頼
		WorkEntryService entryService = new WorkEntryService();
		
		WorkResult result = entryService.receipt("1");

		System.out.println("============================");
		System.out.println(result.getContent());
		for (Item item : result.getItemList()) {
			System.out.println("item " + item.toString());
		}
		System.out.println("============================");
	}
}

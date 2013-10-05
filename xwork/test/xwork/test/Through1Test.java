package xwork.test;

import static org.junit.Assert.*;

import javax.xml.bind.JAXB;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

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
 * JUnitテストクラス.
 * 
 * @author taichi
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)		// メソッド名順に実行
public class Through1Test {

	/** エントリサービス */
	WorkEntryService entryService = new WorkEntryService();
	
	/** ジョブサービス */
	private JobService service = new JobService();
	
	/**
	 * 初期化
	 */
	@BeforeClass
	public static void setup() {
		
		// WorkFlow定義のロード
		WorkFlowModel workFlow = JAXB.unmarshal(new java.io.File("src\\flow2.xml"), WorkFlowModel.class);
		
		// WorkFlow定義管理に登録
		WorkFlowModelManager.table.put("digitize",workFlow);
		
		// WorkFlow処理の開始
		new Thread(new WorkFlowService()).start();		
	}
	
	@Before
	public void befor() {
		try {
			Thread.sleep(100);			
		} catch (Exception ex) {}		
	}
	
	/**
	 * 作業依頼
	 */
	@Test
	public void step1_WorkRequest() {
		
		// デジタル化処理依頼
		WorkRequest request = new WorkRequest();
		request.setWorkTypeID("digitize-trim");
		request.setConetnt("あいうえお");

		// 項目1(画像1)
		request.addItem(new Item("item001", null, "画像①"));
		request.addItem(new Item("item002", null, "画像②"));
		
		// 処理依頼
		WorkEntryResult result = new WorkEntryResult();
		entryService.entry(request, result);
		
		assertEquals("OK", result.getStatus());
	}
	
	/**
	 * トリム処理
	 */
	@Test
	public void step2_trim() {
		
		Job job = this.service.get();
		this.trace(job);

		// トリムジョブであること
		assertEquals("Trim", job.getJobName());
		
		// ジョブ結果設定
		JobResult ret = new JobResult();
		ret.setContent("とりむ～～");
		ret.addItem("Name", "宮内太一");
		ret.addItem("TEL", "090-1970-0895");		
		job.add(ret);
		
		service.submit(job);			
	}

	/**
	 * エントリ名前-1
	 */
	@Test
	public void step3_entry1() {
		Job job = service.get();
		this.trace(job);

		// エントリジョブであること
		assertEquals("Entry", job.getJobName());
		
		// ジョブ結果設定
		JobResult ret = new JobResult();
		ret.setContent("宮内太一");
		job.add(ret);
		
		service.submit(job);			
	}
	
	/**
	 * エントリ名前-2
	 */
	@Test
	public void step3_entry2() {
		Job job = service.get();
		this.trace(job);

		// エントリジョブであること
		assertEquals("Entry", job.getJobName());
		
		// ジョブ結果設定
		JobResult ret = new JobResult();
		ret.setContent("宮内太一");
		job.add(ret);
		
		service.submit(job);			
	}

	/**
	 * エントリ電話-1
	 */
	@Test
	public void step4_entry1() {
		Job job = service.get();
		this.trace(job);

		// エントリジョブであること
		assertEquals("Entry", job.getJobName());
		
		// ジョブ結果設定
		JobResult ret = new JobResult();
		ret.setContent("090-1970-0895");
		job.add(ret);
		
		service.submit(job);			
	}

	/**
	 * エントリ電話-2
	 */
	@Test
	public void step5_entry2() {
		Job job = service.get();
		this.trace(job);

		// エントリジョブであること
		assertEquals("Entry", job.getJobName());
		
		// ジョブ結果設定
		JobResult ret = new JobResult();
		ret.setContent("090-1970-0895");
		job.add(ret);
		
		service.submit(job);			
	}

	@Test
	public void step999() {
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
	
	
	private void trace(Job job) {
		assertNotNull("Jobなし", job);
		System.out.println(" ----------------------------------------------------");
		System.out.println(" - JobName:" + job.getJobName());
		System.out.println(" - Content:" + job.getRequest().getContent());
		System.out.println(" ----------------------------------------------------");		
	}
	
}

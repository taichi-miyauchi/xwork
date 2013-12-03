package xwork.test;

import static org.hamcrest.CoreMatchers.*;
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
import xwork.core.model.Item;
import xwork.flow.WorkFlowModelManager;
import xwork.flow.WorkFlowService;
import xwork.flow.model.WorkFlowModel;
import xwork.job.JobService;
import xwork.job.model.Job;
import xwork.job.model.JobResult;

/**
 * JUnitテストクラス.
 *・等価であるか：is()
 *・等価でないか：not()
 *・nullであるか：nullValue()
 *・nullでないか：notNullValue()
 *・指定したインスタンスか：instanceOf()
 *・同じインスタンスか：sameInstance()
 *・全ての条件を満たすか：allOf()
 *・いずれかの条件を満たすか：anyOf()
 *・値は何でもよい：anything()
 *・トレースに表示する文字列を変更：describedAs() * 
 * 
 * @author taichi
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)		// メソッド名順に実行
public class Through1Test {

	/** エントリサービス */
	private WorkEntryService entryService = new WorkEntryService();
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
		//request.setConetnt("あいうえお");

		// 項目1(画像1)
		request.addItem(new Item("item001", null, "画像①"));
		//request.addItem(new Item("item002", null, "画像②"));
		
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
		assertThat(job.getJobName(), is("Trim"));
		assertThat(job.getRequest().getContent(), is("画像①"));
		
		// ジョブ結果設定
		JobResult ret = new JobResult();
		ret.addItem("sub1", "Name", "宮内太一");
		ret.addItem("sub2", "TEL", "090-1970-0895");		
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
		try {
			Thread.sleep(1000L);
		} catch (Exception ex) {}		

		// 結果取得
		while (entryService.receipt("1") == null) {
			try { Thread.sleep(1000L);} catch (Exception ex) {} 
		}
		WorkResult result = entryService.receipt("1");

		System.out.println("============================");
		System.out.println(result.getContent());
		for (Item item : result.getItemList()) {
			System.out.println("item " + item.toString());
		}
		System.out.println("============================");			
	}
	
	
	private void trace(Job job) {
		assertThat(job, notNullValue());
		System.out.println(" ----------------------------------------------------");
		System.out.println(" - JobName:" + job.getJobName());
		System.out.println(" - Content:" + job.getRequest().getContent());
		System.out.println(" ----------------------------------------------------");		
	}
	
}

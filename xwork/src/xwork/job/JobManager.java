package xwork.job;

import java.util.HashMap;
import java.util.Map;

import xwork.job.model.Job;

public class JobManager {

	private static Map<String, Job> jobList = new HashMap<String, Job>();
	
	/**
	 * 登録
	 * @param job
	 */
	public static void regist(Job job) {
		jobList.put(job.getJobID(), job);
	}

	/**
	 * 取得
	 * @param jobID
	 * @return
	 */
	public static Job get(String jobID) {
		if (jobList.containsKey(jobID)) {
			return jobList.get(jobID);
		} else {
			return null;
		}
	}
	
	/**
	 * 更新.
	 * @param job
	 */
	public static void update(Job job) {
		//Job original = jobList.get(job.getJobID());
		//original = job;
	}
}

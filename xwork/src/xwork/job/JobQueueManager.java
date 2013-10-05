package xwork.job;

import java.util.ArrayList;
import java.util.List;

import xwork.job.model.Job;

public class JobQueueManager {

	private static List<Job> jobList = new ArrayList<Job>();
	
	public static void put(Job job) {
		jobList.add(job);
	}
	
	public static Job get() {
		if (jobList.size() > 0) {
			return jobList.remove(0);
		} else {
			return null;
		}
	}

}

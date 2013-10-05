package xwork;

import java.util.HashMap;
import java.util.Map;

public class WorkRequestManager {

	private static Map<String, WorkRequest> table = new HashMap<String, WorkRequest>();
	
	
	public static void add(WorkRequest req) {
		table.put(req.getWorkID(), req);
	}
	
	public static WorkRequest get() {
		return table.values().iterator().next();
	}
}

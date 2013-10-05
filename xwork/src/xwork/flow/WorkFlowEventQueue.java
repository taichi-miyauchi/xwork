package xwork.flow;

import java.util.*;

public class WorkFlowEventQueue {
	
	private static List<WorkFlowEvent> queue = new ArrayList<WorkFlowEvent>();
	
	public static void put(WorkFlowEvent event) {
		queue.add(event);
	}
	
	public static WorkFlowEvent get() {
		if (queue.size() > 0) {
			return queue.remove(0);
		} else {
			return null;
		}
	}
}

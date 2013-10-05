package xwork.job;

import xwork.WorkData;
import xwork.flow.WorkFlowEvent;

public interface IJobProcess {

	void start(WorkData data, WorkFlowEvent event);
	
	void finish(WorkData data, WorkFlowEvent event);
}

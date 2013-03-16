package ubadb.external.bufferManagement.traceGenerators;

import java.util.List;

import ubadb.core.common.PageId;
import ubadb.core.common.TransactionId;
import ubadb.external.bufferManagement.etc.PageReferenceTrace;

public class MultipleAccessNoFileScanTraceGenerator extends PageReferenceTraceGenerator{

	public PageReferenceTrace generateTrace(int firstBatch, String firstTable, int secondBatch, String secondTable){
		PageReferenceTrace ret = new PageReferenceTrace();
			
		ret.concatenate(buildRequestAndRelease(new TransactionId(0), generateSequentialPages(firstTable, 0, firstBatch)));
		ret.concatenate(buildRequestAndRelease(new TransactionId(0), generateSequentialPages(secondTable, 0, secondBatch)));
		ret.concatenate(buildRequestAndRelease(new TransactionId(0), generateSequentialPages(firstTable, 0, firstBatch)));
		ret.concatenate(buildRequestAndRelease(new TransactionId(0), generateSequentialPages(secondTable, secondBatch, secondBatch*2)));
		ret.concatenate(buildRequestAndRelease(new TransactionId(0), generateSequentialPages(firstTable, 0, firstBatch)));
		ret.concatenate(buildRequestAndRelease(new TransactionId(0), generateSequentialPages(secondTable, secondBatch*2, secondBatch*3)));
		ret.concatenate(buildRequestAndRelease(new TransactionId(0), generateSequentialPages(firstTable, 0, firstBatch)));

		return ret;
	}
}

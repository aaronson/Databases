package ubadb.external.bufferManagement;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ubadb.core.common.TransactionId;
import ubadb.external.bufferManagement.etc.PageReferenceTrace;
import ubadb.external.bufferManagement.etc.PageReferenceTraceSerializer;
import ubadb.external.bufferManagement.traceGenerators.AnotherTraceGenerator;
import ubadb.external.bufferManagement.traceGenerators.BNLJTraceGenerator;
import ubadb.external.bufferManagement.traceGenerators.FileScanTraceGenerator;
import ubadb.external.bufferManagement.traceGenerators.IndexScanTraceGenerator;
import ubadb.external.bufferManagement.traceGenerators.MixedTraceGenerator;
//import ubadb.external.bufferManagement.traceGenerators.MultipleAccessNoFileScanTraceGenerator;
import ubadb.external.bufferManagement.traceGenerators.MultipleAccessTraceGenerator;


public class MainTraceGenerator
{
	public static void main(String[] args) throws Exception
	{
		basicDataSet();
		//complexDataSet();
	}
	
	private static void basicDataSet() throws Exception
	{
		PageReferenceTraceSerializer serializer = new PageReferenceTraceSerializer();
		
		//File Scan
		String fileNameA1 = "generated/fileScan-Company.trace";
		PageReferenceTrace traceA1 = new FileScanTraceGenerator().generateFileScan(1, "Company", 10);
		serialize(fileNameA1, traceA1, serializer);
		
		String fileNameA2 = "generated/fileScan-Product.trace";
		PageReferenceTrace traceA2 = new FileScanTraceGenerator().generateFileScan(1, "Product", 100);
		serialize(fileNameA2, traceA2, serializer);
		
		String fileNameA3 = "generated/fileScan-Sale.trace";
		PageReferenceTrace traceA3 = new FileScanTraceGenerator().generateFileScan(1,"Sale", 1000);
		serialize(fileNameA3, traceA3, serializer);
		
		//Index Scan Clustered
		String fileNameB1 = "generated/indexScanClustered-Product.trace";
		PageReferenceTrace traceB1 = new IndexScanTraceGenerator().generateIndexScanClustered(1,"Product", 3, 10, 50);
		serialize(fileNameB1, traceB1, serializer);
		
		String fileNameB2 = "generated/indexScanClustered-Sale.trace";
		PageReferenceTrace traceB2 = new IndexScanTraceGenerator().generateIndexScanClustered(1,"Sale", 4, 200, 100);
		serialize(fileNameB2, traceB2, serializer);
		
		//Index Scan Unclustered
		String fileNameC1 = "generated/indexScanUnclustered-Product.trace";
		PageReferenceTrace traceC1 = new IndexScanTraceGenerator().generateIndexScanUnclusteredForASingleLeaf(1,"Product", 3, 40, 100);
		serialize(fileNameC1, traceC1, serializer);
		
		String fileNameC2 = "generated/indexScanUnclustered-Sale.trace";
		PageReferenceTrace traceC2 = new IndexScanTraceGenerator().generateIndexScanUnclusteredForASingleLeaf(1,"Sale", 4, 250, 1000);
		serialize(fileNameC2, traceC2, serializer);
		
		//BNLJ
		String fileNameD1 = "generated/BNLJ-ProductXSale-group_50.trace";
		PageReferenceTrace traceD1 = new BNLJTraceGenerator().generateBNLJ(1,"Product", 100, "Sale", 1000, 50);
		serialize(fileNameD1, traceD1, serializer);
		
		String fileNameD2 = "generated/BNLJ-ProductXSale-group_75.trace";
		PageReferenceTrace traceD2 = new BNLJTraceGenerator().generateBNLJ(1,"Product", 100, "Sale", 1000, 75);
		serialize(fileNameD2, traceD2, serializer);
		
		String fileNameD3 = "generated/BNLJ-ProductXSale-group_100.trace";
		PageReferenceTrace traceD3 = new BNLJTraceGenerator().generateBNLJ(1,"Product", 100, "Sale", 1000, 100);
		serialize(fileNameD3, traceD3, serializer);
		
		String fileNameD4 = "generated/BNLJ-SaleXProduct-group_100.trace";
		PageReferenceTrace traceD4 = new BNLJTraceGenerator().generateBNLJ(1,"Sale", 1000, "Product", 100, 100);
		serialize(fileNameD4, traceD4, serializer);

		String fileNameD5 = "generated/BNLJ-SaleXProduct-group_250.trace";
		PageReferenceTrace traceD5 = new BNLJTraceGenerator().generateBNLJ(1,"Sale", 1000, "Product", 100, 250);
		serialize(fileNameD5, traceD5, serializer);
		
		String fileNameMu1 = "generated/MultiAccess-50.trace";
		PageReferenceTrace traceMu1 = new MultipleAccessTraceGenerator().generateTrace(50, "Sale", 50, "Company", 1000, "Product");
		serialize(fileNameMu1,traceMu1, serializer);
		
		String fileNameMu2 = "generated/MultiAccess-100.trace";
		PageReferenceTrace traceMu2 = new MultipleAccessTraceGenerator().generateTrace(100, "Sale", 100, "Company", 1000, "Product");
		serialize(fileNameMu2,traceMu2, serializer);
		
		String fileNameMu3 = "generated/MultiAccess-200.trace";
		PageReferenceTrace traceMu3 = new MultipleAccessTraceGenerator().generateTrace(200, "Sale", 200, "Company", 1000, "Product");
		serialize(fileNameMu3,traceMu3, serializer);
		
		String fileNameAT1 = "generated/Trace-50.trace";
		PageReferenceTrace traceAT1 = new AnotherTraceGenerator().generateTrace(50, "Sale", 50, "Company", 1000, "Product");
		serialize(fileNameAT1,traceAT1, serializer);
		
		String fileNameAT2 = "generated/Trace-100.trace";
		PageReferenceTrace traceAT2 = new AnotherTraceGenerator().generateTrace(100, "Sale", 100, "Company", 1000, "Product");
		serialize(fileNameAT2,traceAT2, serializer);
		
		String fileNameAT3 = "generated/Trace-200.trace";
		PageReferenceTrace traceAT3 = new AnotherTraceGenerator().generateTrace(200, "Sale", 200, "Company", 1000, "Product");
		serialize(fileNameAT3,traceAT3, serializer);
		
//		String fileNameMNFS1 = "generated/MultiAccessNoFS-50.trace";
//		PageReferenceTrace traceMNFS1 = new MultipleAccessNoFileScanTraceGenerator().generateTrace(50, "Sale", 100, "Company");
//		serialize(fileNameMNFS1,traceMNFS1, serializer);
//		
//		String fileNameMNFS2 = "generated/MultiAccessNoFS-100.trace";
//		PageReferenceTrace traceMNFS2 = new MultipleAccessNoFileScanTraceGenerator().generateTrace(100, "Sale", 100, "Company");
//		serialize(fileNameMNFS2,traceMNFS2, serializer);
//		
//		String fileNameMNFS3 = "generated/MultiAccessNoFS-200.trace";
//		PageReferenceTrace traceMNFS3 = new MultipleAccessNoFileScanTraceGenerator().generateTrace(200, "Sale", 100, "Company");
//		serialize(fileNameMNFS3,traceMNFS3, serializer);
	}
	
	private static void serialize(String fileName, PageReferenceTrace trace, PageReferenceTraceSerializer serializer) throws Exception
	{
		serializer.write(trace, fileName);
		System.out.println("File '" + fileName + "' generated!!");
	}
	
	private static void complexDataSet() throws Exception
	{
		PageReferenceTraceSerializer serializer = new PageReferenceTraceSerializer();
		String folderA = "generated/escenario3/originalesA";
		String fileA = "generated/mixedA_tot4_conc2.trace";
		mixTraces(fileA,folderA,4,2,serializer);

		String folderB = "generated/escenario3/originalesB";
		String fileB = "generated/mixedB_tot10_conc2.trace";
		mixTraces(fileB,folderB,10,2,serializer);

		String folderC = "generated/escenario3/originalesC";
		String fileC = "generated/mixedC_tot50_conc2.trace";
		mixTraces(fileC,folderC,50,2,serializer);

		String folderD = "generated/escenario3/originalesD";
		String fileD = "generated/mixedD_tot50_conc5.trace";
		mixTraces(fileD,folderD,50,5,serializer);

		String folderE = "generated/escenario3/originalesE";
		String fileE = "generated/mixedE_tot100_conc5.trace";
		mixTraces(fileE,folderE,100,5,serializer);
	}

	private static void mixTraces(String fileNameForNewTrace, String folderName, int totalTracesCount, int maxConcurrentTracesCount, PageReferenceTraceSerializer serializer) throws Exception
	{
		List<PageReferenceTrace> tracesToMix = buildTracesToMix(folderName,totalTracesCount,serializer);
		
		PageReferenceTrace mixedTrace = new MixedTraceGenerator().generateMixedTrace(tracesToMix, totalTracesCount, maxConcurrentTracesCount);
		
		serializer.write(mixedTrace, fileNameForNewTrace);
		System.out.println("File " + fileNameForNewTrace + " generated");
	}

	private static List<PageReferenceTrace> buildTracesToMix(String folderName, int totalTracesCount, PageReferenceTraceSerializer serializer) throws Exception
	{
		List<PageReferenceTrace> tracesToMix = new ArrayList<>();
		Random random = new Random(System.currentTimeMillis());
		
		String[] traceFiles = Paths.get(folderName).toFile().list();
		
		for(int i=0; i < totalTracesCount; i++)
		{
			int anyTraceIndex = random.nextInt(traceFiles.length); 
			PageReferenceTrace anyTrace = serializer.read(folderName + "/" + traceFiles[anyTraceIndex]);
			anyTrace.changeTransactionId(new TransactionId(i));
			
			tracesToMix.add(anyTrace);
		}
		
		return tracesToMix;
	}
}

package com.data.preprocessor;

import java.text.DecimalFormat;
import java.util.Arrays;

public class Record {

	private int queryId;
	private int relevance;
	private  double[] features;
	private String currentRecord;
	
	DecimalFormat numberFormat=new DecimalFormat("0.00000"); 
	
	Record(String currentLine)
	{
		String[] recordArray=currentLine.split(",");
		int length=recordArray.length;
		queryId=Integer.parseInt(recordArray[0]);
		relevance=Integer.parseInt(recordArray[length-1]);
		currentRecord=currentLine;
		//System.out.println("length of split array :"+length+" "+recordArray[1]);
		features=new double[recordArray.length-2];
		
		for(int i=0;i<length-2;i++)
		{
			//System.out.println(recordArray[i+1]);
			features[i]=Double.parseDouble(recordArray[i+1]);
		}
	}

	public String getCurrentRecord() {
		return currentRecord;
	}

	public void setCurrentRecord(String currentRecord) {
		this.currentRecord = currentRecord;
	}

	public int getQueryId() {
		return queryId;
	}

	public void setQueryId(int queryId) {
		this.queryId = queryId;
	}

	public int getRelevance() {
		return relevance;
	}

	public void setRelevance(int relevance) {
		this.relevance = relevance;
	}

	
	public String getDifference(Record b)
	{
		String recordToWrite="";
		
		if((this.relevance) > (b.getRelevance()) )
		{
			recordToWrite+="1";
		}
		else
		{
			recordToWrite+="-1";
		}
		
		for(int i=0;i<features.length;i++)
		{
			recordToWrite+=",";
			recordToWrite+=String.valueOf(numberFormat.format(this.features[i]-b.features[i]));
		}	
		return recordToWrite;
	}

	@Override
	public String toString() {
		return "Record [queryId=" + queryId + ", relevance=" + relevance
				+ ", features=" + Arrays.toString(features);
	}
}

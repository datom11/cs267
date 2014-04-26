package com.data.preprocessor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class ConvertInputData {

	List<Record> docRecords; 
	
	public void readFile(String filename) throws Exception
	{
		String currentLine;
		BufferedReader br= new BufferedReader(new FileReader(new File(filename)));
		
		docRecords=new ArrayList<Record>();
		
		while((currentLine=br.readLine())!=null)
		{
			Record record=new Record(currentLine);
			docRecords.add(record);
		}
	}
	
	public void writeDatatoFile(String outputFilename)
	{
		PrintWriter out=null;
		try { 
			 out=new PrintWriter(outputFilename);
		} catch (FileNotFoundException e) {
			System.out.println("file not exists");
			e.printStackTrace();
		}
		Record a,b;
		if(out!=null)
		{
			for(int i=0;i<docRecords.size();i++)
			{
				a=docRecords.get(i);
				for(int j=i+1;j<docRecords.size();j++)
				{
					b=docRecords.get(j);
				 if(a.getQueryId()==b.getQueryId() && a.getRelevance()!=b.getRelevance())
				 {	 
						out.println(a.getDifference(b));
						//System.out.print(" for " + a.getRelevance() + " and " + b.getRelevance() + "   ");
						//System.out.println(a.getDifference(b));
				 }
				}
			}
			out.flush();
			out.close();
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//String inputFilename="/home/kuldeep/workspace/DM/src/train10.txt";
		
		String inputFilename="/home/hdu/Documents/train.csv";
		String outputFilename="/home/kuldeep/workspace/DM/src/com/data/preprocessor/out5.txt";
		ConvertInputData convertData = new ConvertInputData();
		try {
			convertData.readFile(inputFilename);
			convertData.writeDatatoFile(outputFilename);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

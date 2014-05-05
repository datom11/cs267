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

	public void readFile(String filename) throws Exception {
		String currentLine;
		BufferedReader br = new BufferedReader(new FileReader(
				new File(filename)));

		docRecords = new ArrayList<Record>();

		while ((currentLine = br.readLine()) != null) {
			Record record = new Record(currentLine);
			docRecords.add(record);
		}
	}

	public void writeDatatoFile(String outputFilename) {
		PrintWriter out = null;
		try {
			out = new PrintWriter(outputFilename);
		} catch (FileNotFoundException e) {
			System.out.println("file not exists");
			e.printStackTrace();
		}
		Record a, b;
		if (out != null) {
			for (int i = 0; i < docRecords.size(); i++) {
				a = docRecords.get(i);
				for (int j = i + 1; j < docRecords.size(); j++) {
					b = docRecords.get(j);
					if (a.getQueryId() == b.getQueryId()
							&& a.getRelevance() != b.getRelevance()) {
						out.println(a.getDifference(b));
						// System.out.print(" for " + a.getRelevance() + " and "
						// + b.getRelevance() + "   ");
						// System.out.println(a.getDifference(b));
					}
				}
			}
			out.flush();
			out.close();
		}
	}

	public void makeStats() {

		PrintWriter out = null;
		try {
			out = new PrintWriter(
					"/home/kuldeep/git/cs267/src/com/data/preprocessor/group_stats.txt");
		} catch (FileNotFoundException e) {
			System.out.println("file not exists");
			e.printStackTrace();
		}

		System.out.println("# of recs = " + docRecords.size());
		int k = 0;
		int i = 0;
		int diff = 0;
		//for (int i = 0; i < docRecords.size(); i++) {
		while (i < docRecords.size()) {	
			
			System.out.println("start = " + i);
			Record a = docRecords.get(i);

			int cnt = 1, c4 = 0, c3 = 0, c2 = 0, c1 = 0, c0 = 0;
			diff = 1;
			
			if (a.getRelevance() == 4)
				c4++;

			if (a.getRelevance() == 3)
				c3++;

			if (a.getRelevance() == 2)
				c2++;

			if (a.getRelevance() == 1)
				c1++;

			if (a.getRelevance() == 0)
				c0++;

			for (int j = i + 1; j < docRecords.size(); j++) {
				Record b = docRecords.get(j);
				
				
				if (a.getQueryId() != b.getQueryId())
					break;

				diff++;
				cnt++;

				if (b.getRelevance() == 4)
					c4++;

				if (b.getRelevance() == 3)
					c3++;

				if (b.getRelevance() == 2)
					c2++;

				if (b.getRelevance() == 1)
					c1++;

				if (b.getRelevance() == 0)
					c0++;

			}
			k++;
			out.println(a.getQueryId() + ":" + cnt + ":" + c4 + ":" + c3 + ":"
					+ c2 + ":" + c1 + ":" + c0);

			/*System.out.println(a.getQueryId() + ":" + cnt + ":" + c4 + ":" + c3
					+ ":" + c2 + ":" + c1 + ":" + c0);*/
		i = i + diff;
		System.out.println("this chunk = " + diff + "next start = " + i);
		
		/*if (k == 14)
			System.exit(0);*/
		}
		out.flush();
		out.close();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// String inputFilename="/home/kuldeep/workspace/DM/src/train10.txt";

		String inputFilename = "/home/kuldeep/git/cs267/src/com/data/preprocessor/test.arff";
		String outputFilename = "/home/kuldeep/git/cs267/src/com/data/preprocessor/pair-wise-test.csv";
		ConvertInputData convertData = new ConvertInputData();
		try {
			System.out.println("b4 deser");
			convertData.readFile(inputFilename);
			System.out.println("a4 deser");
			convertData.writeDatatoFile(outputFilename);
			System.out.println("DONE...");
			//convertData.makeStats();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

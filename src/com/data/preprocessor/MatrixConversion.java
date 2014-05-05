package com.data.preprocessor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class MatrixConversion {

	int resultMatrix[][];
	File pairwiseInput;

	public void intializeMatrix() {

	}

	public void getMatrix() throws Exception {
		String prediction, statLine;
		
		BufferedReader input = new BufferedReader(new FileReader(new File(
				"/home/kuldeep/git/cs267/src/com/data/preprocessor/rftransformation/input.txt")));
		BufferedReader stats = new BufferedReader(new FileReader(new File(
				"/home/kuldeep/git/cs267/src/com/data/preprocessor/rftransformation/stats.txt")));
		BufferedReader label = new BufferedReader(new FileReader(new File(
				"/home/kuldeep/git/cs267/src/com/data/preprocessor/rftransformation/label.txt")));
		//out=new PrintWriter("/home/kuldeep/git/cs267/src/com/data/preprocessor/rftransformation/scores_pred.txt");
		PrintWriter out = null;
		out = new PrintWriter("/home/kuldeep/git/cs267/src/com/data/preprocessor/rftransformation/scores_pred.txt");

		while ((statLine = stats.readLine()) != null) {
			int size = Integer.parseInt(statLine.split(":")[1]);
			//System.out.println("matrix size:"+size);
			resultMatrix = new int[size][size];

			Map<Integer, Integer> labelMap = new HashMap<>();
			for (int cnt = 0; cnt < size; cnt++) {
				labelMap.put(cnt, Integer.parseInt(label.readLine()));
			}
			for (int i = 0; i < size; i++) {
				for (int j = i; j < size; j++) {
					if (i == j || labelMap.get(i) == labelMap.get(j)) {
						resultMatrix[i][j] = 0;
						resultMatrix[j][i] = 0;
					} else {
						int temp=Integer.parseInt(input.readLine());
						//System.out.println(temp);
						resultMatrix[i][j] = temp;//Integer.parseInt(input.readLine());
						resultMatrix[j][i]= -1 * (resultMatrix[i][j]);
					}
				}
			}
			for(int i=0;i<size;i++)
			{
				int score=0;
				for(int j=0;j<size;j++)
				{
					System.out.print("  "+resultMatrix[i][j]);
					score+=resultMatrix[i][j];
				}
				System.out.println(score);
				out.println(score);
			}
		}
		out.flush();
		out.close();
	}
	public static void main(String[] agrs) throws Exception
	{
		MatrixConversion matconv=new MatrixConversion();
		try {
			matconv.getMatrix();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}


package com.infy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Tester {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		RPNCalculator rpnCalculator = new RPNCalculator();
		String oldInput= "";
		for (;;) {			
			BufferedReader reader =
	                new BufferedReader(new InputStreamReader(System.in));
			String input = oldInput+reader.readLine();
			String temp = input + " ";
			Object[] obj = rpnCalculator.Calculation(input);
			int counter = (int) obj[1]+1;
			String error = (String) obj[2];
			
			ArrayList<String> array = (ArrayList<String>) obj[0];
			String res = String.join(" ", array);
			ArrayList<String> num_array = new ArrayList<String>();
			double temp_num;
			double scale = Math.pow(10, 10);
			String temp_str;
			String res_dob = null;
			for (String num : array) {
				temp_num = Double.parseDouble(num);
				temp_num = Math.round(temp_num * scale) / scale;
				temp_str = Double.toString(temp_num);
				num_array.add(temp_str);
				res_dob= String.join(" ", num_array);
				
			}
			if (error.equals("Yes")) {
				int location = (int) obj[3];
				String[] splitStr = input.split("\\s+");
				String correctInput = "";
				String syntax = splitStr[location];
				for (int i = 0; i < location; i++) {
					correctInput = correctInput+" "+splitStr[i];
				}
					temp = correctInput.trim()+" ";
					oldInput = temp;
					System.out.println("Operator "+syntax+" (position: "+(oldInput.length()+1)+")"+": insufficient parameters stack: "+ res);
			}
			else if (error.equals("InvalidInput")) {

				temp = oldInput;
				oldInput = temp + "";
			}
			else {
				oldInput = temp;
				System.out.println("Stack: "+res_dob+"\n\n");
			}
		}
	}
}

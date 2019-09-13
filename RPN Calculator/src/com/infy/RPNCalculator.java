package com.infy;

import java.sql.Array;
import java.util.ArrayList;
import java.io.Console;
import java.lang.Math;

public class RPNCalculator {	
	public Boolean RPNValidator(String input) {
		String[] splitStr = input.split("\\s+");
		if(splitStr[0].equals("redo")) {
			return false;
		}
		for(String num:splitStr) {
			if(num.isEmpty()) {
				return false;
			}
			if (!(num.contains("clear")||num.contains("undo")||num.equals("sqrt")||
					num.matches("^(\\+|-)?([0-9]+)$")||num.matches("^(\\+|-)?([0-9]+(\\.[0-9]+))$")||
					num.equals("+")||num.equals("-")||
					num.equals("*")||num.equals("/"))) {
				return false;
				
			}
		}

		return true;	
	}
	public Object[] Calculation(String input){
		Object[] returnObj = new Object[4];
		String error = "No";
		int i = 0;
		int counter = 0; 
		int size_input = 0;
		ArrayList<String> calculation = new ArrayList<String>();
		// Create the Array that can be used for calculation
		try {
		if (RPNValidator(input)) {
			String[] splitStr = input.split("\\s+");
			for(String num :splitStr) {
				calculation.add(num);
			}
			size_input = calculation.size();
			
		}
		else {
			error = "InvalidInput";
			returnObj[0] = calculation;
			returnObj[1] = 0;
			returnObj[2] = error;
			returnObj[3] = 0;
 			
 			System.out.println("The input is invalid");
 			return returnObj;
		}
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		ArrayList<String> result = new ArrayList<String>();
		//for loop for the calculation +_*/ sqrt clear undo
		// counter for undo
		while(i<calculation.size()) {
			if (calculation.contains("clear")) {
				calculation.remove(0);
				i = 0;
				counter = 0;
			}
			else if (calculation.contains("undo")) {
				int undo = calculation.indexOf("undo");
				counter++;
				if (calculation.size()==1) {
					calculation.clear();
				}
				else {
				calculation.remove(undo-1);
				calculation.remove(undo-1);	
				i = 0;
				}
				counter--;
			}
			else {				
				if (i<calculation.size()-1) {
					//first digit					
					if (calculation.get(i).matches("^(\\+|-)?([0-9]+)$")||calculation.get(i).matches("^(\\+|-)?([0-9]+(\\.[0-9]+))$")) {
						counter++;					
						//sqrt calculation
						if (calculation.get(i+1).equals("sqrt")) {							
							double num1 = Double.parseDouble(calculation.get(i));
							Double temp = Math.sqrt(num1);
							calculation.set(i,temp.toString());
							calculation.remove(i+1);
							if (i < calculation.size()-1) {
								i = 0;		
							}
						}	
						// Second digit
						else if (calculation.get(i+1).matches("^(\\+|-)?([0-9]+)$")||calculation.get(i+1).matches("^(\\+|-)?([0-9]+(\\.[0-9]+))$")) {
							counter++;
							
							if (i+2 < calculation.size()) {
								if (calculation.get(i+2).equals("+")||calculation.get(i+2).equals("-")||
										calculation.get(i+2).equals("*")||calculation.get(i+2).equals("/")) {
									counter++;
									
									Double num1= Double.parseDouble(calculation.get(i));
									Double num2 = Double.parseDouble(calculation.get(i+1));
									
									String token = calculation.get(i+2);
									Double num;
									String str= null;
									switch (token) {
									case "+":
									num = num1+num2;
									str = num.toString();
									break;
									case "-":
									num = num1-num2;
									str = num.toString();
									break;
									case "*":
									num = num1*num2;
									str = num.toString();
									break;
									case"/":
									num = num1/num2;
									str = num.toString();
									break;
									}
									calculation.set(i,str.toString());
									calculation.remove(i+1);
									calculation.remove(i+1);									
									i = 0;
								}
							else {
								i++;
	
							}
								
							}
							else {
								i++;	
							}							
						}
						else {
							int k = calculation.size();
							for (int j = k-1 ; j > i; j--) {
								calculation.remove(j);
							}
							error = "Yes";
							returnObj[0] = calculation;
							returnObj[1] = counter;
							returnObj[2] = error;
							returnObj[3] = size_input- k+1;
				 			return returnObj;
						}

					}
				}
				else {
					i++;
				}

		}
	}	
		returnObj[0] = calculation;
		returnObj[1] = counter;
		returnObj[2] = error;
			return returnObj;		
	}
}

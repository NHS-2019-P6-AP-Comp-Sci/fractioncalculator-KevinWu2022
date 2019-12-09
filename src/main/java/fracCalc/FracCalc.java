/**
 * @author Mr. Rasmussen
 */
package fracCalc;

import java.util.*;

public class FracCalc {
	
	// [12/8/2019 KW] passed all checkpoints and final tests except extra credit
	
	public static void main(String[] args) {
		// TODO: Read the input from the user and call produceAnswer with an equation
		Scanner s = new Scanner(System.in);
		String input = " ";
		// [12/8/2019 KW] program continues to run until user types "quit"
		while (input.equals("quit") != true) {
			input = s.nextLine();
			if (input.equals("quit")) {
				s.close();
			} else {
				System.out.println(produceAnswer(input));
			}
		}
	}

	// ** IMPORTANT ** DO NOT DELETE THIS FUNCTION. This function will be used to
	// test your code
	// This function takes a String 'input' and produces the result
	//
	// input is a fraction string that needs to be evaluated. For your program, this
	// will be the user input.
	// e.g. input ==> "1/2 + 3/4"
	//
	// The function should return the result of the fraction after it has been
	// calculated
	// e.g. return ==> "1_1/4"
	
	// [12/8/2019 KW] parse user input into substrings and calculate answer in unreduced format
	// [12/8/2019 KW] pass unreduced format into helper method to calculate reduced format
	public static String produceAnswer(String input) {
		// TODO: Implement this function to produce the solution to the input
		int space = input.indexOf(" ");
		String firstOperand = input.substring(0, space);
		int firstSlash = firstOperand.indexOf("/");
		int firstUnderscore = firstOperand.indexOf("_");
		String firstNumerator = "";
		String firstDenominator = "";
		String firstWhole = "";
		// [12/8/2019 KW] calculate whole, numerator, and denominator for first operand
		if (firstSlash == -1 && firstUnderscore == -1) {
			firstNumerator = "0";
			firstDenominator = "1";
			firstWhole = firstOperand;
		} else if (firstSlash != -1 && firstUnderscore != -1) {
			firstNumerator = firstOperand.substring(firstUnderscore + 1, firstSlash);
			firstDenominator = firstOperand.substring(firstSlash + 1);
			firstWhole = firstOperand.substring(0, firstUnderscore);
		} else if (firstSlash != -1 && firstUnderscore == -1) {
			firstNumerator = firstOperand.substring(0, firstSlash);
			firstDenominator = firstOperand.substring(firstSlash + 1);
			firstWhole = "0";
		}
		String operator = input.substring(space + 1, space + 2);
		String secondOperand = input.substring(space + 3);
		int secondSlash = secondOperand.indexOf("/");
		int secondUnderscore = secondOperand.indexOf("_");
		String secondNumerator = "";
		String secondDenominator = "";
		String secondWhole = "";
		// [12/8/2019 KW] calculate whole, numerator, and denominator for second operand
		if (secondSlash == -1 && secondUnderscore == -1) {
			secondNumerator = "0";
			secondDenominator = "1";
			secondWhole = secondOperand;
		} else if (secondSlash != -1 && secondUnderscore != -1) {
			secondNumerator = secondOperand.substring(secondUnderscore + 1, secondSlash);
			secondDenominator = secondOperand.substring(secondSlash + 1);
			secondWhole = secondOperand.substring(0, secondUnderscore);
		} else if (secondSlash != -1 && secondUnderscore == -1) {
			secondNumerator = secondOperand.substring(0, secondSlash);
			secondDenominator = secondOperand.substring(secondSlash + 1);
			secondWhole = "0";
		}
		// [12/8/2019 KW] parse strings into integers for whole, numerator,
		// and denominator for each operand
		int fWhole = Integer.parseInt(firstWhole);
		int fNumerator = Integer.parseInt(firstNumerator);
		if (fWhole < 0) {
			fNumerator = fNumerator * -1;
		}
		int fDenominator = Integer.parseInt(firstDenominator);
		int sWhole = Integer.parseInt(secondWhole);
		int sNumerator = Integer.parseInt(secondNumerator);
		if (sWhole < 0) {
			sNumerator = sNumerator * -1;
		}
		int sDenominator = Integer.parseInt(secondDenominator);
		int fImNum = fWhole * fDenominator + fNumerator;
		int sImNum = sWhole * sDenominator + sNumerator;
		String result = "";
		int resultNum = 0;
		int resultDenom = 0;
		
		// [12/8/2019 KW] operator(+, -, *, /) conditions
		if (operator.equals("+")) {
			resultNum = fImNum * sDenominator + sImNum * fDenominator;
			resultDenom = fDenominator * sDenominator;
		} else if (operator.equals("-")) {
			resultNum = fImNum * sDenominator - sImNum * fDenominator;
			resultDenom = fDenominator * sDenominator;
		} else if (operator.equals("*")) {
			resultNum = fImNum * sImNum;
			resultDenom = fDenominator * sDenominator;
		} else if (operator.equals("/")) {
			resultNum = fImNum * sDenominator;
			resultDenom = fDenominator * sImNum;
		}
		result = reduceAnswer(resultNum, resultDenom);
		return result;
	}

	// TODO: Fill in the space below with any helper methods that you think you will
	// need
	
	// [12/8/2019 KW] helper method to calculate and return reduced form of fraction
	public static String reduceAnswer(int resultNum, int resultDenom) {
		String reducedResult = "";
		// [12/8/2019 KW] checks if both numerator and denominator can be divided
		// by a common factor
		for (int i = 2; i <= 100; i++) {
			while (resultNum % i == 0 && resultDenom % i == 0) {
				// if (resultNum % i == 0 && resultDenom % i == 0) {
				resultNum /= i;
				resultDenom /= i;
				// }
			}
		}
		int numSign = 1;
		int denomSign = 1;
		if (resultNum < 0) {
			numSign = -1;
		}
		if (resultDenom < 0) {
			denomSign = -1;
		}
		if (resultNum % resultDenom ==  0) {
			reducedResult = (resultNum / resultDenom) + "";
		}
		else if (Math.abs(resultNum) > Math.abs(resultDenom)) {
			reducedResult = (resultNum / resultDenom) + "_" + Math.abs((resultNum % resultDenom))
			+ "/" + Math.abs(resultDenom);
		}
		else if (Math.abs(resultNum) < Math.abs(resultDenom)){
			reducedResult = Math.abs(resultNum) + "/" + Math.abs(resultDenom);
			if (numSign * denomSign < 0) {
				reducedResult = "-" + reducedResult;
			}
		}
		return reducedResult;
		/*
		if (numSign * denomSign > 0) {
			return reducedResult;
		}
		else {
			return "-" + reducedResult;
		}
		*/
	}
}

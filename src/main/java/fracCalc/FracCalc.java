/**
 * @author Mr. Rasmussen
 */
package fracCalc;
import java.util.*;
public class FracCalc {

    public static void main(String[] args)
    {
        // TODO: Read the input from the user and call produceAnswer with an equation
    	Scanner s = new Scanner(System.in);
    	String input = " ";
    	while (input.equals("quit") != true) {
    		input = s.nextLine();
    		if (input.equals("quit")) {
    			s.close();
    		}
    		else {
    			System.out.println(produceAnswer(input));
    		}
    	}
    }

    // ** IMPORTANT ** DO NOT DELETE THIS FUNCTION.  This function will be used to test your code
    // This function takes a String 'input' and produces the result
    //
    // input is a fraction string that needs to be evaluated.  For your program, this will be the user input.
    //      e.g. input ==> "1/2 + 3/4"
    //
    // The function should return the result of the fraction after it has been calculated
    //      e.g. return ==> "1_1/4"
    public static String produceAnswer(String input)
    {
        // TODO: Implement this function to produce the solution to the input
    	int space = input.indexOf(" ");
    	String firstOperand = input.substring(0, space);
    	int firstSlash = firstOperand.indexOf("/");
    	int firstUnderscore = firstOperand.indexOf("_");
    	String firstNumerator = "";
    	String firstDenominator = "";
    	String firstWhole = "";
    	if (firstSlash == -1 && firstUnderscore == -1) {
    		firstNumerator = "0";
    		firstDenominator = "1";
        	firstWhole = firstOperand;
    	}
    	else if (firstSlash != -1 && firstUnderscore != -1) {
    		firstNumerator = firstOperand.substring(firstUnderscore + 1, firstSlash);
    		firstDenominator = firstOperand.substring(firstSlash + 1);
        	firstWhole = firstOperand.substring(0, firstUnderscore);
    	}
    	else if (firstSlash != -1 && firstUnderscore == -1) {
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
    	if (secondSlash == -1 && secondUnderscore == -1) {
    		secondNumerator = "0";
    		secondDenominator = "1";
        	secondWhole = secondOperand;
    	}
    	else if (secondSlash != -1 && secondUnderscore != -1) {
    		secondNumerator = secondOperand.substring(secondUnderscore + 1, secondSlash);
    		secondDenominator = secondOperand.substring(secondSlash + 1);
        	secondWhole = secondOperand.substring(0, secondUnderscore);
    	}
    	else if (secondSlash != -1 && secondUnderscore == -1) {
    		secondNumerator = secondOperand.substring(0, secondSlash);
    		secondDenominator = secondOperand.substring(secondSlash + 1);
    		secondWhole = "0";
    	}
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
    	if (operator.equals("+")) {
    		result = (fImNum * sDenominator + sImNum * fDenominator) + "/" + (fDenominator * sDenominator);
    	}
    	else if (operator.equals("-")) {
    		result = (fImNum * sDenominator - sImNum * fDenominator) + "/" + (fDenominator * sDenominator);
    	}
    	else if (operator.equals("*")) {
    		result = (fImNum * sImNum) + "/" + (fDenominator * sDenominator);
    	}
    	else if (operator.equals("/")) {
    		result = (fImNum * sDenominator) + "/" + (fDenominator * sImNum);
    	}
        return result;
    }

    // TODO: Fill in the space below with any helper methods that you think you will need

}

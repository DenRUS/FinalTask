package calculator;

import calculator.Executor;;

/**
 * main class
 * @author DenRUS
 *
 */
public class Calc {
	final static String outputFileName = new String("./output.xml");
	
	
	public static void main(String[] args) {
//		args = new String[1];
//		args[0] = "C:/Users/DenRUS/Documents/workspace-sts-3.7.3.RELEASE/FracCalc/bin/calculator/1.txt";
//		args[1] = "1/8 * 23/3";
		Executor exec = new Executor(outputFileName);
		exec.execute(args);
		
	}
}

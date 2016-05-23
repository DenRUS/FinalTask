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
		Executor exec = new Executor(outputFileName);
		exec.execute(args);
		
		
	}
}

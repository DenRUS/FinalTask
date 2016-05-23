package calculator;

import calculator.Executor;
import test.TestCalculator;;

/**
 * main class
 * @author DenRUS
 *
 */
public class Calc {
	final static String outputFileName = new String("./output.xml");
	
	
	public static void main(String[] args) {
		if (args.length == 0) {
			TestCalculator.testMethod();
		} else {
			Executor exec = new Executor(outputFileName);
			exec.execute(args);
		}
	}
}

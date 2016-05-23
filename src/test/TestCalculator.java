package test;

import calculator.Executor;

/**
 * class for testing (will be added soon)
 * @author DenRUS
 *
 */
public class TestCalculator {
	public static String[] args;
	public static void testMethod() {
		System.out.println("You run the program in test regime. To run the program normally you should"
				+ "\nwrite arguments like expressions (2/3 + 3/2 etc.) or filename with expressions.");
		args = new String[5];
		args[0]="1/2 + 3/2;";
		args[1]="2/3- 4/4;";
		args[2]="7/8/2/32;";
		args[3]="1/5 *4 /10;";
		args[4]="1 / 8 + 12/7;";
		Executor exec = new Executor();
		exec.execute(args);
	}

}

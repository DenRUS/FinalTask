package calculator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import mathem.FracOperations;
import xml.Result;
import editors.Editor;

/**
 * performs logic
 * @author DenRUS
 *
 */
public class Executor {
	/**
	 * editor
	 */
	Editor edit;
	/**
	 * write result in this file, if it is necessary
	 */
	String outputFileName;
	/**
	 * constructs a new Executor with basic parameters to perform calculation
	 */
	public Executor(){
		this.edit = new Editor();
		this.outputFileName = "executorOutput.xml";
	}
	/**
	 * constructs a new Executor with custom parameters to perform calculation 
	 * @param FileName write result in this file, if it is necessary
	 */
	public Executor(String FileName){
		this.edit = new Editor();
		this.outputFileName = FileName;
	}
	
	/**
	 * performs a calculation according to the logic of the project
	 * @param args input
	 */
	public void execute(String[] args) {
		if (args.length == 1 && Pattern.matches(".*\\..{2,4}$", args[0])) {
			fileExec(args[0]);
		} else {
			for (int k = 1; k < args.length; k++) {
				if (Pattern.matches(".*\\..{1,}$", args[k])){
					args[k] = "n*";
				}
			}
			try {
				for(String s: fracExec(args)){
					System.out.println(s);
				}
			} catch (Exception e) {
				System.out.println("Error!");
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * performs checks and executing of the input expressions according to the logic of the program
	 * 
	 * @param args string array with expressions to execute
	 * 
	 * @return string array with results
	 * 
	 * @throws Exception if calculation can not be made
	 */
	public String[] fracExec (String[] args) throws Exception {
		String input = edit.inputEditing(args);
		String[] expressions = edit.fragmentationToExspressions(input);
		FracOperations fracCalc = new FracOperations();
		String[] res = new String[expressions.length];
		for ( int i = 0; i < expressions.length; i++) {
			try {
				res[i] = expressions[i]+" = "+operationExec(expressions[i],fracCalc);
			} catch (Exception e) {
				res[i] = "Wrong input!";
				System.out.println("Exception at execution!");
				e.printStackTrace();
			}
		}
		return res;		
	}
	
	/**
	 * execute the operation, which is written in input string
	 * 
	 * @param input string with expression
	 * @param fracCalc calculator
	 * 
	 * @return string with the result
	 * 
	 * @throws Exception incorrect input(wrong symbols or operation can not be calculated)
	 */
	public String operationExec (String input,FracOperations fracCalc) throws Exception {	
		if (!edit.correctActionNumberCheck(input)) {throw new Exception("Wrong number of actions!");}
		String[] ops = edit.fragmentationToOperands(input);
		if (!edit.correctOperandsNumberCheck(ops)) {throw new Exception("Wrong input!");}
		int[] intOps = new int[4];
		for ( int j = 0; j < intOps.length; j++) {intOps[j] = new Integer(ops[j]);}
		String expressionToExecute = input.replaceAll("[^\\+\\*-/\\\\]", "").substring(1, 2);
		if (edit.divisionByZeroCheck(intOps)&& expressionToExecute.equals("/")) {throw new ArithmeticException("Divide by zero!");}
		return fracCalc.exec(intOps, expressionToExecute);
	}

	/**
	 * realizes the logic of the program in case of the filename is set as the parameter
	 * 
	 * @param inputString name of the file with expressions
	 */
	public void fileExec(String inputString) {
		ArrayList<String> list = new ArrayList<String>();
		try {
		FileReader inputReader = new FileReader(inputString);
		BufferedReader bufInput = new BufferedReader(inputReader);
		try {
			String line;
			line = bufInput.readLine();
			list.add(line+";");
			while (line != null) {
				line = bufInput.readLine();
				list.add(line+";");
			}
		} finally {
			bufInput.close();
		}
	}catch (IOException e) {
		System.out.println("File not found!");
		e.printStackTrace();
		return;
	}
		String[] args = list.toArray(new String[list.size()]);
		try {
			Result result = new Result(fracExec(args));	
			result.marsh(outputFileName);
		} catch (Exception e) {
			System.out.println("Error!");
			e.printStackTrace();
		}		
	}

}

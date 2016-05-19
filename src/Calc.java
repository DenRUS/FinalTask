//import java.util.Arrays;
import java.awt.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.*;

public class Calc {
	private static String outputFileName = new String("output.xml");

	
	public static void main(String[] args) {
		if (args.length == 1 && Pattern.matches(".*\\..{1,3}$", args[0])) {
			fileExec(args[0]);
		} else {
			try {
				System.out.println(fracExec(args));
			} catch (Exception e) {
				System.out.println("Error!");
				e.printStackTrace();
			}
		}
		
	}
	
	/*
	 * Проверяет входной массив на корректность по критериям корректности входных данных программы
	 */
	public static String inputCheck(String[] args) {
		String input= "";
		if (args.length > 7) System.out.println("Too many operands!Spliting them...");
		for ( int i = 0; i < args.length; i++){
			input = input + args[i].replaceAll(" ","");
		}
		System.out.println(input);
		if (Pattern.matches("(.*;null;$)|(.*;$)", input)) {
			input = input.replaceAll("(;null;$)|(;$)", "");
		}
		System.out.println(input);
		
		if (Pattern.matches(".*[^\\+\\d\\*:;/-].*", input)) {
			System.out.println("Input contains letters! Removing them...");
			input = input.replaceAll("[^\\+\\d\\*:/-]", "");
		}
		System.out.println(input);
		
		return input;		
	}
	
	/*
	 * Разделяет строку на операнды(тоже строкового типа) согласно логике программы
	 */
	public static String[] fragmentationToOperands(String input) {
		Pattern pattern = Pattern.compile(":|-|[*]|[+]|/");
		return pattern.split(input);				
	}
	
	/*
	 * операция сложения обычных дробей
	 */
	private static String fracSum (int[] ops) {
		return Integer.toString(ops[0]*ops[3]+ops[2]*ops[1])+"/"+Integer.toString(ops[1]*ops[3]);
	}
	
	/*
	 * операция вычитания обычных дробей
	 */
	private static String fracDif (int[] ops) {
		return Integer.toString(ops[0]*ops[3]-ops[2]*ops[1])+"/"+Integer.toString(ops[1]*ops[3]);
	}
	/*
	 * операция умножения обычных дробей
	 */
	private static String fracMult (int[] ops) {
		return Integer.toString(ops[0]*ops[2])+"/"+Integer.toString(ops[1]*ops[3]);
	}
	/*
	 * операция деления обычных дробей
	 */
	private static String fracDiv (int[] ops) {
		return Integer.toString(ops[0]*ops[3])+"/"+Integer.toString(ops[1]*ops[2]);
	}
	
	/*
	 * выполнение операции, записанной в строке
	 */
	public static String fracExec (String[] args) throws Exception {
		String input = inputCheck(args);
		
		
		String[] ops = fragmentationToOperands(input);
		if (ops.length != 4 ) {throw new Exception("Wrong input!");}
		int[] intOps = new int[4];
		for ( int j = 0; j < 4; j++) {intOps[j] = new Integer(ops[j]);}
		if (intOps[1]*intOps[3] == 0) {throw new Exception("Divide by zero!");}
		String expressionToExecute = input.replaceAll("[^-/\\+\\*]", "").substring(1, 2);
		switch (expressionToExecute) {
		case "+":return fracSum(intOps);
		case "-":return fracDif(intOps);
		case "/":return fracDiv(intOps);
		case "*":return fracMult(intOps);
		default:throw new Exception("Something goes wrong.. there is no operation symbol.");
		}
	}

	/*
	 * реализует логику программы, если в качестве аргумента указан файл
	 */
	public static void fileExec(String inputString) {
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
		System.out.println("ОЙ! А где файл?");
		e.printStackTrace();
	}
		String[] args = list.toArray(new String[list.size()]);
		System.out.println(args.length+"   "+args[0]+args[1]);
		try {
			System.out.println(fracExec(args));
		} catch (Exception e) {
			System.out.println("Error!");
			e.printStackTrace();
		}
		
	}

	

}


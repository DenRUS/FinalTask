//import java.util.Arrays;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.regex.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class Calc {
	private static String outputFileName = new String("output.xml");

	
	public static void main(String[] args) {
		if (args.length == 1 && Pattern.matches(".*\\..{1,3}$", args[0])) {
			fileExec(args[0]);
		} else {
			try {
				String [] result = fracExec(args);
				for (int j = 0; j < result.length; j++){
					System.out.println(result[j]);
				}				
			} catch (Exception e) {
				System.out.println("Error!");
				e.printStackTrace();
			}
		}
		
	}
	
	/*
	 * ѕровер€ет входной массив на корректность по критери€м корректности входных данных программы
	 */
	public static String inputCheck(String[] args) {
		String input= "";
		if (args.length > 7) System.out.println("Too many operands!Spliting them...");
		for ( int i = 0; i < args.length; i++){
			input = input + args[i].replaceAll(" ","");
		}
		if (Pattern.matches("(.*;null;$)|(.*;$)", input)) {
			input = input.replaceAll("(;null;$)|(;$)", "");
		}
		if (Pattern.matches(".*[^\\+\\d\\*:;/-].*", input)) {
			System.out.println("Input contains letters! Removing them...");
			input = input.replaceAll("[^\\+\\d\\*:/-]", "");
		}
		return input;		
	}
	
	/*
	 * –аздел€ет строку на операнды(тоже строкового типа) согласно логике программы
	 */
	public static String[] fragmentationToOperands(String input) {
		Pattern pattern = Pattern.compile(":|-|[*]|[+]|/");
		return pattern.split(input);				
	}
	
	/*
	 * –аздел€ет входной массив на несколько выражений дл€ счета
	 */
	public static String[] fragmentationToExspressions(String input) {
		Pattern pattern = Pattern.compile(";");
		return pattern.split(input);				
	}
	
	
	/*
	 * операци€ сложени€ обычных дробей
	 */
	private static String fracSum (int[] ops) {
		return Integer.toString(ops[0]*ops[3]+ops[2]*ops[1])+"/"+Integer.toString(ops[1]*ops[3]);
	}
	
	/*
	 * операци€ вычитани€ обычных дробей
	 */
	private static String fracDif (int[] ops) {
		return Integer.toString(ops[0]*ops[3]-ops[2]*ops[1])+"/"+Integer.toString(ops[1]*ops[3]);
	}
	/*
	 * операци€ умножени€ обычных дробей
	 */
	private static String fracMult (int[] ops) {
		return Integer.toString(ops[0]*ops[2])+"/"+Integer.toString(ops[1]*ops[3]);
	}
	/*
	 * операци€ делени€ обычных дробей
	 */
	private static String fracDiv (int[] ops) {
		return Integer.toString(ops[0]*ops[3])+"/"+Integer.toString(ops[1]*ops[2]);
	}
	
	/*
	 * реализует вычислени€ согласно логике программы
	 */
	public static String[] fracExec (String[] args) throws Exception {
		String input = inputCheck(args);
		String[] expressions = fragmentationToExspressions(input);
		String[] res = new String[expressions.length];
		for ( int i = 0; i < expressions.length; i++) {
			res[i] = operationExec(expressions[i]);
		}
		return res;
		
	}
	
	/*
	 * выполнение операции, записанной в строке
	 */
	public static String operationExec (String input) throws Exception {	
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
		System.out.println("Ouch!What`s going on?Where is the file?");
		e.printStackTrace();
		return;
	}
		String[] args = list.toArray(new String[list.size()]);
		try {
			Result result = new Result(fracExec(args));	
			marsh(result);
		} catch (Exception e) {
			System.out.println("Error!");
			e.printStackTrace();
		}		
	}
	/*
	 * маршализаци€ элемента типа Result
	 */
	public static void marsh(Result arg)	{
		OutputStream os = null;
		try {
			File of = new File(outputFileName);
			os = new FileOutputStream(of);
			JAXBContext context = JAXBContext.newInstance(Result.class);
			Marshaller mar = context.createMarshaller();
			mar.marshal(arg, os);
			os.close();
		} catch (IOException ex) {
			System.out.println("Oh..again troubles..");
			ex.printStackTrace();
		} catch (JAXBException ex) {
			System.out.println("Oh..again troubles..");
			ex.printStackTrace();
		}
	}
}
/*
 * класс дл€ маршализации
 */
@XmlRootElement
class Result {
	public String[] res;
	Result(String[] args) {this.res = args;}
	Result(){};
}
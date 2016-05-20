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
			for (int k = 1; k < args.length; k++) {
				if (Pattern.matches(".*\\..{1,5}$", args[k])){
					args[k] = "n*";
				}
			}
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
	 * Checks input array for correctness by criteria of the logic of the program
	 */
	public static String inputCheck(String[] args) {
		String input= "";
		for ( int i = 0; i < args.length; i++){
			input = input + args[i].replaceAll(" ","");
		}
		input = input.replaceAll("(n\\*){2,}", "\\*");
		if (Pattern.matches("(.*;null;$)|(.*;$)", input)) {
			input = input.replaceAll("(;null;$)|(;$)", "");
		}
		if (Pattern.matches(".*[^\\+\\d\\*\\\\:;/-].*", input)) {
			System.out.println("Input contains invalid characters! Deleting them...");
			input = input.replaceAll("[^\\+\\d\\*\\\\:;/-]", "");
		}
		return input;		
	}
	
	/*
	 * Splits input String into the array of strings according to the logic of the program (and pattern)
	 */
	public static String[] fragmentationToOperands(String input) {
		Pattern pattern = Pattern.compile(":|-|[*]|[+]|/|\\\\");
		return pattern.split(input);				
	}
	
	/*
	 * Splits input string into different strings, which are expressions for execution
	 */
	public static String[] fragmentationToExspressions(String input) {
		Pattern pattern = Pattern.compile(";");
		return pattern.split(input);				
	}
	
	
	/*
	 * sum operation for fractions
	 */
	private static String fracSum (int[] ops) {
		return Integer.toString(ops[0]*ops[3]+ops[2]*ops[1])+"/"+Integer.toString(ops[1]*ops[3]);
	}
	
	/*
	 * difference operation for fractions
	 */
	private static String fracDif (int[] ops) {
		return Integer.toString(ops[0]*ops[3]-ops[2]*ops[1])+"/"+Integer.toString(ops[1]*ops[3]);
	}
	/*
	 * multiplication operation for fractions
	 */
	private static String fracMult (int[] ops) {
		return Integer.toString(ops[0]*ops[2])+"/"+Integer.toString(ops[1]*ops[3]);
	}
	/*
	 * division operation for fractions
	 */
	private static String fracDiv (int[] ops) {
		return Integer.toString(ops[0]*ops[3])+"/"+Integer.toString(ops[1]*ops[2]);
	}
	
	/*
	 * execute calculation according to the logic of the program
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
	 * execute the operation, which is written in input string
	 */
	public static String operationExec (String input) throws Exception {	
		String[] ops = fragmentationToOperands(input);
		if (ops.length != 4 ) {throw new Exception("Wrong input!");}
		int[] intOps = new int[4];
		for ( int j = 0; j < 4; j++) {intOps[j] = new Integer(ops[j]);}
		String expressionToExecute = input.replaceAll("[^\\+\\*-/\\\\]", "").substring(1, 2);
		if (intOps[1]*intOps[3] == 0 || (intOps[2] == 0 && expressionToExecute.equals("/"))) {throw new Exception("Divide by zero!");}
		switch (expressionToExecute) {
		case "+":return fracSum(intOps);
		case "-":return fracDif(intOps);
		case "\\":
		case "/":return fracDiv(intOps);
		case "*":return fracMult(intOps);
		default:throw new Exception("Something goes wrong.. there is no operation symbol.");
		}
	}

	/*
	 * realizes the logic of the program in case of the filename is set as the parameter
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
	 * marshallise argument 
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
 * class for marshalling
 */
@XmlRootElement
class Result {
	public String[] res;
	Result(String[] args) {this.res = args;}
	Result(){};
}
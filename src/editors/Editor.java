package editors;

import java.util.regex.Pattern;

/**
 * performs editing and checks
 * 
 * @author DenRUS
 *
 */
public class Editor implements Checker, Fragmentator{
	/**
	 * Checks the correctness of input array according to the logic of the program
	 * and edit it if necessary
	 * 
	 * @param args string array which correctness should checked
	 * 
	 * @return edited string without invalid characters
	 */
	public String inputEditing(String[] args) {
		String input= "";
		for (String arg: args) {
			input = input + arg.replaceAll(" ", "");
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
}

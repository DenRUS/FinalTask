package editors;

import java.util.regex.Pattern;

/**
 * performs fragmentation
 * 
 * @author DenRUS
 *
 */
public interface Fragmentator {
	/**
	 * Splits input string into the array of strings according to the logic of the program (i.e. using the pattern)
	 * 
	 * @param input string that contains operation and should be split to operands
	 * 
	 * @return string array, which elements are operands 
	 */
	public default String[] fragmentationToOperands(String input) {
		Pattern pattern = Pattern.compile(":|-|[*]|[+]|/|\\\\");
		return pattern.split(input);				
	}
	
	/**
	 * Splits one input string into expressions for execution (different strings)
	 * 
	 * @param input string that contains some expressions and should be split
	 * 
	 * @return string array, which elements are expressions
	 */
	public default String[] fragmentationToExspressions(String input) {
		Pattern pattern = Pattern.compile(";");
		return pattern.split(input);				
	}

}
package editors;

/**
 * performs checks of arguments for operation validity
 * 
 * @author DenRUS
 *
 */
public interface Checker {
	/**
	 * checks number of actions in input string
	 * @param input string with expression
	 * @return if number of actions is correct - true, incorrect - false
	 */
	public default boolean correctActionNumberCheck(String input) {
		if (input.replaceAll("[^\\+]", "").length() > 1 || input.replaceAll("[^-]", "").length() > 1
				|| input.replaceAll("[^\\*]", "").length() > 1 || input.replaceAll("[^\\+\\*\\\\:/-]", "").length() > 3 
				|| input.replaceAll("[^\\\\:/]", "").length() < 2) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * checks division by zero
	 * @param intOps operands
	 * @return division by zero - true, no division by zero - false
	 */
	public default boolean divisionByZeroCheck (int[] intOps) {
		if ((intOps[1]*intOps[3]) == 0 || (intOps[2] == 0)){
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * checks number of operands
	 * @param ops operands to check
	 * @return true if there are correct number of operands and false if not
	 */
	public default boolean correctOperandsNumberCheck(String[] ops){
		if (ops.length != 4){
			return false;
		} else {
			return true;
		}
	}

}

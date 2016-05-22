package mathem;


/**
 * performs mathematical operations with ordinary fractions
 * 
 * @author DenRUS
 *
 */
public class FracOperations {
	public String exec(int[] operands, String operation) throws Exception{
		switch (operation) {
		case "+":return fracSum(operands);
		case "-":return fracDif(operands);
		case "\\":
		case "/":return fracDiv(operands);
		case "*":return fracMult(operands);
		default:throw new Exception("Something goes wrong.. there is no operation symbol.");
		}
		
	}
	
	/**
	 * sum operation for fractions
	 * 
	 * @param ops integer array with operands
	 * 
	 * @return result of operation as string
	 */
	private String fracSum (int[] ops) {
		return Integer.toString(ops[0]*ops[3]+ops[2]*ops[1])+"/"+Integer.toString(ops[1]*ops[3]);
	}
	
	/**
	 * difference operation for fractions
	 * 
	 * @param ops integer array with operands
	 * 
	 * @return result of operation as string
	 */
	private String fracDif (int[] ops) {
		return Integer.toString(ops[0]*ops[3]-ops[2]*ops[1])+"/"+Integer.toString(ops[1]*ops[3]);
	}
	
	/**
	 * multiplication operation for fractions
	 * 
	 * @param ops integer array with operands
	 * 
	 * @return result of operation as string
	 */
	private String fracMult (int[] ops) {
		return Integer.toString(ops[0]*ops[2])+"/"+Integer.toString(ops[1]*ops[3]);
	}
	
	/**
	 * division operation for fractions
	 * 
	 * @param ops integer array with operands
	 * 
	 * @return result of operation as string
	 */
	private String fracDiv (int[] ops) {
		return Integer.toString(ops[0]*ops[3])+"/"+Integer.toString(ops[1]*ops[2]);
	}

	
}

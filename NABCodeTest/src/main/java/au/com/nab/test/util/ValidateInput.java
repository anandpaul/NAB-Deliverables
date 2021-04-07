/**
 * 
 */
package au.com.nab.test.util;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 * @author Ananda Paul
 *
 */
public class ValidateInput {
	private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	/**
	 * This method check if the entered instruction is a valid one
	 * 
	 * @param input
	 * @return
	 */
	public static boolean isValid(String input) {
		boolean isValid = false;
		List<String> instructions = Arrays.asList("PUSH", "POP", "CLEAR", "ADD", "MUL", "NEG", "INV", "UNDO", "PRINT",
				"QUIT");
		String message = "";
		if (input != null) {
			String number[] = input.trim().split(" ");

			// If the instruction is for PUSH, then it should be followed by number
			if (number[0].trim().equalsIgnoreCase("PUSH")) {
				String secondInput = "";
				if (number.length > 1) {
					secondInput = number[1].trim();

					// Checks if the second part of PUSH instruction is a number
					Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
					if (pattern.matcher(secondInput).matches()) {
						isValid = true;
					} else {
						message = "Number is invalid. Please enter the correct number";
						System.out.println(message);
						LOGGER.warning(message);
					}
				} else {
					message = "Number is missing in the input. Please enter number";
					System.out.println(message);
					LOGGER.warning(message);
					return isValid;
				}
			} else if (instructions.contains(input.trim().toUpperCase())) {
				isValid = true;
			}
		}
		return isValid;
	}

	/**
	 * This method displays the instruction banner
	 */
	public static void displayInstructions() {
		System.out.println("***************************************************************************************\r\n"
				+ "                                  NAB STACK MACHINE                        \r\n"
				+ "***************************************************************************************");
		System.out.println("Enter the instruction \"PUSH <<number>>\" to insert a number into the stack\r\n"
				+ "Enter the instruction \"POP\" to remove the top element from the stack\r\n"
				+ "Enter the instruction \"CLEAR\" to remove all the elements from the stack\r\n"
				+ "Enter the instruction \"ADD\" to add the top two elements of the stack\r\n"
				+ "Enter the instruction \"MUL\" to multiply the top two elements of the stack\r\n"
				+ "Enter the instruction \"NEG\" to negate the top element of the stack\r\n"
				+ "Enter the instruction \"INV\" to invert the top element of the stack\r\n"
				+ "Enter the instruction \"UNDO\" to undo the last instruction\r\n"
				+ "Enter the instruction \"PRINT\" to print all the elements from the stack\r\n"
				+ "Enter the instruction \"QUIT\" to exit the program ");
		System.out.println("***************************************************************************************");
	}
}

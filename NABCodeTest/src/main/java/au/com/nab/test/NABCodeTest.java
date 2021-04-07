/**
 * 
 */
package au.com.nab.test;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Logger;

import au.com.nab.test.business.StackMachineBusiness;
import au.com.nab.test.util.StackLogger;
import au.com.nab.test.util.ValidateInput;

/**
 * @author Ananda Paul
 *
 */
public class NABCodeTest {

	private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ValidateInput.displayInstructions();
		try {
			StackLogger.setup();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boolean process = true;
		StackMachineBusiness stackMachine = new StackMachineBusiness();

		// System gets the user input until user enters "Quit"
		while (process) {
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in);
			System.out.print("Enter the Instruction - ");
			String input = scanner.nextLine();
			if (ValidateInput.isValid(input)) {
				LOGGER.info("User entered - " + input);
				stackMachine.processStacking(input);
			} else {
				String msg = "Invalid Instruction. Please enter the correct instruction. For details, please refer to the introduction banner.";
				System.out.println(msg);
				LOGGER.warning(msg);
			}
			if (input != null && input.trim().toUpperCase().equalsIgnoreCase("QUIT")) {
				System.out.println("******************************************");
				System.out.println("Thanks for using our system. Good Bye....");
				System.out.println("******************************************");
				process = false;
			}
		}

	}

}

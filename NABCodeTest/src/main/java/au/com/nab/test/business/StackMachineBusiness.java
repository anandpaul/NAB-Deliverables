/**
 * 
 */
package au.com.nab.test.business;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

/**
 * This class mainly processes the input and actions the respective operation
 * 
 * @author Ananda Paul
 *
 */
public class StackMachineBusiness {

	// This holds the instructions entered by the user
	private List<String> stackList = new ArrayList<String>();

	// This holds all the instructions at that point of time, used for UNDO purpose
	private List<List<String>> undoStackList = new ArrayList<>();

	// LOGGER variable is declared to log info or warning or error statements
	private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	/**
	 * This method mainly identifies the instruction and calls the respective
	 * methods to perform
	 * 
	 * @param input
	 */
	public void processStacking(String input) {
		String number = "";

		// Strips the number for the PUSH instruction
		if (input.trim().toUpperCase().startsWith("PUSH")) {
			number = input.trim().split(" ")[1].trim();
			input = input.trim().split(" ")[0].trim();
		}

		switch (input.toUpperCase()) {
		case "PUSH":
			push(number);
			break;
		case "POP":
			pop();
			break;
		case "CLEAR":
			clear();
			break;
		case "ADD":
			addNumbers();
			break;
		case "MUL":
			multiply();
			break;
		case "NEG":
			negate();
			break;
		case "INV":
			inverse();
			break;
		case "UNDO":
			undo();
			break;
		case "PRINT":
			print();
			break;
		case "QUIT":
			System.exit(0);
			break;
		default:
			break;
		}
	}

	/**
	 * This method returns the top element from the stack and also removes it from
	 * the stack
	 * 
	 * @return
	 */
	private String pop() {
		if (stackList.isEmpty()) {
			return null;
		}
		String top = stackList.get(stackList.size() - 1);

		// remove the top element from the stack
		stackList.remove(stackList.size() - 1);

		String msg = "The top element " + top + " is removed";
		System.out.println(msg);
		LOGGER.info(msg);
		return top;
	}

	/**
	 * This method pushes the value to the top of the stack
	 * 
	 * @param element
	 */
	private void push(String element) {
		stackList.add(element);
//		stackList.add(String.valueOf(Math.round((Double.parseDouble(element) * 100.0) / 100.0)));

		String msg = stackList.get(stackList.size() - 1) + " is pushed to the stack";
		System.out.println(msg);
		LOGGER.info(msg);
		addStackModel();
	}

	/**
	 * This method removes all the elements from the stack
	 */
	private void clear() {
		stackList.clear();

		String msg = "All the elements in the stack are removed. The stack is now empty.";
		System.out.println(msg);
		LOGGER.info(msg);
		addStackModel();
	}

	/**
	 * This method prints the stack
	 */
	private void print() {
		List<String> localList = new ArrayList<String>();
		localList.addAll(stackList);
		Collections.reverse(localList);

		localList.stream().forEach(i -> {
			System.out.println(i);
			LOGGER.info(i);
		});
	}

	/**
	 * This method fetches the first top two elements from the stack, adds those two
	 * numbers and inserts the result to the stack as the top element
	 */
	private void addNumbers() {
		if (isMoreThanTwoNumbers("Addition")) {
			double num1 = Double.parseDouble(pop());
			double num2 = Double.parseDouble(pop());
			double result = num1 + num2;

			String msg = "The instruction ADD is performed successfully.";
			System.out.println(msg);
			LOGGER.info(msg);

			// The result is pushed to the stack
			push(String.valueOf(result));
		}
	}

	/**
	 * This method fetches the first top two elements from the stack, multiplies
	 * those two numbers and inserts the result to the stack as the top element
	 */
	private void multiply() {
		if (isMoreThanTwoNumbers("Multiplication")) {
			double num1 = Double.parseDouble(pop());
			double num2 = Double.parseDouble(pop());
			double result = num1 * num2;

			String msg = "The instruction MUL is performed successfully.";
			System.out.println(msg);
			LOGGER.info(msg);

			// The result is pushed to the stack
			push(String.valueOf(result));
		}
	}

	/**
	 * This method fetches the first top element from the stack, negates the value
	 * and inserts the result to the stack as the top element
	 */
	private void negate() {
		if (isMoreThanOneNumber("Negation")) {
			double num1 = Double.parseDouble(pop());
			double result = num1 * -1;

			String msg = "The instruction NEG is performed successfully.";
			System.out.println(msg);
			LOGGER.info(msg);

			// The result is pushed to the stack
			push(String.valueOf(result));
		}
	}

	/**
	 * This performs the inverse operation of the top element of the stack and
	 * inserts the result into the stack
	 */
	private void inverse() {
		if (isMoreThanOneNumber("Inverse")) {
			double num1 = Double.parseDouble(pop());
			double result = 1 / num1;

			String msg = "The instruction INV is performed successfully.";
			System.out.println(msg);
			LOGGER.info(msg);

			// The result is pushed to the stack
			push(String.valueOf(result));
		}
	}

	/**
	 * This method adds the the stackList at that point of time for UNDO purpose
	 */
	private void addStackModel() {
		List<String> localElements = new ArrayList<String>();
		localElements.addAll(stackList);
		undoStackList.add(localElements);
	}

	/**
	 * This method undo-es the previous operations
	 */
	private void undo() {
		String msg = "";
		if (!undoStackList.isEmpty()) {
			int size = undoStackList.size();
			undoStackList.remove(size - 1);
			if (undoStackList.isEmpty()) {
				stackList.clear();
			} else {
				stackList = undoStackList.get(size - 2);
			}

			msg = "The instruction UNDO is performed successfully.";
		} else {
			msg = "There is nothing to UNDO as the stack is empty. Please PUSH numbers and then try UNDO";
		}
		System.out.println(msg);
		LOGGER.info(msg);
	}

	/**
	 * This method checks if the stack has at least 2 elements in the stack to
	 * perform the operations like ADD and Multiply
	 * 
	 * @param instruction
	 * @return
	 */
	private boolean isMoreThanTwoNumbers(String instruction) {
		boolean isTrue = true;
		if (stackList.size() < 2) {
			String msg = "You need at least two numbers to perform " + instruction
					+ ". The stack currently has less than 2 numbers. Please perform PUSH operation to enter one more number";
			System.out.println(msg);
			LOGGER.info(msg);
			isTrue = false;
		}
		return isTrue;
	}

	/**
	 * This method checks if the stack has at least 1 element in the stack to
	 * perform the operations like Negation and Inverse
	 * 
	 * @param instruction
	 * @return
	 */
	private boolean isMoreThanOneNumber(String instruction) {
		boolean isTrue = true;
		if (stackList.isEmpty()) {
			String msg = "You need at least one number to perform " + instruction
					+ ". The stack is currently emtpy. Please perform PUSH operation to enter one more number";
			System.out.println(msg);
			LOGGER.info(msg);
			isTrue = false;
		}
		return isTrue;
	}

	/**
	 * @return the stackList
	 */
	public List<String> getStackList() {
		return stackList;
	}

	/**
	 * @param stackList the stackList to set
	 */
	public void setStackList(List<String> stackList) {
		this.stackList = stackList;
	}

}

package au.com.nab.test.business;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * This TEST class performs unit testing on the counter class
 * StackMachineBusiness and its methods with different scenarios
 * 
 * @author Ananda Paul
 *
 */
public class StackMachineBusinessTest {

	@Before
	public void setUp() {

	}

	@Test
	public void testProcessStacking_TestCaseFromRequirementDocument() {
		StackMachineBusiness stack = new StackMachineBusiness();
		stack.processStacking("PUSH 1.5");
		stack.processStacking("PUSH -3.5");
		stack.processStacking("PUSH -1");
		stack.processStacking("MUL");
		stack.processStacking("ADD");
		stack.processStacking("INV");
		stack.processStacking("PRINT");
		assertEquals(0.2, Double.parseDouble(stack.getStackList().get(0)), 0);
	}

	@Test
	public void testProcessStacking_TestCaseWithMultipleInstructions() {
		StackMachineBusiness stack = new StackMachineBusiness();
		stack.processStacking("PUSH 1");
		stack.processStacking("PUSH 5.5");
		stack.processStacking("PUSH -1");
		stack.processStacking("MUL");
		stack.processStacking("PUSH -9");
		stack.processStacking("ADD");
		stack.processStacking("neg");
		stack.processStacking("INV");
		stack.processStacking("undo");
		assertEquals(14.5, Double.parseDouble(stack.getStackList().get(stack.getStackList().size()-1)), 0);
	}

	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testProcessStacking_PushWithoutNumber() {
		StackMachineBusiness stack = new StackMachineBusiness();
		stack.processStacking("PUSH");
	}

	@Test
	public void testProcessStacking_ADDTwoNumbers() {
		StackMachineBusiness stack = new StackMachineBusiness();
		stack.processStacking("PUSH 1.5");
		stack.processStacking("PUSH -3.5");
		stack.processStacking("ADD");
		assertEquals(-2.0, Double.parseDouble(stack.getStackList().get(0)), 0);
	}

	@Test
	public void testProcessStacking_ADDWithOneNumberInStack() {
		StackMachineBusiness stack = new StackMachineBusiness();
		stack.processStacking("PUSH 1.5");
		stack.processStacking("ADD");
		assertEquals(1.5, Double.parseDouble(stack.getStackList().get(0)), 0);
	}

	@Test
	public void testProcessStacking_CLEARStack() {
		StackMachineBusiness stack = new StackMachineBusiness();
		stack.processStacking("PUSH 1.5");
		stack.processStacking("PUSH 5.5");
		stack.processStacking("PUSH 34");
		stack.processStacking("clear");
		assertEquals(0, stack.getStackList().size());
	}

	@Test
	public void testProcessStacking_POPStack() {
		StackMachineBusiness stack = new StackMachineBusiness();
		stack.processStacking("PUSH 1.5");
		stack.processStacking("PUSH 5.5");
		stack.processStacking("PUSH 34");
		stack.processStacking("pop");
		assertEquals(5.5, Double.parseDouble(stack.getStackList().get(stack.getStackList().size() - 1)), 0);
	}

	@Test
	public void testProcessStacking_MultiplyTwoNumbersInStack() {
		StackMachineBusiness stack = new StackMachineBusiness();
		stack.processStacking("PUSH 1.5");
		stack.processStacking("PUSH 5");
		stack.processStacking("PUSH 4");
		stack.processStacking("mul");
		assertEquals(20, Double.parseDouble(stack.getStackList().get(stack.getStackList().size() - 1)), 0);
	}

	@Test
	public void testProcessStacking_MultiplyTwiceInStack() {
		StackMachineBusiness stack = new StackMachineBusiness();
		stack.processStacking("PUSH 1.5");
		stack.processStacking("PUSH 5");
		stack.processStacking("PUSH 4");
		stack.processStacking("mul");
		stack.processStacking("mul");
		assertEquals(30, Double.parseDouble(stack.getStackList().get(stack.getStackList().size() - 1)), 0);
	}

	@Test
	public void testProcessStacking_NEGInStack() {
		StackMachineBusiness stack = new StackMachineBusiness();
		stack.processStacking("PUSH 1.5");
		stack.processStacking("PUSH 5");
		stack.processStacking("PUSH 4");
		stack.processStacking("neg");
		assertEquals(-4, Double.parseDouble(stack.getStackList().get(stack.getStackList().size() - 1)), 0);
	}

	@Test
	public void testProcessStacking_NEGWithEmptyStack() {
		StackMachineBusiness stack = new StackMachineBusiness();
		stack.processStacking("PUSH 1.5");
		stack.processStacking("PUSH 5");
		stack.processStacking("PUSH 4");
		stack.processStacking("clear");
		stack.processStacking("neg");
		assertEquals(true, stack.getStackList().isEmpty());
	}

	@Test
	public void testProcessStacking_INVInStack() {
		StackMachineBusiness stack = new StackMachineBusiness();
		stack.processStacking("PUSH 1.5");
		stack.processStacking("PUSH 5");
		stack.processStacking("PUSH 4");
		stack.processStacking("inv");
		assertEquals(0.25, Double.parseDouble(stack.getStackList().get(stack.getStackList().size() - 1)), 0);
	}

	@Test
	public void testProcessStacking_UNDOOnceInStack() {
		StackMachineBusiness stack = new StackMachineBusiness();
		stack.processStacking("PUSH 1.5");
		stack.processStacking("PUSH 5");
		stack.processStacking("PUSH 4");
		stack.processStacking("undo");
		assertEquals(5, Double.parseDouble(stack.getStackList().get(stack.getStackList().size() - 1)), 0);
	}

	@Test
	public void testProcessStacking_UNDOTwiceInStack() {
		StackMachineBusiness stack = new StackMachineBusiness();
		stack.processStacking("PUSH 1.5");
		stack.processStacking("PUSH 5");
		stack.processStacking("PUSH 4");
		stack.processStacking("add");
		stack.processStacking("mul");
		stack.processStacking("undo");
		stack.processStacking("undo");
		assertEquals(4, Double.parseDouble(stack.getStackList().get(stack.getStackList().size() - 1)), 0);
	}

	@Test
	public void testProcessStacking_UNDOUntilEmptyStack() {
		StackMachineBusiness stack = new StackMachineBusiness();
		stack.processStacking("PUSH 1.5");
		stack.processStacking("PUSH 5");
		stack.processStacking("PUSH 4");
		stack.processStacking("add");
		stack.processStacking("mul");
		stack.processStacking("undo");
		stack.processStacking("undo");
		stack.processStacking("undo");
		stack.processStacking("undo");
		stack.processStacking("undo");
		assertEquals(true, stack.getStackList().isEmpty());
	}
}

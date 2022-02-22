public class Notation {
	/**
	 * @author Equara Khan - CMSC204
	
	 */
	/**
	 * Convert an infix expression into a postfix expression
	 * @param infix The infix expression in string format
	 * @return The postfix expression in a string format
	 * @throws InvalidNotationFormatException If the infix expression format is invalid
	 */
	public static String convertInfixToPostfix(String infix) throws InvalidNotationFormatException {
		MyQueue<Character> queue = new MyQueue<Character>(infix.length());
		MyStack<Character> stack = new MyStack<Character>(infix.length());

		char[] string = infix.toCharArray();

		try {
			for (char character : string) {
				if (Character.isDigit(character)) {
					queue.enqueue(character);
				}
				if (character == '(') {
					stack.push(character);
				}
				if (character == '*' || character == '/' || character == '+' || character == '-') {
					if (!stack.isEmpty()) {
						char top = stack.top();
						if (top == '*' || top == '/' || character == '-' && top == '-' || character == '-' && top == '+'
								|| character == '+' && top == '-' || character == '+' && top == '+') {
							queue.enqueue(stack.pop());
						}
					}
					stack.push(character);
					continue;
				}
				if (character == ')') {
					while (stack.top() != '(') {
						queue.enqueue(stack.pop());
						if (stack.top() == null) {
							throw new InvalidNotationFormatException();
						}
					}
					stack.pop();
				}
			}
			while (!stack.isEmpty())
				queue.enqueue(stack.pop());
		} catch (QueueOverflowException | StackOverflowException | StackUnderflowException e) {
			throw new InvalidNotationFormatException();
		}
		return queue.toString();
	}
	/**
	 * Converts the postfix expression to the infix expression
	 * @param postfix the postfix expression in string format
	 * @return the infix expression in string format
	 * @throws InvalidNotationFormatException if the postfix expression format is invalid
	 */

	public static String convertPostfixToInfix(String postfix) throws InvalidNotationFormatException {
		MyStack<String> stack = new MyStack<String>(postfix.length());

		try {
			for (int i = 0; i < postfix.length(); i++) {
				char current = postfix.charAt(i);

				if (Character.isDigit(current)) {
					stack.push(Character.toString(current));
				}
				if (current == '*' || current == '/' || current == '+' || current == '-') {
					if (stack.size() < 2) {
						throw new InvalidNotationFormatException();
					}
					String first = stack.pop();
					String second = stack.pop();
					String s = "(" + second + current + first + ")";
					stack.push(s);
				}
			}

		} catch (StackUnderflowException | StackOverflowException e) {
			throw new InvalidNotationFormatException();
		}
		if (stack.size() > 1) {
			throw new InvalidNotationFormatException();
		}
		return stack.toString();
	}
	/**
	 * Evaluates a postfix expression from a string to a double
	 * @param postfixExpr The postfix expression in String format
	 * @return The evaluation of the postfix expression as a double 
	 * @throws InvalidNotationFormatException If the postfix expression format is invalid
	 */

	public static double evaluatePostfixExpression(String postfixExpr) throws InvalidNotationFormatException {
		MyStack<Double> stack = new MyStack<Double>(postfixExpr.length());
		char[] string = postfixExpr.toCharArray();

		try {
			for (char character : string) {
				if (Character.isDigit(character) || character == '(') {
					stack.push(Double.parseDouble(Character.toString(character)));
				}
				if (character == '*' || character == '/' || character == '+' || character == '-') {
					if (stack.size() < 2) {
						throw new InvalidNotationFormatException();
					}
					double right = stack.pop();
					double left = stack.pop();

					switch (character) {
					case '*':
						stack.push(left * right);
						break;
					case '/':
						stack.push(left / right);
						break;
					case '+':
						stack.push(left + right);
						break;
					case '-':
						stack.push(left - right);
					}
				}
			}
		} catch (StackOverflowException | StackUnderflowException e) {
			throw new InvalidNotationFormatException();
		}
		if (stack.size() > 1) {
			throw new InvalidNotationFormatException();
		}
		return Double.parseDouble(stack.toString());
	}
}
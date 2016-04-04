import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Node {
	int type; // 0: oper; 1: value; 2: open bracket; 3: close bracket
	int priority; // priority of oper
	int priorityBefore; // priority before it goes into stack
	String name; // name of oper
	float value; // value

	public Node(int type, int priority, String name, float value) {
		this.type = type;
		this.priority = priority;
		this.priorityBefore = priority;
		this.name = name;
		this.value = value;

	}

	public Node(int type, int priority, int priorityBefore, String name, float value) {
		this.type = type;
		this.priority = priority;
		this.priorityBefore = priorityBefore;
		this.name = name;
		this.value = value;

	}

	public String toString() {
		return (type == 1) ? ("" + value) : (">" + name);
	}
}

class InfixExpression {
	int priority; 
	String expression; 
	
	public InfixExpression(String expression, int priority){
		this.priority = priority;
		this.expression = expression;
	}

	public String toString() {
		return expression;
	}
}

public class MyCaculator {

	private static List<Node> toList(String s) {
		List<Node> l = new ArrayList<>();

		Pattern p = Pattern.compile("(\\(|\\+|\\)|/|\\*{1,2}|-)|(\\d+\\.?\\d*)|(\\d*\\.?\\d+)|(\\d+\\.?\\d+)");
		Matcher m = p.matcher(s);

		while (m.find()) {
			if (m.group().matches("[\\+\\-]")) { // +,- priority 10
				l.add(new Node(0, 10, m.group(), 0));
			} else if (m.group().matches("[\\*/]|\\*\\*")) { // *,/ priority 20
				l.add(new Node(0, 20, m.group(), 0));
			} else if (m.group().matches("[\\(]")) { // ( has highest priority out
													// of stack, but lowest
													// priority in stack
				l.add(new Node(2, 0, 40, m.group(), 0));
			} else if (m.group().matches("[\\)]")) { // ) priority 5
				l.add(new Node(3, 5, m.group(), 0));
			} else if (m.group().matches("(\\d+\\.?\\d*)|(\\d*\\.?\\d+)|(\\d+\\.?\\d+)")) {
				l.add(new Node(1, 100, m.group(), Float.valueOf(m.group())));
			} else {
				throw new RuntimeException("Unhandled symble");
			}
		}

		l.add(new Node(4, 0, "END", 0));
		return l;
	}

	private static float caculateBasic(float a, float b, String oper) {
		float result = 0;

		switch (oper) {
		case "+":
			result = a + b;
			break;
		case "-":
			result = a - b;
			break;
		case "*":
			result = a * b;
			break;
		case "/":
			result = a / b;
			break;
		case "**":
			result = (float) Math.pow(a, b);
			break;
		}
		return result;
	}

	public static float caculateSuffix(String s) {

		List<Node> l = toList(s);
		System.out.println(l);

		MyStack<Node> stack = new MyStack<>();

		float result = 0, a = 0, b = 0;

		for (Node item : l) {
			if (item.type != 1 && item.type != 4) {
				// if oper, pop 2 numbers from stack
				b = stack.pop().value;
				a = stack.pop().value;
				result = caculateBasic(a, b, item.name);
				stack.push(new Node(1, 0, String.valueOf(result), result));
			} else {
				stack.push(item);
			}
		}

		return result;
	}

	public static float caculateInfix(String s) {
		String postfix = infixToPostfix(s);
		return caculateSuffix(postfix);
	}

	public static String postfixToInfix(String s) {

		List<Node> l = toList(s);
		MyStack<InfixExpression> stack = new MyStack <> ();
		System.out.println(l);
		
		InfixExpression a, b;
		String result = "", expa = "", expb = "";

		for (Node item : l) {
			if (item.type != 1 && item.type != 4) {
				// if oper, pop 2 expressions from stack
				b = stack.pop();
				a = stack.pop();
				if (b.priority < item.priority) {
					expb = "(" + b.expression + ")";
				} else {
					expb = b.expression;
				}
				
				if (a.priority < item.priority) {
					expa = "(" + a.expression + ")";
				} else {
					expa = a.expression;
				}

				result = expa + item.name + expb;
				stack.push(new InfixExpression(result, item.priority));
			} else {
				stack.push(new InfixExpression(String.valueOf(item.value), item.priority));
			}
		}

		return result;
	}

	public static String infixToPostfix(String s) {
		List<Node> l = toList(s);

		StringBuffer sb = new StringBuffer();

		MyStack<Node> ms = new MyStack<>();

		for (Node item : l) {

			// if the items is number, put it in result
			if (item.type == 1) {
				sb.append(" " + item.value);
				continue;
			}

			while (true) {
				Node oper = (Node) ms.peek();
				// if the stack is empty, just push in the new one
				if (oper == null) {
					ms.push(item);
					break;
				}

				// if the previous operator's priority is lower
				// keep it in stack, and push the new operator into the stack
				// if the new operator is right bracket, it would eat the left bracket
				if (oper.priority < item.priorityBefore) {

					if (oper.type == 2 && item.type == 3) {
						ms.pop();
					} else {
						ms.push(item);
					}
					break;
				}

				// if the previous operator's priority is higher,
				// it can get out of stack
				if (oper.priority >= item.priorityBefore) {
					sb.append(" " + oper.name);
					ms.pop();
					continue;
				}
			}
		}
		return sb.toString();
	}

}

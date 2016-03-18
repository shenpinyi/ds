import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Node {
	int type; // 0: oper; 1: value
	int priority; // priority of oper
	String name; // name of oper
	float value; // value

	public Node(int type, int priority, String name, float value) {
		this.type = type;
		this.priority = priority;
		this.name = name;
		this.value = value;

	}

	public String toString() {
		return (type == 0) ? (">" + name) : ("" + value);
	}
}

public class MyCaculator {

	private static List<Node> toList(String s) {
		List<Node> l = new ArrayList<>();

		Pattern p = Pattern.compile("(\\(|\\+|\\)|/|\\*|-)|(\\d+\\.?\\d*)|(\\d*\\.?\\d+)|(\\d+\\.?\\d+)");
		Matcher m = p.matcher(s);

		while (m.find()) {
			if (m.group().matches("[\\+\\-]")) {
				l.add(new Node(0, 10, m.group(), 0));
			} else if (m.group().matches("[\\*/]")) {
				l.add(new Node(0, 20, m.group(), 0));
			} else if (m.group().matches("[\\(\\)]")) {
				l.add(new Node(0, 30, m.group(), 0));
			} else if (m.group().matches("(\\d+\\.?\\d*)|(\\d*\\.?\\d+)|(\\d+\\.?\\d+)")) {
				l.add(new Node(1, 0, m.group(), Float.valueOf(m.group())));
			} else {
				throw new RuntimeException("Unhandled symble");
			}
		}

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
		}
		return result;
	}

	public static float caculateSuffix(String s) {

		List<Node> l = toList(s);
		System.out.println(l);

		MyStack<Node> stack = new MyStack<>();

		float result = 0, a = 0, b = 0;

		for (Node item : l) {
			if (item.type == 0) {
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

}

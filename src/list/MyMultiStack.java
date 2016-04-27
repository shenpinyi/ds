package list;

public class MyMultiStack<T> {

	private final int DEFAULT_SIZE = 20;
	private final int size = DEFAULT_SIZE;
	private final int STACK_NUM = 3;
	Object[] nodes = new Object[size];
	Object[] stacks = new Object[STACK_NUM];
	AvailableStack availableStack = new AvailableStack();
	int nullIdx = 0;

	private class Node {
		T item;
		int next;

		public Node(T t, int idx) {
			item = t;
			next = idx;
		}

		public String toString() {
			return item + "(" + next + ") ";
		}
	}
	
	public void printAvaliableStack(){
		System.out.println(availableStack);
	}

	private class AvailableStack {
		int top = -1;
		int idx;

		public int pop() {
			if (top == -1) {
				return -1;
			} else {
				idx = top;
				top = ((Node) nodes[idx]).next;
				return idx;
			}
		}

		public void push(int idx) {
			((Node) nodes[idx]).next = top;
			top = idx;
		}

		public String toString() {
			StringBuffer buff = new StringBuffer();
			int idx = top;
			while (idx != -1) {
				buff.append(((Node) nodes[idx]).item + "(" + ((Node) nodes[idx]).next + ")" + " ");
				idx = ((Node) nodes[idx]).next;
			}

			return buff.toString();
		}
	}

	private class InnerStack implements MyStackIntf<T> {
		int top;

		@Override
		public T pop() {
			if (top == -1) {
				return null;
			} else {
				int idx = top;
				Node node = (Node) nodes[top];
				top = node.next; // change the top
				releaseNode(idx); // release this node to available node list
				return node.item;
			}
		}

		@Override
		public void push(T t) {

			int idx = allocateNode();
			if (idx == -1) {
				throw new RuntimeException("Stack is full");
			} else {
				Node n = new Node(t, top);
				nodes[idx] = n;
				top = idx;
			}
		}

		@Override
		public T peek() {
			if (top == -1) {
				return null;
			} else {
				return ((Node) nodes[top]).item;
			}
		}

		public String toString() {
			StringBuffer buff = new StringBuffer();
			int idx = top;
			while (idx != -1) {
				buff.append(((Node) nodes[idx]).item + "(" + ((Node) nodes[idx]).next + ")" + " ");
				idx = ((Node) nodes[idx]).next;
			}

			return buff.toString();
		}
	}

	public MyMultiStack() {

		for (int i = 0; i < STACK_NUM; i++) {
			InnerStack s = new InnerStack();
			s.top = -1;
			stacks[i] = s;
		}

	}

	private int allocateNode() {
		int idx;

		if (nullIdx < size) {
			idx = nullIdx;
			nullIdx++;
			return idx;
		} else {
			return availableStack.pop();
		}

	}

	private void releaseNode(int idx) {
		availableStack.push(idx);
	}

	public MyStackIntf<T> getStack(int idx) {
		return (MyStackIntf<T>) stacks[idx];
	}

	public String toString() {
		StringBuffer buff = new StringBuffer();
		for (Object n : nodes) {
			buff.append((Node) n);
			buff.append(" ");
		}
		return buff.toString();
	}

}

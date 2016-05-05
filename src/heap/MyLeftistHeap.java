package heap;

public class MyLeftistHeap <T extends Comparable <? super T>> {

	class Entry {
		T element;
		Entry left;
		Entry right;
		int npl;
		
		public String toString() {
			return "[" + element.toString() + ", " + npl + "]";
		}
	}
	
	int size;
	
	Entry root;
	
	public MyLeftistHeap() {
		root = null;
		size = 0;
	}
	
	public void insert(T t) {
		Entry h = new Entry();
		h.element = t;
		h.npl = 0;
		h.left = h.right = null;
		root = merge(root, h);
	}
	
	public Entry merge(Entry t1, Entry t2) {
		if (t1 == null) {
			return t2;
		}
		
		if (t2 == null) {
			return t1;
		}
		
		if (t1.element.compareTo(t2.element) > 0) {
			Entry t = t1;
			t1 = t2;
			t2 = t;
		}
		
		if (t1.right == null) {
			t1.right = t2;
		} else {
			t1.right = merge(t1.right, t2);
		}

		if (npl(t1.left) < npl(t1.right)) {
			swap(t1);
		}

		t1.npl = npl(t1.right) + 1;

		return t1;
	}
	
	private int npl(Entry h) {
		return h == null ? -1 : h.npl;
	}
	
	private void swap(Entry h) {
		Entry t = h.left;
		h.left = h.right;
		h.right = t;
	}
	
	public void merge(MyLeftistHeap <T> h) {
		if (h == null) {
			return;
		}
		root = merge(root, h.root);
	}
	
	public T findMin() {
		return root == null ? null : root.element;
	}
	
	public T deleteMin() {
		if (root == null) {
			return null;
		}
		
		T t = root.element;
		
		root = merge(root.left, root.right);

		return t;
	}
	
	public boolean isEmpty() {
		return root == null;
	}
	
	public void makeEmpty() {
		root = null;
	}
	
	public String toString() {
		StringBuffer buff = new StringBuffer();
		printTree(root, 0, "T:", buff);
		return buff.toString();
	}
	
	private void printTree(Entry e, int level, String pos, StringBuffer buff){
		if (e == null){
			printSp(level, buff);
			buff.append(pos + "null\r\n");
			return;
		}
		printTree(e.right, level + 1, "R:", buff);

		printSp(level, buff);
		buff.append(pos + e + "<\r\n");

		printTree(e.left, level + 1, "L:", buff);
	}
	
	private void printSp(int level, StringBuffer buff) {
		for (int i = 0; i < level; i ++) {
			buff.append("            ");
		}
	}
	
}

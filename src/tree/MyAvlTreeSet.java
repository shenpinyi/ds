package tree;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;

public class MyAvlTreeSet <E> implements Set <E>{
	Node root;
	int size;
	Comparator <E> comparator;
	
	class Node {
		E element;
		Node right;
		Node left;
		int height;
		
		public String toString() {
			return "(" + element + ", h:" + height + ")";
		}
	}
	
	public MyAvlTreeSet(Comparator <E> p){
		comparator = p;
	}
	
	public MyAvlTreeSet() {
		comparator = null;
		root = null;
		size = 0;
	}
	
	@SuppressWarnings("unchecked")
	private int compare(E e1, E e2){
		if (comparator == null) {
			return ((Comparable <E>) e1).compareTo(e2); 
		} else {
			return comparator.compare(e1, e2);
		}
	}
	
	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(E e) {
		int oldSize = size;
		root = add(e, root);
		return size != oldSize;
	}
	
	private int height(Node t) {
		if (t == null) {
			return -1;
		} else {
			return t.height;
		}
	}
	
	// return the root of new sub-tree
	private Node add(E e, Node t) {
		if (t == null) {
			Node node = new Node();
			node.element = e;
			node.left = null;
			node.right = null;
			node.height = 0;
			size ++;
			return node;
		}
		
		int r = compare(e, t.element);
		Node subTree = null;
		if (r == 0) {
			t.element = e;
			return t;
		} else if (r > 0) {
			subTree = add(e, t.right);
			t.right = subTree;
			if ((height(t.right) - height(t.left)) >= 2) {
				t = rotateRight(t);
			}
			t.height = Math.max(height(t.right), height(t.left)) + 1;
		} else {
			subTree = add(e, t.left);
			t.left = subTree;
			if ((height(t.left) - height(t.right)) >= 2) {
				t = rotateLeft(t);
			}
			t.height = Math.max(height(t.right), height(t.left)) + 1;
		}
		
		return t;
	}
	
	private Node rotateLeft(Node t) {
		
		if (height(t.left.left) > height(t.left.right)) {
			return singleRotateLeft(t);
		} else {
			return biRotateLeft(t);
		}
	}
	
	private Node singleRotateLeft(Node t) {
		Node r;
		r = t.left;
		
		// single rotate
		t.left = r.right;
		r.right = t.left;
		
		//re-calculate height
		t.height = Math.max(height(t.right), height(t.left)) + 1;
		r.height = Math.max(height(r.left), height(r.right)) + 1;
		return r;
	}
	
	private Node biRotateLeft(Node t) {
		Node r = t.left.right;
		
		// rotate left
		t.left.right = r.left;
		r.left = t.left;

		//rotate right
		t.left = r.right;
		r.right = t;
		
		r.left.height = Math.max(height(r.left.left), height(r.left.right)) + 1;
		r.right.height = Math.max(height(r.right.left), height(r.right.right)) + 1;
		r.height = Math.max(height(r.left), height(r.left)) + 1;
		return r;
	}
	
	private Node rotateRight(Node t) {
		if (height(t.right.right) > height(t.right.left)) {
			return singleRotateRight(t);
		} else {
			return biRotateRight(t);
		}
	}
	
	private Node singleRotateRight(Node t) {
		Node r;
		r = t.right;

		// single rotate
		t.right = r.left;
		r.left = t;

		//re-calculate the height 
		t.height = Math.max(height(t.right), height(t.left)) + 1;
		r.height = Math.max(height(r.left), height(r.right)) + 1;
		return r;
	}
	
	private Node biRotateRight(Node t) {
		Node r = t.right.left;
		
		// rotate right
		t.right.left = r.right;
		r.right = t.right;

		//rotate left
		t.right = r.left;
		r.left = t;
		
		r.left.height = Math.max(height(r.left.left), height(r.left.right)) + 1;
		r.right.height = Math.max(height(r.right.left), height(r.right.right)) + 1;
		r.height = Math.max(height(r.left), height(r.left)) + 1;
		return r;
	}
	
	public boolean validateAvl() {
		
		if (validateAvl(root) >= -1) {
			return true;
		} else {
			return false;
		}
	}
	
	private int validateAvl(Node t) {
		if (t == null) {
			return -1;
		}
		
		int right = validateAvl(t.right);
		int left = validateAvl(t.left);
		
		if (right < -1) {
			return right;
		}
		
		if (left < -1) {
			return left;
		}

		if (Math.abs(right - left) > 1) {
			return -3;
		}
		
		int height = Math.max(right, left) + 1;
		
		if (height != t.height) {
			return -2;
		}
		
		return height;
	}

	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}
	
	public String toString() {
		StringBuffer buff = new StringBuffer();
		printTree(root, 0, "T:", buff);
		return buff.toString();
	}
	
	private void printTree(Node e, int level, String pos, StringBuffer buff){
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

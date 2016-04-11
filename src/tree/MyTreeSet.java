package tree;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;

public class MyTreeSet <E> implements Set <E> {
	
	int size = 0;
	TreeNode root = null;
	Comparator <E> comparator = null;
	
	public MyTreeSet() {
		super();
	}
	
	public MyTreeSet(Comparator <E> p) {
		comparator = p;
	}
	
	class TreeNode {
		TreeNode right;
		TreeNode left;
		TreeNode parent;
		E element;	
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
		if (findNode((E) o, root) == null) {
			return false;
		}
		return true;
	}
	
	private int compare(E e1, E e2) {
		if (comparator != null) {
			return comparator.compare(e1, e2);
		}
		return ((Comparable <E>) e1).compareTo((E) e2);
	}
	
	public TreeNode findNode(E e, TreeNode t) {
		if (t == null) {
			return null;
		}
		
		int result = compare(e, t.element);
		
		if (result == 0) {
			return t;
		} else if (result > 0) {
			return findNode(e, t.right);
		} else {
			return findNode(e, t.left);
		}
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
		int i = size;
		root = add(e, root);
		return i != size;
	}
	
	private TreeNode add(E e, TreeNode t) {
		if (t == null) {
			TreeNode node = new TreeNode();
			node.element = e;
			node.left = null;
			node.right = null;
			size++;
			return node;
		}
		
		int result = compare(e, t.element);
		TreeNode nodeAdd = null;
		
		if (result == 0) {
			return t;
		} else if (result > 0) {
			nodeAdd = add(e, t.right);
			t.right = nodeAdd;
		} else {
			nodeAdd = add(e, t.left);
			t.left = nodeAdd;
		}
		
		nodeAdd.parent = t;
		size++;
		return t;
	}
	

	@Override
	public boolean remove(Object o) {
		int i = size;
		root = remove((E) o, root);
		return i != size;
	}
	
	public TreeNode remove(E e, TreeNode t) {
		
		if (t == null) {
			return t;
		}
		
		int result = compare(e, t.element);		
		if (result == 0) {
			if (t.right == null && t.left == null) {
				return null;
			} else if(t.left == null) {
				return t.right;
			} else if(t.right == null) {
				return t.left;
			} else {
				TreeNode rep = findMax(t.left);
				t.left = remove(rep.element, t.left);
				t.element = rep.element;
				return t;
			}
		} else if (result > 0) {
			t.right = remove(e, t.right);
			return t;
		} else {
			t.left = remove(e, t.left); 
			return t;
		}
	}
	
	public E findMin() {
		return findMin(root).element;
	}

	public E findMax() {
		return findMax(root).element;
	}

	public TreeNode findMin(TreeNode t) {
		if (t.left == null) {
			return t;
		} else {
			return findMin(t.left);
		}
	}
	
	public TreeNode findMax(TreeNode t) {
		if (t.right == null) {
			return t;
		} else {
			return findMax(t.right);
		}
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
		root = null;
		size = 0;
	}

	public String toString() {
		StringBuffer buff = new StringBuffer();
		printTree(buff, root, 0);
		return buff.toString();
	}
	
	public void printTree(StringBuffer buff, TreeNode t, int level) {
		
		if (t == null) { 
			buff.append("NULL\r\n");
			return;
		}
		
		buff.append(t.element + "\r\n");
		buff.append(printTables(level) + "R:");
		printTree(buff, t.right, level + 1);
		buff.append(printTables(level) + "L:");
		printTree(buff, t.left, level + 1);
	}
	
	private String printTables(int level) {
		StringBuffer s = new StringBuffer();
		for (int i = 0; i < level; i ++) {
			s.append("\t");
		}
		return s.toString();
	}
	
}

package tree;

public class BinarySearchTree <E extends Comparable <? super E>> {

	BinaryNode <E> root;
	
	public BinarySearchTree() {
		root = null;
	}
	
	public void insert(E e) {
		insert(e, root);
	}
	
	public void insert(E e, BinaryNode <E> t) {
		if (t == null) {
			BinaryNode <E> node = new BinaryNode<>();
			node.element = e;
			node.left = null;
			node.right = null;
			root = node; 
		} else {
			if (e.compareTo(t.element) == 0) {
				return;
			}
			
			if (e.compareTo(t.element) > 0) {
				if (t.right != null) {
					insert(e, t.right);
					return;
				} else {
					BinaryNode <E> node = new BinaryNode<>();
					node.element = e;
					node.left = null;
					node.right = null;
					t.right = node;
					return;
				}
			}
			
			if (e.compareTo(t.element) < 0) {
				if (t.left != null) {
					insert(e, t.left);
					return;
				} else {
					BinaryNode <E> node = new BinaryNode<>();
					node.element = e;
					node.left = null;
					node.right = null;
					t.left = node;
					return;
				}
			}
		}
	}
	
	public void remove(E e) {
		root = remove(e, root);
	}

	public BinaryNode <E> remove(E e, BinaryNode <E> t) {
		
		if (t == null) {
			return t;
		}
		
		if (e.compareTo(t.element) == 0) {
			if (t.right == null && t.left == null) {
				return null;
			} else if(t.left == null) {
				return t.right;
			} else if(t.right == null) {
				return t.left;
			} else {
				BinaryNode <E> rep = findMax(t.left);
				t.left = remove(rep.element, t.left);
				t.element = rep.element;
				return t;
			}
		} else if (e.compareTo(t.element) > 0) {
			t.right = remove(e, t.right);
			return t;
		} else {
			t.left = remove(e, t.left); 
			return t;
		}
	}

	public boolean contains(E e) {
		return contains(e, root);
	}
	
	public boolean contains(E e, BinaryNode <E> t) {
		if (t == null) {
			return false;
		}
		
		if (e.compareTo(t.element) == 0) {
			return true;
		} else if (e.compareTo(t.element) > 0) {
			return contains(e, t.right);
		} else {
			return contains(e, t.left);
		}
	}
	
	public E findMin() {
		return findMin(root).element;
	}

	public E findMax() {
		return findMax(root).element;
	}

	public BinaryNode <E> findMin(BinaryNode <E> t) {
		if (t.left == null) {
			return t;
		} else {
			return findMin(t.left);
		}
	}

	public BinaryNode <E> findMax(BinaryNode <E> t) {
		if (t.right == null) {
			return t;
		} else {
			return findMax(t.right);
		}
	}
	
	public void clear() {
		root = null;
	}
	
	public String toString() {
		StringBuffer buff = new StringBuffer();
		printTree(buff, root, 0);
		return buff.toString();
	}
	
	public void printTree(StringBuffer buff, BinaryNode <E> t, int level) {
		
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

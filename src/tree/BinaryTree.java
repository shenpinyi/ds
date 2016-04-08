package tree;

import java.util.ArrayList;
import java.util.List;

public class BinaryTree <E extends Comparable <? super E>> {

	BinaryNode <E> root;
	
	public BinaryTree(List <E> elements) {
		List <E> l = new ArrayList<E> (elements);
		root = addList(l);
	}
	
	public BinaryNode <E> addList(List <E> l) {
		if (l.isEmpty()) {
			return null;
		}
		
		E e = l.remove(0);
		
		if (e == null){
			return null;
		}
		
		BinaryNode <E> node = new BinaryNode <> ();
		node.element = e;
		
		node.left = addList(l);
		node.right = addList(l);
		return node;
	}
	
	public String preorder() {
		
		StringBuffer buff = new StringBuffer();
		preorder(root, buff);
		return buff.toString();
	}

	public void preorder(BinaryNode<E> t, StringBuffer buff) {
		
		if(t == null) {
			return;
		}
		
		buff.append(t.element + " ");
		preorder(t.left, buff);
		preorder(t.right, buff);
	}

	public String midorder() {
		
		StringBuffer buff = new StringBuffer();
		midorder(root, buff);
		return buff.toString();
	}

	public void midorder(BinaryNode<E> t, StringBuffer buff) {
		
		if(t == null) {
			return;
		}
		
		midorder(t.left, buff);
		buff.append(t.element + " ");
		midorder(t.right, buff);
	}

	public String postorder() {
		
		StringBuffer buff = new StringBuffer();
		postorder(root, buff);
		return buff.toString();
	}

	public void postorder(BinaryNode<E> t, StringBuffer buff) {
		
		if(t == null) {
			return;
		}
		
		postorder(t.left, buff);
		postorder(t.right, buff);
		buff.append(t.element + " ");
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

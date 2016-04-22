package tree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class MyBplusTree <E> implements Set <E>{
	
	Node root;
	int size;
	
	class Node {
		List<E> elements;
		List<Node> sons;
		
		public Node(){
			elements = new ArrayList<E>();
			sons = new ArrayList<Node>();
		}
	}
	
	public MyBplusTree(){
		root = null;
		size = 0;
	}
	

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
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
		// TODO Auto-generated method stub
		return false;
	}
	
	private Node add(E e, Node t) {
		if (t == null) {
			Node n = new Node();
			n.elements.add(e);
			return n;
		}
		
		
		
		
		
		return null;
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

}

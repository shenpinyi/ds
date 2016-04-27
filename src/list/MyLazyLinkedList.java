package list;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MyLazyLinkedList <T> implements List <T>{
	
	private class Node {
		private T item;
		private Boolean b;
		public Node next;
		public Node prev;
		
		public Node() {
			item = (T) new Object();
			b = true;
			next = null;
			prev = null;
		}
		
		public Node(T t) {
			item = t;
			b = true;
			next = null;
			prev = null;
		}
	} 

	// Available items in list
	private int size = 0;
	
	private int deleteCount = 0;
	
	// All items in list, including deleted
	private int length = 0;
	
	private Node head;
	
	private Node tail;
	
	private final static int DEFAULT_CAPACITY = 10;
	
	public MyLazyLinkedList(){
		size = 0;
		length = 0;
		deleteCount = 0;
		head = new Node();
		tail = new Node();
		head.next = tail;
		tail.prev = head;
	}
	
	private void checkAddBound(int index){
		if (index > size) {
			throw new RuntimeException("Index out of bound");
		}
	}
	
	private void checkBound(int index){
		if (index > size - 1) {
			throw new RuntimeException("Index out of bound");
		}
	}
	
	
	private Node getNode(int index){
		checkAddBound(index);
		Node n = head;
		int i = -1;
		while(i < index) {
			n = n.next;
			if (n.b == true){
				i ++;
			} else {
				continue;
			}
		}
		return n;
	}

	private Node getNode(Object o){
		int i = -1;
		Node n = head;
		while(i < size - 1){
			n = n.next;
			if (n.b == true) {
				i ++;
				if (n.item.equals(o)) {
					return n;
				}
			} else {
				continue;
			}
			
		}
		return null;
	}
	
	private void addNode(int index, T item){
		Node n = getNode(index);
		if (n == null) {
			return;
		}
		addBefore(n, item);
	}
	
	private void addBefore(Node n, T item){
		if (n.prev.b == false) {
			n.prev.item = item;
			n.prev.b = true;
			deleteCount --;
		} else {
			Node newNode = new Node(item);
			newNode.prev = n.prev;
			newNode.next = n;
			n.prev.next = newNode;
			n.prev = newNode;
		}
		size ++;
	}
	
	private void removeNode(Node n) {
		n.prev.next = n.next;
		n.next.prev = n.prev;
	}

	private void deleteNode(Node n) {
		n.b = false;
		deleteCount ++;
		size --;
		if (deleteCount < size) {
			return;
		}
		
	    Node nd = head.next;
		while (nd != tail) {
			if (nd.b == false) {
				removeNode(nd);
			}
			
			nd = nd.next;
		}
		
		deleteCount = 0;
	}

	
	@Override
	public boolean add(T e) {
		addBefore(tail, e);
		return true;
	}
	
	@Override
	public void add(int index, T element) {
		if (index > size) {
			throw new RuntimeException("index out of bound");
		}
		addNode(index, element);
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
		
		return false;
	}

	@Override
	public Iterator<T> iterator() {
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
	public boolean addAll(Collection<? extends T> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(int index, Collection<? extends T> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public T get(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T set(int index, T element) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public T remove(int index) {
		checkBound(index);
		Node n = getNode(index);
		deleteNode(n);
		return n.item;
	}

	@Override
	public int indexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int lastIndexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ListIterator<T> listIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListIterator<T> listIterator(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> subList(int fromIndex, int toIndex) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String toString(){
		StringBuffer buff = new StringBuffer();
		buff.append("head,");
		Node n = head;
		while (n.next != tail) {
			n = n.next;
			if (n.b == true){
				buff.append(n.item);
			} else {
				buff.append("(*" + n.item + ")");
			}
			buff.append(",");
		}
		buff.append("tail");
		return buff.toString();
	}

}

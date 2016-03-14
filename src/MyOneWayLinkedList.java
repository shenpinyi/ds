import java.util.Iterator;

public class MyOneWayLinkedList  <T> implements Iterable <T>{
	
	Node <T> head;
	
	@SuppressWarnings("hiding")
	private class Node <T> {
		Node <T> next;
		T item;
		
		Node() {
			item = null;
			next = null;
		}
		
		Node(T t) {
			item = t;
			next = null;
		}
	}

	MyOneWayLinkedList(){
		head = new Node<T>();
	}
	
	public boolean add(T t){
		
		if (contains(t) != -1) {
			return false;
		}
		Node<T> n = new Node<T>(t);
		n.next = head.next;
		head.next = n;
		return true;
	}
	
	public int size(){
		int size = 0;
		Node <T> current = head;
		while (current.next != null) {
			current = current.next;
			size++;
		}
		return size;
	}
	
	public int contains(T t){
		Node <T> current = head;
		int idx = -1;
		while (current.next != null) {
			current = current.next;
			idx++;
			if (current.item.equals(t)) {
				return idx;
			}
		}
		return -1;
		
	}
	
	public int remove(T t){
		Node <T> current = head, previous = null;
		int idx = -1;
		while (current.next != null) {
			previous = current;
			current = current.next;
			idx++;
			if (current.item.equals(t)) {
				removeBehind(previous);
				return idx;
			}
		}
		return -1;
	}
	
	private void removeBehind(Node <T> n) {
		n.next = n.next.next;
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	public String toString(){
		StringBuffer buff = new StringBuffer();
		Node<T> n = head;
		buff.append("head->");
		while(n.next != null){
			n = n.next;
			buff.append(n.item + "->");
		}
		buff.append("null");
		
		return buff.toString();
	}
}

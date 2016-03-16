import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class MyLinkedList<T> implements Iterable<T> {

	private int size;
	private Node<T> head;
	private Node<T> tail;

	@SuppressWarnings("hiding")
	private class Node<T> {
		Node<T> next;
		Node<T> prev;
		T item;
	}

	public MyLinkedList() {
		clear();
	}

	public MyLinkedList(T[] ts) {
		clear();
		for (int i = 0; i < ts.length; i++) {
			add(ts[i]);
		}
	}

	public void clear() {
		head = new Node<T>();
		tail = new Node<T>();
		head.next = tail;
		tail.prev = head;
		head.prev = null;
		tail.next = null;
		size = 0;
	}

	public int size() {
		return size;
	}

	public boolean add(T t) {
		return add(size, t);
	}

	public boolean add(int idx, T t) {
		Node<T> node = new Node<T>();
		node.item = t;
		Node<T> current = find(idx);
		current.prev.next = node;
		node.prev = current.prev;
		node.next = current;
		current.prev = node;
		size++;
		return true;
	}

	private Node<T> find(int idx) {
		int i = -1;
		Node<T> n = head;
		while (i < idx && i < size) {
			n = n.next;
			i++;
		}
		return n;
	}

	private void remove(T t) {
		int i = -1;
		Node<T> n = head;
		while (i < size) {
			n = n.next;
			i++;
			if (n.item.equals(t)) {
				removeNode(n);
			}
		}
	}

	public boolean remove(int idx) {
		if (idx > size - 1) {
			return false;
		}

		Node<T> n = find(idx);
		return removeNode(n);
	}

	public boolean removeNode(Node<T> n) {
		n.next.prev = n.prev;
		n.prev.next = n.next;
		size--;
		return true;
	}

	public T get(int idx) {
		if (idx > size - 1) {
			return null;
		}

		return find(idx).item;
	}

	public boolean set(int idx, T t) {
		if (idx > size - 1) {
			return false;
		}
		Node<T> n = find(idx);
		n.item = t;
		return true;
	}

	public int contains(T t) {
		Node<T> n = head;
		for (int i = 0; i < size; i++) {
			n = n.next;
			if (n.item.equals(t)) {
				return i;
			}
		}
		return -1;
	}

	public Iterator<T> iterator() {
		return new LinkedListIterator();
	}

	public ListIterator<T> listIterator() {
		return new LinkedListIterator();
	}

	public void addAll(Iterable<? extends T> items) {
		for (T t : items) {
			add(t);
		}
	}

	public void removeAll(Iterable<? extends T> items) {
		for (T t : items) {
			remove(t);
		}
	}
	
	public void splice(Iterator <T> itr, MyLinkedList <? extends T> lst) {
		LinkedListIterator litr = (MyLinkedList.LinkedListIterator) itr;
	    Node <T> cursor = litr.getCursor();
	    if ( this == lst) { 
	    	return; 
	    }
	    size += lst.size;
	    
	    Node <T> lstHead = (MyLinkedList<T>.Node<T>) lst.head;
	    Node <T> lstTail = (MyLinkedList<T>.Node<T>) lst.tail;
	    cursor.prev.next = lstHead.next;
	    cursor.prev.next.prev = cursor.prev;
	    lstTail.prev.next = cursor;
	    cursor.prev = lstTail.prev;
	    lst.clear();
	}
	

	public class LinkedListIterator implements ListIterator<T> {

		Node<T> cursor = head.next;
		int cursorIndex = 0;
		Node<T> lastRet = null;
		
		public Node<T> getCursor() {
			return cursor;
		}

		@Override
		public boolean hasNext() {
			return cursor != tail;
		}

		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}

			lastRet = cursor;
			cursor = cursor.next;
			cursorIndex ++;
			return lastRet.item;
		}

		public void remove() {
			if (lastRet == null) {
				throw new IllegalStateException();
			}
			removeNode(lastRet);
			cursorIndex --;
			lastRet = null;
		}

		@Override
		public boolean hasPrevious() {
			return cursor.prev != head;
		}

		@Override
		public T previous() {
			if (!hasPrevious()){
				throw new NoSuchElementException();
			}
			
			lastRet = cursor.prev;
			cursor = cursor.prev;
			cursorIndex --;
			return lastRet.item;
		}

		@Override
		public int nextIndex() {
			return cursorIndex;
		}

		@Override
		public int previousIndex() {
			return cursorIndex - 1;
		}

		@Override
		public void set(T e) {
			if ( lastRet == null) {
				throw new IllegalStateException(); 
			}
			
			lastRet.item = e;
		}

		@Override
		public void add(T e) {
			MyLinkedList.this.add(cursorIndex, e);
			cursorIndex ++;
		}

	}

	public String toString() {
		StringBuffer buff = new StringBuffer();
		Node<T> n = head;
		buff.append("head->");
		// from head to tail
		for (int i = 0; i < size; i++) {
			n = n.next;
			buff.append(n.item + "->");
		}
		n = n.next;
		if (n == tail) {
			buff.append("tail->");
		}

		// from tail to head
		for (int i = 0; i < size; i++) {
			n = n.prev;
			buff.append(n.item + "->");
		}
		n = n.prev;
		if (n == head) {
			buff.append("head");
		}

		return buff.toString();
	}

}

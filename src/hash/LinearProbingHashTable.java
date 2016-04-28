package hash;

import java.util.LinkedList;
import java.util.List;

public class LinearProbingHashTable <T> implements MyHashTable <T> {
	
	private final int DEF_TABLE_SIZE = 10;
	private final double MAX_FILL_FACTOR = 0.5;
	private int currentSize = 0;
	private HashEntry<T>[] elements;
	private long capacity;
	
	@SuppressWarnings("hiding")
	class HashEntry <T> {
		T element;
		boolean isActive;
		
		public HashEntry() {
			isActive = true;
			element = null;
		}
		
		public String toString() {
			return element + ":" + isActive;
		}
	}
	
	
	public LinearProbingHashTable() {
		capacity = DEF_TABLE_SIZE;
		makeEmpty();
	}

	public LinearProbingHashTable(int size) {
		capacity = size;
		makeEmpty();
	}
	
	public int getIdx(T t) {
		int hashVal = t.hashCode();
		
		hashVal %= elements.length;
		
		if (hashVal < 0 ) {
			hashVal += elements.length;
		}
		
		HashEntry <T> e = elements[hashVal];
		int idx = -1;
		
		while (e != null) {
			if (e.isActive == true && e.element.equals(t)) {
				return hashVal;
			}
			
			if (e.isActive == false && idx == -1) {
				idx = hashVal;
			}
			
			hashVal ++;
			hashVal %= elements.length;
			
			e = elements[hashVal];
		}
		
		return idx == -1 ? hashVal : idx;
	}
	
	
	@Override
	public void insert(T t) {
		int idx = getIdx(t);
		
		if (elements[idx] == null) {
			elements[idx] = new HashEntry<T>();
		}
		
		elements[idx].element = t;
		elements[idx].isActive = true;
	}

	@Override
	public void remove(T t) {
        int idx = getIdx(t);
		
		if (elements[idx] == null) {
			return;
		}
		
		elements[idx].element = null;
		elements[idx].isActive = false;
		
	}

	@Override
	public boolean contains(T t) {
        int idx = getIdx(t);
		
		if (elements[idx] == null || elements[idx].isActive == false 
				|| elements[idx].element == null 
				|| !elements[idx].element.equals(t)) {
			return false;
		}
		
		return true;
	}

	@Override
	public void makeEmpty() {
		elements = new HashEntry[(int) capacity];
	}
	
	public void rehash(long cap) {
		capacity = cap;
		HashEntry[] oldElements = elements;
		elements = new HashEntry[(int) capacity];
		currentSize = 0;
		
		for (int i = 0; i < oldElements.length; i++) {
			if (oldElements[i] != null && oldElements[i].isActive) {
				insert((T) oldElements[i].element);
			}
		}
	}
	
	public String toString() {
		StringBuffer buff = new StringBuffer();
		
		for (int i = 0; i < elements.length; i++) {
			if (elements[i] != null) {
				buff.append("(" + i + ")");
				buff.append(elements[i]);
				buff.append("\r\n");
			}
		}
		return buff.toString();
	}

}

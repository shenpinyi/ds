package hash;

import java.util.LinkedList;
import java.util.List;

import utils.PrimeNumber;

public class SeparateChainingHashTable <T> implements MyHashTable <T> {
	
	private final int DEF_HASH_TABLE_SIZE = 10;
	private final double MAX_FILL_FACTOR = 0.5;
	private long capacity;
	private long currentSize = 0;
	
	private List<Object>[] elements;

	@SuppressWarnings("unchecked")
	public SeparateChainingHashTable() {
		capacity = DEF_HASH_TABLE_SIZE;
		makeEmpty();
	}
	
	public SeparateChainingHashTable( int size ) {
		capacity = size;
		makeEmpty();
	}

	@Override
	public void insert(T t) {
		int idx = getIdx(t);
		List<Object> list = elements[idx];

		if (list == null) {
			list = new LinkedList<Object>();
			elements[idx] = list;
		}

		if (list.contains(t)) {
			return;
		}
		
		currentSize ++;
		list.add(0, t);
		//checkHash();
	}
	
	private void checkHash() {
		double factor = ((double) currentSize) / ((double) capacity);
		
		if (factor >= MAX_FILL_FACTOR) {
			rehash(PrimeNumber.get(capacity));
		}
	}
	
	public void rehash(long cap) {
		capacity = cap;
		List<Object>[] oldElements = elements;
		elements = new LinkedList[(int) capacity];
		currentSize = 0;
		
		for (int i = 0; i < oldElements.length; i++) {
			if (oldElements[i] != null) {
				for (Object t : oldElements[i]) {
					insert((T) t);
				}
			}
		}
	}
	
	private int getIdx(T t) {
		int hashVal = t.hashCode();
		
		hashVal %= elements.length;
		
		if (hashVal < 0) {
			hashVal += elements.length;
		}
		
		return hashVal;
	}
	
	@Override
	public void remove(T t) {
		
		int idx = getIdx(t);
		List<Object> list = elements[idx];
		
		if (list == null) {
			return;
		}
		
		list.remove(t);
		currentSize --;
	}

	@Override
	public boolean contains(T t) {
		int idx = getIdx(t);
		List<Object> list = elements[idx];
		
		if (list == null) {
			return false;
		}
		
		return list.contains(t);
	}

	@Override
	public void makeEmpty() {
		elements = new LinkedList[(int) capacity];
		currentSize = 0;
	}
	
	public String toString() {
		StringBuffer buff = new StringBuffer();
		buff.append("Capacity:" + capacity + "\r\n");
		buff.append("CurrentSize:" + currentSize + "\r\n");
		for (int i = 0; i < elements.length; i++) {
			List<Object> list = elements[i];
			if (list != null) {
				buff.append("(" + i + ") ");
				buff.append(list);
				buff.append("\r\n");
			} else {
				buff.append("(" + i + ") ");
				buff.append("null");
				buff.append("\r\n");
			}
		}
		return buff.toString();
	}
	
}

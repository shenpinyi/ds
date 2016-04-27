package hash;

import java.util.LinkedList;
import java.util.List;

public class SeparateChainingHashTable <T> implements MyHashTable <T> {
	
	private int DEF_HASH_TABLE_SIZE = 10;
	private int currentSize;
	
	private List<Object>[] elements;

	@SuppressWarnings("unchecked")
	public SeparateChainingHashTable() {
		currentSize = DEF_HASH_TABLE_SIZE;
		makeEmpty();
	}
	
	public SeparateChainingHashTable( int size ) {
		currentSize = size;
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
		
		list.add(0, t);
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
		elements = new LinkedList[currentSize];
	}
	
	public String toString() {
		StringBuffer buff = new StringBuffer();
		
		for (int i = 0; i < elements.length; i++) {
			List<Object> list = elements[i];
			if (list != null) {
				buff.append("(" + i + ")");
				buff.append(list);
				buff.append("\r\n");
			}
		}
		return buff.toString();
	}
	
}

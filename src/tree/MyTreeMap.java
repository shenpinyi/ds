package tree;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MyTreeMap <K, V> implements Map <K, V>, Iterable <K>{
	int size = 0;
	Entry root = null;
	
	Entry head = null;
	Entry tail = null;
	
	Comparator <K> comparator;
	
	public MyTreeMap(Comparator <K> p){
		this();
		comparator = p;
	}
	
	public MyTreeMap(){
		head = new Entry();
		tail = new Entry();
		head.next = tail;
		tail.prev = head;
	}
	
	class MyTreeMapIterator implements Iterator <K> {
		
		Entry prev = null;
		Entry next = head.next;

		@Override
		public boolean hasNext() {
			return next != tail;
		}

		@Override
		public K next() {
			if (!hasNext()) { return null;}
			prev = next;
			next = next.next;
			return prev.key;
		}
	} 
	
	class Entry implements Map.Entry<K, V> {
		K key;
		V value;
		
		Entry left;
		Entry right;
		Entry parent;
		
		Entry next;
		Entry prev;
		
		@Override
		public K getKey() {
			return key;
		}
		@Override
		public V getValue() {
			return value;
		}
		@Override
		public V setValue(V value) {
			this.value = value;
			return value;
		}
		@Override
		public String toString() {
			return "[" + key + ", " + value + "]";
		}
		
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean containsKey(Object key) {
		return findByKey((K) key, root) != null;
	}
	
	
	@SuppressWarnings("unchecked")
	private int compare(K key1, K key2) {
		if (comparator != null) {
			return comparator.compare(key1, key2);
		} else {
			return ((Comparable <K>) key1).compareTo(key2);
		}
	}
	
	
	private Entry findByKey(K key, Entry t) {
		if (t == null) {
			return null;
		}
		
		int result = compare(key, t.getKey());
		if (result == 0) {
			return t;
		} else if (result > 0) {
			return findByKey(key, t.right);
		} else {
			return findByKey(key, t.left);
		}
	}

	@Override
	public boolean containsValue(Object value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public V get(Object key) {
		@SuppressWarnings("unchecked")
		Entry e = findByKey((K) key, root);
		return e != null ? e.value : null;
	}

	@Override
	public V put(K key, V value) {
		V oldValue = null;
		Entry parent = null;
		Entry t = root;
		int flag = 0;
		
		while (t != null) {
			int result = compare(key, t.key);
			if (result == 0) {
				oldValue = t.value;
				t.value = value;
				return oldValue;
			} else if (result > 0 ) {
				parent = t;
				t = t.right;
				flag = 1;
			} else {
				parent = t;
				t = t.left;
				flag = -1;
			}
		}
		
		//add entry to tree
		Entry e = new Entry();
		e.key = key;
		e.value = value;
		e.right = null;
		e.left = null;
		e.parent = parent;
		if (flag == 1) {
			parent.right = e;
		} else if (flag == -1) {
			parent.left = e;
		} else {
			root = e;
		}
		
		/*add entry to list
		 *because the entry's parent is largest one among the entries 
		 * smaller than this entry (if entry is right of parent)
		 * or the least one among the entries larger than this entry
		 * (if entry is left of parent) 
		 * so, just add this entry behind or before the parent
		 * if the parent is null, add this entry behind head*/
		if (parent == null){
			e.prev = head;
			e.next = tail;
			tail.prev = e;
			head.next = e;
		} else if (flag == 1){ 
			//right branch
			e.prev = parent;
			e.next = parent.next;
			parent.next.prev = e;
			parent.next = e;
		} else {
			//left branch
			e.prev = parent.prev;
			e.next = parent;
			parent.prev.next = e;
			parent.prev = e;
		}
		
		size ++;
		return null;
	}

	@Override
	public V remove(Object key) {
		// TODO Auto-generated method stub
		V oldValue = null;
		Entry parent = null;
		Entry t = root;
		int flag = 0;
		
		while (t != null) {
			@SuppressWarnings("unchecked")
			int result = compare((K) key, t.key);
			if (result == 0) {
				break;
			} else if (result > 0 ) {
				parent = t;
				t = t.right;
				flag = 1;  //right to parent
			} else {
				parent = t;
				t = t.left;
				flag = -1; //left to parent
			}
		}
		
		if (t == null){ //goes to the leaf, not found
			return null;
		}
		
		oldValue = t.value;
		
		if (t.right == null && t.left == null) {
			if (flag == 1) {
				parent.right = t.left;
			} else if (flag == -1) {
				parent.left = t.left;
			} else {
				root = t.left;
			}
			
			t.prev.next = t.next;
			t.next.prev = t.prev;

			size --;
		} else if (t.right == null) {
			if (flag == 1) {
				parent.right = t.left;
			} else if (flag == -1) {
				parent.left = t.left;
			} else {
				root = t.left;
			}
			t.left.parent = parent;
			
			t.prev.next = t.next;
			t.next.prev = t.prev;
			
			size --;
		} else if (t.left == null) {
			if (flag == 1) {
				parent.right = t.right;
			} else if (flag == -1) {
				parent.left = t.right;
			} else {
				root = t.right;
			}
			t.right.parent = parent;

			t.prev.next = t.next;
			t.next.prev = t.prev;

			size --;
		} else {
			
			@SuppressWarnings("unchecked")
			Entry replace = findMin((K) key, t.right);
			remove(replace.key);
			t.key = replace.key;
			t.value = replace.value;
		}
		
		return oldValue;
	}
	
	private Entry findMin(K key, Entry t) {
		int result = compare((K) key, t.key);
		if (result == 0) {
			return t;
		} else if (result > 0 ) {
			if (t.right == null) {
				return t;
			}
			return findMin(key, t.right);
		} else {
			if (t.left == null) {
				return t;
			}
			return findMin(key, t.left);
		}
	}
	

	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<K> keySet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<V> values() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<java.util.Map.Entry<K, V>> entrySet() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String toString() {
		StringBuffer buff = new StringBuffer();
		printTree(root, 0, "T:", buff);
		return buff.toString();
	}
	
	private void printTree(Entry e, int level, String pos, StringBuffer buff){
		if (e == null){
			printSp(level, buff);
			buff.append(pos + "null\r\n");
			return;
		}
		printTree(e.right, level + 1, "R:", buff);

		printSp(level, buff);
		buff.append(pos + e + "<\r\n");

		printTree(e.left, level + 1, "L:", buff);
	}
	
	private void printSp(int level, StringBuffer buff) {
		for (int i = 0; i < level; i ++) {
			buff.append("            ");
		}
	}

	@Override
	public Iterator<K> iterator() {
		return new MyTreeMapIterator();
	}
	
	


}

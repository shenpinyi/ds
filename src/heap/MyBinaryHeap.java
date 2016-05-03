package heap;

import java.util.Arrays;

public class MyBinaryHeap <T extends Comparable <? super T>>{

	private Object[] elements;
	private final static int DEF_QUEUE_CAP = 3;
	private int currentSize;
	
	public MyBinaryHeap() {
		elements = new Object[DEF_QUEUE_CAP];
		currentSize = 0;
	}
	
	public MyBinaryHeap(int capacity) {
		elements = new Object[capacity];
		currentSize = 0;
	}
	
	public MyBinaryHeap(T[] items) {
		int cap = (int) Math.pow(2, Math.ceil(Math.log(items.length + 1)/ Math.log(2))) - 1;
		currentSize = items.length;
		elements = Arrays.copyOf(items, cap);
		buildHeap();
	}
	
	public void insert(T t) {
		int hole = currentSize;
		currentSize ++;
		
		if (hole >= elements.length) {
			int cap = (int) Math.pow(2, Math.ceil(Math.log(elements.length + 1 + 1)/ Math.log(2))) - 1;
			enlargeArray(cap);
		}
		
		while ((hole + 1) /2 != 0) {
			@SuppressWarnings("unchecked")
			T parent = (T) elements[(hole + 1) / 2 - 1];
			if (parent.compareTo(t) > 0) {
				elements[hole] = parent;
				hole = (hole + 1) / 2 - 1;
			} else {
				break;
			}
		}
		
		elements[hole] = t;
	}
	
	@SuppressWarnings("unchecked")
	public T findMin() {
		
		if (currentSize == 0) {
			return null;
		}
		
		return (T) elements[0];
	}
	
	public T deleteMin() {
		
		if (currentSize == 0){
			return null;
		} 
		
		@SuppressWarnings("unchecked")
		T current = (T) elements[1];
		
		if (currentSize == 1){
			elements[currentSize --] = null;
			return current; 
		}

		// make a hole and place the last item of heap to the hole
		elements[0] = elements[currentSize - 1];
		currentSize --;
		percolateDown(1);
		return current;
	}
	
	public boolean isEmpty() {
		return currentSize == 0;
	}
	
	public void makeEmpty() {
		currentSize = 0;
	}
	
	
	// hole is index of array + 1
	@SuppressWarnings("unchecked")
	private void percolateDown(int hole) {
		T current = (T) elements[hole -1];

		while (true) {
			if (hole == currentSize) {
				break;
			}

			int left = hole * 2;
			int right = hole * 2 + 1;
			
			if (left > currentSize) {
				// the hole is a leaf
				break;
			} else if (right > currentSize) {
				// the hole only has left leaf
				if (current.compareTo((T) elements[left - 1]) > 0) {
					// target item is bigger, should go down
					elements[hole - 1] = elements[left - 1];
					hole = left;
				} else {
					// target item is smaller, stop here
					break;
				}
			} else {
				int compare = ((T) elements[left - 1]).compareTo(((T) elements[right - 1]));
				if (compare < 0) {
					//left is smaller
					if (current.compareTo((T) elements[left - 1]) > 0) {
						elements[hole - 1] = elements[left - 1];
						hole = left;
					} else {
						break;
					}
				} else {
					//right is smaller
					if (current.compareTo((T) elements[right - 1]) > 0) {
						elements[hole - 1] = elements[right - 1];
						hole = right;
					} else {
						break;
					}
				}
			}
		}
		// fill the target item in hole
		elements[hole -1] = current;
	}
	
	private void buildHeap() {
		for (int i = currentSize / 2; i > 0; i--) {
			percolateDown(i);
		}
	}
	
	private void enlargeArray(int newSize) {
		if (newSize < currentSize) {
			return;
		}
		
		elements = Arrays.copyOf(elements, newSize);
	}
	
	public String toString() {
		StringBuffer buff = new StringBuffer();
		for (int i = 0; i < currentSize; i ++) {
			buff.append(elements[i] + ",");
		}
		return buff.toString();
	}

}

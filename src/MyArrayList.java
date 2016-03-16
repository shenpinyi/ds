import java.util.Arrays;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class MyArrayList <T> implements List <T>{

	private class MyArrayListIterator implements ListIterator <T> {
		
		int cursor = 0;
		int lastRet = -1;
		int modiCountItr = -1;
		
		public MyArrayListIterator(){
			cursor = 0;
			lastRet = -1;
			modiCountItr = MyArrayList.this.modiCount;
		}
		
		@Override
		public void add(T e) {

			MyArrayList.this.add(cursor, e);
			lastRet = cursor;
			cursor++;
			incModiCountItr();
		}
		
		private boolean checkModiCount(){
			return modiCountItr == MyArrayList.this.modiCount;
		}

		@Override
		public boolean hasNext() {
			return cursor != size;
		}

		@Override
		public boolean hasPrevious() {
			return cursor - 1 >= 0;
		}

		private void incModiCountItr(){
			modiCountItr ++;
		}

		@SuppressWarnings("unchecked")
		@Override
		public T next() {
			if (!checkModiCount()){
				throw new ConcurrentModificationException();
			}
			
			lastRet = cursor;
			return (T) l[cursor++];
		}

		@Override
		public int nextIndex() {
			return cursor;
		}

		@SuppressWarnings("unchecked")
		@Override
		public T previous() {
			if (!checkModiCount()){
				throw new ConcurrentModificationException();
			}
			if (cursor == 0) {
				throw new NoSuchElementException();
			}
			cursor--;
			lastRet = cursor;
			return (T) l[cursor];
		}

		@Override
		public int previousIndex() {
			return cursor - 1;
		}

		@Override
		public void remove() {
			if (!checkModiCount()){
				throw new ConcurrentModificationException();
			}

			if (lastRet == -1) {
				throw new IllegalStateException();
			}
			MyArrayList.this.remove(lastRet);
			lastRet = -1;
			cursor--;
			incModiCountItr();
			
		}

		@Override
		public void set(T e) {
			if (!checkModiCount()){
				throw new ConcurrentModificationException();
			}

			if (lastRet == -1) {
				throw new IllegalStateException();
			}
			MyArrayList.this.set(lastRet, e);
		}
		
	}
	
	private class MyArrayListReverseIterator implements ListIterator <T> {
		
		int cursor = size - 1;
		int lastRet = -1;
		int modiCountItr = -1;
		
		public MyArrayListReverseIterator(){
			cursor = size - 1;
			lastRet = -1;
			modiCountItr = MyArrayList.this.modiCount;
		}
		@Override
		public void add(T e) {

			if (!checkModiCount()){
				throw new ConcurrentModificationException();
			}

			MyArrayList.this.add(cursor + 1, e);
			lastRet = cursor + 1;
			incModiCountItr();
		}
		
		private boolean checkModiCount(){
			return modiCountItr == MyArrayList.this.modiCount;
		}
		@Override
		public boolean hasNext() {
			return cursor != 0;
		}

		@Override
		public boolean hasPrevious() {
			return cursor < size - 1;
		}

		private void incModiCountItr(){
			modiCountItr ++;
		}

		@SuppressWarnings("unchecked")
		@Override
		public T next() {
			if (!checkModiCount()){
				throw new ConcurrentModificationException();
			}

			lastRet = cursor;
			return (T) l[cursor--];
		}

		@Override
		public int nextIndex() {
			return cursor;
		}

		@SuppressWarnings("unchecked")
		@Override
		public T previous() {
			if (!checkModiCount()){
				throw new ConcurrentModificationException();
			}

			if (cursor == size - 1) {
				throw new NoSuchElementException();
			}
			cursor ++;
			lastRet = cursor;
			return (T) l[cursor];
		}

		@Override
		public int previousIndex() {
			return cursor + 1;
		}

		@Override
		public void remove() {
			if (!checkModiCount()){
				throw new ConcurrentModificationException();
			}

			if (lastRet == -1) {
				throw new IllegalStateException();
			}
			MyArrayList.this.remove(lastRet);
			lastRet = -1;
			incModiCountItr();
		}

		@Override
		public void set(T e) {
			if (!checkModiCount()){
				throw new ConcurrentModificationException();
			}

			if (lastRet == -1) {
				throw new IllegalStateException();
			}
			MyArrayList.this.set(lastRet, e);
		}
		
	}

	
	final static int DEF_CAPACITY = 10;
	
	int size = 0;
	
	int modiCount = 0;
	
	@SuppressWarnings("unchecked")
	T[] l = (T[]) new Object[DEF_CAPACITY];

	
	@Override
	public void add(int index, T element) {
		if (size == l.length) {
			ensureCapacity(l.length * 2);
		}
		for (int i = size; i > index; i--){
			l[i] = l[i-1];
		}
		l[index] = element;
		size ++;
		incModiCount();
		
	}
	
	@Override
	public boolean add(T e) {
		if (size == l.length) {
			ensureCapacity(l.length * 2);
		}
		l[size] = e;
		size ++;
		incModiCount();
		return true;
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		if (c == null){
			return false;
		}
		
		for (T t : c){
			add(t);
		}
		incModiCount();
		return true;
	}

	@Override
	public boolean addAll(int index, Collection<? extends T> c) {
		// TODO Auto-generated method stub
		incModiCount();
		return false;
	}

	@Override
	public void clear() {
		size = 0;
		incModiCount();
	}

	@Override
	public boolean contains(Object o) {
		for (int i = 0; i < size; i++){
			if (l[i].equals(o)){
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		for (Object o : c) {
			if (!contains(o)) {
				return false;
			}
		}
		return true;
	}

	public void ensureCapacity(int newLength){
		if (l.length >= newLength){
			return;
		}
		l = Arrays.copyOf(l, newLength);
	}

	@Override
	public T get(int index) {
		if (index > size - 1 ) {
			throw new IndexOutOfBoundsException();
		}
		return l[index];
	}

	private void incModiCount(){
		modiCount++;
	}

	@Override
	public int indexOf(Object o) {
		for (int i = 0; i < size - 1; i++) {
			if (l[i].equals(o)) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int lastIndexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ListIterator<T> listIterator() {
		return new MyArrayListIterator();
	}

	@Override
	public ListIterator<T> listIterator(int index) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public T remove(int index) {
		T t = null;
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		t = l[index];
		for (int i = index; i < size-1; i++) {
			l[i] = l[i+1];
		}
		incModiCount();
		return t;
	} 

	@Override
	public boolean remove(Object o) {
		int idx = indexOf(o);
		if (idx == -1){
			return false;
		}
		remove(idx);
		incModiCount();
		return true;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		if (c == null) {
			return false;
		}
		
		for (Object o : c) {
			remove(o);
		}
		incModiCount();
		return true;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	public ListIterator<T> reverseListIterator() {
		return new MyArrayListReverseIterator();
	}

	@Override
	public T set(int index, T e) {
		T t = null;
		if (index >= size){
			return null;
		} else {
			t = l[index];
			l[index] = e;
			return e;
		}
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public List<T> subList(int fromIndex, int toIndex) {
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
	
	public String toString(){
		StringBuffer buff = new StringBuffer();
		buff.append("Total length: " + l.length + "; ");
		buff.append("Actual size: " + size + "; ");
		for (int i = 0; i < size; i++){
			buff.append(l[i] + " ");
		}
		return buff.toString();
	}

}

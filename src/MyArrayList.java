import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MyArrayList <T> implements List <T>{

	final static int DEF_CAPACITY = 10;
	int size = 0;
	
	@SuppressWarnings("unchecked")
	T[] l = (T[]) new Object[DEF_CAPACITY];
	
	@Override
	public boolean add(T e) {
		if (size == l.length) {
			ensureCapacity(l.length * 2);
		}
		l[size] = e;
		size ++;
		return true;
	}
	
	public void ensureCapacity(int newLength){
		if (l.length >= newLength){
			return;
		}
		l = Arrays.copyOf(l, newLength);
	}

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
		
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		if (c == null){
			return false;
		}
		
		for (T t : c){
			add(t);
		}
		return true;
	}

	@Override
	public boolean addAll(int index, Collection<? extends T> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		size = 0;
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public T get(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int indexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListIterator<T> listIterator(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public T remove(int index) {
		// TODO Auto-generated method stub
		return null;
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
	public T set(int index, T element) {
		if (index >= size){
			return null;
		} else {
			l[index] = element;
			return element;
		}
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
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

package hash;

public interface MyHashTable <T> {
	public void insert (T t);
	public void remove(T t);
	public boolean contains(T t);
	public void makeEmpty();
}

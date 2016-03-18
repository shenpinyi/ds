import java.util.NoSuchElementException;

public class MyStack <T> extends MyArrayList <T> {

	
	
	public void push(T t) {
		super.add(t);
	}
	
	public T pop() {
		if (size == 0){
			throw new NoSuchElementException ();
		}
		return super.remove(size - 1);
	}
	
	public T peek() {
		if (size == 0){
			return null;
		}
		return super.get(size - 1);
	}
	
}

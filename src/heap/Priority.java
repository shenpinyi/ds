package heap;

public interface Priority <T> {
	public T increase(T t, int diff);
	public T decrease(T t, int diff);
}

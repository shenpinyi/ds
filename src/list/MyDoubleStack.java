package list;

public class MyDoubleStack <T> {
    Object[] items;
    private int size, topa, topb;
    private final int DEFAULT_SIZE = 20;
    private MyStackIntf <T> sa;
    private MyStackIntf <T> sb;
    
    public MyDoubleStack(){
    	size = DEFAULT_SIZE;
    	topa = -1;
    	topb = size;
    	items = new Object[size];
    	sa = new InnerStackA ();
    	sb = new InnerStackB ();
    }
    
    public MyStackIntf <T> getStackA() {
    	return sa;
    }
    
    public MyStackIntf <T> getStackB() {
    	return sb;
    }
    
    private class InnerStackA implements MyStackIntf <T> {
    	
    	@Override
		public T pop() {
			if (topa == -1) {
				return null;
			} else {
				return (T) items[topa--];
			}
		}

		@Override
		public void push(T t) {
			if (topa + 1 == topb ) {
				throw new RuntimeException("Stack is full");
			} else {
				items[++topa] = t;
			}
		}

		@Override
		public T peek() {
			if (topa == -1) {
				return null;
			} else {
				return (T) items[topa];
			}
		}
    }

    private class InnerStackB implements MyStackIntf <T> {

    	@Override
		public T pop() {
			if (topb == size) {
				return null;
			} else {
				return (T) items[topb++];
			}
		}

		@Override
		public void push(T t) {
			if (topb - 1 == topa ) {
				throw new RuntimeException("Stack is full");
			} else {
				items[--topb] = t;
			}
		}

		@Override
		public T peek() {
			if (topb == size) {
				return null;
			} else {
				return (T) items[topb];
			}
		}
    	
    }
    
    public String toString() {
    	StringBuffer buff = new StringBuffer();
    	for (Object t : items) {
    		buff.append((T) t);
    		buff.append(" ");
    	}
    	return buff.toString();
    }
}

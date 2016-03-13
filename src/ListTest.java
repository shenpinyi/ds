import java.util.Iterator;

public class ListTest {

	public static void main(String[] args) {
		test04();
	}
	
	public static void test01(){
		MyLinkedList <Integer> l = new MyLinkedList<Integer>();

		for (int i = 0; i < 10; i++){
			l.add(i);
		}
		
		for (int i = 0; i < 10; i+=2){
			l.remove(i);
		}
		System.out.println(l);
	}
	
	public static void test03(){
		MyLinkedList <Integer> l = new MyLinkedList<Integer>();
		for (int i = 0; i < 10; i++){
			l.add(i);
		}
		
		System.out.println(l.contains(5));
		System.out.println(l.contains(10));
		System.out.println(l.contains(0));
		System.out.println(l.contains(9));
		
	}
	
	public static void testIterator(){
		MyLinkedList <Integer> la = new MyLinkedList<Integer>( new Integer[]{1,3,5,7,9,11,13});
		for (int i : la){
			System.out.println(i);
		}
	}
	
	
	public static void test04(){
		
		MyLinkedList <Integer> la = new MyLinkedList<Integer>( );
		MyLinkedList <Integer> lb = new MyLinkedList<Integer>( );//new Integer[]{1,3,5,7,9,11,13}
		MyLinkedList <Integer> lc = union(la, lb);
		MyLinkedList <Integer> ld = intersection(la, lb);
		System.out.println(lc);
		System.out.println(ld);
	}
	
	
	public static <T> MyLinkedList <T> union(MyLinkedList<T> la, MyLinkedList<T> lb) {
		
		Iterator <T> itera = la.iterator(), iterb = lb.iterator();
		MyLinkedList <T> lc  = new MyLinkedList <T> ();
		
		T ta = null;
		T tb = null;
		int flag = 0; //0: null; 1: a; 2: b
		
		while (true) {
			if (flag == 0){
				ta = itera.next();
				tb = iterb.next();
			} else if (flag == 1) {
				ta = itera.next();
			} else {
				tb = iterb.next();
			}
			
			if (ta == null && tb == null) {
				break;
			}
			
			if (ta == null) {
				lc.add(tb);
				flag = 2;
				continue;
			}

			if (tb == null) {
				lc.add(ta);
				flag = 1;
				continue;
			}
			
			if (ta.hashCode() <= tb.hashCode()) {
				lc.add(ta);
				flag = 1;
			} else {
				lc.add(tb);
				flag = 2;
			}
		}

		return lc;
	}
	
	public static <T> MyLinkedList <T> intersection(MyLinkedList<T> la, MyLinkedList<T> lb) {
		
		Iterator <T> itera = la.iterator(), iterb = lb.iterator();
		MyLinkedList <T> lc  = new MyLinkedList <T> ();
		
		T ta = null;
		T tb = null;
		int flag = 0; //0: null; 1: a; 2: b
		
		while (true) {
			if (flag == 0){
				ta = itera.next();
				tb = iterb.next();
			} else if (flag == 1) {
				ta = itera.next();
			} else {
				tb = iterb.next();
			}
			
			if (ta == null || tb == null) {
				break;
			}
			
			if (ta.hashCode() < tb.hashCode()) {
				flag = 1;
			} else if (ta.hashCode() > tb.hashCode()){
				flag = 2;
			} else {
				flag = 0;
				lc.add(ta);
			}
		}

		return lc;
	}

}

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class ListTest {

	public static void main(String[] args) {
		test13();
	}
	
	public static void test13() {
		MyArrayList <Integer> l = new MyArrayList <Integer> ();
		for (int i = 2; i < 30; i += 2){
			l.add(i);
		}
		System.out.println(l);
		l.add(0, 0);
		l.add(3, 3);
		System.out.println(l);
		System.out.println(l.contains(3));
		System.out.println(l.contains(30));
		
		//l.clear();
		//System.out.println(l);
		l.addAll(Arrays.asList(new Integer[]{1,2,3,4,5,6}));
		System.out.println(l);
		
	}
	

	public static void test01() {
		MyLinkedList<Integer> l = new MyLinkedList<Integer>();

		for (int i = 0; i < 10; i++) {
			l.add(i);
		}

		for (int i = 0; i < 10; i += 2) {
			l.remove(i);
		}
		System.out.println(l);
	}

	public static void test03() {
		MyLinkedList<Integer> l = new MyLinkedList<Integer>();
		for (int i = 0; i < 10; i++) {
			l.add(i);
		}

		System.out.println(l.contains(5));
		System.out.println(l.contains(10));
		System.out.println(l.contains(0));
		System.out.println(l.contains(9));

	}

	public static void testIterator() {
		MyLinkedList<Integer> la = new MyLinkedList<Integer>(new Integer[] { 1, 3, 5, 7, 9, 11, 13 });
		for (int i : la) {
			System.out.println(i);
		}
	}

	public static void test04() {

		MyLinkedList<Integer> la = new MyLinkedList<Integer>();
		MyLinkedList<Integer> lb = new MyLinkedList<Integer>();// new Integer[]{1,3,5,7,9,11,13}
		MyLinkedList<Integer> lc = union(la, lb);
		MyLinkedList<Integer> ld = intersection(la, lb);
		System.out.println(lc);
		System.out.println(ld);
	}

	public static void test06() {
		josephus(5, 1);
	}
	
	public static void test10() {
		MyLinkedList<Integer> la = new MyLinkedList<Integer>(new Integer[]{1,3,5,7,3,9,3,11,13});
		MyLinkedList<Integer> lb = new MyLinkedList<Integer>(new Integer[]{11,3,13});
		la.removeAll(lb);
		System.out.println(la);
		
	}

	public static void test11() {
		MyOneWayLinkedList<Integer> l = new MyOneWayLinkedList<Integer>();
		l.add(1);
		l.add(2);
		l.add(3);
		l.add(4);
		System.out.println(l);
	}
	
	public static void test12() {
		MyOneWayLinkedList<Integer> l = new MyOneWayLinkedList<Integer>();
		l.add(1);
		l.add(2);
		l.add(3);
		l.add(4);
		System.out.println(l);
		System.out.println(l.size());
		System.out.println(l.contains(4));
		System.out.println(l.contains(1));
		System.out.println(l.contains(2));
		System.out.println(l.contains(5));
		System.out.println(l.remove(4));
		System.out.println(l.remove(5));
		System.out.println(l);
		System.out.println(l.add(4));
		System.out.println(l.add(3));
		System.out.println(l);
	}

	public static <T> MyLinkedList<T> union(MyLinkedList<T> la, MyLinkedList<T> lb) {

		Iterator<T> itera = la.iterator(), iterb = lb.iterator();
		MyLinkedList<T> lc = new MyLinkedList<T>();

		T ta = null;
		T tb = null;
		int flag = 0; // 0: null; 1: a; 2: b

		while (true) {
			if (flag == 0) {
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

	public static <T> MyLinkedList<T> intersection(MyLinkedList<T> la, MyLinkedList<T> lb) {

		Iterator<T> itera = la.iterator(), iterb = lb.iterator();
		MyLinkedList<T> lc = new MyLinkedList<T>();

		T ta = null;
		T tb = null;
		int flag = 0; // 0: null; 1: a; 2: b

		while (true) {
			if (flag == 0) {
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
			} else if (ta.hashCode() > tb.hashCode()) {
				flag = 2;
			} else {
				flag = 0;
				lc.add(ta);
			}
		}

		return lc;
	}

	public static void josephus(int n, int m) {

		MyLinkedList<Integer> l = new MyLinkedList<Integer>();
		for (int i = 1; i <= n; i++) {
			l.add(i);
		}
		int size = n;
		Iterator<Integer> iter = l.iterator();
		int current = iter.next();
		while (true) {
			for (int j = 0; j < m; j++) {
				if (iter.hasNext()) {
					current = iter.next();
				} else {
					iter = l.iterator();
					current = iter.next();
				}

			}
			iter.remove();
			System.out.println(current);
			size--;
			if (size == 0) {
				break;
			}
			if (iter.hasNext()) {
				current = iter.next();
			} else {
				iter = l.iterator();
				current = iter.next();
			}
		}
	}

}

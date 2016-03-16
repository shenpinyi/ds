import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

public class ListTest {

	public static void main(String[] args) {

		//3.13 & 3.14 Implement ListIterator for MyArrayList and MyLinkedList;
//		testArrayList();
//		testMyArrayList();
//		testLinkedList();
//		testMyLinkedList();
		
		//3.15 test splice of MyLinkedList
//		testSplice();
		
		//3.16 test MyArrayList reverseIterator
//		testReverseIterator();
		
		//3.17 test concurrent modification
//		testConModiIterator();
		
	}
	
	public static void testConModiIterator(){
		
//		ArrayList <Integer> la = new ArrayList <Integer> ();
//		la.addAll(Arrays.asList(new Integer[]{0,1,2,3,4,5}));
//	    ListIterator <Integer> iter = la.listIterator();
//	    iter.next();
//	    iter.next();
//	    la.add(6);
//	    iter.set(4);
	    
		MyArrayList <Integer> la = new MyArrayList <Integer> ();
		la.addAll(Arrays.asList(new Integer[]{0,1,2,3,4,5}));
	    ListIterator <Integer> iter = la.listIterator();
	    iter.next();
	    iter.next();
	    la.add(6);
	    ListIterator <Integer> iter2 = la.listIterator();
	    //la.add(6);
	    //iter.remove();
	    iter2.next();
		
		
	}
	
	
	public static void testReverseIterator(){
		//test iterator
		MyArrayList <Integer> l = new MyArrayList <Integer>();
		l.addAll(Arrays.asList(new Integer[] {10,9,8,7,6,5,4,3,2,1,0}));
		ListIterator <Integer> iter = l.reverseListIterator();
	    System.out.println();
		System.out.print(iter.next() + " "); //0
		System.out.print(iter.next() + " "); //1
		System.out.print(iter.nextIndex() + " "); //8
		System.out.print(iter.next() + " "); //2
		System.out.print(" ");
		System.out.print(iter.previousIndex() + " "); //8
		System.out.print(iter.previous() + " "); //2
		System.out.print(iter.previousIndex() + " "); //9
		System.out.print(iter.nextIndex() + " "); //8
		System.out.print(iter.next() + " "); //2
		System.out.print(" ");
		iter.remove();
		System.out.print(iter.nextIndex() + " "); //7
		System.out.print(iter.next() + " "); //3
		iter.remove();
		//iter.remove(); //exception
	}

	public static void testSplice() {
		MyLinkedList <Integer> la = new MyLinkedList <Integer> ();
		MyLinkedList <Integer> lb = new MyLinkedList <Integer> ();
		la.addAll(Arrays.asList(new Integer[] {0,1,2,3,4,5,6,7}));
		lb.addAll(Arrays.asList(new Integer[]{101,102,103}));
		
		System.out.println(la); //0,1,2,3,4,5,6,7
		System.out.println(lb); //101,102,103

		ListIterator itr = la.listIterator();
		la.splice(itr, lb);
		System.out.println(la); //101,102,103,0,1,2,3,4,5,6,7
		System.out.println(lb); //null
		
		lb.addAll(Arrays.asList(new Integer[]{201,202,203}));
		itr = la.listIterator();
		itr.next();
		itr.next();
		la.splice(itr, lb);
		System.out.println(la); //101,102,201,202,203,103,0,1,2,3,4,5,6,7
		System.out.println(lb); //null
		
		lb.addAll(Arrays.asList(new Integer[]{301,302,303}));
		itr = la.listIterator();
		while(itr.hasNext()){
			itr.next();
		}
		la.splice(itr, lb);
		System.out.println(la); //101,102,201,202,203,103,0,1,2,3,4,5,6,7,301,302,303
		System.out.println(lb); //null
	}

	
	public static void testArrayList() {
		//test iterator
		ArrayList <Integer> l = new ArrayList <Integer>();
		l.addAll(Arrays.asList(new Integer[] {0,1,2,3,4,5,6,7,8,9,10}));
		ListIterator <Integer> iter = l.listIterator();
	    System.out.println();
		System.out.print(iter.next() + " "); //0
		System.out.print(iter.next() + " "); //1
		System.out.print(iter.nextIndex() + " "); //2
		System.out.print(iter.next() + " "); //2
		System.out.print(" ");
		System.out.print(iter.previousIndex() + " "); //2
		System.out.print(iter.previous() + " "); //2
		System.out.print(iter.previousIndex() + " "); //1
		System.out.print(iter.nextIndex() + " "); //2
		System.out.print(iter.next() + " "); //2
		System.out.print(" ");
		iter.remove();
		System.out.print(iter.nextIndex() + " "); //2
		System.out.print(iter.next() + " "); //3
		iter.remove();
		//iter.remove(); //exception
		
	}
	
	public static void testMyArrayList() {
		//test iterator
		MyArrayList <Integer> l = new MyArrayList <Integer>();
		l.addAll(Arrays.asList(new Integer[] {0,1,2,3,4,5,6,7,8,9,10}));
		ListIterator <Integer> iter = l.listIterator();
	    System.out.println();
		System.out.print(iter.next() + " "); //0
		System.out.print(iter.next() + " "); //1
		System.out.print(iter.nextIndex() + " "); //2
		System.out.print(iter.next() + " "); //2
		System.out.print(" ");
		System.out.print(iter.previousIndex() + " "); //2
		System.out.print(iter.previous() + " "); //2
		System.out.print(iter.previousIndex() + " "); //1
		System.out.print(iter.nextIndex() + " "); //2
		System.out.print(iter.next() + " "); //2
		System.out.print(" ");
		iter.remove();
		System.out.print(iter.nextIndex() + " "); //2
		System.out.print(iter.next() + " "); //3
		iter.remove();
		//iter.remove(); //exception
	}

	public static void testLinkedList() {
		//test iterator
		LinkedList <Integer> l = new LinkedList <Integer>();
		l.addAll(Arrays.asList(new Integer[] {0,1,2,3,4,5,6,7,8,9,10}));
		ListIterator <Integer> iter = l.listIterator();
	    System.out.println();
		System.out.print(iter.next() + " "); //0
		System.out.print(iter.next() + " "); //1
		System.out.print(iter.nextIndex() + " "); //2
		System.out.print(iter.next() + " "); //2
		System.out.print(" ");
		System.out.print(iter.previousIndex() + " "); //2
		System.out.print(iter.previous() + " "); //2
		System.out.print(iter.previousIndex() + " "); //1
		System.out.print(iter.nextIndex() + " "); //2
		System.out.print(iter.next() + " "); //2
		System.out.print(" ");
		iter.remove();
		System.out.print(iter.nextIndex() + " "); //2
		System.out.print(iter.next() + " "); //3
		iter.remove();
		//iter.remove(); //exception
		
	}
	public static void testMyLinkedList() {
		//test iterator
		MyLinkedList <Integer> l = new MyLinkedList <Integer>();
		l.addAll(Arrays.asList(new Integer[] {0,1,2,3,4,5,6,7,8,9,10}));
		ListIterator <Integer> iter = l.listIterator();
	    System.out.println();
		System.out.print(iter.next() + " "); //0
		System.out.print(iter.next() + " "); //1
		System.out.print(iter.nextIndex() + " "); //2
		System.out.print(iter.next() + " "); //2
		System.out.print(" ");
		System.out.print(iter.previousIndex() + " "); //2
		System.out.print(iter.previous() + " "); //2
		System.out.print(iter.previousIndex() + " "); //1
		System.out.print(iter.nextIndex() + " "); //2
		System.out.print(iter.next() + " "); //2
		System.out.print(" ");
		iter.remove();
		System.out.print(iter.nextIndex() + " "); //2
		System.out.print(iter.next() + " "); //3
		iter.remove();
		//iter.remove(); //exception
		
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
		System.out.println(l.containsAll(Arrays.asList(new Integer[]{1,2,3,4,5,6})));
		System.out.println(l.containsAll(Arrays.asList(new Integer[]{1,2,3,4,5,6,101})));
		System.out.println(l.get(0));
		System.out.println(l.get(21));
		//System.out.println(l.get(22));
		System.out.println(l.indexOf(0));
		System.out.println(l.indexOf(6));
		System.out.println(l.indexOf(101));
		
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

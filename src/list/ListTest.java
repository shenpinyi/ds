package list;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
		
		//3.18
//		test18();
		
		//3.20
//		test20();
		
		//3.21
//		test21();
		
		//3.22
//		test22();
		
		//3.23
		//test23();
		
		//3.24
		//test24();
		
		//3.26
		test26();
		
		
	}
	
	public static void test26(){
		MyMultiStack <Integer> ms = new MyMultiStack<>();
		
		MyStackIntf <Integer> s0 = ms.getStack(0);
		MyStackIntf <Integer> s1 = ms.getStack(1);
		MyStackIntf <Integer> s2 = ms.getStack(2);
		
		s0.push(0);
		s0.push(1);
		s0.pop();
		s0.push(2);
		s0.push(3);
		s0.push(4);
		s0.push(5);
		s0.push(7);
		s0.push(8);
		s0.push(9);

		System.out.println(ms);
		ms.printAvaliableStack();
		System.out.println(s0);
		System.out.println(s1);
		System.out.println(s2);
		
		s1.push(10);
		s1.push(11);
		s1.push(12);
		s1.push(13);
		s1.push(14);
		s1.push(15);
		s1.push(16);
		s1.push(17);
		s1.push(18);
		s1.pop();
		s1.pop();

		System.out.println(ms);
		ms.printAvaliableStack();
		System.out.println(s0);
		System.out.println(s1);
		System.out.println(s2);

		
		s2.push(20);
		s2.push(21);
		s2.push(22);
		s2.push(23);
		s2.push(24);
		//s2.push(25);
		//s2.push(27);
		//s2.push(28);
		//s2.push(29);
		s2.pop();
		s2.pop();
		
		
		System.out.println(ms);
		ms.printAvaliableStack();
		System.out.println(s0);
		System.out.println(s1);
		System.out.println(s2);
		
	}
	
	public static void test24(){
		MyDoubleStack <Integer> ds = new MyDoubleStack <> ();
		MyStackIntf <Integer> sa = ds.getStackA(), sb = ds.getStackB();
		sa.push(1);
		sa.push(2);
		sa.push(3);
		
		sb.push(20);
		sb.push(19);
		sb.push(18);
		
		System.out.println(ds);
		
		System.out.println(sa.peek());
		System.out.println(sb.peek());
		
		System.out.println(sa.pop());
		System.out.println(sa.pop());
		sa.push(102);
		sa.push(103);
		
		System.out.println(sb.pop());
		System.out.println(sb.pop());
		sb.push(119);
		sb.push(118);
		
		System.out.println(ds);
		
		for (int i = 0; i < 20; i ++) {
			sa.push(i);
			System.out.println(ds);
		}
		
	}
	
	public static void test23(){
		String s = "((5+3)*(6+7)-90/5)*(9+10)+8*10";
		System.out.println(s);
		String postfix = MyCaculator.infixToPostfix(s);
		System.out.println(postfix); 
		System.out.println(MyCaculator.caculateInfix(s));
		System.out.println(MyCaculator.postfixToInfix(MyCaculator.infixToPostfix(s)));
	}
	
	public static void test22(){
		System.out.println(MyCaculator.caculateSuffix("2 3 + 4.5 * 0.5 - 5 3.0 / -"));
	}
	
	
	public static void test21(){
		
		//test MyStack
		//testMyStack();
		
		//read a java file
		
		class MyStackNode {
			int line;
			String code;
			
			MyStackNode(int line, String code){
				this.line = line;
				this.code = code;
			}
		}
		
		String file = "D:\\temp\\MyArrayList.java";
		MyStack <MyStackNode> ms = new MyStack<>();
		Pattern p = Pattern.compile("([\\(\\){}\\[\\]]|(/\\*)|(\\*/)|(\\\\\\\\))+?");
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			int lineNo = 0;
			String line;
			int count = 0;
			int result = 0;
			String s = "";
			String top = "";
			int topLine = -1;
			while((line = reader.readLine()) != null){
				lineNo ++;
				Matcher m = p.matcher(line);
				while(m.find()){
					s = m.group();
					MyStackNode n = new MyStackNode(lineNo, s);
					
					if (ms.peek() != null){
						top = ms.peek().code;
						topLine = ms.peek().line;
					} else {
						top = "";
						topLine = -1;
					}
					//System.out.println(lineNo + ":" + m.group());
					
					// /* has the highest priority, so only */ can pop it out, others will be discard
					if (top != null && top.equals("/*")) {
						if (s.equals("*/")) {
							ms.pop();
							count++;
							continue;
						}
						continue;
					}
					
					// if it's //, discard the rest of the line
					if (s.equals("//")){
						continue;
					}
					
					// if /* { ( [ push
					if (s.matches("[\\({\\[]|(/\\*)")) {
						ms.push(n);
						continue;
					}

					// if ] ) } */ pull
					if (s.equals("]")) {
						if (top == null) {
							result = 1;
							break;
						}
						
						if (!top.equals("[")){
							result = 2;
							break;
						}
						ms.pop();
						count++;
					}
					if (s.equals("}")) {
						if (top == null) {
							result = 1;
							break;
						}
						
						if (!top.equals("{")){
							result = 2;
							break;
						}
						ms.pop();
						count++;
					}
					if (s.equals(")")) {
						if (top == null) {
							result = 1;
							break;
						}
						
						if (!top.equals("(")){
							result = 2;
							break;
						}
						ms.pop();
						count++;
					}
					if (s.equals("*/")) {
						if (top == null) {
							result = 1;
							break;
						}
						
						if (!top.equals("/*")){
							result = 2;
							break;
						}
						ms.pop();
						count++;
					}
				}
				
				if (result != 0) {
					break;
				}
			}
			
			if (result == 1){
				System.out.println("line " + lineNo + "\"" + s + "\"" + "hasn't opened");
			} else if (result == 2) {
				System.out.println("line " + topLine + "\"" + top + "\"" + "hasn't closed");
				System.out.println("Or");
				System.out.println("line " + lineNo + "\"" + s + "\"" + "hasn't opened");
			} else if (ms.size != 0){
				System.out.println("line " + topLine + "\"" + top + "\"" + "hasn't closed");
			} else {
				System.out.println("Validate okay " + count + " closed");
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//analysis codes
		
		//provide error and line number
	}
	
	public static void testMyStack(){
		MyStack <Integer> ms = new MyStack<>();
		ms.push(0);
		ms.push(1);
		ms.push(2);
		ms.push(3);
		System.out.println(ms); //0,1,2,3
		System.out.println(ms.pop()); //3
		System.out.println(ms); //0,1,2
		System.out.println(ms.peek()); //2
		System.out.println(ms); //0,1,2
		System.out.println(ms.pop()); //2
		System.out.println(ms.pop()); //1
		System.out.println(ms.pop()); //0
		//System.out.println(ms.pop()); //exception
		System.out.println(ms.peek()); //null
		System.out.println(ms); // null
	}
	
	
	public static void test20(){
		MyLazyLinkedList <Integer> l = new MyLazyLinkedList <>();
		for (int i = 0; i < 5; i++) {
			l.add(i);
		}
		
		System.out.println(l); //0,1,2,3,4
		
		l.remove(0);
		System.out.println(l); //(*0),1,2,3,4
		l.remove(1);
		System.out.println(l); //(*0),1,(*2),3,4
		l.add(0,0);
		System.out.println(l); //0,1,(*2),3,4
		l.add(2,2);
		System.out.println(l); //0,1,2,3,4
		l.remove(4);
		System.out.println(l); //0,1,2,3,(*4)
		l.remove(0);
		System.out.println(l); //(*0),1,2,3,(*4)
		l.remove(1);
		System.out.println(l); //1,3
		l.remove(1); 
		System.out.println(l); //1
	}
	
	
	public static void test18(){
		MyLinkedList <Integer> l = new MyLinkedList<Integer>();
		
		for (int i = 0; i < 10; i++) {
			l.add(i);
		}
		System.out.println(l); //0,1,2,3,4,5,6,7,8,9
		System.out.println(l.getFirst());//0
		System.out.println(l.getLast());//9
		l.removeFirst();
		System.out.println(l);//1,2,3,4,5,6,7,8,9
		l.removeLast();
		System.out.println(l);//1,2,3,4,5,6,7,8
		l.addFirst(100);
		System.out.println(l);//100,1,2,3,4,5,6,7,8
		l.addLast(200);
		System.out.println(l);//100,1,2,3,4,5,6,7,8,200
		l.remove(1);
		l.add(1, 300);
		System.out.println(l);//100,300,2,3,4,5,6,7,8,200
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

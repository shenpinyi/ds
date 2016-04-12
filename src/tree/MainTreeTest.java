package tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class MainTreeTest {

	public static void main(String[] args) {
		System.out.println("Start Tree Test");

		//test binary search tree
		//testxx1();
		
		//4.8
		//test08();
		
		//4.9
		//test09();
		
		//4.10
		//test10();
		
		//4.11
		//test1101();
		//test1102();
		//test11();
		
		//4.12
		test12();
	}
	
	public static void test12(){
		MyTreeMap <Integer, String> map = new MyTreeMap <> ();
		map.put(3, "a"); //31469257
		map.put(1, "a");
		map.put(4, "a");
		map.put(6, "a");
		map.put(9, "a");
		map.put(2, "a");
		map.put(5, "a");
		map.put(7, "a");
		
		System.out.println(map);
		
		
	}
	
	public static void test11() {
		Set <Integer> t = new MyTreeSet<>(new Comparator <Integer> () {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2 - o1;
			}
		}
		);
		t.add(3); //31469257
		t.add(1);
		t.add(4);
		t.add(6);
		t.add(9);
		t.add(2);
		t.add(5);
		t.add(7);
		System.out.println(t);
		
		t.remove(3);
		System.out.println(t);
		
		Iterator <Integer> iter = t.iterator();
		
		while (iter.hasNext()) {
			System.out.print(" " + iter.next());
		}
		
	}
	
	
	public static void test1101() {
		
		class E {
			public E(int i) {
				a = i;
			}
			public int a;
		}
		
		TreeSet <E> t =  new TreeSet<E>();
		t.add(new E(1)); //exception ClassCastException expected
	}
	
	public static void test1102() {
		Set <Integer> t = new MyTreeSet<>(new Comparator <Integer> () {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2 - o1;
			}
		}
		);
		t.add(3); //31469257
		t.add(1);
		t.add(4);
		t.add(6);
		t.add(9);
		t.add(2);
		t.add(5);
		t.add(7);
		System.out.println(t);
		
		t.remove(3);
		System.out.println(t);
	}
	
	
	public static void test10() {
		
		FileDirectory path = new FileDirectory("D:\\01books\\00Perl");
		path.printAll();
		
	}
	
	public static void test08(){
		
		List <String> l = new ArrayList<> ();
		l.addAll(Arrays.asList("-","*","*","a",null,null,"b",null,null,
				               "+","c",null,null,"d",null,null,
				               "e", null, null));
		BinaryTree <String> t = new BinaryTree<>(l);
		
		System.out.println(t);
		
		System.out.println(t.preorder());
		System.out.println(t.midorder());
		System.out.println(t.postorder());
		
	}
	
	public static void test09(){
		BinarySearchTree <Integer> t = new BinarySearchTree<>();
		t.insert(3); //31469257
		t.insert(1);
		t.insert(4);
		t.insert(6);
		t.insert(9);
		t.insert(2);
		t.insert(5);
		t.insert(7);
		System.out.println(t);
		
		t.remove(3);
		System.out.println(t);
	}
	
	public static void testxx1() {
		BinarySearchTree <Integer> t = new BinarySearchTree<>();
		t.insert(10);
		t.insert(5);
		t.insert(15);
		t.insert(7);
		t.insert(13);
		t.insert(3);
		t.insert(17);
		System.out.println(t);
		
		//System.out.println(t.findMax());
		//System.out.println(t.findMin());
		
		t.remove(5);
		System.out.println(t);
		
		t.remove(17);
		System.out.println(t);

		t.remove(10);
		System.out.println(t);
}

}

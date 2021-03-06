package tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
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
		//test12();
		
		//4.13
		//test13();
		
		//4.14
		//test14();
		
		//4.19
		//test19();
		
		//4.20
		//test20();
		
		//4.22
		//test22();
		
		//4.24
		//test24();
		
		//4.27
		//test27();
		
		//4.28 delete element in a splay tree
		//test28();
		
		//4.29
		//test29();
		
		//4.30
//		test30();
		
		//4.31
		//test31();
		
		//4.37
		//test37();
		
		//4.41
		test41();
		
		
	}
	
	public static void test41() {
		int n = 200000;
		
		MyRandomGenerator g1 = new MyRandomGenerator(1, n);
		g1.setName("G1");
		
		MySplayTreeSet <Integer> t = new MySplayTreeSet<>();
		
		int i = n;
		while (i > 0) {
			int key = g1.getNext();
			t.add(key);
			i--;
		} 
		//System.out.println(t);
		
		Long m1 = (long) 0, m2 = (long) 0;
		m1 = System.currentTimeMillis();
		int x = 1000;
		List<List<Integer>> ll = null;
		while (x >0) {
			ll = new ArrayList<> ();
			getLayerValues(t.root, 0, ll);
			x --;
		}
		m2 = System.currentTimeMillis();

		//System.out.println(ll);
		System.out.println((m2 - m1));
	}
	
	public static void getLayerValues(MySplayTreeSet<Integer>.Node t, int level, List<List<Integer>> ll){
		
		if (t == null) {
			return;
		}
		
		List<Integer> l;
		if (ll.size() <= level) {
			l = new ArrayList<Integer>();
			ll.add(l);
		}
		l = ll.get(level);
		
		l.add(t.element);
		
		getLayerValues(t.left, level + 1, ll);
		getLayerValues(t.right, level + 1, ll);
		
	}
	
	
	public static void test37() {
		
		int n = 50;
		
		MyRandomGenerator g1 = new MyRandomGenerator(1, n);
		g1.setName("G1");
		
		MySplayTreeSet <Integer> t = new MySplayTreeSet<>();
		
		int i = n;
		while (i > 0) {
			int key = g1.getNext();
			t.add(key);
			i--;
		} 
		
		count = 0;
		//System.out.println(t);
		getValues(3, 37, t.root);
		System.out.println("\n" + count);

		
	}
	
	static int count = 0;
	
	private static void getValues(int k1, int k2, MySplayTreeSet<Integer>.Node t) {
		
		count ++;
		
		if (t == null) {
			return;
		}
		
		if (t.element > k1 && t.left != null) {
			getValues(k1, k2, t.left);
		}
		
		if (t.element >= k1 && t.element <= k2) {
			System.out.print(t.element + " ");
		}
		
		if (t.element < k2 && t.right != null) {
			getValues(k1, k2, t.right);
		}
		
		return;
	}
	
	public static void test31() {
		int n = 50;
		int alpha = 10;
		int found = 0;
		
		MyRandomGenerator g1 = new MyRandomGenerator(1, n * alpha);
		MyRandomGenerator g2 = new MyRandomGenerator(n * alpha);
		g1.setName("G1");
		g2.setName("G2");
		
		MySplayTreeSet <Integer> t = new MySplayTreeSet<>();
		
		int i = n;
		while (i > 0) {
			g2.add(i);
			t.add(i);
			i--;
		} 
		
		i = n * n;
		while (i > 0) {
			
			int key = g2.getNext();
			g2.add(key);
			t.remove(key);
			t.add(key);
			i--;
		}
		
		System.out.println(getLeafCount(t.root) + "");
		System.out.println(getNodeCount(t.root) + "");
		System.out.println(getFullNodeCount(t.root) + "");
		System.out.println(checkBiSearchTree(t.root) + "");
		
	}

	private static boolean checkBiSearchTree(MySplayTreeSet<Integer>.Node t) {
		if (t == null) {
			return true;
		}
		
		boolean r1 = true, r2 = true;
		if(t.right != null) {
			r1 = (t.right.element > t.element) && checkBiSearchTree(t.right);
		}
		
		if(t.left != null) {
			r2 = (t.element > t.left.element) && checkBiSearchTree(t.left);
		}
		
		return r1 && r2;
	}

	private static int getFullNodeCount(MySplayTreeSet<Integer>.Node t) {
		if (t == null) {
			return 0;
		}
		
		if (t.right == null && t.left == null) {
			return 0;
		}
		
		int left = getFullNodeCount(t.left);
		int right = getFullNodeCount(t.right);
		int current = left + right;
		
		if(t.right != null && t.left != null) {
			current ++;
		}
		
		return current;
	}

	private static int getNodeCount(MySplayTreeSet<Integer>.Node t) {
		if (t == null) {
			return 0;
		}
		
		if (t.right == null && t.left == null) {
			return 0;
		}
		
		int left = getNodeCount(t.left);
		int right = getNodeCount(t.right);
		int current = left + right;
		
		if(t.right != null || t.left != null) {
			current ++;
		}
		
		return current;
	}

	private static int getLeafCount(MySplayTreeSet<Integer>.Node t) {
		if (t == null) {
			return 0;
		}
		
		if (t.right == null && t.left == null) {
			return 1;
		}
		
		int left = getLeafCount(t.left);
		int right = getLeafCount(t.right);
		
		return left + right;
		
		
	}

	public static void test29() {
		int n = 50;
		int alpha = 10;
		int found = 0;
		
		MyRandomGenerator g1 = new MyRandomGenerator(1, n * alpha);
		MyRandomGenerator g2 = new MyRandomGenerator(n * alpha);
		g1.setName("G1");
		g2.setName("G2");
		
		MySplayTreeSet <Integer> t = new MySplayTreeSet<>();
		
		int i = n;
		while (i > 0) {
			g2.add(i);
			t.add(i);
			i--;
		} 
		
		i = n * n;
		while (i > 0) {
			
			int key = g2.getNext();
			g2.add(key);
			t.remove(key);
			t.add(key);
			i--;
		}

		//System.out.println(t);
		
//		t.findCount = 0;
//		t.rotateCount = 0;
//		
//		i = 10;
//		while (i > 0) {
//			int x = n;
//			while (x > 0) {
//				found += t.contains(x)?1:0;
//				x--;
//			} 
//			System.out.println("Find: " + t.findCount + "Found:"  + found + "; Rotate: " + t.rotateCount);
//			i--;
//		}
		
		t.findCount = 0;
		t.rotateCount = 0;
		
		i = 10;
		while (i > 0) {
			int x = n;
			while (x > 0) {
				found += t.contains(20)?1:0;
				x--;
			} 
			System.out.println("Find: " + t.findCount + "Found:"  + found + "; Rotate: " + t.rotateCount);
			i--;
		}
		//t.creatTree();
	}
	
	public static void test30() {
		testSplay();
		testBi();
		testAvl();
		
	}
	
	public static void testBi() {
		int n = 50;
		int alpha = 10;
		
		MyRandomGenerator g1 = new MyRandomGenerator(1, n * alpha);
		MyRandomGenerator g2 = new MyRandomGenerator(n * alpha);
		g1.setName("G1");
		g2.setName("G2");
		
		BinarySearchTree <Integer> t = new BinarySearchTree<>();

		int i = n;
		while (i > 0) {
			g2.add(i);
			t.insert(i);
			i--;
  	    } 
//		//System.out.println(t);
//		
//		while (i > 0) {
//			int key = g1.getNext();
//			g2.add(key);
//			t.insert(key);
//			i--;
//		} 
//
//		//System.out.println(t);
//		
		i = n * n * n;
		while (i > 0) {
			
			int key = g2.getNext();
			t.remove(key);
			g1.add(key);

			key = g1.getNext();
			g2.add(key);
			t.insert(key);
			i--;
		}
		
		
		i = 1000000;
		while (i > 0) {
			
			int key = g2.getNext();
			g2.add(key);

			t.contains(key);
			i--;
		}
		System.out.println("Find: " + t.findCount);
		
		//System.out.println(t);
	}
	
	public static void testAvl() {
		int n = 50;
		int alpha = 10;
		
		MyRandomGenerator g1 = new MyRandomGenerator(1, n * alpha);
		MyRandomGenerator g2 = new MyRandomGenerator(n * alpha);
		g1.setName("G1");
		g2.setName("G2");
		
		MyTreeMap <Integer, String> t = new MyTreeMap<>();
		
//		while (i > 0) {
//			g2.add(i);
//			t.put(i, "A");
//			i--;
//  	    } 
		//System.out.println(t);

		
		int i = n;
		while (i > 0) {
			int key = g1.getNext();
			g2.add(key);
			t.put(key, "a");
			i--;
		} 

		//System.out.println(t);
		
		i = n * n * n;
		while (i > 0) {
			
			int key = g2.getNext();
			t.remove(key);
			g1.add(key);

			key = g1.getNext();
			g2.add(key);
			t.put(key, "a");
			i--;
		}
		
		i = 10000;
		while (i > 0) {
			
			int key = g2.getNext();
			g2.add(key);

			t.containsKey(key);
			i--;
		}
		System.out.println("Find: " + t.findCount);
		
		//System.out.println(t);
	}
	
	public static void testSplay() {
		int n = 10;
		int alpha = 10;
		int found = 0;
		
		MyRandomGenerator g1 = new MyRandomGenerator(1, n * alpha);
		MyRandomGenerator g2 = new MyRandomGenerator(n * alpha);
		g1.setName("G1");
		g2.setName("G2");
		
		MySplayTreeSet <Integer> t = new MySplayTreeSet<>();
		
		int i = n;
		while (i > 0) {
			int key = g1.getNext();
			g2.add(key);
			t.add(key);
			i--;
		} 

		//System.out.println(t);
		
		i = 100;
		while (i > 0) {
			
			int key = g2.getNext();
			g2.add(key);

			found += t.contains(key)?1:0;
			System.out.println("Find: " + t.findCount + "Found:"  + found + "; Rotate: " + t.rotateCount);
			i--;
		}
		t.creatTree();
		
		//System.out.println(t);
	}
	
	public static void test28() {
		MySplayTreeSet <Integer> t = new MySplayTreeSet <> ();
		
		t.add(10);
		t.add(4);
		t.add(11);
		t.add(2);
		t.add(6);
		t.add(12);
		t.add(1);
		t.add(3);
		t.add(5);
		t.add(8);
		t.add(7);
		t.add(9);
		t.add(13);
		
		t.contains(3);
		t.contains(9);
		t.contains(1);
		t.contains(5);
		//System.out.println(t);
		
		t.remove(6);
		System.out.println(t);
	}
	
	public static void test27() {
		MySplayTreeSet <Integer> t = new MySplayTreeSet <> ();
		
		t.add(10);
		t.add(4);
		t.add(11);
		t.add(2);
		t.add(6);
		t.add(12);
		t.add(1);
		t.add(3);
		t.add(5);
		t.add(8);
		t.add(7);
		t.add(9);
		t.add(13);
		
		t.contains(3);
		t.contains(9);
		t.contains(1);
		t.contains(5);
		
//		for (int i = 32; i > 0; i --) {
//			t.add(i);
//		}
//		
//		System.out.println(t.contains(1));
//		System.out.println(t.contains(2));
//		System.out.println(t.contains(3));
//		System.out.println(t.contains(4));
//		System.out.println(t.contains(5));
//		System.out.println(t.contains(6));
//		System.out.println(t.contains(7));

		System.out.println(t);
		t.creatTree();
		
	}
	
	public static void test24() {
		int k = 3;
		double count = Math.pow(2, k) - 1;
		MyAvlTreeSet <Integer> t = new MyAvlTreeSet <> ();
		
//		for (int i = 1; i <= count; i++) {
//			t.add(i); System.out.println(t.validateAvl());
//		}
//		
//		t.remove(1); System.out.println(t.validateAvl());
//		t.remove(2); System.out.println(t.validateAvl());
//		t.remove(3); System.out.println(t.validateAvl());
		
		t.add(2); System.out.println(t.validateAvl());
		t.add(1); System.out.println(t.validateAvl());
		t.add(4); System.out.println(t.validateAvl());
		t.add(5); System.out.println(t.validateAvl());
		t.add(9); System.out.println(t.validateAvl());
		t.add(3); System.out.println(t.validateAvl());
		t.add(6); System.out.println(t.validateAvl());
		t.add(7); System.out.println(t.validateAvl());
		
		t.remove(2); System.out.println(t.validateAvl());
		t.remove(1); System.out.println(t.validateAvl());
		t.remove(4); System.out.println(t.validateAvl());
		t.remove(5); System.out.println(t.validateAvl());
		t.remove(9); System.out.println(t.validateAvl());
		t.remove(3); System.out.println(t.validateAvl());
		t.remove(6); System.out.println(t.validateAvl());
		t.remove(7); System.out.println(t.validateAvl());
		
		System.out.println(t);
	}
	
	
	public static void test22() {
		MyAvlTreeSet <Integer> t = new MyAvlTreeSet <> ();
		
		t.add(2); System.out.println(t.validateAvl());
		t.add(1); System.out.println(t.validateAvl());
		t.add(4); System.out.println(t.validateAvl());
		t.add(5); System.out.println(t.validateAvl());
		t.add(9); System.out.println(t.validateAvl());
		t.add(3); System.out.println(t.validateAvl());
		t.add(6); System.out.println(t.validateAvl());
		t.add(7); System.out.println(t.validateAvl());
		
		System.out.println(t);
	}
	
	public static void test20() {
		int k = 5;
		double count = Math.pow(2, k) - 1;
		MyAvlTreeSet <Integer> t = new MyAvlTreeSet <> ();
		
		for (int i = 1; i <= count; i++) {
			t.add(i); System.out.println(t.validateAvl());
		}
		
		System.out.println(t);
	}
	
	
	public static void test19() {
		MyAvlTreeSet <Integer> t = new MyAvlTreeSet <> ();
		
		t.add(2);
		t.add(1);
		t.add(4);
		t.add(5);
		t.add(9);
		t.add(3);
		t.add(6);
		t.add(7);
		
		System.out.println(t);
		
	}
	
	public static void test14(){
		int n = 50;
		int alpha = 10;
		
		MyRandomGenerator g1 = new MyRandomGenerator(1, n * alpha);
		MyRandomGenerator g2 = new MyRandomGenerator(n * alpha);
		g1.setName("G1");
		g2.setName("G2");
		
		MyTreeMap <Integer, String> map = new MyTreeMap <> ();
		
		int i = n;
		while (i > 0) {
			int key = g1.getNext();
			g2.add(key);
			map.put(key, "a");
			i--;
		} 

		System.out.println(map);
		
		i = n * n * n;
		while (i > 0) {
			
			int key = g2.getNext();
			map.remove(key);
			g1.add(key);

			key = g1.getNext();
			g2.add(key);
			map.put(key, "a");
			i--;
		}
		
		System.out.println(map);
		map.creatTree();

		//g1.compress();
		//g2.compress();

		//System.out.println(g1);
		//System.out.println(g2);
		
	}

	
	public static void test13(){
		MyTreeMap <Integer, String> map = new MyTreeMap <> ();
		map.put(3, "a"); //31469257
		map.put(1, "a");
		map.put(4, "a");
		map.put(6, "a");
		map.put(9, "a");
		map.put(2, "a");
		map.put(5, "a");
		map.put(7, "a");
		map.remove(3);
		map.remove(5);
		
		System.out.println(map);
		
		Iterator <Integer> iter = map.iterator();
		while (iter.hasNext()) {
			System.out.print(iter.next() + " ");
		}
		
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

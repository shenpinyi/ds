package heap;

public class HeapTest {

	public static void main(String[] args) {
		
//		test01();
		
//		test02();
		
//		test03();
		
		test04();

	}
	
	private static void test04() {
//		MySkewHeap <Integer> h = new MySkewHeap <> ();
		MyLeftistHeap <Integer> h = new MyLeftistHeap <> ();
		h.insert(4);
		h.insert(3);
		h.insert(2);
		h.insert(1);
		
		System.out.println(h);
		
		h.makeEmpty();

		h.insert(1);
		h.insert(2);
		h.insert(3);
		h.insert(4);
		h.insert(5);
		h.insert(6);
		h.insert(7);
		
		System.out.println(h);
		
	}
	
	private static void test03() {
		
		MySkewHeap <Integer> h = new MySkewHeap <> ();
		
		h.insert(12);
		h.insert(11);
		h.insert(18);
		h.insert(17);
		h.insert(2);
		h.insert(15);
		h.insert(8);
		h.insert(5);
		
//		System.out.println(h);

		
		MySkewHeap <Integer> h2 = new MySkewHeap <> ();
		
		h2.insert(31);
		h2.insert(18);
		h2.insert(9);
		h2.insert(10);
		h2.insert(4);
		h2.insert(21);
		h2.insert(11);
		h2.insert(6);

//		System.out.println(h2);
		
		h.merge(h2);
		
		System.out.println(h);
		
//		System.out.println(h.deleteMin());
//		System.out.println(h);
		
	}

	
	private static void test02() {
		
		MyLeftistHeap <Integer> h = new MyLeftistHeap <> ();
		
		h.insert(12);
		h.insert(11);
		h.insert(18);
		h.insert(17);
		h.insert(2);
		h.insert(15);
		h.insert(8);
		h.insert(5);
		
//		System.out.println(h);

		
		MyLeftistHeap <Integer> h2 = new MyLeftistHeap <> ();
		
		h2.insert(31);
		h2.insert(18);
		h2.insert(9);
		h2.insert(10);
		h2.insert(4);
		h2.insert(21);
		h2.insert(11);
		h2.insert(6);

//		System.out.println(h2);
		
		h.merge(h2);
		
//		System.out.println(h);
		
		System.out.println(h.deleteMin());
		System.out.println(h);
		
	}
	
	private static void test01() {
		class IntegerPriority implements Priority <Integer> {
			@Override
			public Integer increase(Integer t, int diff) {
				return t + diff;
			}
			@Override
			public Integer decrease(Integer t, int diff) {
				return t - diff;
			}
		}
		
		MyBinaryHeap <Integer> h = new MyBinaryHeap<>();
        //new Integer[]{10,12,1,14,6,5,8,15,3,9,7,4,11,13,2}
		h.setPriorityModifier(new IntegerPriority());
		
		h.insert(10);
		h.insert(12);
		h.insert(1);
		h.insert(14);
		h.insert(6);
		h.insert(5);
		h.insert(8);
		h.insert(15);
		h.insert(3);
		h.insert(9);
		h.insert(7);
		h.insert(4);
		h.insert(11);
		h.insert(13);
		h.insert(2);
		
		System.out.println(h);
		h.increaseKey(1, 20);
		
		System.out.println(h);
		h.decreaseKey(21, 20);
		
		//h.deleteMin();
		
		System.out.println(h);
		
		h.delete(5);
		
		System.out.println(h);
		
		System.out.println(h.getLowers(20));
		
		int index = 6;
		for (int i = 0; i < index; i++) {
			System.out.println(h.deleteMin());
		}
		
		System.out.println(h);
		
//		MyBinaryHeap <Integer> h2 = new MyBinaryHeap<>(new Integer[]{10,12,1,14,6,5,8,15,3,9,7,4,11,13,2});
//		System.out.println(h2);
	}
	
	

}

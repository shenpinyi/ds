package heap;

public class HeapTest {

	public static void main(String[] args) {
		
		
		test01();

	}
	
	private static void test01() {
		MyBinaryHeap <Integer> h = new MyBinaryHeap<>();
        //new Integer[]{10,12,1,14,6,5,8,15,3,9,7,4,11,13,2}
		
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
		
		//h.deleteMin();
		
		System.out.println(h);
		
		MyBinaryHeap <Integer> h2 = new MyBinaryHeap<>(new Integer[]{10,12,1,14,6,5,8,15,3,9,7,4,11,13,2});
		System.out.println(h2);
	}
	
	

}

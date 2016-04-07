package tree;

public class MainTreeTest {

	public static void main(String[] args) {
		System.out.println("Start Tree Test");

		//test binary search tree
		testxx1();
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

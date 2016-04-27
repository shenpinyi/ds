package hash;

public class HashTest {

	public static void main(String[] args) {
		
		//5.1
		test01();

	}
	
	private static void test01() {
//		SeparateChainingHashTable <Integer> ht = new SeparateChainingHashTable<>();
//		LinearProbingHashTable <Integer> ht = new LinearProbingHashTable<> ();
//		QuadraticProbingHashTable <Integer> ht = new QuadraticProbingHashTable<> ();
		DoubleHashingHashTable <Integer> ht = new DoubleHashingHashTable<> (
				new  MyHashCode <Integer> () {
					public int hashCode(Integer x) {
						return 7 - x % 7;
					}
				});
//		DoubleHashingHashTable <Integer> ht = new DoubleHashingHashTable<> ();
		
		ht.insert(4371);
		ht.insert(1323);
		ht.insert(6173);
		ht.insert(4199);
		ht.insert(4344);
		ht.insert(9679);
		ht.insert(1989);
//		ht.remove(4);
//		ht.remove(11);
//		ht.remove(0);
//		ht.insert(25);
//		ht.insert(36);
//		ht.insert(14);
//		ht.insert(11);
//		ht.insert(0);
//		ht.insert(1);
//		ht.insert(10);

		System.out.println(ht);
	}
	
	
	

}

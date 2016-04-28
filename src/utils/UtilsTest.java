package utils;

import java.util.ArrayList;
import java.util.List;

public class UtilsTest {

	public static void main(String[] args) {
		
		testPrimeNumber();

	}
	
	public static void testPrimeNumber() {
		
		int count = 1000000;
		
		// method 1
		List <Long> l = new ArrayList <Long> ();
		l.add((long) 2);
		Long m1 = (long) 0, m2 = (long) 0;
		m1 = System.currentTimeMillis();
		Long last = (long) 2;
		for (int i = 0; i < count; i ++) {
			last = PrimeNumber.get(last + 1);
			l.add(last);
		}
//		last = PrimeNumber.get(15485866);
		m2 = System.currentTimeMillis();
		//System.out.println(l);
		System.out.println((m2 - m1));
//		System.out.println(last);
		System.out.println(l.get(l.size() - 2));

		// method 2
		l = new ArrayList <Long> ();
		l.add((long) 2);
		m1 = System.currentTimeMillis();
//		last = (long) 2;
//		for (int i = 0; i < count; i ++) {
//			last = PrimeNumber.getAlter(last + 1);
//			l.add(last);
//		}
		last = PrimeNumber.getAlter(15485864);
		m2 = System.currentTimeMillis();
		//System.out.println(l);
		System.out.println((m2 - m1));
		System.out.println(last);
//		System.out.println(l.get(l.size() - 1));
		

		//method 3: one stupid way copy from Internet
		l = new ArrayList <Long> ();
		l.add((long) 2);
		Long i;
		Long num;
		Long n = (long) 7927;
		m1 = System.currentTimeMillis();
		for (i = (long) 1; i <= n; i++) {
			int counter = 0;
			for (num = i; num >= 1; num--) {
				if (i % num == 0) {
					counter = counter + 1;
				}
			}
			if (counter == 2) {
				l.add(i);
			}
		}		
		m2 = System.currentTimeMillis();
		System.out.println((m2 - m1));
		System.out.println(l.get(l.size() - 1));
	}
}

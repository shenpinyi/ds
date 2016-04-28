package utils;

import java.util.Iterator;
import java.util.TreeSet;

public class PrimeNumber {
	
	static TreeSet <Long> primes = new TreeSet<>();
	static Long lastCheck;
	
	static {
		primes.add((long) 2);
		lastCheck = (long) 2;
	}
	
	public static boolean isPrime(long l) {

		if (l == 2) {
			return true;
		}
		
		if (l == 1) {
			return false;
		}
		
		Long last = primes.last(); // the largest prime we stored
		
		// phase 0: check lastCheck
		if (l <= lastCheck) {
			if (primes.contains(l)) {
				return true;
			} else {
				return false;
			}
		}		
		
		Long check = lastCheck + 1;
		while (true) { // loop until find the number larger than l
			boolean primeFlag = true;
			boolean checked = false;

			//phase one: use primes to divide
			Iterator <Long> iter = primes.iterator();
			while (iter.hasNext()) {
				Long div = iter.next();
				
				if (div * div > check) {
					primeFlag = true;
					checked = true;
					break;
				}
				if (check % div == 0) {
					primeFlag = false;
					checked = true;
					break; //check is not a prime
				}
			}
			
			if (!checked) {
				for (Long div = last + 1; div * div <= check; div ++) {
					if (check % div == 0) {
						primeFlag = false;
						checked = true;
					}
				}
			}
			
			if(!checked) {
				primeFlag = true;
			}
			
			if (primeFlag) {
				primes.add(check);
				last = check;
			}
			
			if (check == l) {
				lastCheck = check;
				if (primeFlag) {
					return true;
				} else {
					return false;
				}
			}

			check ++;
		}
	}
	
	// it's good for get prime numbers from very little to a large number
	public static long get(long l) {
	
		Long next = primes.ceiling(l);
		
		if (next != null) return next;
		
		for (long i = l; ; i ++) {
			if (isPrime(i)) {
				return i;
			}
		}
	}
	
	
	// it's good for get prime number only for a few times
	public static long getAlter(long l) {
		for (long i = l; ; i ++) {
			
			boolean primeFlag = true;
			if (i == 2) {
				return 2;
			}
			
			if (i == 1) {
				return 2;
			}
			
			for (Long div = (long) 2; div * div <= i; div ++) {
				if (i % div == 0) {
					primeFlag = false;
				}
			}
			
			if (primeFlag) {
				return i;
			}
		}
	}
}

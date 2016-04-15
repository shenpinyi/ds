package tree;

import java.util.Random;

public class MyRandomGenerator {
	
	int[] a;
	int size;  		//size of the array
	int top;        //top of pool
	String name;
	static int cc = 0;
	
	// create a empty pool
	public MyRandomGenerator (int s) {
		this.size = s;
		a = new int[size];
		for (int i = 0; i < size; i ++) {
			a[i] = -1;
		}
		top = -1;
	}
	
	public MyRandomGenerator (int min, int max){
		this((max - min + 1) * 2);

		for (int i = 0; i < (max - min + 1); i ++) {
			a[i] = i + min;
		}
		top = (max - min + 1) - 1;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public int compress() {
		int i = 0;
		int j = -1;
		
		for (i = 0; i < size; i ++ ) {
			if (a[i] != -1){
				j ++;
				if (j != i) {
					a[j] = a[i];
					a[i] = -1;
				}
			}
		}
		
		top = j;
		
		System.out.println(name + " compress executed" + cc++);
		return top;
	}	
	
	// put a valid value into pool
	public boolean add(int n) {
		if (top == size -1) {
			compress();
		}
		
		if (top == size - 1) {
			// if the pool is full, add failed
			return false;
		}
		
		top ++;
		a[top] = n;
		return true;
	}
	
	public int getNext() {
		// create a random number from 0 to top
		Random rand = new Random();
		int idx;
		int value = -1;
		
		
		if (top == -1) {
			// if the pool is empty
			return -1;
		} else if (top == 0){
			value = a[0];
			a[0] = -1;
			return value;
		}
		
		int i = 0;
		int maxTry = 10;
		while (i < maxTry) {
			idx = rand.nextInt(top);
			if (a[idx] == -1 ) {
				i ++;
			} else {
				value = a[idx];
				a[idx] = -1;
				return value;
			}
		}
		
		// if times of try is too high, compress the pool
		compress();
		
		if (top == -1) {
			// if the pool is empty
			return -1;
		} else if (top == 0){
			value = a[0];
			a[0] = -1;
			return value;
			
		} else {
			idx = rand.nextInt(top);
			value = a[idx];
			a[idx] = -1;
			return value;
		}
	}
	
	public String toString() {
		StringBuffer buff = new StringBuffer();
		buff.append("Top:" + top);
		buff.append("[");
		buff.append(a[0]);
		for (int i = 1; i < size; i ++) {
			buff.append(",");
			if (i % 10 == 0){
				buff.append("\n");
			}
			buff.append("\t" + a[i]);
		}
		buff.append("]");
		return buff.toString();
	}
	
}

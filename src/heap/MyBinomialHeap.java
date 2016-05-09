package heap;

import java.util.ArrayList;
import java.util.List;

public class MyBinomialHeap <T extends Comparable < ? super T>> {
	
	Entry root;
	
	class Entry {
		T item;
		int height;
		List <Entry> f;
		
		public Entry() {
			f = new ArrayList<>();
			height = 0;
			item = null;
		}
	}
	
	
	public MyBinomialHeap() {
		root = new Entry();
	}
	
	public void insert(T item) {
		Entry e = new Entry();
		e.item = item;
		addTree(root, e);
	}
	
	public void merge(MyBinomialHeap <T> h) {
		merge(root, h.root);
	}
	
	
	private void merge(Entry h1, Entry h2) {
		for (Entry child : h2.f) {
			addTree(h1, child);
		}
	}
	
	private void addTree(Entry h, Entry t) {
		Entry child = null;
		int index = 0;
		for (int i = 0; i < h.f.size(); i ++) {
			if (h.f.get(i).height == t.height) {
				child = h.f.remove(i);
				index = i;
				break;
			}
			
			if (h.f.get(i).height < t.height) {
				index = i;
				break;
			}
			
		}
		
		if (child == null) {
			h.f.add(index, t);
		} else {
			if (child.item.compareTo(t.item) < 0) {
				child.f.add(0, t);
				child.height ++;
			} else {
				t.f.add(0, child);
				t.height ++;
				child = t;
			}
			addTree(h, child);
		}
		
	}

	
	public String toString() {
		
		StringBuffer buff = new StringBuffer();
		printHeap(root, 0, buff);
		return buff.toString();
		
	}
	
	private void printHeap(Entry h, int level, StringBuffer buff) {
		
		if (h == null) {
			return;
		}
		
		buff.append("[" + h.item + "," + h.height + "]\r\n");
		
		for (int i = h.f.size() - 1; i > -1; i --) {
			Entry child = h.f.get(i);
			if (child != null) {
				printTable(level, buff);
				printHeap(child, level + 1, buff);
				buff.append("\r\n");
			}
		}
		
	}
	
	private void printTable(int level, StringBuffer buff) {
		
		for (int i = 0; i < level; i ++) {
			buff.append("\t");
		}
		
		
	}

}

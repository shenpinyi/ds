package tree;

import java.awt.Canvas;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.swing.JFrame;

public class MySplayTreeSet <E> implements Set <E> {
	
	int size;
	Node root;
	
	double rotateCount = 0;
	double findCount = 0;
	
	Comparator <E> comparator;
	
	class Node {
		E element;
		Node left;
		Node right;
		Node parent;
		
		public String toString() {
			return "(" + element + "," + " P:" + (parent == null ? "null" : parent.element) + ")";
		}
	}
	
	public MySplayTreeSet() {
		comparator = null;
		size = 0;
		root = null;
	}
	
	public MySplayTreeSet(Comparator <E> p) {
		comparator = p;
	}
	
	@SuppressWarnings("unchecked")
	public int compare(E e1, E e2) {
		if (comparator != null) {
			return comparator.compare(e1, e2);
		} else {
			return ((Comparable <E>) e1).compareTo(e2);
		}
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public boolean contains(Object o) {
		@SuppressWarnings("unchecked")
		Node node = find((E) o, root);
		read(node);
		return node != null;
	}
	
	private void read(Node node) {
		Node t = node;
		
		while (t != null) {
			
//			System.out.println(this);
			Node g = null;
			Node p = null;
			Node supr = null;
			int gd  = 0, pd = 0, sd = 0; //right: 1; left: -1;

			if (t.parent != null && t.parent.parent != null) {
				p = t.parent;
				g = t.parent.parent;
				gd = (g.left == p ? -1 : 1);
				pd = (p.left == t ? -1 : 1);
				supr = g.parent;
				if (supr != null) {
					sd = (supr.left == g ? -1 : 1);
				}
			} else if ( t.parent != null) {
				p = t.parent;
				pd = (p.left == t ? -1 : 1);
			} else {
				root = t;
				return; //root
			}

			if (g == null) {
				rotateCount ++;
				if (pd == 1) {
					t = singleRotateRight(p);
				} else {
					t = singleRotateLeft(p);
				}
				root = t;
				t.parent = null;
			} else {
				rotateCount ++;
				if (pd == 1 && gd == 1) {
					t = zigRotateRight(g);
				} else if (gd == -1 && pd == -1) {
					t = zigRotateLeft(g);
				} else if (gd == 1 && pd == -1) {
					t = zagRotateRight(g);
				} else {
					t = zagRotateLeft(g);
				}
				
				if (sd == -1) {
					supr.left = t;
					t.parent = supr;
				} else if (sd == 1) {
					supr.right = t;
					t.parent = supr;
				} else {
					t.parent = null;
					root = t;
				}
			}
		}
	}
	
	private Node zigRotateRight(Node g) {
		Node p = g.right;
		Node t = p.right;
		
		g.right = p.left;
		if (p.left != null) {
			p.left.parent = g;
		}
		p.left = g;
		p.right = t.left;
		if (t.left != null) {
			t.left.parent = p;
		}
		t.left = p;
		g.parent = p;
		p.parent = t;
		
		return t;
	}
	
	private Node zagRotateRight(Node g) {
		
		Node p = g.right;
		Node t = p.left;
		
		g.right = t.left;
		if (t.left != null) {
			t.left.parent = g;
		}
		p.left = t.right;
		if (t.right != null) {
			t.right.parent = p;
		}
		t.right = p;
		t.left = g;
		g.parent = t;
		p.parent = t;
		
		return t;
	}

	private Node zigRotateLeft(Node g) {
		Node p = g.left;
		Node t = p.left;
		
		g.left = p.right;
		if (p.right != null) {
			p.right.parent = g;
		}
		p.right = g;
		p.left = t.right;
		if (t.right != null) {
			t.right.parent = p;
		}
		
		t.right = p;
		g.parent = p;
		p.parent = t;
		
		return t;
	}

	private Node zagRotateLeft(Node g) {
		Node p = g.left;
		Node t = p.right;
		
		g.left = t.right;
		if (t.right != null) {
			t.right.parent = g;
		}
		p.right = t.left;
		if (t.left != null) {
			t.left.parent = p;
		}
		t.left = p;
		t.right = g;
		g.parent = t;
		p.parent = t;
		
		return t;
	}

	private Node singleRotateRight(Node p) {
		Node t = p.right;
		p.right = t.left;
		if (t.left != null) {
			t.left.parent = p;
		}
		t.left = p;
		p.parent = t;
		return t;
	}

	private Node singleRotateLeft(Node p) {
		Node t = p.left;
		p.left = t.right;
		if (t.right != null) {
			t.right.parent = p;
		}
		t.right = p;
		p.parent = t;
		return t;
	}

	private Node find(E e, Node t) {
		findCount ++;
		if (t == null) {
			return null;
		}
		
		int r = compare(e, t.element);
		if (r == 0) {
			return t;
		} else if (r > 0) {
			return find(e, t.right);
		} else {
			return find(e, t.left);
		}
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(E e) {
		int old = size;
		root = add(e, root);
		return old != size;
	}
	
	private Node add(E e, Node t) {
		if (t == null) {
			Node node = new Node();
			node.element = e;
			node.left = node.right = null;
			node.parent = null;
			size ++;
			return node;
		}
		
		int r = compare(e, t.element);
		if (r == 0) {
			t.element = e;
		} else if (r > 0) {
			t.right = add(e, t.right);
			t.right.parent = t;
		} else {
			t.left = add(e, t.left);
			t.left.parent = t;
		}
		
		return t;
	}

	@Override
	public boolean remove(Object o) {
		if (!this.contains(o)) {
			return false;
		}
		
		Node left = root.left;
		Node right = root.right;
		
		if (left == null) {
			root = root.right;
			root.parent = null;
			return true;
		}
		
		Node t = findMax(left);
		root = left;
		left.parent = null;
		read(t);
		t.right = right;
		if (right != null){
			right.parent = t;
		}
		return true;
	}
	
	private Node findMax(Node t) {
		if (t.right == null) {
			return t;
		} else {
			return findMax(t.right);
		}
	}
	
	private Node findMin(Node t) {
		if (t.left == null) {
			return t;
		} else {
			return findMin(t.left);
		}
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}
	
	public String toString() {
		StringBuffer buff = new StringBuffer();
		printTree(root, 0, "T:", buff);
		return buff.toString();
	}
	
	private void printTree(Node e, int level, String pos, StringBuffer buff){
		if (e == null){
			printSp(level, buff);
			buff.append(pos + "null\r\n");
			return;
		}
		printTree(e.right, level + 1, "R:", buff);

		printSp(level, buff);
		buff.append(pos + e + "<\r\n");

		printTree(e.left, level + 1, "L:", buff);
	}
	
	private void printSp(int level, StringBuffer buff) {
		for (int i = 0; i < level; i ++) {
			buff.append("            ");
		}
	}

	class Point {
		int x;
		int y;
		int width;
	}
	
	class Line {
		int x1;
		int y1;
		int x2;
		int y2;
	}

	public void creatTree(){
		List <Point> nodes = new ArrayList <> ();
		List <Line> lines = new ArrayList <> ();
		List <Point> fullNodes = new ArrayList <> ();
		int maxLevel = getLevel();
		
		for (int i = 0; i < (Math.pow(2, (maxLevel + 1)) + 1); i ++) {
			fullNodes.add(new Point());
		}
		
		createFullTree(1, fullNodes, 0, maxLevel);
		createTree(1, root, fullNodes, nodes, lines);
		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("HelloWorldSwing");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(400, 400);
                
                MyCanvas cnvs = new MyCanvas(nodes, lines);
                cnvs.setSize(1000, 1000);
                frame.add(cnvs);
                frame.setVisible(true);
            }
        });
		
	}
	
	public class MyCanvas extends Canvas{
		
		private static final long serialVersionUID = 1120409038397998949L;
		List <Point> nodes;
		List <Line> lines;
		
		public MyCanvas(List <Point> ns, List <Line> ls) {
			nodes = ns;
			lines = ls;
		}
		
		public void paint(Graphics g) {
	        drawNodes(g);
	        drawLines(g);
		}
		
		private void drawNodes(Graphics g) {
			for (Point n : nodes) {
				g.drawOval(n.x, n.y, n.width, n.width);
			}
		}
		
		private void drawLines(Graphics g) {
			for (Line l : lines) {
				g.drawLine(l.x1, l.y1, l.x2, l.y2);
			}
			
		}
	}

	
	private void createTree(int id, Node t, List <Point> fullNodes, List <Point> nodes, List <Line> lines) {
		Point node = fullNodes.get(id);
		nodes.add(node);
		
		if (t.left != null) {
			Line line = new Line();
			line.x1 = node.x;
			line.y1 = node.y;
			
			Point left = fullNodes.get(id * 2);
			line.x2 = left.x;
			line.y2 = left.y;
			
			lines.add(line);
			
			createTree(id * 2, t.left, fullNodes, nodes, lines);
		}

		if (t.right != null) {
			Line line = new Line();
			line.x1 = node.x;
			line.y1 = node.y;
			
			Point right = fullNodes.get(id * 2 + 1);
			line.x2 = right.x;
			line.y2 = right.y;
			
			lines.add(line);
			
			createTree(id * 2 + 1, t.right, fullNodes, nodes, lines);
		}

	}
	
	private int createFullTree(int id, List <Point> fullNodes, int level, int maxLevel){
		
		Point node = new Point();
		
		int left;
		int right;
		int current;
		
		int firstId = (int) Math.pow(2, level);
		
		if (level == maxLevel) {
			current = 2 * (id - firstId) + 10;
		} else {
			left = createFullTree(id * 2, fullNodes, level + 1, maxLevel);
			right = createFullTree(id * 2 + 1, fullNodes, level + 1, maxLevel);
			current = (left + right) / 2;
		}
		
		node.x = current;
		node.y = 10 + 40 * level;
		node.width = 3;
		
		fullNodes.set(id, node);
		return current;
	}
	
	
	public int getLevel() {
		return getLevel(root, 0);
	}
	
	private int getLevel(Node t, int level) {
		int level_right = 0;
		int level_left = 0;
		int level_max = 0;
		
		if (t.right == null && t.left == null){
			return level;
		}

		if (t.right != null) {
			level_right = getLevel(t.right, level + 1);
		}
		
		if (t.left != null) {
			level_left = getLevel(t.left, level + 1);
		}


		level_max = Math.max(level_right, level_left); 
		
		return level_max; 
		
	} 
}

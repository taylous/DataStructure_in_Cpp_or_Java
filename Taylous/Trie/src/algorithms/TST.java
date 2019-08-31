package algorithms;

public class TST {
	
	private Node root;
	private int n;
	
	private static class Node {
		
		char c;
		int frequency;
		
		Node left, middle, right;
	}
	
	public TST() {}
	
	public int size() {
		
		return this.n;
	}
	
	public boolean isEmpty() {
		
		return this.n == 0;
	}
	
	public void put(String key) {
		
		if(key == null)
			return;
		
		if(!contains(key))
			this.n++;
		root = put(root, key, 0);
	}
	
	private Node put(Node node, String key, int idx) {
		
		char c = key.charAt(idx);
		
		if(node == null) {
			node = new Node();
			node.c = c;
		}
		
		if(node.c < c)
			node.right = put(node.right, key, idx);
		else if(node.c > c)
			node.left = put(node.left, key, idx);
		else if(idx < key.length() - 1)
			node.middle = put(node.middle, key, idx + 1);
		else
			node.frequency++;
		return node;
	}
	
	public boolean contains(String key) {
		
		if(key == null)
			return false;
		return get(key) > 0;
	}
	
	public int get(String key) {
		
		if(key == null)
			return -1;
		if(key.length() == 0)
			return -2;
		
		Node node = get(root, key, 0);
		if(node == null)
			return -3;
		return node.frequency;
	}
	
	private Node get(Node node, String key, int idx) {
		
		if(node == null)
			return null;
		
		char c = key.charAt(idx);
		
		if(node.c < c)
			return get(node.right, key, idx);
		else if(node.c > c)
			return get(node.left, key, idx);
		else if(idx < key.length() - 1)
			return get(node.middle, key, idx + 1);
		else
			return node;
	}
}

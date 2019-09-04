package trie;

public class TrieST {
    private static final int R = 256;

    private Node root;
    private int n;

    private static class Node {
        private int frequency = 0;
        private Node[] next = new Node[R];
    }

    public TrieST() {}

    public int size() {
        return this.n;
    }

    public boolean isEmpty() {
        return this.n == 0;
    }

    public boolean contains(String key) {
        if(key == null)
            return false;
        return get(root, key, 0) != null;
    }

    public int get(String key) {
        if(key == null)
            return -1;

        Node x = get(root, key, 0);

        if(x == null)
            return -1;
        return x.frequency;
    }

    //재귀 보다 for문이 더 좋음
    private Node get(Node node, String key, int idx) {
        if(node == null)
            return null;

        if(idx == key.length())
            return node;

        char c = key.charAt(idx);
        return get(node.next[c], key, idx + 1);
    }

    public void put(String key) {
        if(key == null)
            return;

        root = put(root, key, 0);
    }

    private Node put(Node node, String key, int idx) {
        if(node == null)
            node = new Node();

        if(key.length() == idx) {
            if(node.frequency == 0)
                this.n++;
            node.frequency++;
            return node;
        }

        char c = key.charAt(idx);
        node.next[c] = put(node.next[c], key, idx + 1);
        return node;
    }

    public void delete(String key) {
        delete(root, key, 0);
    }

    private void delete(Node node, String key, int idx) {
        if(node == null)
            return;

        if(key.length() == idx) {
            if(node.frequency != 0)
                this.n--;
            node.frequency--;
        }
        else {
            char c = key.charAt(idx);
            delete(node.next[c], key, idx + 1);
        }
    }
}
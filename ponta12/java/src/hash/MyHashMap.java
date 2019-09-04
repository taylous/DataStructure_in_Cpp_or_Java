package hash;

public class MyHashMap {
    private class Node {
        int hash;

        String key;
        int data;
        Node right;

        public Node(String key, int data) {
            this.key = key;
            this.data = data;
            this.right = null;
            this.hash = 0;
        }
    }

    private Node[] table;
    private int tbSize;
    private int n;

    public MyHashMap(int tbSize) {
        this.table = new Node[tbSize];
        this.tbSize = tbSize;
        this.n = 0;
    }

    public int size() {
        return this.n;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void put(String key, int value) {
        int hash = hashCode(key);

        Node node = new Node(key, value);
        node.hash = hash;

        if (this.table[hash] != null) {
            Node entry = this.table[hash];

            while(entry.right != null && !entry.key.equals(key))
                entry = entry.right;

            if(entry.key.equals(key)) {
                entry.data++;
            } else {
                entry.right = node;
                this.n++;
            }
        } else {
            this.table[hash] = node;
            this.n++;
        }
    }

    public int get(String key) {
        int hash = hashCode(key);

        if(this.table[hash] != null) {
            if(this.table[hash].key.equals(key))
                return this.table[hash].data;

            Node entry = this.table[hash];

            while(entry != null && entry.key.equals(key))
                entry = entry.right;

            if(entry == null)
                return -1;
            return entry.data;
        }
        return -1;
    }

    private int hashCode(String key) {
        return (hash(key) & 0x7fffffff) % this.tbSize;
    }

    private int hash(String key) {
        int val = 0;

        if(key.length() > 0) {
            char[] arr = key.toCharArray();
            for(char c : arr) {
                val = 31 * val + c;
            }
        }

        return val;
    }
}

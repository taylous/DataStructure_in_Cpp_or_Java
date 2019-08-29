import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Iterator;

class IHashMap {

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

    public IHashMap(int tbSize) {

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

        if(this.table[hash] != null) {

            Node entry = this.table[hash];

            while(entry.right != null && !entry.key.equals(key))
                entry = entry.right;

            if(entry.key.equals(key)) {

                entry.data++;
            }
            else {

                entry.right = node;
                this.n++;
            }
        }
        else {

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

            while(entry != null && !entry.key.equals(key))
                entry = entry.right;

            if(entry == null)
                return -1;
            return entry.data;
        }
        return -1;
    }

    private int hash(String key) {

        int h = 0;
        if(key.length() > 0) {

            char[] var = strToCharArray(key);

            for(int i = 0; i < var.length; i++)
                h = 31 * h + var[i];
        }
        return h;
    }

    private int hashCode(String key) {

        return (hash(key) & 0x7fffffff) % this.tbSize;
    }

    private char[] strToCharArray(String str) {

        char[] ret = new char[str.length()];
        for(int i = 0; i < str.length(); i++)
            ret[i] = str.charAt(i);
        return ret;
    }
}

public class Main {

    static String Answer;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        IHashMap hashMap = new IHashMap(n);
        HashSet<String> set = new HashSet<>();

        String book;
        int criteria, count = 0;

        while(n-- > 0) {

            book = br.readLine();

            set.add(book);
            hashMap.put(book, 1);
        }

        Iterator<String> it = set.iterator();

        while(it.hasNext()) {

            book = it.next();
            criteria = hashMap.get(book);

            if(criteria == -1)
                continue;

            if(count < criteria) {

                count = criteria;
                Answer = book;
            }
            else if(count == criteria) {

                if(Answer.compareTo(book) > 0)
                    Answer = book;
            }
        }
        System.out.println(Answer);
        br.close();
    }

//    static int compare(String aStr, String bStr) {
//
//        char[] a = aStr.toCharArray();
//        char[] b = bStr.toCharArray();
//
//        int len = Math.min(a.length, b.length);
//
//        for(int i = 0; i < len; i++) {
//
//            if(a[i] < b[i])
//                return -1;
//            else if(a[i] > b[i])
//                return 1;
//        }
//        if(a.length < b.length)
//            return -1;
//        else if(a.length > b.length)
//            return 1;
//        return 0;
//    }
}

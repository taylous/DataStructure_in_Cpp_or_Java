package sort;

public class RadixSort {
    private final static int ARRSIZE = 20;

    public static void main(String[] args) {
        int[] arr = new int[ARRSIZE];
        for(int i = 0; i < ARRSIZE; i++) {
            arr[i] = (int)(Math.random() * 1000);
        }

        System.out.print("sorting 전 : ");
        for(int i = 0; i < ARRSIZE; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();

        int reNum = getRadixMax(arr);
        for(int j = 1; j < reNum; j *= 10) {
            radixSort(arr, j);
        }

        System.out.print("sorting 후 : ");
        for(int i = 0; i < ARRSIZE; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    private static void radixSort(int[] arr, int j) {
        MyLinkedList[] count = new MyLinkedList[10];
    }

    private static int getRadixMax(int[] arr) {

    }

    static class MyLinkedList {
        int data;
        MyLinkedList next;

        public MyLinkedList() {
            super();
        }

        public MyLinkedList(int data, MyLinkedList next) {
            super();
            this.data = data;
            this.next = next;
        }
    }
}

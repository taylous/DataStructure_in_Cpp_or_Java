package sort;

public class QuickSort {
    public static void main(String[] args) {
        int[] arr1 = new int[20];
        int[] arr2 = new int[20];
        for(int i = 0; i < 20; i++) {
            arr1[i] = (int)(Math.random() * 1000);
            arr2[i] = (int)(Math.random() * 1000);
        }

        System.out.print("sorting1 전 : ");
        for(int i = 0; i < 20; i++) {
            System.out.print(arr1[i] + " ");
        }
        System.out.println();

        hoareQuickSort(arr1, 0, 19);

        System.out.print("sorting1 후 : ");
        for(int i = 0; i < 20; i++) {
            System.out.print(arr1[i] + " ");
        }
        System.out.println();

        System.out.print("sorting2 전 : ");
        for(int i = 0; i < 20; i++) {
            System.out.print(arr2[i] + " ");
        }
        System.out.println();

        lomutoQuickSort(arr2, 0, 19);

        System.out.print("sorting2 후 : ");
        for(int i = 0; i < 20; i++) {
            System.out.print(arr2[i] + " ");
        }
        System.out.println();
    }

    private static void hoareQuickSort(int[] arr, int i, int j) {
        if(i < j) {
            int k = hoarePartition(arr, i , j);
            hoareQuickSort(arr, i, k - 1);
            hoareQuickSort(arr, k + 1, j);
        }
    }

    private static int hoarePartition(int[] arr, int i, int j) {
        int pivot = i++;
        while(true) {
            while(i < arr.length && arr[i] < arr[pivot]) i++;
            while(arr[j] > arr[pivot]) j--;
            if(i < j) swap(arr, i, j);
            else break;
        }

        swap(arr, pivot, j);

        return j;
    }

    private static void lomutoQuickSort(int[] arr, int i, int j) {
        if (i < j) {
            int k = lomutoPartition(arr, i , j);
            lomutoQuickSort(arr, i, k - 1);
            lomutoQuickSort(arr, k + 1, j);
        }
    }

    private static int lomutoPartition(int[] arr, int i, int j) {
        int pivot = j;
        int a = i - 1;
        int b = i;

        while(b < pivot) {
            if(arr[b] < arr[pivot]) {
                a++;
                swap(arr, a, b);
            }
            b++;
        }
        swap(arr, a+1, pivot);

        return a+1;
    }

    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}

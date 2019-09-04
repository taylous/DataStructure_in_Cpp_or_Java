package sort;

public class MergeSort {
    static int[] sorted = new int[20];

    public static void main(String[] args) {
        int[] arr1 = new int[20];

        for(int i = 0; i < 20; i++) {
            arr1[i] = (int)(Math.random() * 1000);
        }


        System.out.print("sorting1 전 : ");
        for(int i = 0; i < 20; i++) {
            System.out.print(arr1[i] + " ");
        }
        System.out.println();

        mergeSort(arr1, 0, 19);

        System.out.print("sorting1 후 : ");
        for(int i = 0; i < 20; i++) {
            System.out.print(arr1[i] + " ");
        }
        System.out.println();
    }

    private static void mergeSort(int[] list, int i, int j) {
        if (i < j) {
            int mid = (i + j) / 2;
            mergeSort(list, i, mid);
            mergeSort(list, mid + 1, j);
            merge(list, i, mid, j);
        }
    }

    private static void merge(int[] list, int i, int mid, int j) {
        int start = i;
        int startLeft = i;
        int startRight = mid + 1;
        while(startLeft <= mid && startRight <= j) {
            if(list[startLeft] <= list[startRight]) {
                sorted[start++] = list[startLeft++];
            } else {
                sorted[start++] = list[startRight++];
            }
        }

        if(startLeft > mid) {
            while(startRight <= j) sorted[start++] = list[startRight++];
        } else {
            while(startLeft <= mid) sorted[start++] = list[startLeft++];
        }

        for(int k = i; k <= j; k++)
            list[k] = sorted[k];
    }
}
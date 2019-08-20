
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
		if(i < j) {
			int mid = (i + j) / 2;
			mergeSort(list, i, mid);
			mergeSort(list, mid + 1, j);
			merge(list, i, mid, j);
		}
	}
	
	private static void merge(int[] list, int i, int m, int j) {
		int start = i;
		int start1 = i;
		int start2 = m + 1;
		
		while(start1 <= m && start2 <= j) {
			if(list[start1] <= list[start2]) {
				sorted[start++] = list[start1++];
			} else {
				sorted[start++] = list[start2++];
			}
		}
		
		if(start1 > m) {
			while(start2 <= j) sorted[start++] = list[start2++];
		} else {
			while(start1 <= m) sorted[start++] = list[start1++];
		}
		
		for(int k = i; k <= j; k++) {
			list[k] = sorted[k];
		}
	}
}

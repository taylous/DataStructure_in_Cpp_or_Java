import java.util.Random;

public class LomutoQuickSort {

	public static void main(String[] args) {
		
		Random random = new Random();
		int[] arr = new int[100];
		
		for (int i = 0; i < arr.length; i++)
			arr[i] = random.nextInt(1000);

		print(arr);
		sort(arr, 0, arr.length - 1);
		print(arr);
	}

	static void sort(int[] arr, int start, int end) {
		if (start >= end)
			return;
		
		int pivot = end, i = start - 1, j = start;
		
		while (j < end) {
			if (arr[j] <= arr[pivot])
				swap(arr, ++i, j);
			j++;
		}
		
		swap(arr, ++i, pivot);
		
		sort(arr, start, i - 1);
		sort(arr, i + 1, end);
	}
	
	static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	static void print(int[] arr) {
		for (int i = 0; i < arr.length; i++)
			System.out.print(arr[i] + " ");
		System.out.println();
	}
}

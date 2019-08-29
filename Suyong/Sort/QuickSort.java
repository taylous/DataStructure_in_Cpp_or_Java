import java.util.Random;

public class QuickSort {

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
		
		int pivot = start, i = start, j = end;
		
		while (i < j) {
			
			while (i < end && arr[i] <= arr[pivot])
				i++;
			
			while (arr[j] > arr[pivot])
				j--;
			
			if (i < j)
				swap(arr, i , j);
		}
		
		swap(arr, pivot, j);
		
		sort(arr, start, j - 1);
		sort(arr, j + 1, end);
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

import java.util.Random;

public class MergeSort {

	public static void main(String[] args) {
		
		Random random = new Random();
		int[] arr = new int[100];
		
		for (int i = 0; i < arr.length; i++)
			arr[i] = random.nextInt(100);

		print(arr);
		mergeBU(arr);
		print(arr);
	}

	static void mergeBU(int[] arr) {
		for (int i = 1; i < arr.length; i *= 2)
			for (int j = 0; j < arr.length; j += i * 2)
				if (j + i - 1 < arr.length - 1 && j + 1 < arr.length - 1)
					sort(arr, j, j + i - 1, j + i, Math.min(j + i * 2 - 1, arr.length - 1));
	}
	
	static void sort(int[] arr, int lStart, int lEnd, int rStart, int rEnd) {
		System.out.println(lStart + " " + lEnd + " " + rStart + " " + rEnd);
		int[] left = new int[lEnd - lStart + 1];
		int[] right = new int[rEnd - rStart + 1];
		int leftC = 0, rightC = 0;
		
		for (int i = 0; i < left.length; i++)
			left[i] = arr[lStart + i];
		
		for (int i = 0; i < right.length; i++)
			right[i] = arr[rStart + i];
		
		for (int i = lStart; i <= rEnd; i++) {
			if (leftC == left.length)
				arr[i] = right[rightC++];
			else if (rightC == right.length)
				arr[i] = left[leftC++];
			else if (left[leftC] < right[rightC])
				arr[i] = left[leftC++];
			else
				arr[i] = right[rightC++];
		}
		
//		for (int i = lStart; i <= rEnd; i++)
//			System.out.print(arr[i] + " ");
//		System.out.println();
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

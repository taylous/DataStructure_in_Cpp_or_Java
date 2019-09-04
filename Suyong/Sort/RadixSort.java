import java.util.Arrays;
import java.util.Random;

public class RadixSort {

	public static void main(String[] args) {
		
		Random random = new Random();
		int[] arr = new int[100];
		
		for (int i = 0; i < arr.length; i++)
			arr[i] = random.nextInt(1000);

		print(arr);
		sort(arr);
		print(arr);
	}

	static void sort(int[] arr) {
		int max = arr[0], radix = 1;
		int[] brr = new int[arr.length];
		int[] crr = new int[10];
		
		for (int i = 1; i < arr.length; i++)
			max = Math.max(max, arr[i]);
		
		while (max / radix > 0) {
			Arrays.fill(crr, 0);
			
			for (int i = 0; i < arr.length; i++)
				crr[(arr[i] / radix) % 10]++;
			
			for (int i = 1; i < crr.length; i++)
				crr[i] += crr[i - 1];
			
			for (int i = arr.length - 1; i >= 0; i--)
				brr[--crr[(arr[i] / radix) % 10]] = arr[i];
			
			for (int i = 0; i < arr.length; i++)
				arr[i] = brr[i];
			
			radix *= 10;
		}
		
	}
	
	static void print(int[] arr) {
		for (int i = 0; i < arr.length; i++)
			System.out.print(arr[i] + " ");
		System.out.println();
	}
}

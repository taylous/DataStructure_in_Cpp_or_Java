package test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import algorithms.MergeTD;
import algorithms.QuickSortLomuto;

public class TestClient {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new FileReader("1Kints.txt"));
		QuickSortLomuto quickSort = new QuickSortLomuto();
		MergeTD mergeSort = new MergeTD();
		
		int[] arr = new int[100];
		int[] arr2 = new int[100];
		
		String temp;
		double start, end;
		int idx = 0;
		
		while(true) {
			
			temp = br.readLine();
			
			if(temp == null || idx == 100)
				break;
			
			arr[idx++] = Integer.parseInt(temp.trim());
		}
		System.arraycopy(arr, 0, arr2, 0, 100);
		
		System.out.println("Lomuto's Quick Sort");
		System.out.println("BEFORE> " + Arrays.toString(arr));
		start = System.currentTimeMillis();
		quickSort.sort(arr, 0, arr.length - 1);
		end = System.currentTimeMillis() - start;
		System.out.println("AFTER> " + Arrays.toString(arr));
		System.out.println(end + "초\n\n");
		
		System.out.println("Optimized Merge Sort");
		System.out.println("BEFORE> " + Arrays.toString(arr2));
		start = System.currentTimeMillis();
		mergeSort.sort(arr2);
		end = System.currentTimeMillis() - start;
		System.out.println("AFTER> " + Arrays.toString(arr2));
		System.out.println(end + "초\n\n");
		
		br.close();
	}
}
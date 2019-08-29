import java.util.Scanner;

public class BestSeller {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int count = 1, max = 1, index = 0;
		String best = "";
		
		String[] arr = new String[N];
		
		for (int i = 0; i < N; i++)
			arr[i] = sc.next();
		
		sort(arr, 0, arr.length - 1);
		
		best = arr[0];
		for (int i = 1; i < N; i++) {
			if (check(arr[index], arr[i]) == 0) {
				count++;
				if (max < count) {
					best = arr[i];
					max = count;
				}
			}
			else {
				index = i;
				count = 1;
			}
		}
		
		System.out.println(best);
		
		sc.close();
	}

	static void sort(String[] arr, int start, int end) {
		if (start >= end)
			return;
		
		int pivot = start, i = start, j = end;
		
		while (i < j) {
			
			while (i < end && check(arr[i], arr[pivot]) < 1)
				i++;
			
			while (check(arr[j], arr[pivot]) > 0)
				j--;
			
			if (i < j)
				swap(arr, i, j);
		}
		
		swap(arr, pivot, j);
		sort(arr, start, j - 1);
		sort(arr, j + 1, end);
	}
	
	static void swap(String[] arr, int i, int j) {
		String temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	static int check(String a, String b) {
		if (a.equals(b))
			return 0;
		
		int length = a.length() < b.length() ? a.length() : b.length();
		
		for (int i = 0; i < length; i++) {
			if (a.charAt(i) < b.charAt(i))
				return -1;
			else if (a.charAt(i) > b.charAt(i))
				return 1;
		}
		
		if (a.length() < b.length())
			return -1;
		else 
			return 1;
	}
}
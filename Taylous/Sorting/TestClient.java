import java.util.Arrays;
import java.util.Random;

public class TestClient {

	public static void main(String[] args) {
		
		QuickSortLomuto lomuto = new QuickSortLomuto();
		MergeTD merge = new MergeTD();
		Radix radix = new Radix();
		Random random = new Random();
		
		int[] arr = new int[10];
		
		for(int i = 0; i < 10; i++)
			arr[i] = random.nextInt(100);
		
		System.out.println("BEFORE: " + Arrays.toString(arr));
		lomuto.quickSort(arr, 0, 9);
		System.out.println("AFTER: " + Arrays.toString(arr));
		
		arr = new int[30];
		
		for(int i = 0; i < 30; i++)
			arr[i] = random.nextInt(100);
		
		System.out.println("BEFORE: " + Arrays.toString(arr));
		merge.sort(arr);
		System.out.println("AFTER: " + Arrays.toString(arr));
		
		arr = new int[10];
		
		for(int i = 0; i < 10; i++)
			arr[i] = random.nextInt(100);
		
		System.out.println("BEFORE: " + Arrays.toString(arr));
		radix.sort(arr);
		System.out.println("AFTER: " + Arrays.toString(arr));
	}
}

public abstract class Sorting {

	public void swap(int[] arr, int a, int b) {
		
		int t = arr[a];
		arr[a] = arr[b];
		arr[b] = t;
	}
	
	public void printArr(int[] arr) {
		
		for(int data : arr)
			System.out.print(data + " ");
		System.out.println();
	}
	
	public boolean less(int a, int b) {
		
		return a < b ? true : false;
	}
}

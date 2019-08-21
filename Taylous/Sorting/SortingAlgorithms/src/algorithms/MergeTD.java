package algorithms;

public class MergeTD {

	private final int CUTOFF = 7;
	
	private void merge(int[] src, int[] dest, int low, int mid, int high) {
		
		int i = low;
		int j = mid + 1;
		
		for(int k = low; k <= high; k++) {
			
			if(i > mid)
				dest[k] = src[j++];
			else if(j > high)
				dest[k] = src[i++];
			else if(less(src[j], src[i]))
				dest[k] = src[j++];
			else
				dest[k] = src[i++];
		}
	}
	
	private void sort(int[] src, int[] dest, int low, int high) {
		
		if(high <= low + CUTOFF) {
			
			insertionSort(dest, low, high);
			return;
		}
		
		int mid = low + (high - low) / 2;
		
		sort(dest, src, low, mid);
		sort(dest, src, mid + 1, high);
		merge(src, dest, low, mid, high);
	}
	
	public void sort(int[] src) {
		
		int[] dest = new int[src.length];
		for(int i = 0; i < src.length; i++)
			dest[i] = src[i];
		sort(dest, src, 0, src.length - 1);
	}
	
	private boolean less(int i, int j) {
		
		return i < j ? true : false;
	}
	
	private void swap(int[] src, int i, int j) {
		
		int t = src[i];
		src[i] = src[j];
		src[j] = t;
	}
	
	private void insertionSort(int[] src, int low, int high) {
		
		for(int i = low; i <= high; i++)
			for(int j = i; j > low && less(src[j], src[j - 1]); j--)
				swap(src, j, j - 1);
	}
}

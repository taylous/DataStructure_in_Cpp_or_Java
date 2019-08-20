
public class MergeTD extends Sorting {

	private final int CUTOFF = 7;
	
	private void merge(int[] src, int[] dst, int low, int mid, int high) {
		
		int i = low;
		int j = mid + 1;
		
		for(int k = low; k <= high; k++) {
			
			if(i > mid)
				dst[k] = src[j++];
			else if(j > high)
				dst[k] = src[i++];
			else if(less(src[j], src[i]))
				dst[k] = src[j++];
			else
				dst[k] = src[i++];
		}
	}
	
	public void sort(int[] src) {
		
		int[] dst = new int[src.length];
		for(int i = 0; i < src.length; i++)
			dst[i] = src[i];
		sort(dst, src, 0, src.length - 1);
	}
	
	private void sort(int[] src, int[] dst, int low, int high) {
		
//		if(high <= low)
//			return;
		if(high <= low + CUTOFF) {
			
			insertionSort(dst, low, high);
			return;
		}
		
		int mid = low + (high - low) / 2;
		
		sort(dst, src, low, mid);
		sort(dst, src, mid + 1, high);
		merge(src, dst, low, mid, high);
	}
	
	private void insertionSort(int[] src, int low, int high) {
		
		for(int i = low; i <= high; i++) {
			for(int j = i; j > low && less(src[j], src[j-1]); j--)
				swap(src, j, j - 1);
		}
	}
}


public class Radix {

	public void sort(int[] a) {
		
		int i;
		int m = a[0];
		int exp = 1;
		int n = a.length;
		
		int[] b = new int[n];
		
		// 가장 큰 수 검색
		for(i = 1; i < n; i++)
			if(a[i] > m)
				m = a[i];
		
		while(m / exp > 0) {
			
			int[] c = new int[10];
			
			for(i = 0; i < n; i++)
				c[(a[i]/exp) % 10]++;
			for(i = 1; i < 10; i++)
				c[i] += c[i - 1];
			for(i = n - 1; i >= 0; i--)
				b[--c[(a[i]/exp) % 10]] = a[i];
			for(i = 0; i < n; i++)
				a[i] = b[i];
			exp *= 10;
		}
	}
}

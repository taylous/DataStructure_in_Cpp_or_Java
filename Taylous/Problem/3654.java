import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int[] dvdList;
	static int[] dvdIndex;
	
	static int N;
	static int M;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int next, number;
		
		int T = Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case <= T; test_case++) {
			
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			dvdList = new int[200002];
			dvdIndex = new int[200002];
			
			for(int i = 1; i <= N; i++) {
				
				dvdIndex[i] = N - i + 1;
				update(i, 1);
			}
			st = new StringTokenizer(br.readLine());
			next = N + 1;
			
			while(M-- > 0) {
				
				number = Integer.parseInt(st.nextToken());
				
				sb.append(rsq(dvdIndex[number], 200001));
				sb.append(" ");
				
				update(dvdIndex[number], -1);
				dvdIndex[number] = next++;
				update(dvdIndex[number], 1);
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}
	
	static int rsq(int index) {
		
		int sum = 0;
		while(index > 0) {
			
			sum += dvdList[index];
			index -= index & (-index);
		}
		return sum;
	}
	
	static int rsq(int a, int b) {
		
		return rsq(b) - rsq(a);
	}
	
	static void update(int index, int value) {
		
		while(index < dvdList.length) {
			
			dvdList[index] += value;
			index += index & (-index);
		}
	}
}

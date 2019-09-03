import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		int N = 0, num = 0;
		String[] numbers;
		String result = "YES";
		
		for (int testCase = 1; testCase <= T; testCase++) {
			N = sc.nextInt();
			numbers = new String[N];
			
			for (int i = 0; i < N; i++)
				numbers[i] = sc.next();
			
			Trie head = new Trie();
			Trie node = null;
			
			loop : for (int i = 0; i < N; i++) {
				node = head;

				for (int j = 0; j < numbers[i].length(); j++) {
					num = numbers[i].charAt(j) - '0';
					node.haveChild = true;
					
					if (node.children[num] == null) {
						node.children[num] = new Trie();
						node.children[num].data = num;
					}
					
					node = node.children[num];
					
					if (node.isEnd) {
						result = "NO";
						break loop;
					}

					if (j == numbers[i].length() - 1) {
						if (node.haveChild) {
							result = "NO";
							break loop;
						}
						else
							node.isEnd = true;
					}
				}
			}
			
			System.out.println(result);
			result = "YES";
		}
		
		sc.close();
	}
	
	static class Trie {
		int data;
		Trie[] children = new Trie[10];
		boolean haveChild = false;
		boolean isEnd = false;
	}
}

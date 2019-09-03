import java.util.Scanner;

public class Main {
	
	static int result = 0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		int N = 0, M = 0, num = 0;
		String[] files, nofiles;
		char now;
		
		for (int testCase = 1; testCase <= T; testCase++) {
			N = sc.nextInt();
			
			files = new String[N];
			
			for (int i = 0; i < N; i++)
				files[i] = sc.next();
			
			M = sc.nextInt();
			nofiles = new String[M];
			
			for (int i = 0; i < M; i++)
				nofiles[i] = sc.next();
			
			Trie head = new Trie();
			
			if (M != 0)
				head.canDelete = false;
			Trie node = null;
			
			for (int i = 0; i < N; i++) {
				node = head;
				
				for (int j = 0; j < files[i].length(); j++) {
					now = files[i].charAt(j);
					
					node.haveChild = true;
					
					if (now >= 'A' && now <= 'Z')
						num = now - 'A' + 11;
					else if (now >= 'a' && now <= 'z')
						num = now - 'a' + 37;
					else if (now >= '0' && now <= '9')
						num = now - '0';
					else
						num = 10;
					
					if (node.children[num] == null) {
						node.children[num] = new Trie();
						node.children[num].data = now;
					}
					
					node = node.children[num];
					
					if (j == files[i].length() - 1)
						node.isEnd = true;
				}
			}
			
			for (int i = 0; i < M; i++) {
				node = head;
				
				for (int j = 0; j < nofiles[i].length(); j++) {
					now = nofiles[i].charAt(j);
					
					if (now >= 'A' && now <= 'Z')
						num = now - 'A' + 11;
					else if (now >= 'a' && now <= 'z')
						num = now - 'a' + 37;
					else if (now >= '0' && now <= '9')
						num = now - '0';
					else
						num = 10;
					
					if (node.children[num] == null) {
						node.children[num] = new Trie();
						node.children[num].data = now;
					}
					
					node = node.children[num];
					node.canDelete = false;
				}
			}
			
			check(head);
			System.out.println(result);
			result = 0;
		}
		
		sc.close();
	}
	
	static void check(Trie node) {
		
		if (node.canDelete) {
			result++;
			return;
		}
		else {
			if (node.isEnd)
				result++;
		}
		
		for (int i = 0; i < 63; i++) {
			if (node.children[i] != null)
				check(node.children[i]);
		}
	}
	
	static class Trie {
		char data;
		Trie[] children = new Trie[63]; // 대문자 26, 소문자 26, 숫자 10, . 1
		boolean haveChild = false;
		boolean isEnd = false;
		boolean canDelete = true;
	}
}

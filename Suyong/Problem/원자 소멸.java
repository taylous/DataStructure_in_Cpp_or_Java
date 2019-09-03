import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		int N = 0, x = 0, y = 0, d = 0, k = 0, nextX = 0, nextY = 0, sum = 0, nextA = 0;
		int[] dx = { -1, 1, 0, 0 };
		int[] dy = { 0, 0, -1, 1 };
		int[][] atom, map;
		Queue<Node> deleteQ;
		Node temp;
		
		for (int testCase = 1; testCase <= T; testCase++) {
		    N = sc.nextInt();
		    
		    atom = new int[N + 1][4];
		    map = new int[2001][2001];
		    
		    deleteQ = new LinkedList<Solution.Node>();
		    
		    for (int i = 1; i <= N; i++) {
		    	x = 1000 + sc.nextInt();
		    	y = 1000 - sc.nextInt();
		    	d = sc.nextInt();
		    	k = sc.nextInt();
		    	
		    	atom[i][0] = y;
		    	atom[i][1] = x;
		    	atom[i][2] = d;
		    	atom[i][3] = k;
		    	
		    	map[y][x] = i;
		    }
		    
		    while (N > 0) {
		    	for (int i = 1; i < atom.length; i++) {
		    		if (atom[i][0] == -1)
		    			continue;
		    		
		    		nextX = atom[i][0] + dx[atom[i][2]];
		    		nextY = atom[i][1] + dy[atom[i][2]];
		    		
		    		if (nextX < 0 || nextX > 2000 || nextY < 0 || nextY > 2000) {
		    			N--;
		    			map[atom[i][0]][atom[i][1]] = 0;
		    			atom[i][0] = -1;
		    			continue;
		    		}
		    		
		    		if (map[nextX][nextY] != 0) { // 움직인 방향에 원자가 있는 경우
		    			nextA = map[nextX][nextY];
		    			if (i < nextA) { // 다음에 움직이는 원자인 경우
		    				if (atom[i][2] + atom[nextA][2] == 1 || atom[i][2] + atom[nextA][2] == 5) { // 0.5초에 터지는 경우
		    					N -= 2;
		    					sum += (atom[i][3] + atom[nextA][3]);
		    					
		    					map[atom[i][0]][atom[i][1]] = 0;
		    					map[nextX][nextY] = 0;
		    					
		    					atom[i][0] = -1;
		    					atom[nextA][0] = -1;
		    				}
		    				else { // 다음에 움직이는 원자와 만났지만 터지지 않는 경우
		    					map[nextX][nextY] = i; // 원자 이동시켜줌
		    					if (map[atom[i][0]][atom[i][1]] == i) // 이전에 있던 위치에 다른 원자가 오지 않은 경우
		    						map[atom[i][0]][atom[i][1]] = 0; // 초기화
		    					atom[i][0] = nextX; // x좌표 갱신
		    					atom[i][1] = nextY; // y좌표 갱신
		    				}
		    			}
		    			else { // 이전에 움직인 원자인 경우 - 터져야함
		    				if (atom[nextA][0] != -1) { // 처음 터지는 경우
		    					N -= 2; // 처음 터지기 때문에 2개 같이 터짐
		    					sum += (atom[i][3] + atom[nextA][3]);
		    					map[atom[i][0]][atom[i][1]] = 0;
		    					atom[i][0] = -1;
		    					atom[nextA][0] = -1;
		    					
		    					deleteQ.add(new Node(nextX, nextY)); // 다 이동하고 난후 지워야할 노드 위치 추가
		    				}
		    				else { // 이전에 다른 친구와 터져있던 경우
		    					N--; // 해당 원자만 터지면 됨
		    					sum += atom[i][3];
		    					map[atom[i][0]][atom[i][1]] = 0;
		    					atom[i][0] = -1;
		    				}
		    			}
		    		}
		    		else { // 빈 공간인 경우 원자 이동시켜줌
		    			map[nextX][nextY] = i;
		    			if (map[atom[i][0]][atom[i][1]] == i)
		    				map[atom[i][0]][atom[i][1]] = 0;
		    			
		    			atom[i][0] = nextX;
		    			atom[i][1] = nextY;
		    		}
		    	}
		    	
		    	// 원자 다 돌았으니 map에 지워야할 좌표 지워줌
		    	while (!deleteQ.isEmpty()) {
		    		temp = deleteQ.poll();
		    		
		    		map[temp.x][temp.y] = 0;
		    	}
		    }
		    
		    System.out.println("#" + testCase + " " + sum);
		    sum = 0;
		}
		
		sc.close();
	}
	
	static class Node {
		int x;
		int y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}

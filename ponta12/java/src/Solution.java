import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());

        for(int test_case = 1; test_case <= t; test_case++) {
            int n = Integer.parseInt(br.readLine());
            int[][] map = new int[n][n];
            boolean[][] visit = new boolean[n][n];
            ArrayList<int []> list = new ArrayList<>();

            //초기화
            for(int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int x, y;
            for(int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if(map[i][j] != 0 && !visit[i][j]) {
                        x = i;
                        y = j;
                        while(x < n && map[x][y] != 0) x++;
                        while(y < n && map[x - 1][y] != 0) y++;
                        list.add(new int[] {x - i, y - j});

                        for(int k = i; k < x; k++) {
                            for(int l = j; l < y; l++) {
                                visit[k][l] = true;
                            }
                        }
                    }
                }
            }

            list.sort(new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    int mulo1 = o1[0] * o1[1];
                    int mulo2 = o2[0] * o2[1];

                    if(mulo1 < mulo2) return -1;
                    else if(mulo1 > mulo2) return 1;
                    else {
                        if(o1[0] < o2[0]) return -1;
                        else if(o1[0] > o2[0]) return 1;
                        else {
                            if(o1[1] < o2[1]) return -1;
                            else return 1;
                        }
                    }
                }
            });


            System.out.print("#" + test_case + " ");
            System.out.print(list.size() + " ");
            for(int i = 0; i < list.size(); i++) {
                for(int j : list.get(i)) {
                    System.out.print(j + " ");
                }
            }
            System.out.println();
        }
    }
}

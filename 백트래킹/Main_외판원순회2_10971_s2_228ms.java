package 백트래킹;

import java.util.Scanner;

public class Main_외판원순회2_10971_s2_228ms {
	static int n;
	static int[][] map;
	static int minD = Integer.MAX_VALUE;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();
		map = new int[n][n];

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		int visited = 0;
		for (int i = 0; i < n; i++) {
			dfs(i, i, 0, visited | 1 << i, 0);
		}

		System.out.println(minD);

	} // end of main

	public static void dfs(int start, int cur, int depth, int visited, int sum) {
		// backtracking
		if (sum > minD)
			return;

		// 최단거리 찾기
		if (depth == n - 1) {
			if (map[cur][start] > 0) {
				minD = Math.min(minD, sum + map[cur][start]);
			}
			return;
		}

		for (int i = 0; i < map.length; i++) {
			if (map[cur][i] == 0 || (visited & 1 << i) > 0)
				continue;
			dfs(start, i, ++depth, visited | 1 << i, sum + map[cur][i]);
		}
	} // end of dfs

}

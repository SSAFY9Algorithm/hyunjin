package dfs_bfs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main_보물섬_2589_g5 {
	static char[][] map;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();

		map = new char[n][m];
		for (int i = 0; i < n; i++) {
			map[i] = sc.next().toCharArray();
		}
		
		// 1. map의 모든 육지에 대해 bfs 실행
		// 2. 최장 거리 찾기
		int maxDist = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] == 'L') {
					int dist = bfs(i, j, n, m);
					maxDist = Math.max(maxDist, dist - 1);
				}
			}
		}

		System.out.println(maxDist);

	} // end of main

	public static int bfs(int x, int y, int n, int m) {
		int[][] visited = new int[n][m];
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] { x, y });
		visited[x][y] = 1;

		int dist = 0;
		int[] dx = { 1, 0, -1, 0 };
		int[] dy = { 0, 1, 0, -1 };
		while (queue.size() > 0) {
			int[] cur = queue.poll();
			for (int i = 0; i < 4; i++) {
				int nx = cur[0] + dx[i];
				int ny = cur[1] + dy[i];
				if (nx < n && nx >= 0 && ny < m && ny >= 0 && map[nx][ny] == 'L' && visited[nx][ny] == 0) {
					queue.add(new int[] { nx, ny });
					visited[nx][ny] = visited[cur[0]][cur[1]] + 1;
					dist = Math.max(dist, visited[nx][ny]);
				}
			} // end of while
		}
		return dist;
	} // end of bfs

}// end of class

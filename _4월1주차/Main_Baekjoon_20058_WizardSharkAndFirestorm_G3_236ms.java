package _4월1주차;

import java.io.*;
import java.util.*;

public class Main_Baekjoon_20058_WizardSharkAndFirestorm_G3_236ms {
	static int n, q;
	static int[][] map;
	static int[] levels;
	static int totalIce, sizeIce;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		n = (int) Math.pow(2, n);
		q = Integer.parseInt(st.nextToken());

		map = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		levels = new int[q];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < q; i++) {
			levels[i] = Integer.parseInt(st.nextToken());
		}

		solution();
		System.out.println(totalIce);
		System.out.println(sizeIce);
	}

	private static void solution() {
		// TODO 문제풀이 함수
		for (int i = 0; i < q; i++) {
			if (levels[i] != 0)
				fireStorm(levels[i]);
			checkIce();
		}
		findTotalIce();
		findMaxIce();
	}

	private static void fireStorm(int level) {
		// TODO 파이어스톰 시전하기
		int len = (int) Math.pow(2, level);
		int[][] newMap = new int[n][n];
		for (int i = 0; i < n; i += len) {
			for (int j = 0; j < n; j += len) {
				rotate(newMap, i, j, len);
			}
		}
		map = newMap;
	}

	private static void rotate(int[][] newMap, int r, int c, int len) {
		// TODO 격자 시계방향으로 90도 회전시키기
		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len; j++) {
				newMap[r + j][c + len - i - 1] = map[r + i][c + j];
			}
		}
	}

	static int[][] d = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };

	private static void checkIce() {
		// TODO 얼음과 3개 이상 인접해있지 않은 칸 얼음의 양 1 줄이기
		int[][] newMap = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (map[i][j] == 0)
					continue;
				int cnt = 0;
				for (int k = 0; k < 4; k++) {
					int nx = i + d[k][0];
					int ny = j + d[k][1];
					if (nx < 0 || nx >= n || ny < 0 || ny >= n || map[nx][ny] == 0)
						continue;
					cnt++;
				}
				newMap[i][j] = map[i][j];
				if (cnt < 3)
					newMap[i][j]--;
			}
		}
		map = newMap;
	}

	private static void findTotalIce() {
		// TODO 남아있는 얼음의 양 구하기
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				totalIce += map[i][j];
			}
		}
	}

	private static void findMaxIce() {
		// TODO 가장 큰 얼음 덩어리 찾기
		boolean[][] visited = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (visited[i][j] || map[i][j] == 0)
					continue;
				doBFS(visited, i, j);
			}
		}
	}

	private static void doBFS(boolean[][] visited, int x, int y) {
		// TODO bfs
		Queue<int[]> queue = new ArrayDeque<>();
		int[] cur;
		int cnt = 1;
		queue.offer(new int[] { x, y });
		visited[x][y] = true;
		while (!queue.isEmpty()) {
			cur = queue.poll();
			for (int i = 0; i < 4; i++) {
				int nx = cur[0] + d[i][0];
				int ny = cur[1] + d[i][1];
				if (nx < 0 || nx >= n || ny < 0 || ny >= n || visited[nx][ny] || map[nx][ny] == 0)
					continue;
				visited[nx][ny] = true;
				cnt++;
				queue.offer(new int[] { nx, ny });
			}
		}
		sizeIce = Math.max(sizeIce, cnt);
	}
}

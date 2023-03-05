package 삼성기출;

import java.io.*;
import java.util.*;

// 15C3 * 15 * 15 = 102,375

public class Main_백준_17135_캐슬디펜스_골드3_416ms {

	static int n, m, d;
	static int[][] map;

	static int[] comb = new int[3];
	static int cnt;

	static class Enemy implements Comparable<Enemy> {
		int x, y, dist;

		public Enemy(int x, int y, int dist) {
			super();
			this.x = x;
			this.y = y;
			this.dist = dist;
		}

		@Override
		public int compareTo(Enemy o) {
			return this.dist == o.dist ? this.y - o.y : this.dist - o.dist;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());

		map = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		getCombination(0, 0);

		System.out.println(cnt);
	}

	public static void getCombination(int depth, int cur) {
		if (depth == 3) {
			int[][] copyMap = new int[n][m];
			for (int i = 0; i < n; i++) {
				System.arraycopy(map[i], 0, copyMap[i], 0, m);
			}
			cnt = Math.max(cnt, kill(copyMap));
			return;
		}
		for (int i = cur; i < m; i++) {
			comb[depth] = i;
			getCombination(depth + 1, i + 1);
		}
	}

	private static int kill(int[][] map) {
		// TODO 적 제거하기
		int cnt = 0;
		while (true) {
			// 적 위치 찾기
			Enemy e1 = findEnemy(comb[0], map);
			Enemy e2 = findEnemy(comb[1], map);
			Enemy e3 = findEnemy(comb[2], map);

			// 적 제거
			if (e1 != null) {
				map[e1.x][e1.y] = 0;
				cnt++;
			}
			if (e2 != null && map[e2.x][e2.y] == 1) {
				map[e2.x][e2.y] = 0;
				cnt++;
			}
			if (e3 != null && map[e3.x][e3.y] == 1) {
				map[e3.x][e3.y] = 0;
				cnt++;
			}

			// 적 1칸씩 이동
			map[n - 1] = new int[m];
			boolean isEmpty = true;
			for (int i = n - 2; i >= 0; i--) {
				for (int j = 0; j < m; j++) {
					if (map[i][j] == 1) {
						isEmpty = false;
						map[i + 1][j] = map[i][j];
					}
				}
				map[i] = new int[m];
			}

			// 남아있는 적이 없다면 종료
			if (isEmpty)
				break;
		}

		return cnt;
	}

	private static Enemy findEnemy(int c, int[][] map) {
		// TODO 가장 가까운 적 찾기
		PriorityQueue<Enemy> pq = new PriorityQueue<Enemy>();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] != 0) {
					int dist = Math.abs(i - n) + Math.abs(j - c);
					if (dist <= d)
						pq.offer(new Enemy(i, j, dist));
				}
			}
		}
		return pq.isEmpty() ? null : pq.poll();
	}
}

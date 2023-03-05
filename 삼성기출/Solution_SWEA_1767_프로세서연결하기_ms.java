package 삼성기출;

import java.io.*;
import java.util.*;

public class Solution_SWEA_1767_프로세서연결하기_ms {
	static int n;
	static int[][] map;
	static List<int[]> cores;
	static PriorityQueue<Wire> pq;

	static class Wire implements Comparable<Wire> {
		int coreCnt, len;

		public Wire(int coreCnt, int len) {
			super();
			this.coreCnt = coreCnt;
			this.len = len;
		}

		@Override
		public int compareTo(Wire o) {
			return this.coreCnt == o.coreCnt ? this.len - o.len : o.coreCnt - this.coreCnt;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {

			n = Integer.parseInt(br.readLine());

			map = new int[n][n];
			cores = new ArrayList<int[]>();
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == 1 && i < n - 1 && i > 0 && j < n - 1 && j > 0)
						cores.add(new int[] { i, j });
				}
			}

			pq = new PriorityQueue<Wire>();
			for (int i = 0; i < 4; i++) {
				findWire(0, i, 0, 0);
			}

			sb.append(String.format("#%d %d\n", tc, pq.poll().len));

		} // end of tc

		System.out.println(sb);
	}

	// dir -> 0:동 1:서 2:남 3:북
	public static void findWire(int depth, int dir, int cnt, int len) {
		if (depth == cores.size()) {
			pq.offer(new Wire(cnt, len));
			if (cnt == 8 && len==23) {
				for (int[] row : map) {
					System.out.println(Arrays.toString(row));
				}
				System.out.println(len);
			}
			return;
		}
		int x = cores.get(depth)[0];
		int y = cores.get(depth)[1];

		// 원래 멕시노스
		int[] row = new int[n];
		System.arraycopy(map[x], 0, row, 0, n);
		int[] col = new int[n];
		for (int i = 0; i < n; i++) {
			col[i] = map[i][y];
		}

		int i;
		switch (dir) {
		// 동
		case 0:
			for (i = y + 1; i < n; i++) {
				if (map[x][i] != 0)
					break;
				map[x][i] = 2;
			}
			// 전선 연결 가능
			if (i == n) {
				for (int j = 0; j < 4; j++) {
					findWire(depth + 1, j, cnt + 1, len + (n - y - 1));
				}
			}
			// 전선 연결 불가능
			else {
				// 멕시노스 복구
				map[x] = row;
				for (int j = 0; j < 4; j++) {
					findWire(depth + 1, j, cnt, len);
				}
			}
			// 멕시노스 복구
			map[x] = row;
			break;
		// 서
		case 1:
			for (i = y - 1; i >= 0; i--) {
				if (map[x][i] != 0)
					break;
				map[x][i] = 2;
			}
			// 전선 연결 가능
			if (i == -1) {
				for (int j = 0; j < 4; j++) {
					findWire(depth + 1, j, cnt + 1, len + y);
				}
			}
			// 전선 연결 불가능
			else {
				// 멕시노스 복구
				map[x] = row;
				for (int j = 0; j < 4; j++) {
					findWire(depth + 1, j, cnt, len);
				}
			}
			// 멕시노스 복구
			map[x] = row;
			break;
		// 남
		case 2:
			for (i = x + 1; i < n; i++) {
				if (map[i][y] != 0)
					break;
				map[i][y] = 2;
			}
			// 전선 연결 가능
			if (i == n) {
				for (int j = 0; j < 4; j++) {
					findWire(depth + 1, j, cnt + 1, len + (n - x - 1));
				}
			}
			// 전선 연결 불가능
			else {
				// 멕시노스 복구
				for (int j = x + 1; j < n; j++) {
					map[j][y] = col[j];
				}
				for (int j = 0; j < 4; j++) {
					findWire(depth + 1, j, cnt, len);
				}
			}
			// 멕시노스 복구
			for (int j = x + 1; j < n; j++) {
				map[j][y] = col[j];
			}
			break;
		// 북
		case 3:
			for (i = x - 1; i >= 0; i--) {
				if (map[i][y] != 0)
					break;
				map[i][y] = 2;
			}
			// 전선 연결 가능
			if (i == -1) {
				for (int j = 0; j < 4; j++) {
					findWire(depth + 1, j, cnt + 1, len + x);
				}
			}
			// 전선 연결 불가능
			else {
				// 멕시노스 복구
				for (int j = x - 1; j >= 0; j--) {
					map[j][y] = col[j];
				}
				for (int j = 0; j < 4; j++) {
					findWire(depth + 1, j, cnt, len);
				}
			}
			// 멕시노스 복구
			for (int j = x - 1; j >= 0; j--) {
				map[j][y] = col[j];
			}
			break;
		}
//		}

	}
}

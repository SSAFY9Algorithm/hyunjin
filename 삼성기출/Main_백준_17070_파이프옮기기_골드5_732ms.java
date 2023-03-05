package 삼성기출;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_백준_17070_파이프옮기기_골드5_732ms {
	static int cnt, n;
	static int[][] map;

	static class Pipe {
		// dir -> 1:가로 2:세로 3:대각선
		int x1, y1, x2, y2, dir;

		public Pipe(int x1, int y1, int x2, int y2, int dir) {
			super();
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
			this.dir = dir;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		bfs();

		System.out.println(cnt);
	}

	public static void bfs() {
		Queue<Pipe> queue = new ArrayDeque<Pipe>();
		queue.offer(new Pipe(0, 0, 0, 1, 1));
		Pipe cur;
		while (!queue.isEmpty()) {
			cur = queue.poll();

			if (cur.x2 == n - 1 && cur.y2 == n - 1) {
				cnt++;
				continue;
			}

			// 가로
			if (cur.dir != 2 && cur.y2 + 1 < n && map[cur.x2][cur.y2 + 1] == 0)
				queue.offer(new Pipe(cur.x2, cur.y2, cur.x2, cur.y2 + 1, 1));
			// 세로
			if (cur.dir != 1 && cur.x2 + 1 < n && map[cur.x2 + 1][cur.y2] == 0)
				queue.offer(new Pipe(cur.x2, cur.y2, cur.x2 + 1, cur.y2, 2));
			// 대각선
			if (cur.y2 + 1 < n && cur.x2 + 1 < n && map[cur.x2][cur.y2 + 1] == 0 && map[cur.x2 + 1][cur.y2] == 0
					&& map[cur.x2 + 1][cur.y2 + 1] == 0)
				queue.offer(new Pipe(cur.x2, cur.y2, cur.x2 + 1, cur.y2 + 1, 3));
		}
	}
}

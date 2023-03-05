package 삼성기출;

import java.io.*;
import java.util.*;

public class Solution_SWEA_2115_벌꿀채취_133ms {
	static class Honey implements Comparable<Honey> {
		int r, c1, c2, price;

		public Honey(int r, int c1, int c2, int price) {
			super();
			this.r = r;
			this.c1 = c1;
			this.c2 = c2;
			this.price = price;
		}

		@Override
		public int compareTo(Honey o) {
			return o.price - this.price;
		}
	}

	static int n, m, c;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {

			st = new StringTokenizer(br.readLine());

			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());

			map = new int[n][n];
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			PriorityQueue<Honey> pq = new PriorityQueue<Honey>();
			findHoney(pq);

			Honey h1 = pq.poll();
			Honey h2;
			while (true) {
				h2 = pq.poll();
				// 1. 서로 다른 행일 때
				// 2. 열이 겹치지 않을 때
				if (h2.r != h1.r || h1.c2 < h2.c1) {
					sb.append(String.format("#%d %d\n", tc, h1.price + h2.price));
					break;
				}
			}

		} // end of tc

		System.out.println(sb);
	}

	private static void findHoney(PriorityQueue<Honey> pq) {
		// TODO 가능한 채취할 수 있는 모든 경우의 벌꿀 찾기
		for (int i = 0; i < n; i++) {
			// 한 행에서 채취할 수 있는 모든 조합
			for (int j = 0; j <= n - m; j++) {
				int maxPrice = 0;
				// m개의 연속한 벌통에서 채취할 수 있는 부분집합
				// 그 중에서 최대 수익을 가지는 부분집합 찾기
				for (int flag = 0; flag < 1 << m; flag++) {
					int sum = 0;
					int price = 0;
					for (int idx = 0; idx < m; idx++) {
						if ((flag & 1 << idx) != 0) {
							sum += map[i][j + idx];
							price += map[i][j + idx] * map[i][j + idx];
						}
					}
					// 최대로 채취할 수 있는 양 이하일 때
					if (sum <= c)
						maxPrice = Math.max(maxPrice, price);
				}
				pq.offer(new Honey(i, j, j + m - 1, maxPrice));
			}
		}
	}

}

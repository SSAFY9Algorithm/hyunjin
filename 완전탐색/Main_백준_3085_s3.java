package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_백준_3085_s3 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		char[][] map = new char[n][n];
		for (int i = 0; i < map.length; i++) {
			st = new StringTokenizer(br.readLine());
			String s = st.nextToken();
			for (int j = 0; j < map.length; j++) {
				map[i][j] = s.charAt(j);
			}
		} // get inputs

		int max = 0;
		// row
		// 왼쪽 -> 오른쪽
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				int idx = 1;
				while (j + idx < n && map[i][j] == map[i][j + idx]) {
					idx++;
				}
				if (j + idx + 1 < n && map[i][j] == map[i][j + idx + 1])
					idx++;
				max = Math.max(max, idx);
			}
		}
		// 오른쪽 -> 왼쪽
		for (int i = 0; i < map.length; i++) {
			for (int j = map.length - 1; j >= 0; j--) {
				int idx = 1;
				while (j - idx >= 0 && map[i][j] == map[i][j - idx]) {
					idx++;
				}
				if (j - idx - 1 >= 0 && map[i][j] == map[i][j - idx - 1])
					idx++;
				max = Math.max(max, idx);
			}
		}

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				int[] dx = { -1, 1 };
				// 위아래 인접 칸 색깔 다를 때
				for (int k = 0; k < 2; k++) {
					int cnt = 0;
					int nx = i + dx[k];
					if (nx < n && nx >= 0) {
						// 왼쪽 위, 왼쪽 아래
						int idx = 1;
						while (j - idx >= 0 && map[nx][j - idx] == map[i][j]) {
							idx++;
						}
						cnt += idx;
//						max = Math.max(max, idx);
						// 오른쪽 위, 오른쪽 아래
						idx = 1;
						while (j + idx < n && map[nx][j + idx] == map[i][j]) {
							idx++;
						}
						cnt += idx;
//						max = Math.max(max, idx);
					}
					max = Math.max(max, cnt-1);
				}
			}
		}

		// col
		// 위 -> 아래
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				int idx = 1;
				while (j + idx < n && map[j][i] == map[j + idx][i]) {
					idx++;
				}
				if (j + idx + 1 < n && map[j][i] == map[j + idx + 1][i])
					idx++;
				max = Math.max(max, idx);
			}
		}
		// 아래 -> 위
		for (int i = 0; i < map.length; i++) {
			for (int j = map.length - 1; j >= 0; j--) {
				int idx = 1;
				while (j - idx >= 0 && map[j][i] == map[j - idx][i]) {
					idx++;
				}
				if (j - idx - 1 >= 0 && map[j][i] == map[j - idx - 1][i])
					idx++;
				max = Math.max(max, idx);
			}
		}

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				int[] dy = { -1, 1 };
				// 왼쪽오른쪽 인접 칸 색깔 다를 때
				for (int k = 0; k < 2; k++) {
					int cnt = 0;
					int ny = i + dy[k];
					if (ny < n && ny >= 0) {
						// 왼쪽 위, 왼쪽 아래
						int idx = 1;
						while (j - idx >= 0 && map[j - idx][ny] == map[j][i]) {
							idx++;
						}
						cnt += idx;
//						max = Math.max(max, idx);
						// 오른쪽 위, 오른쪽 아래
						idx = 1;
						while (j + idx < n && map[j + idx][ny] == map[j][i]) {
							idx++;
						}
						cnt += idx;
//						max = Math.max(max, idx);
					}
					max = Math.max(max, cnt-1);
				}
			}
		}

		System.out.println(max);

	} // end of main
} // end of class

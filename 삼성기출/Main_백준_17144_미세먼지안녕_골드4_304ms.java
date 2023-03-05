package 삼성기출;

import java.io.*;
import java.util.*;

public class Main_백준_17144_미세먼지안녕_골드4_304ms {
	static int r, c, t;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());

		map = new int[r][c];
		int a = -1, b = -1;
		for (int i = 0; i < r; i++) {
			st = new StringTokenizer(br.readLine());
			map[i][0] = Integer.parseInt(st.nextToken());
			// 공기청정기 위치 찾기
			if (a == -1 && map[i][0] == -1) {
				a = i;
				b = i + 1;
			}
			for (int j = 1; j < c; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		while (t-- > 0) {
			spread(a, b);
			airCleaner(a, b);
		}

		int cnt = 0;
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (map[i][j] > 0)
					cnt += map[i][j];
			}
		}

		System.out.println(cnt);
	}

	static int[] dx = { 1, 0, -1, 0 }, dy = { 0, 1, 0, -1 };

	private static void spread(int a, int b) {
		// TODO 미세먼지 확산
		int[][] newMap = new int[r][c];
		newMap[a][0] = newMap[b][0] = -1;
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (map[i][j] > 0) {
					int dirt = map[i][j] / 5;
					int cnt = 0;
					for (int k = 0; k < 4; k++) {
						int nx = i + dx[k];
						int ny = j + dy[k];
						if (nx < r && nx >= 0 && ny < c && ny >= 0&&newMap[nx][ny] != -1) {
							cnt++;
							newMap[nx][ny] += dirt;
						}
					}
					newMap[i][j] += map[i][j] - dirt * cnt;
				}
			}
		}
		map = newMap;
	}

	private static void airCleaner(int a, int b) {
		// TODO 공기 청정기 작동
		counterClockwise(a);
		clockwise(b);
	}

	private static void counterClockwise(int start) {
		// TODO 반시계방향 회전
		// 위->아래
		for (int i = start; i > 0; i--) {
			map[i][0] = map[i - 1][0];
		}
		// 오른쪽->왼쪽
		for (int i = 0; i < c - 1; i++) {
			map[0][i] = map[0][i + 1];
		}
		// 아래->위
		for (int i = 0; i < start; i++) {
			map[i][c - 1] = map[i + 1][c - 1];
		}
		// 왼쪽->오른쪽
		for (int i = c - 1; i > 0; i--) {
			map[start][i] = map[start][i - 1];
		}
		map[start][0] = -1;
		map[start][1] = 0;
	}

	private static void clockwise(int start) {
		// TODO 시계방향 회전
		// 아래->위
		for (int i = start; i < r - 1; i++) {
			map[i][0] = map[i + 1][0];
		}
		// 오른쪽->왼쪽
		for (int i = 0; i < c - 1; i++) {
			map[r - 1][i] = map[r - 1][i + 1];
		}
		// 위->아래
		for (int i = r - 1; i > start; i--) {
			map[i][c - 1] = map[i - 1][c - 1];
		}
		// 왼쪽->오른쪽
		for (int i = c - 1; i > 0; i--) {
			map[start][i] = map[start][i - 1];
		}
		map[start][0] = -1;
		map[start][1] = 0;
	}
}

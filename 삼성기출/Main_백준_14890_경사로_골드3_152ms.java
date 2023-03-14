package 삼성기출;

import java.io.*;
import java.util.*;

public class Main_백준_14890_경사로_골드3_152ms {
	// O(n^2) 200*100 + 200*100 = 40000

	static int n, l;
	static int[][] map;
	// 경사로 설치 유무
	static boolean[][] isExist;
	// 지나갈 수 있는 길의 개수
	static int cnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		l = Integer.parseInt(st.nextToken());

		map = new int[n][n];
		isExist = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		checkRow();
		checkCol();

		System.out.println(cnt);
	}

	private static void checkRow() {
		// TODO 열에서 지나갈 수 있는지를 판단
		// 왼쪽 -> 오른쪽
		for (int i = 0; i < n; i++) {
			int needHeight = 0, lowCnt = 0;
			int j = 0;
			for (; j < n; j++) {
				// 경사로가 들어갈 수 있는지 판단
				if (needHeight > 0) {
					// 경사로를 놓을 수 있는 높이인 경우
					if (map[i][j] == needHeight && !isExist[i][j]) {
						isExist[i][j] = true;
						lowCnt++;
						// 경사로를 놓을 수 있는 길이가 된다면
						if (lowCnt == l) {
							needHeight = 0;
							lowCnt = 0;
						}
					}
					// 경사로를 놓을 수 있는 높이보다 높거나 낮은 경우
					// 이미 경사로가 놓여져 있는 경우
					else {
						break;
					}
				}
				// 다음 칸이 현재 칸보다 낮다면
				// 경사로 놓을 수 있는 높이 설정
				if (needHeight == 0 && j < n - 1 && map[i][j + 1] < map[i][j]) {
					if (map[i][j + 1] + 1 != map[i][j])
						break;
					needHeight = map[i][j + 1];
				}

			} // end of 왼쪽 -> 오른쪽

			// 오른쪽 -> 왼쪽
			if (j == n && needHeight == 0) {
				j--;
				for (; j >= 0; j--) {
					// 경사로가 들어갈 수 있는지 판단
					if (needHeight > 0) {
						// 경사로를 놓을 수 있는 높이인 경우
						if (map[i][j] == needHeight && !isExist[i][j]) {
							isExist[i][j] = true;
							lowCnt++;
							// 경사로를 놓을 수 있는 길이가 된다면
							if (lowCnt == l) {
								needHeight = 0;
								lowCnt = 0;
							}
						}
						// 경사로를 놓을 수 있는 높이보다 높거나 낮은 경우
						// 이미 경사로가 놓여져 있는 경우
						else {
							break;
						}
					}
					// 다음 칸이 현재 칸보다 낮다면
					// 경사로 놓을 수 있는 높이 설정
					if (needHeight == 0 && j > 0 && map[i][j - 1] < map[i][j]) {
						if (map[i][j - 1] + 1 != map[i][j])
							break;
						needHeight = map[i][j - 1];
					}

				} // end of checking each row

				// 해당 행 통과 가능
				if (j == -1 && needHeight == 0) {
					System.out.println("row:" + i);
					cnt++;
				}
			} // end of 오른쪽 -> 왼쪽

			// 경사로 초기화
			isExist[i] = new boolean[n];

		}
	}

	private static void checkCol() {
		// TODO 행에서 지나갈 수 있는지를 판단
		// 요구되는 높이
		for (int i = 0; i < n; i++) {
			// 위 -> 아래
			int needHeight = 0, lowCnt = 0;
			int j = 0;
			for (; j < n; j++) {
				// 경사로가 들어갈 수 있는지 판단
				if (needHeight > 0) {
					// 경사로를 놓을 수 있는 높이인 경우
					if (map[j][i] == needHeight && !isExist[j][i]) {
						isExist[j][i] = true;
						lowCnt++;
						// 경사로를 놓을 수 있는 길이가 된다면
						if (lowCnt == l) {
							needHeight = 0;
							lowCnt = 0;
						}
					}
					// 경사로를 놓을 수 있는 높이보다 높거나 낮은 경우
					// 이미 경사로가 놓여져 있는 경우
					else {
						break;
					}
				}
				// 다음 칸이 현재 칸보다 낮다면
				// 경사로 놓을 수 있는 높이 설정
				if (needHeight == 0 && j < n - 1 && map[j + 1][i] < map[j][i]) {
					if (map[j + 1][i] + 1 != map[j][i])
						break;
					needHeight = map[j + 1][i];
				}

			} // end of 위 -> 아래

			// 아래 -> 위
			if (j == n && needHeight == 0) {
				j--;
				for (; j >= 0; j--) {
					// 경사로가 들어갈 수 있는지 판단
					if (needHeight > 0) {
						// 경사로를 놓을 수 있는 높이인 경우
						if (map[j][i] == needHeight && !isExist[j][i]) {
							isExist[j][i] = true;
							lowCnt++;
							// 경사로를 놓을 수 있는 길이가 된다면
							if (lowCnt == l) {
								needHeight = 0;
								lowCnt = 0;
							}
						}
						// 경사로를 놓을 수 있는 높이보다 높거나 낮은 경우
						// 이미 경사로가 놓여져 있는 경우
						else {
							break;
						}
					}
					// 다음 칸이 현재 칸보다 낮다면
					// 경사로 놓을 수 있는 높이 설정
					if (needHeight == 0 && j > 0 && map[j - 1][i] < map[j][i]) {
						if (map[j - 1][i] + 1 != map[j][i])
							break;
						needHeight = map[j - 1][i];
					}
				}

				// 해당 열 통과 가능
				if (j == -1 && needHeight == 0) {
					System.out.println("col:" + i);
					cnt++;
				}
			} // end of 아래 -> 위
		}
	}
}

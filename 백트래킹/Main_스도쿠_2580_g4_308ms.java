package 백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_스도쿠_2580_g4_308ms {
	// 스도쿠 판
	static int[][] map = new int[9][9];
	// 빈칸 좌표 배열
	static List<Integer[]> coords = new ArrayList<Integer[]>();
	// 총 합, 사용한 수 정보(row, col, 3*3 별로 저장)
	static int[][] rInfo = new int[9][2]; // co1:sum, col2:사용한 수
	static int[][] cInfo = new int[9][2];
	static int[][] boxInfo = new int[9][2];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		for (int i = 0; i < 9; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 9; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 0)
					coords.add(new Integer[] { i, j });
				else {
					int bIdx = (i / 3) * 3 + j / 3;
					rInfo[i][0] += map[i][j];
					rInfo[i][1] = rInfo[i][1] | 1 << map[i][j];
					cInfo[j][0] += map[i][j];
					cInfo[j][1] = cInfo[j][1] | 1 << map[i][j];
					boxInfo[bIdx][0] += map[i][j];
					boxInfo[bIdx][1] = boxInfo[bIdx][1] | 1 << map[i][j];
				}
			}
		}

		dfs(0);
	}

	public static void dfs(int depth) {
		if (depth == coords.size()) {
			print();
			System.exit(0);
		}

		int x = coords.get(depth)[0], y = coords.get(depth)[1];
		for (int i = 1; i < 10; i++) {
			int bIdx = (x / 3) * 3 + y / 3;
			// 이미 사용한 수 일 때
			if ((rInfo[x][1] & 1 << i) > 0 || (cInfo[y][1] & 1 << i) > 0 || (boxInfo[bIdx][1] & 1 << i) > 0)
				continue;

			map[x][y] = i;
			rInfo[x][0] += i;
			rInfo[x][1] = rInfo[x][1] | 1 << i;
			cInfo[y][0] += i;
			cInfo[y][1] = cInfo[y][1] | 1 << i;
			boxInfo[bIdx][0] += i;
			boxInfo[bIdx][1] = boxInfo[bIdx][1] | 1 << i;

			// 다음 빈칸 탐색
			dfs(depth + 1);

			map[x][y] = 0;
			rInfo[x][0] -= i;
			rInfo[x][1] = rInfo[x][1] & ~(1 << i);
			cInfo[y][0] -= i;
			cInfo[y][1] = cInfo[y][1] & ~(1 << i);
			boxInfo[bIdx][0] -= i;
			boxInfo[bIdx][1] = boxInfo[bIdx][1] & ~(1 << i);
		}
	}

	// 스도쿠 출력 함수
	public static void print() {
		StringBuilder sb = new StringBuilder();
		for (int[] row : map) {
			for (int e : row) {
				sb.append(e + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}

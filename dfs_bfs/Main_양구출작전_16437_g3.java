package dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 시간초과 남
public class Main_양구출작전_16437_g3 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());
		boolean[] hasChildren = new boolean[n + 1];
		// col0:부모 섬 번호, col1:늑대 수, col2:양 수
		int[][] map = new int[n + 1][3];
		for (int i = 2; i < map.length; i++) {
			st = new StringTokenizer(br.readLine());
			if (st.nextToken().equals("W"))
				map[i][1] = Integer.parseInt(st.nextToken());
			else
				map[i][2] = Integer.parseInt(st.nextToken());
			map[i][0] = Integer.parseInt(st.nextToken());
			hasChildren[map[i][0]] = true;
		}

		long res = 0;
		// 리프 노드들을 대상으로
		for (int i = 2; i < hasChildren.length; i++) {
			if (hasChildren[i])
				continue;
			long sTotal = map[i][2];
			int cur = i, next = map[cur][0];
			while (next != 1) {
				// 늑대를 만난 경우
				if (map[next][1] > 0) {
					// 늑대가 양을 잡아 먹음
					if (sTotal > map[next][1]) {
						sTotal -= map[next][1];
						map[next][1] = 0;
					}
					// 가는 중간에 양이 모두 잡아 먹힌 경우
					else {
						map[next][1] -= sTotal;
						sTotal = 0;
					}
				}
				// 양을 만난 경우
				else if (map[next][2] > 0) {
					sTotal += map[next][2];
					map[next][2] = 0;
				}
				cur = next;
				next = map[cur][0];
			}
			res += sTotal;
		}

		System.out.println(res);

	} // end of main
} // end of class

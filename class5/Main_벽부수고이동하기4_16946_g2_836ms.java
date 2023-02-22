package class5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class Main_벽부수고이동하기4_16946_g2_836ms {
	static char[][] map;
	static int n, m;

	static int idx = 1;
	static int[][] checkedMap;
	static Map<Integer, Integer> bfsCnt = new HashMap<Integer, Integer>();

	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		map = new char[n][m];
		for (int i = 0; i < n; i++) {
			map[i] = br.readLine().toCharArray();
		}

		// 이동할 수 있는 부분 구역별로 표시
		checkedMap = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] == '0' && checkedMap[i][j] == 0) {
					int cnt = bfs(i, j);
					// 해당하는 구역에 포함되는 칸의 개수 저장
					bfsCnt.put(idx, cnt);
					idx++;
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		Set<Integer> idxList = new HashSet<>();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				// 현재 벽이라면
				if (map[i][j] == '1') {
					int total = 0;
					// 동서남북의 구역 번호 확인
					for (int k = 0; k < 4; k++) {
						int nx = i + dx[k];
						int ny = j + dy[k];
						if (nx < n && nx >= 0 && ny < m && ny >= 0 && checkedMap[nx][ny] > 0) {
							// 동일한 구역이면 중복해서 합을 구하지 않도록 방지
							if (!idxList.contains(checkedMap[nx][ny])) {
								idxList.add(checkedMap[nx][ny]);
								// 구역번호를 통해 갈 수 있는 칸의 개수 총 합 계산
								total += bfsCnt.get(checkedMap[nx][ny]);
							}
						}
					}
					sb.append((total + 1) % 10);
					idxList.clear();
				} else
					sb.append(0);
			}
			sb.append("\n");
		}

		System.out.println(sb);

	}

	private static int bfs(int x, int y) {
		Deque<int[]> dq = new ArrayDeque<int[]>();
		dq.offerLast(new int[] { x, y });
		checkedMap[x][y] = idx;

		int[] cur;
		int cnt = 1;
		while (dq.size() > 0) {
			cur = dq.pollFirst();
			for (int i = 0; i < 4; i++) {
				int nx = cur[0] + dx[i];
				int ny = cur[1] + dy[i];
				if (nx < n && nx >= 0 && ny < m && ny >= 0 && map[nx][ny] == '0' && checkedMap[nx][ny] == 0) {
					checkedMap[nx][ny] = idx;
					dq.offerLast(new int[] { nx, ny });
					cnt++;
				}
			}
		}
		return cnt;
	}
}

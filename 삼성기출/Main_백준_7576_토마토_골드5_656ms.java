package 삼성기출;

import java.io.*;
import java.util.*;

public class Main_백준_7576_토마토_골드5_656ms {
	static int[][] map;
	static int n, m, tomatoCnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		map = new int[n][m];

		List<int[]> tCoords = new ArrayList<int[]>();
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] != -1)
					tomatoCnt++;
				if (map[i][j] == 1)
					tCoords.add(new int[] { i, j });
			}
		}

		System.out.println(bfs(tCoords));

	}

	private static int bfs(List<int[]> tCoords) {
		int cnt = 0;
		Queue<int[]> queue = new ArrayDeque<int[]>();
		int[][] visited = new int[n][m];
		int[] dx = { 1, 0, -1, 0 }, dy = { 0, 1, 0, -1 };

		queue.addAll(tCoords);
		for (int[] coord : tCoords) {
			visited[coord[0]][coord[1]] = 1;
		}

		int[] cur = new int[2];
		while (!queue.isEmpty()) {
			cur = queue.poll();
			cnt++;
			for (int i = 0; i < 4; i++) {
				int nx = cur[0] + dx[i];
				int ny = cur[1] + dy[i];
				if (nx < n && nx >= 0 && ny < m && ny >= 0 && map[nx][ny] != -1 && visited[nx][ny] == 0) {
					visited[nx][ny] = visited[cur[0]][cur[1]] + 1;
					queue.offer(new int[] { nx, ny });
				}
			}
		}

		if (tomatoCnt == cnt)
			return visited[cur[0]][cur[1]] - 1;
		return -1;
	}
}

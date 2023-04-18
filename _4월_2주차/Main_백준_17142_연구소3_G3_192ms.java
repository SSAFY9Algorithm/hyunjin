package _4월_2주차;

import java.io.*;
import java.util.*;

public class Main_백준_17142_연구소3_G3_192ms {
	static int n, m, spaceCnt, minTime = 10000;
	static int[][] map;
	static List<int[]> virus = new ArrayList<int[]>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				// 채워야 하는 칸 수 세기
				if (map[i][j] == 0)
					spaceCnt++;
				// 바이러스 있는 칸 저장
				else if (map[i][j] == 2)
					virus.add(new int[] { i, j });
			}
		}

		solution();

		System.out.println(minTime == 10000 ? -1 : minTime);
	}

	private static void solution() {
		// TODO 문제 풀이 함수
		findTime(0, -1, 0);
	}

	private static void findTime(int depth, int start, int selected) {
		// TODO 활성화 된 바이러스 경우의 수
		if (depth == m) {
			minTime = Math.min(minTime, doBFS(selected));
			return;
		}
		for (int i = start + 1; i < virus.size(); i++) {
			findTime(depth + 1, i, selected | 1 << i);
		}
	}

	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	private static int doBFS(int selected) {
		// TODO bfs 실행
		Queue<int[]> queue = new ArrayDeque<int[]>();
		int[][] v = new int[n][n];
		// 활성화된 바이러스 큐에 넣기
		for (int i = 0; i < virus.size(); i++) {
			if ((selected & 1 << i) != 0) {
				int[] coord = virus.get(i);
				queue.offer(coord);
				v[coord[0]][coord[1]] = 1;
			}
		}
		int[] cur;
		int time = 0, cnt = 0;
		while (!queue.isEmpty()) {
			cur = queue.poll();
			for (int i = 0; i < 4; i++) {
				int nx = cur[0] + dx[i];
				int ny = cur[1] + dy[i];
				if (nx < 0 || nx >= n || ny < 0 || ny >= n || map[nx][ny] == 1 || v[nx][ny] != 0)
					continue;
				v[nx][ny] = v[cur[0]][cur[1]] + 1;
				if (map[nx][ny] == 0) {
					cnt++;
					// 백트래킹
					if (v[nx][ny] > minTime)
						return 10000;
					// 걸린 시간 구하기
					if (time < v[nx][ny])
						time = v[nx][ny];
				}
				queue.offer(new int[] { nx, ny });
			}
		}

		// 모든 칸 방문했는지 확인
		return cnt == spaceCnt ? time == 0 ? time : time - 1 : 10000;
	}
}

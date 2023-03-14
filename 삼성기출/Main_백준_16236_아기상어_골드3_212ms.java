package 삼성기출;

import java.io.*;
import java.util.*;

public class Main_백준_16236_아기상어_골드3_212ms {

	static class Fish implements Comparable<Fish> {
		int x, y, size, fishCnt, dist;

		public Fish(int x, int y, int size) {
			super();
			this.x = x;
			this.y = y;
			this.size = size;
		}

		public Fish(int x, int y, int size, int dist) {
			this(x, y, size);
			this.dist = dist;
		}

		@Override
		public int compareTo(Fish o) {
			return this.dist == o.dist ? this.x == o.x ? this.y - o.y : this.x - o.x : this.dist - o.dist;
		}
	}

	static int n, time;
	static int[][] map;
	static Fish shark;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				// 상어 위치 찾기
				if (map[i][j] == 9) {
					shark = new Fish(i, j, 2);
					map[i][j] = 0;
				}
			}
		}

		while (true) {
			if (!findFish())
				break;
		}

		System.out.println(time);
	}

	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	private static boolean findFish() {
		// TODO 먹을 수 있는 물고기 찾기
		Queue<int[]> queue = new ArrayDeque<int[]>();
		PriorityQueue<Fish> pq = new PriorityQueue<Fish>();
		int[][] visited = new int[n][n];
		queue.offer(new int[] { shark.x, shark.y });
		visited[shark.x][shark.y] = 1;
		int[] cur;
		// bfs
		while (!queue.isEmpty()) {
			cur = queue.poll();

			for (int i = 0; i < 4; i++) {
				int nx = cur[0] + dx[i];
				int ny = cur[1] + dy[i];
				if (nx < n && nx >= 0 && ny < n && ny >= 0 && map[nx][ny] <= shark.size && visited[nx][ny] == 0) {
					visited[nx][ny] = visited[cur[0]][cur[1]] + 1;
					queue.offer(new int[] { nx, ny });
					// 먹을 수 있는 물고기 리스트 생성
					if (map[nx][ny] > 0 && map[nx][ny] < shark.size) {
						pq.offer(new Fish(nx, ny, map[nx][ny], visited[nx][ny] - 1));
					}
				}
			}
		}

		// 모든 물고기를 먹은 경우 종료
		if (pq.isEmpty())
			return false;

		eatFish(pq);
		return true;
	}

	private static void eatFish(PriorityQueue<Fish> pq) {
		// TODO 물고기 먹기
		Fish fish = pq.poll();
		// 상어 이동
		map[fish.x][fish.y] = 0;
		shark.x = fish.x;
		shark.y = fish.y;
		// 물고기 먹기
		shark.fishCnt++;
		// 걸린 시간 계산
		time += fish.dist;
		// 물고기 먹은 개수랑 크기가 같은 경우
		if (shark.fishCnt == shark.size) {
			shark.size++;
			shark.fishCnt = 0;
		}
	}

}

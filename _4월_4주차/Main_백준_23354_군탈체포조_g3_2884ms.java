package _4월_4주차;

import java.util.*;
import java.io.*;

public class Main_백준_23354_군탈체포조_g3_2884ms {
	static int n, minSum = Integer.MAX_VALUE;
	static int[][] map;
	static int[] hCoord = new int[2];
	static int[][] dist;
	static Map<Integer, Integer> soldiers = new HashMap<Integer, Integer>();
	static List<Integer> srr = new ArrayList<Integer>();
	static int size;

	static class Node implements Comparable<Node> {
		int x, y, dist;

		public Node(int x, int y, int dist) {
			this.x = x;
			this.y = y;
			this.dist = dist;
		}

		@Override
		public int compareTo(Node o) {
			return this.dist - o.dist;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		map = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 0) {
					int coord = 1000 * i + j;
					soldiers.put(coord, srr.size());
					srr.add(coord);
				} else if (map[i][j] == -1) {
					hCoord[0] = i;
					hCoord[1] = j;
				}
			}
		}
		size = srr.size();
		if (size == 0) {
			System.out.println(0);
			return;
		}
		solve();
		System.out.println(minSum);
	}

	private static void solve() {
		// 1. 군인 일렬로 나열 경우의 수 찾기
		// 2. 모든 경우의 수에 대해 경로 계산
		getDist();
		int[] path = new int[soldiers.size()];
		findAllPath(0, 0, path);
	}

	private static void getDist() {
		dist = new int[size + 1][size + 1];
		for (int i = 0; i < size; i++) {
			int sX = srr.get(i) / 1000;
			int sY = srr.get(i) % 1000;
			map[sX][sY] = -2;
			dijkstra(sX, sY, i);
			map[sX][sY] = 0;
		}
	}

	private static void findAllPath(int depth, int selected, int[] path) {
		if (depth == size) {
			calDist(path);
			return;
		}
		for (int i = 0; i < size; i++) {
			if ((selected & 1 << i) != 0)
				continue;
			path[depth] = i;
			findAllPath(depth + 1, selected | 1 << i, path);
		}
	}

	private static void calDist(int[] path) {
		int sum = 0;
		sum += dist[path[0]][size];
		for (int i = 1; i < path.length; i++) {
			if (sum >= minSum)
				return;
			sum += dist[path[i - 1]][path[i]];
		}
		sum += dist[path[size - 1]][size];
		minSum = Math.min(minSum, sum);
	}

	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	private static void dijkstra(int x, int y, int sIdx) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(x, y, 0));
		boolean[][] visited = new boolean[n][n];
		Node cur;
		int cnt = 0;
		while (!pq.isEmpty()) {
			cur = pq.poll();
			if (visited[cur.x][cur.y])
				continue;
			visited[cur.x][cur.y] = true;

			if (map[cur.x][cur.y] == 0) {
				// 군인
				int idx = soldiers.get(cur.x * 1000 + cur.y);
				dist[sIdx][idx] = cur.dist;
				dist[idx][sIdx] = cur.dist;
				cnt++;
			} else if (map[cur.x][cur.y] == -1) {
				// 부대
				dist[sIdx][size] = cur.dist;
				dist[size][sIdx] = cur.dist;
				cnt++;
			}
			if (cnt == size)
				return;

			for (int i = 0; i < 4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				if (nx < 0 || nx >= n || ny < 0 || ny >= n || visited[nx][ny])
					continue;
				if (map[nx][ny] > 0) {
					pq.offer(new Node(nx, ny, cur.dist + map[nx][ny]));
				} else {
					pq.offer(new Node(nx, ny, cur.dist));
				}
			}
		}
	}
}

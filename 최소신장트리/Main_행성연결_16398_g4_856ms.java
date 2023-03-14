package 최소신장트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// MST
// prim

public class Main_행성연결_16398_g4_856ms {
	public static class Flow implements Comparable<Flow> {
		int dest;
		double cost;

		public Flow(int dest, double cost) {
			super();
			this.dest = dest;
			this.cost = cost;
		}

		@Override
		public int compareTo(Flow o) {
			return (int) (this.cost - o.cost);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());
		int[][] map = new int[n][n];
		for (int i = 0; i < map.length; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < map.length; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		PriorityQueue<Flow> pq = new PriorityQueue<Flow>();
		int cnt = 0;
		boolean[] visited = new boolean[n];
		double totalCost = 0;
		Flow cur;

		pq.offer(new Flow(0, 0));
		while (cnt < n) {
			cur = pq.poll();
			if (visited[cur.dest])
				continue;

			visited[cur.dest] = true;
			totalCost += cur.cost;
			cnt++;

			for (int i = 0; i < n; i++) {
				if (visited[i])
					continue;
				pq.add(new Flow(i, map[cur.dest][i]));
			}
		}
		
		System.out.printf("%.0f", totalCost);
	}
}

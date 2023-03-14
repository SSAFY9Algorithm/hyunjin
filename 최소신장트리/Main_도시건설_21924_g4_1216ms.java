package 최소신장트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// MST
// prim
// dense graph에 적합
// 시간복잡도: O(V^2)

public class Main_도시건설_21924_g4_1216ms {
	public static class Road implements Comparable<Road> {
		int dest;
		double dist;

		public Road(int dest, double dist) {
			super();
			this.dest = dest;
			this.dist = dist;
		}

		@Override
		public int compareTo(Road o) {
			return (int) (this.dist - o.dist);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		double totalDist = 0, minDist = 0;
		List<int[]>[] adj = new ArrayList[n + 1];
		while (m-- > 0) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());
			totalDist += dist;
			if (adj[a] == null)
				adj[a] = new ArrayList<int[]>();
			if (adj[b] == null)
				adj[b] = new ArrayList<int[]>();
			adj[a].add(new int[] { b, dist });
			adj[b].add(new int[] { a, dist });
		}

		PriorityQueue<Road> pq = new PriorityQueue<Road>();
		int cnt = 0;
		boolean[] visited = new boolean[n + 1];
		Road cur;

		pq.add(new Road(1, 0));
		while (cnt < n && pq.size() > 0) {
			cur = pq.poll();
			if (visited[cur.dest])
				continue;

			visited[cur.dest] = true;
			cnt++;
			minDist += cur.dist;

			for (int[] next : adj[cur.dest]) {
				if (visited[next[0]])
					continue;
				pq.offer(new Road(next[0], next[1]));
			}
		}

		System.out.printf("%.0f", cnt != n ? -1 : totalDist - minDist);
	}
}

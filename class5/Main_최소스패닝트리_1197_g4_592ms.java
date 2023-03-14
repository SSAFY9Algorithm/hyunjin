package class5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_최소스패닝트리_1197_g4_592ms {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int v = Integer.parseInt(st.nextToken());
		int e = Integer.parseInt(st.nextToken());

		List<int[]>[] adj = new ArrayList[v + 1];

		for (int i = 0; i < e; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());

			if (adj[a] == null)
				adj[a] = new ArrayList<int[]>();
			if (adj[b] == null)
				adj[b] = new ArrayList<int[]>();

			adj[a].add(new int[] { b, dist });
			adj[b].add(new int[] { a, dist });
		}

		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
		int[] cur;
		int weight = 0, cnt = 0;
		boolean[] visited = new boolean[v + 1];

		pq.add(new int[] { 1, 0 });
		while (cnt < v) {
			cur = pq.poll();

			if (visited[cur[0]])
				continue;
			visited[cur[0]] = true;
			weight += cur[1];
			cnt++;

			for (int[] next : adj[cur[0]]) {
				if (visited[next[0]])
					continue;
				pq.offer(next);
			}
		}

		System.out.println(weight);
	}
}

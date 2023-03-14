package class5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_도시분할계획_1647_g4_2280ms {
	static List<int[]>[] adjList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		adjList = new ArrayList[n + 1];

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());

			if (adjList[a] == null)
				adjList[a] = new ArrayList<int[]>();
			if (adjList[b] == null)
				adjList[b] = new ArrayList<int[]>();

			adjList[a].add(new int[] { b, dist });
			adjList[b].add(new int[] { a, dist });
		}

		int cnt = 0;
		List<Integer> ans = new ArrayList<>();
		boolean[] visited = new boolean[n + 1];
		PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a, b) -> a[1] - b[1]);
		pq.add(new int[] { 1, 0 });
		int[] cur;
		while (cnt < n) {
			cur = pq.poll();
			if (visited[cur[0]])
				continue;
			visited[cur[0]] = true;
			cnt++;
			ans.add(cur[1]);
			for (int[] next : adjList[cur[0]]) {
				if (visited[next[0]])
					continue;
				pq.offer(next);
			}
		}

		Collections.sort(ans);
		ans.remove(ans.size()-1);
		int total = 0;
		for (int i : ans) {
			total += i;
		}
		System.out.println(total);

	}
}

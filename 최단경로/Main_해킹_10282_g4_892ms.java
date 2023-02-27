package 최단경로;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// Dijkstra

public class Main_해킹_10282_g4_892ms {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int tc = 0; tc < T; tc++) {

			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			List<int[]>[] dep = new ArrayList[n + 1];
			while (d-- > 0) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int s = Integer.parseInt(st.nextToken());
				if (dep[b] == null)
					dep[b] = new ArrayList<int[]>();
				dep[b].add(new int[] { a, s });
			}

			PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a, b) -> a[1] - b[1]);
			boolean[] visited = new boolean[n + 1];
			int[] cur;
			int time = 0, cnt = 0;

			pq.offer(new int[] { c, 0 });
//			visited[c] = true;
			while (pq.size() > 0) {
				cur = pq.poll();
				if (visited[cur[0]])
					continue;

				visited[cur[0]] = true;
				cnt++;
				if (time < cur[1])
					time = cur[1];

				if (dep[cur[0]] == null)
					continue;
				for (int[] next : dep[cur[0]]) {
					if (visited[next[0]])
						continue;
					pq.offer(new int[] { next[0], cur[1] + next[1] });
				}
			}

			sb.append(cnt).append(" ").append(time).append("\n");

		} // end of tc

		System.out.println(sb);
	}
}

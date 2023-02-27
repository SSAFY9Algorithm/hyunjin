package 최소신장트리;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// mst
// dense graph
// prim

public class Main_우주신과의교감_1774_g3_340ms {
	public static class Node implements Comparable<Node> {
		int idx;
		Double dist;

		public Node(int idx, double dist) {
			this.idx = idx;
			this.dist = dist;
		}

		@Override
		public int compareTo(Node o) {
			return this.dist.compareTo(o.dist);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		int[][] coords = new int[n + 1][2];
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			coords[i][0] = Integer.parseInt(st.nextToken());
			coords[i][1] = Integer.parseInt(st.nextToken());
		}

		double[][] dist = new double[n + 1][n + 1];
		for (int i = 1; i <= n; i++) {
			for (int j = i + 1; j <= n; j++) {
				dist[i][j] = dist[j][i] = getDist(coords[i], coords[j]);
			}
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			dist[a][b] = dist[b][a] = 0;
		}

		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		double total = 0;
		boolean[] visited = new boolean[n + 1];
		int cnt = 0;
		Node cur;

		pq.offer(new Node(1, 0));
		while (true) {
			cur = pq.poll();
			if (visited[cur.idx])
				continue;

			visited[cur.idx] = true;
			total += cur.dist;
			cnt++;
			if (cnt == n)
				break;

			for (int i = 1; i < visited.length; i++) {
				if (i == cur.idx || visited[i])
					continue;
				pq.add(new Node(i, dist[cur.idx][i]));
			}
		}

		System.out.printf("%.2f", total);
	}

	public static double getDist(int[] c1, int[] c2) {
		return Math.sqrt(Math.pow((c1[0] - c2[0]), 2) + Math.pow((c1[1] - c2[1]), 2));
	}
}

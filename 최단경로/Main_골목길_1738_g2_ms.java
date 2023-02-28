package 최단경로;
import java.io.*;
import java.util.*;

// bellman-ford

public class Main_골목길_1738_g2_ms {
	public static void main(String[] args) throws IOException {
		// 양수 -> 음수
		// 음수 -> 양수로 변경
		// 음수 사이클 나오면 -> -1 출력

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		int[][] edges = new int[m][3];
		for (int i = 0; i < edges.length; i++) {
			st = new StringTokenizer(br.readLine());
			edges[i][0] = Integer.parseInt(st.nextToken());
			edges[i][1] = Integer.parseInt(st.nextToken());
			edges[i][2] = -Integer.parseInt(st.nextToken());
		}

		int[] dist = new int[n + 1];
		int[] path = new int[n + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[1] = 0;
		for (int i = 0; i < n - 1; i++) {
			for (int j = 0; j < edges.length; j++) {
				int cur = edges[j][0];
				int next = edges[j][1];
				int d = edges[j][2];
				if (dist[cur] != Integer.MAX_VALUE && dist[next] > dist[cur] + d) {
					dist[next] = dist[cur] + d;
					path[next] = cur;
				}
			}
		}

		// 음의 사이클 생성 여부 확인
		int i;
		for (i = 0; i < edges.length; i++) {
			int cur = edges[i][0];
			int next = edges[i][1];
			int d = edges[i][2];
			if (dist[next] > dist[cur] + d) {
				dist[next] = dist[cur] + d;
				// n이 포함된 음의 사이클을 만드는 경우
				if (next == n || cur == n)
					break;
			}
		}

		// 출력
		if (i == m && path[n] > 0) {
			Deque<Integer> res = new ArrayDeque<Integer>();
			int cur = n;
			while (cur != 1) {
				res.offerFirst(cur);
				cur = path[cur];
			}
			res.offerFirst(1);
			StringBuilder sb = new StringBuilder();
			for (int j : res) {
				sb.append(j).append(" ");
			}
			System.out.println(sb);
		} else {
			System.out.println(-1);
		}
	}
}

package class5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// dfs
// 사이클 찾기
// 백준 -숫자고르기

public class Main_텀프로젝트_9466_g3_1112ms {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int tc = 0; tc < T; tc++) {

			int n = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine(), " ");
			int[] students = new int[n + 1];
			for (int i = 1; i <= n; i++) {
				students[i] = Integer.parseInt(st.nextToken());
			}

			int[] visited = new int[n + 1];
			int[] dfsn = new int[n + 1];
			int cnt = 0;
			for (int i = 1; i <= n; i++) {
				if (visited[i] > 0)
					continue;

				int cur = i, next = students[i];
				while (visited[next] == 0) {
					visited[cur] = i;
					dfsn[next] = dfsn[cur] + 1;
					cur = next;
					next = students[next];
				}
				if (visited[next] == i) {
					cnt += dfsn[cur] - dfsn[next] + 1;
				}
			}

			sb.append(n - cnt).append("\n");

		} // end of tc

		System.out.println(sb);
	}
}

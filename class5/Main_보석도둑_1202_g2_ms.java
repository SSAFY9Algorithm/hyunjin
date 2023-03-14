package class5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// knapsack
// greedy
// dfs -> 아님

public class Main_보석도둑_1202_g2_ms {
	public static int n, k;
	public static int[] m, v, c;

	public static double max = 0;
	public static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		m = new int[n];
		v = new int[n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			m[i] = Integer.parseInt(st.nextToken());
			v[i] = Integer.parseInt(st.nextToken());
		}

		c = new int[k];
		for (int i = 0; i < k; i++) {
			c[i] = Integer.parseInt(br.readLine());
		}
		
		visited = new boolean[n];
		dfs(0, 0);

		System.out.printf("%.0f", max);
	}

	public static void dfs(int depth, double total) {
		if (depth == k) {
			max = Math.max(max, total);
			return;
		}

		for (int i = 0; i < n; i++) {
			if (!visited[i] && c[depth] >= m[i]) {
				visited[i] = true;
				dfs(depth + 1, total + v[i]);
				visited[i] = false;
			}
		}
	}
}

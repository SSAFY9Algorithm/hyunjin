package 최단경로;
import java.io.*;
import java.util.*;

// floyd-warshall

public class Main_역사_1613_g3_564ms {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		int[][] graph = new int[n + 1][n + 1];
		while (k-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a][b] = 1;
		}

		for (int x = 1; x < graph.length; x++) {
			for (int i = 1; i < graph.length; i++) {
				for (int j = 1; j < graph.length; j++) {
					if (i == j || graph[i][x] == 0 || graph[x][j] == 0)
						continue;
					if (graph[i][j] == 0 || graph[i][j] > graph[i][x] + graph[x][j]) {
						graph[i][j] = graph[i][x] + graph[x][j];
					}
				}
			}
		}

		int s = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		while (s-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if (graph[a][b] > 0) {
				sb.append(-1).append("\n");
			} else if (graph[b][a] > 0) {
				sb.append(1).append("\n");
			} else {
				sb.append(0).append("\n");
			}
		}

		System.out.println(sb);
	}
}

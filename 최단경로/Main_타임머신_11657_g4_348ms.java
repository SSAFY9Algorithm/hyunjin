package 최단경로;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 음수 간선 존재: bellman-ford
// V-1번의 매 단계마다 모든 간선 확인(E번)
// 모든 노드간의 최단거리 계산
// 현재 간선을 거쳐서 가는 경우가 더 짧은지 확인: next > cur + weight
// V번째에서 최단 거리 갱신 발생하면 -> negative cycle 존재
// 시간복잡도: O(VE)

public class Main_타임머신_11657_g4_348ms {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		int[][] edges = new int[m][3];
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			edges[i][0] = a;
			edges[i][1] = b;
			edges[i][2] = c;
		}

		// 최단 거리 저장할 배열
		double[] d = new double[n + 1];
		Arrays.fill(d, Double.POSITIVE_INFINITY);
		d[1] = 0;

		int cur, next, dist;
		for (int i = 0; i < n - 1; i++) {
			for (int j = 0; j < m; j++) {
				cur = edges[j][0];
				next = edges[j][1];
				dist = edges[j][2];
				if (d[cur] != Integer.MAX_VALUE && d[next] > d[cur] + dist) {
					d[next] = d[cur] + dist;
				}
			}
		}
		// negative cycle 찾기
		int i;
		for (i = 0; i < m; i++) {
			cur = edges[i][0];
			next = edges[i][1];
			dist = edges[i][2];
			if (Double.isFinite(d[cur]) && d[next] > d[cur] + dist) {
				break;
			}
		}

		// negative cycle 존재할 때
		if (i < m) {
			System.out.println(-1);
			return;
		}

		StringBuilder sb = new StringBuilder();
		for (i = 2; i <= n; i++) {
			sb.append(String.format("%.0f", Double.isFinite(d[i]) ? d[i] : -1)).append("\n");
		}
		System.out.println(sb);
	}
}

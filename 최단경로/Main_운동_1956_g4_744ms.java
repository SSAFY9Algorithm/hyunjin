package 최단경로;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// floyd-warshall

// 사이클 찾기 -> 모든 노드에 대해 최단경로 찾기
// 1. floyd-warshall: O(V^3)
// dense graph에서 유리
// 2. dijkstra: (VElogV)
// sparse graph에서 유리
// dense graph의 경우 E≈V^2 이기에 불리

public class Main_운동_1956_g4_744ms {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int v = Integer.parseInt(st.nextToken());
		int e = Integer.parseInt(st.nextToken());

		int[][] map = new int[v + 1][v + 1];
		while (e-- > 0) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			map[a][b] = c;
		}

		for (int i = 1; i <= v; i++) {
			for (int j = 1; j <= v; j++) {
				for (int k = 1; k <= v; k++) {
					if (map[i][k] == 0 || map[k][j] == 0)
						continue;
					if (map[i][j] == 0 || map[i][j] > map[i][k] + map[k][j]) {
						map[i][j] = map[i][k] + map[k][j];
					}
				}
			}
		}

		double min = Integer.MAX_VALUE;
		for (int i = 0; i < v; i++) {
			if (map[i][i] > 0)
				min = Math.min(min, map[i][i]);
		}
		System.out.printf("%.0f", min == Integer.MAX_VALUE ? -1 : min);
	}
}

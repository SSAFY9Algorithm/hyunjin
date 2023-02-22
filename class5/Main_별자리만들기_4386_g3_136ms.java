package class5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 최소 스패닝 트리(MST)
// prim 알고리즘

public class Main_별자리만들기_4386_g3_136ms {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());
		double[][] stars = new double[n][2];

		for (int i = 0; i < stars.length; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 2; j++) {
				stars[i][j] = Double.parseDouble(st.nextToken());
			}
		}

		PriorityQueue<double[]> pq = new PriorityQueue<>((a, b) -> (int) (a[1] - b[1]));
		boolean[] visited = new boolean[n];
		int cnt = 0;
		double sum = 0;
		double[] cur;

		pq.add(new double[] { 0, 0 });
		while (cnt < n) {
			cur = pq.poll();
			if (visited[(int) cur[0]])
				continue;
			cnt++;
			sum += cur[1];
			visited[(int) cur[0]] = true;

			double x = stars[(int) cur[0]][0];
			double y = stars[(int) cur[0]][1];
			for (int i = 0; i < n; i++) {
				if (i == cur[0] || visited[i])
					continue;
				double nx = stars[i][0];
				double ny = stars[i][1];
				pq.add(new double[] { i, getDistance(x, y, nx, ny) });
			}
		}

		System.out.printf("%.2f", sum);
	} // end of main

	public static double getDistance(double x1, double y1, double x2, double y2) {
		return (double) Math.round(Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2)) * 1000) / 1000;
	}
}

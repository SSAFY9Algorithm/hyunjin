package 분할정복;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_222풀링_17829_s2 {
	static int[][] map;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		System.out.println(pooling(0, 0, n - 1, n - 1));
	}

	public static int pooling(int x1, int y1, int x2, int y2) {
		if (x1 == x2) {
			return map[x1][y1];
		}

		int midX = x1 + (int) ((x2 - x1) / 2);
		int midY = y1 + (int) ((y2 - y1) / 2);
		int n1 = pooling(x1, y1, midX, midY);
		int n2 = pooling(x1, midY + 1, midX, y2);
		int n3 = pooling(midX + 1, y1, x2, midY);
		int n4 = pooling(midX + 1, midY + 1, x2, y2);

		int[] arr = { n1, n2, n3, n4 };
		Arrays.sort(arr);
		return arr[2];
	}
}

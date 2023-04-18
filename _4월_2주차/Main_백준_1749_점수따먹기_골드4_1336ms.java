package _4월_2주차;

import java.io.*;
import java.util.*;

public class Main_백준_1749_점수따먹기_골드4_1336ms {
	static int n, m;
	static long[][] sum;
	static long max = Long.MIN_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		sum = new long[n][m];

		st = new StringTokenizer(br.readLine());
		// 누적합 행렬 만들기
		sum[0][0] = Integer.parseInt(st.nextToken());
		for (int i = 1; i < m; i++) {
			sum[0][i] += sum[0][i - 1] + Integer.parseInt(st.nextToken());
		}
		for (int i = 1; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			sum[i][0] = sum[i - 1][0] + Integer.parseInt(st.nextToken());
			for (int j = 1; j < m; j++) {
				sum[i][j] = Integer.parseInt(st.nextToken()) + sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1];
			}
		}

		findMax();
	}

	private static void findMax() {
		// TODO Auto-generated method stub
		for (int lenR = 0; lenR < n; lenR++) {
			for (int lenC = 0; lenC < m; lenC++) {
				max = Math.max(max, getSum(lenR, lenC));
			}
		}

		System.out.println(max);
	}

	private static long getSum(int lenR, int lenC) {
		// TODO Auto-generated method stub
		long max = Long.MIN_VALUE;
		for (int i = 0; i < n - lenR; i++) {
			for (int j = 0; j < m - lenC; j++) {
				long total = sum[i + lenR][j + lenC];
				if (i - 1 >= 0)
					total -= sum[i - 1][j + lenC];
				if (j - 1 >= 0)
					total -= sum[i + lenR][j - 1];
				if (i - 1 >= 0 && j - 1 >= 0)
					total += sum[i - 1][j - 1];
				max = Math.max(max, total);
			}
		}
		return max;
	}
}

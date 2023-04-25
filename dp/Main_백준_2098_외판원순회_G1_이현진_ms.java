package dp;

import java.io.*;
import java.util.*;

public class Main_백준_2098_외판원순회_G1_이현진_ms {
	static int n;
	static int[][] w, dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		w = new int[n][n];
		for (int i = 0; i < w.length; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < w.length; j++) {
				w[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		dp = new int[1 << n][n];
		System.out.println(doDFS(1, 0));
	}

	private static int doDFS(int visited, int prev) {
		// TODO Auto-generated method stub
		if (dp[visited][prev] != 0)
			return dp[visited][prev];
		if (visited == (1 << n - 1))
			return w[prev][0];
		for (int i = 0; i < n; i++) {
			if ((visited & 1 << i) != 0)
				continue;
			if (dp[visited][prev] == 0)
				dp[visited][prev] = doDFS(visited | 1 << i, i) + w[prev][i];
			else
				dp[visited][prev] = Math.min(dp[visited][prev], doDFS(visited | 1 << i, i) + w[prev][i]);
		}
		return dp[visited][prev];
	}

}

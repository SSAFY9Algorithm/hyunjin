package class5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// dp

public class Main_팰린드롬_10942_g4_1408ms {
	static int[] num;
	static int[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());
		num = new int[n];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < num.length; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}

		int m = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		dp = new int[n][n];
		while (m-- > 0) {
			st = new StringTokenizer(br.readLine(), " ");
			int left = Integer.parseInt(st.nextToken());
			int right = Integer.parseInt(st.nextToken());
			sb.append(check(left - 1, right - 1) - 1).append("\n");
		}

		System.out.println(sb);
	}

	private static int check(int left, int right) {
		if (dp[left][right] != 0)
			return dp[left][right];

		if (num[left] != num[right])
			return dp[left][right] = 1;

		if (left == right || left + 1 == right)
			return dp[left][right] = 2;

		return dp[left][right] = check(left + 1, right - 1);

	}
}

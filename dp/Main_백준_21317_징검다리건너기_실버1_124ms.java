package dp;
import java.io.*;
import java.util.*;

public class Main_백준_21317_징검다리건너기_실버1_124ms {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());

		int[][] costs = new int[n][2];
		for (int i = 0; i < n - 1; i++) {
			st = new StringTokenizer(br.readLine());
			costs[i][0] = Integer.parseInt(st.nextToken());
			costs[i][1] = Integer.parseInt(st.nextToken());
		}

		int k = Integer.parseInt(br.readLine());

		if (n == 1) {
			System.out.println(0);
			return;
		}

		int[][] dp = new int[n][2]; // col0:k안썼을 때 col1:k썼을 때
		for (int i = 1; i < n; i++) {
			dp[i][0] = 9999999;
			dp[i][1] = 9999999;
		}

		// 1. dp -> 최대값으로 초기화 하기
		// 2. 작은 인덱스 -> 큰 인덱스 방향으로 dp 채워주기
		for (int i = 0; i < n - 1; i++) {
			// 1칸 점프
			int next = i + 1;
			dp[next][0] = Math.min(dp[i][0] + costs[i][0], dp[next][0]);
			dp[next][1] = Math.min(dp[i][1] + costs[i][0], dp[next][1]);

			// 큰 점프
			next++;
			if (next == n)
				continue;
			dp[next][0] = Math.min(dp[i][0] + costs[i][1], dp[next][0]);
			dp[next][1] = Math.min(dp[i][1] + costs[i][1], dp[next][1]);

			// 매우 큰 점프
			next++;
			if (next == n)
				continue;
			dp[next][1] = Math.min(dp[i][0] + k, dp[next][1]);
		}

		System.out.println(Math.min(dp[n - 1][0], dp[n - 1][1]));
	}
}

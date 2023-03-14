package dp;
import java.io.*;
import java.util.*;

public class Main_백준_1106_호텔_골드5_124ms {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int c = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());

		int[] dp = new int[1101];
		while (n-- > 0) {
			st = new StringTokenizer(br.readLine());
			int cost = Integer.parseInt(st.nextToken());
			int people = Integer.parseInt(st.nextToken());

			if (dp[people] == 0 || dp[people] > cost)
				dp[people] = cost;
			for (int i = people + 1; i < dp.length; i++) {
				if (dp[i - people] != 0 && (dp[i] == 0 || dp[i] > dp[i - people] + cost)) {
					dp[i] = dp[i - people] + cost;
				}
			}
		}

		int min = Integer.MAX_VALUE;
		for (int i = c; i < dp.length; i++) {
			if (dp[i] == 0)
				continue;
			min = Math.min(min, dp[i]);
		}
		System.out.println(min);
	}
}

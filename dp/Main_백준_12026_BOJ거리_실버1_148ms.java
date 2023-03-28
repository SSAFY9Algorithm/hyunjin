package dp;
import java.io.*;
import java.util.*;

public class Main_백준_12026_BOJ거리_실버1_148ms {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		int[] dp = new int[n];
		String road = br.readLine();

		Map<Character, Character> prevChar = new HashMap<Character, Character>();
		prevChar.put('B', 'J');
		prevChar.put('O', 'B');
		prevChar.put('J', 'O');

		dp[0] = 1;
		for (int i = 1; i < n; i++) {
			char cur = road.charAt(i);
			char prev = prevChar.get(cur);
			for (int j = i - 1; j >= 0; j--) {
				if (prev == road.charAt(j) && dp[j] != 0) {
					int amount = dp[j] + (i - j) * (i - j);
					if (dp[i] == 0 || dp[i] > amount)
						dp[i] = amount;
				}
			}
		}

		System.out.println(dp[n - 1] == 0 ? -1 : dp[n - 1] - 1);
	}
}

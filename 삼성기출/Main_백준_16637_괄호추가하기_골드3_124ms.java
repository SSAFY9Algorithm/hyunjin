package 삼성기출;

import java.io.*;
import java.util.*;

// 2^10 = 1024

public class Main_백준_16637_괄호추가하기_골드3_124ms {
	static int opLen, numLen;
	static char[] op;
	static int[][] cal;

	static int max = Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		// 1개일 때 -> 계산 필요 x
		if (n == 1) {
			System.out.println(br.readLine());
			return;
		}

		opLen = n / 2;
		numLen = opLen + 1;

		int[] nums = new int[numLen];
		op = new char[opLen];
		String str = br.readLine();
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) > '9' || str.charAt(i) < '0')
				op[i / 2] = str.charAt(i);
			else
				nums[i / 2] = str.charAt(i) - '0';
		}

		cal = new int[numLen][2];
		// cal[i][0]: 괄호x 값
		// cal[i][1]: 괄호o 값
		for (int i = 0; i < cal.length; i++) {
			cal[i][0] = nums[i];
			if (i + 1 == cal.length)
				continue;
			switch (op[i]) {
			case '+':
				cal[i][1] = nums[i] + nums[i + 1];
				break;
			case '-':
				cal[i][1] = nums[i] - nums[i + 1];
				break;
			case '*':
				cal[i][1] = nums[i] * nums[i + 1];
				break;
			}
		}

		dfs(0, cal[0][0]);
		dfs(1, cal[0][1]);

		System.out.println(max);
	}

	public static void dfs(int cur, int sum) {
		if (cur >= numLen - 1) {
			max = Math.max(max, sum);
			return;
		}

		// 괄호 하지 않을 때와 할 때 탐색
		switch (op[cur]) {
		case '+':
			dfs(cur + 1, sum + cal[cur + 1][0]);
			if (cur + 2 < numLen)
				dfs(cur + 2, sum + cal[cur + 1][1]);
			break;
		case '-':
			dfs(cur + 1, sum - cal[cur + 1][0]);
			if (cur + 2 < numLen)
				dfs(cur + 2, sum - cal[cur + 1][1]);
			break;
		case '*':
			dfs(cur + 1, sum * cal[cur + 1][0]);
			if (cur + 2 < numLen)
				dfs(cur + 2, sum * cal[cur + 1][1]);
			break;
		}
	}
}

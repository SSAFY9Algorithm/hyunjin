package _4월_2주차;

import java.io.*;
import java.util.*;

public class Main_백준_20437_문자열게임2_골드5_272ms {
	static class Char {
		int end, cnt;

		public Char(int end, int cnt) {
			this.end = end;
			this.cnt = cnt;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int i = 0; i < T; i++) {
			String str = br.readLine();
			int k = Integer.parseInt(br.readLine());
			int[] ans = solution(str, k);
			if (ans[0] == 100001)
				sb.append(-1).append("\n");
			else
				sb.append(ans[0]).append(" ").append(ans[1]).append("\n");

		}
		System.out.println(sb);
	}

	private static int[] solution(String str, int k) {
		// TODO sliding window?
		int minLen = 100001, maxLen = 0;

		List<Char>[] cList = new ArrayList[26];
		for (int i = 0; i < 26; i++) {
			cList[i] = new ArrayList<Char>();
		}
		for (int i = 0; i < str.length(); i++) {
			int cnt = cList[str.charAt(i) - 'a'].size() + 1;
			cList[str.charAt(i) - 'a'].add(new Char(i, cnt));
		}

		for (int i = 0; i < 26; i++) {
			for (int j = 0; j <= cList[i].size() - k; j++) {
				minLen = Math.min(minLen, cList[i].get(j + k - 1).end - cList[i].get(j).end + 1);
				maxLen = Math.max(maxLen, cList[i].get(j + k - 1).end - cList[i].get(j).end + 1);
			}
		}

		return new int[] { minLen, maxLen };
	}

}

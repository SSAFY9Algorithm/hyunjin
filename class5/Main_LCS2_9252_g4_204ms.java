package class5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// lcs 알고리즘

public class Main_LCS2_9252_g4_204ms {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str1 = br.readLine();
		String str2 = br.readLine();

		int len1 = str1.length(), len2 = str2.length(), cnt = 0;
		StringBuilder sb = new StringBuilder();
		int[][] lcs = new int[len1 + 1][len2 + 1];
		for (int i = 1; i <= len1; i++) {
			for (int j = 1; j <= len2; j++) {
				if (str1.charAt(i - 1) == str2.charAt(j - 1))
					lcs[i][j] = lcs[i - 1][j - 1] + 1;
				else
					lcs[i][j] = Math.max(lcs[i - 1][j], lcs[i][j - 1]);
			}
		}

		int i = len1, j = len2;
		while (lcs[i][j] > 0) {
			if (lcs[i - 1][j] == lcs[i][j]) {
				i--;
			} else if (lcs[i][j - 1] == lcs[i][j]) {
				j--;
			} else {
				sb.append(str1.charAt(i - 1));
				i--;
				j--;
			}
		}
		System.out.println(lcs[len1][len2] == 0 ? 0 : lcs[len1][len2] + "\n" + sb.reverse());
	}
}

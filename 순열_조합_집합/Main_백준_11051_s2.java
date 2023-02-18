package 순열_조합_집합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_백준_11051_s2 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		System.out.println(getCombination(n, k));

	} // end of main

	// nCr = n-1Cr + n-1Cr-1
	public static int getCombination(int n, int r) {
		int[][] C = new int[10001][];

		for (int i = 0; i <= n; i++) {
			C[i] = new int[i + 1];
			for (int j = 0; j <= i; j++) {
				if (j == 0 || i == j) {
					C[i][j] = 1;
				} else {
					C[i][j] = C[i - 1][j - 1] % 10007 + C[i - 1][j] % 10007;
				}
			}
		}

		return C[n][r] % 10007;
	}

} // end of class

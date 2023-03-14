package 삼성기출;

import java.io.*;
import java.util.*;

public class Main_백준_17136_색종이붙이기_골드2_ms {
	// sum[i][j]: 10*10 종이에서 i,j까지 합
	static int[][] sum = new int[10][10];
	// 색종이로 덮은 부분 표시
	static int[][] check = new int[10][10];
	// 색종이 개수
	static int[] cnt = new int[6];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		check[0][0] = Integer.parseInt(st.nextToken());
		sum[0][0] = check[0][0];
		for (int i = 1; i < 10; i++) {
			check[0][i] = Integer.parseInt(st.nextToken());
			sum[0][i] = sum[0][i - 1] + check[0][i];
		}
		for (int i = 1; i < 10; i++) {
			st = new StringTokenizer(br.readLine());
			check[i][0] = Integer.parseInt(st.nextToken());
			sum[i][0] = sum[i - 1][0] + check[i][0];
			for (int j = 1; j < 10; j++) {
				check[i][j] = Integer.parseInt(st.nextToken());
				sum[i][j] = sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1] + check[i][j];
			}
		}

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				// 처음 색종이 덮음
				if (check[i][j] == 1) {
					int len = 1;
					int size;
					while (true) {
						if (i + len > 10 || j + len > 10)
							break;
						size = sum[i + len - 1][j + len - 1];
						if (i > 0)
							size -= sum[i - 1][j + len - 1];
						if (j > 0)
							size -= sum[i + len - 1][j - 1];
						if (i > 0 && j > 0)
							size += sum[i - 1][j - 1];
						if (size != len * len)
							break;
						len++;
					}
					for (int n = 0; n < len; n++) {
						for (int m = 0; m < len; m++) {
							
							check[i+n][j+m] = len;
						}
					}
					cnt[len]++;
				}
				// 이미 덮인 색종이 있음
				else if (check[i][j] > 1) {

				}
			}
		}

	}
}

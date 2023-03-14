package class5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// dp

public class Main_RGB거리2_17404_g4_140ms {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());
		int[][] price = new int[n][3];
		for (int i = 0; i < price.length; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				price[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int min;
		int[][] total = new int[n][3];
		// n-1번집 -> R
		total[0][1] = price[0][1] + price[n - 1][0];
		total[0][2] = price[0][2] + price[n - 1][0];
		total[1][0] = Math.min(total[0][1], total[0][2]) + price[1][0];
		total[1][1] = total[0][2] + price[1][1];
		total[1][2] = total[0][1] + price[1][2];
		for (int i = 2; i < total.length - 1; i++) {
			for (int j = 0; j < 3; j++) {
				total[i][j] = Math.min(total[i - 1][(j + 1) % 3], total[i - 1][(j + 2) % 3]) + price[i][j];
			}
		}
		min = Math.min(total[n - 2][1], total[n - 2][2]);

		// n-1번집 -> G
		total[0][0] = price[0][0] + price[n - 1][1];
		total[0][1] = 0;
		total[0][2] = price[0][2] + price[n - 1][1];
		total[1][0] = total[0][2] + price[1][0];
		total[1][1] = Math.min(total[0][0], total[0][2]) + price[1][1];
		total[1][2] = total[0][0] + price[1][2];
		for (int i = 2; i < total.length; i++) {
			for (int j = 0; j < 3; j++) {
				total[i][j] = Math.min(total[i - 1][(j + 1) % 3], total[i - 1][(j + 2) % 3]) + price[i][j];
			}
		}
		min = Math.min(min, Math.min(total[n - 2][0], total[n - 2][2]));

		// n-1번집 -> B
		total[0][0] = price[0][0] + price[n - 1][2];
		total[0][1] = price[0][1] + price[n - 1][2];
		total[0][2] = 0;
		total[1][0] = total[0][1] + price[1][0];
		total[1][1] = total[0][0] + price[1][1];
		total[1][2] = Math.min(total[0][0], total[0][1]) + price[1][2];
		for (int i = 2; i < total.length; i++) {
			for (int j = 0; j < 3; j++) {
				total[i][j] = Math.min(total[i - 1][(j + 1) % 3], total[i - 1][(j + 2) % 3]) + price[i][j];
			}
		}
		min = Math.min(min, Math.min(total[n - 2][0], total[n - 2][1]));

		System.out.println(min);
	}
}

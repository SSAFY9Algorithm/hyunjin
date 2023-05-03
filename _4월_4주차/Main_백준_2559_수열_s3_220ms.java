package _4월_4주차;

import java.util.*;
import java.io.*;

public class Main_백준_2559_수열_s3_220ms {
	static int n, k;
	static int[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		System.out.println(solve());
	}

	private static int solve() {
		// sliding window
		int sum = 0, max = -10000001;
		for (int i = 0; i < k; i++) {
			sum += arr[i];
		}
		max = Math.max(max, sum);
		int i = k;
		while (i < n) {
			sum += arr[i];
			sum -= arr[i-k];
			max = Math.max(max, sum);
			i++;
		}
		return max;
	}
}

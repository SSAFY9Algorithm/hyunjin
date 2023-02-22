package class5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// two pointer

public class Main_부분합_1806_g4_292ms {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int s = Integer.parseInt(st.nextToken());

		int[] arr = new int[n];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		if (arr[0] == s) {
			System.out.println(1);
			return;
		}

		int left = 0, right = 1, len = Integer.MAX_VALUE;
		int sum = arr[left] + arr[right];
		while (left <= right) {
			if (sum >= s) {
				len = Math.min(len, right - left + 1);
				sum -= arr[left];
				left++;
			} else {
				right++;
				if (right == n)
					break;
				sum += arr[right];
			}
		}

		System.out.println(len == Integer.MAX_VALUE ? 0 : len);
	}
}

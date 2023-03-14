package class5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// two pointer

public class Main_용액_2467_g5_400ms {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());
		int[] feat = new int[n];

		int posIdx = -1;
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			feat[i] = Integer.parseInt(st.nextToken());
			if (posIdx == -1 && feat[i] >= 0)
				posIdx = i;
		}

		// 1. 모두 양수일 때
		if (posIdx == 0) {
			System.out.println(feat[0] + " " + feat[1]);
		}
		// 2. 모두 음수일 때
		else if (posIdx == -1) {
			System.out.println(feat[n - 2] + " " + feat[n - 1]);
		}
		// 3. 음수 + 양수
		else {
			int min = Integer.MAX_VALUE;
			int[] res = new int[2];
			int left = 0, right = n - 1;
			while (left < right) {
				int sum = feat[left] + feat[right];
				if (min > Math.abs(sum)) {
					min = Math.abs(sum);
					res[0] = feat[left];
					res[1] = feat[right];
				}
				if (sum > 0)
					right--;
				else if (sum < 0)
					left++;
				else
					break;
			}
			System.out.println(res[0] + " " + res[1]);
		}

	}

}

package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_백준_18111_s2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int[] hMap = new int[257];
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());

		if (n == 1 && m == 1) {
			st = new StringTokenizer(br.readLine());
			System.out.printf("%d %d\n", 0, Integer.parseInt(st.nextToken()));
			return;
		}

		int maxH = 0;
		int minH = Integer.MAX_VALUE;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				int h = Integer.parseInt(st.nextToken());
				hMap[h] += 1;
				maxH = Math.max(maxH, h);
				minH = Math.min(minH, h);
			}
		}

		int resT = Integer.MAX_VALUE;
		int resH = 0;
		for (int height = minH; height <= maxH; height++) {
			int time = 0;
			int b_copy = b;
			for (int i = 0; i <= 256; i++) {
				if (hMap[i] > 0) {
					// 블록 제거
					if (i > height) {
						time += (i - height) * hMap[i] * 2;
						// 인벤토리 추가
						b_copy += (i - height) * hMap[i];
					}
					// 블록 쌓기
					else {
						time += (height - i) * hMap[i];
						// 인벤토리에서 사용
						b_copy -= (height - i) * hMap[i];
					}
				}
			}
			if (b_copy >= 0 && resT >= time) {
				if (resT > time || (resT == time && resH < height)) {
					resT = time;
					resH = height;
				}
			}
		}

		System.out.printf("%d %d\n", resT, resH);

	} // end of main
} // end of class

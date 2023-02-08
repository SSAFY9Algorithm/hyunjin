package 완전탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_백준_창고다각형_2304_실버2 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());

		int[] tops = new int[1001];
		int maxH = 0;
		int maxIdx = 0;
		while (n-- > 0) {
			st = new StringTokenizer(br.readLine());
			int idx = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());
			tops[idx] = h;

			if (maxH < h) {
				maxH = h;
				maxIdx = idx;
			}
		}

		int area = maxH;
		
		int curIdx = 0; 
		int curH = tops[0];
		for (int i = 0; i <= maxIdx; i++) {
			if (tops[i] >= curH) {
				area += curH * (i - curIdx);
				curH = tops[i];
				curIdx = i;
			}
		}

		curIdx = 1000;
		curH = tops[1000];
		for (int i = 1000; i >= maxIdx; i--) {
			if (tops[i] >= curH) {
				area += curH * (curIdx - i);
				curH = tops[i];
				curIdx = i;
			}
		}

		System.out.println(area);
	}
}

package 그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_회의실배정_1931_s1 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());

		int[][] info = new int[n][2];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			info[i][0] = Integer.parseInt(st.nextToken());
			info[i][1] = Integer.parseInt(st.nextToken());
		}

		// 종료 시간이 빠른 회의를 먼저 선택
		// 종료 시간이 같다면, 시작 시간이 빠른 회의를 먼저 선택
		// 종료 시간, 시작 시간 기준으로 회의 정렬
		Arrays.sort(info, (a, b) -> {
			if (a[1] != b[1])
				return a[1] - b[1];
			else
				return a[0] - b[0];
		});
		
		int cnt = 1;
		int end = info[0][1];
		for (int i = 1; i < info.length; i++) {
			if (end <= info[i][0]) {
				cnt++;
				end = info[i][1];
			}
		}

		System.out.println(cnt);
	}
}

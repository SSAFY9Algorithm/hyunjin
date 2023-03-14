package 이분탐색;
import java.io.*;
import java.util.*;

// 매개변수 탐색

public class Main_백준_2110_공유기설치_골드4_324ms {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());

		int[] house = new int[n];
		for (int i = 0; i < n; i++) {
			house[i] = Integer.parseInt(br.readLine());
		}

		Arrays.sort(house);
		int left = 1, right = house[n - 1] - house[0];
		int mid;
		while (left < right) {
			mid = left + (right - left) / 2;

			int start = house[0];
			int cnt = 1;
			for (int i = 1; i < house.length; i++) {
				if (start + mid <= house[i]) {
					start = house[i];
					cnt++;
				}
				if (cnt == c)
					break;
			}
			if (cnt == c) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		
		int start = house[0];
		int cnt = 1;
		for (int i = 1; i < house.length; i++) {
			if (start + left <= house[i]) {
				start = house[i];
				cnt++;
			}
			if (cnt == c)
				break;
		}
		if (cnt != c) {
			left--;
		}
		System.out.println(left);
	}
}

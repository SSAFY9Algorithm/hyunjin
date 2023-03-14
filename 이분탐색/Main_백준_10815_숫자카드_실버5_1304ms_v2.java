package 이분탐색;
import java.io.*;
import java.util.*;

// 이분탐색 

public class Main_백준_10815_숫자카드_실버5_1304ms_v2 {
	static int n;
	static int[] cards;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		n = Integer.parseInt(br.readLine());
		cards = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			cards[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(cards);

		int m = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < m; i++) {
			sb.append(binarySearch(Integer.parseInt(st.nextToken()))).append(" ");
		}

		System.out.println(sb);
	}

	public static int binarySearch(int tar) {
		int low = 0, high = n - 1;
		while (low < high) {
			int mid = low + (high - low) / 2;
			if (cards[mid] == tar)
				return 1;
			else if (cards[mid] > tar) {
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}
		return cards[low] == tar ? 1 : 0;
	}
}

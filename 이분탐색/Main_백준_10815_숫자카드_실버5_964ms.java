package 이분탐색;
import java.io.*;
import java.util.*;

// 해싱

public class Main_백준_10815_숫자카드_실버5_964ms {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int n = Integer.parseInt(br.readLine());
		Map<Integer, Boolean> cards = new HashMap<>();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			cards.put(Integer.parseInt(st.nextToken()), true);
		}

		int m = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < m; i++) {
			sb.append(cards.get(Integer.parseInt(st.nextToken())) == null ? 0 : 1).append(" ");
		}

		System.out.println(sb);
	}
}

package 완전탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_백준_물주기_23351_실버3 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());

		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for (int i = 0; i < n; i++) {
			pq.add(k);
		}

		int water, day = 0;
		while (true) {
			for (int i = 0; i < a; i++) {
				water = pq.poll();
				if (water == 0) {
					System.out.println(day);
					return;
				}
				pq.add(water + b);
			}
			int[] temp = new int[n];
			for (int i = 0; i < n; i++) {
				water = pq.poll();
				temp[i] = water - 1;
			}
			pq.clear();
			for (int i = 0; i < temp.length; i++) {
				pq.add(temp[i]);
			}
//			System.out.println(pq);
			day++;
		}

	}
}

// 2 2 2 2 0 0
// k -(n/a) + b
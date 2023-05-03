package _4월_2주차;

import java.io.*;
import java.util.*;

public class Main_백준_2015_수들의합4_G4_468ms {
	static int n, k;
	static int[] arr;
	static long[] sum;
	static Map<Long, Queue<Integer>> map = new HashMap<Long, Queue<Integer>>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		arr = new int[n];
		sum = new long[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		System.out.println(solution());
	}

	private static long solution() {
		// TODO 누적합, 해시, 트리
		long total = 0;
		long cnt = 0;
		for (int i = 0; i < arr.length; i++) {
			total += arr[i];
			sum[i] = total;
			if (map.get(total) == null) {
				Queue<Integer> q = new ArrayDeque<Integer>();
				q.offer(i);
				map.put(total, q);
			} else {
				Queue<Integer> q = map.get(total);
				q.offer(i);
				map.put(total, q);
			}
		}

		for (int i = 0; i < arr.length; i++) {
			if (sum[i] == k) {
				cnt++;
			}
			long target = k + sum[i];
			Queue<Integer> q = map.get(target);
			if (q != null) {
				while (!q.isEmpty() && q.peek() <= i)
					q.poll();
				if (q.size() == 0)
					map.remove(target);
				else {
					cnt += q.size();
					map.put(target, q);
				}
			}
		}

		return cnt;
	}
}

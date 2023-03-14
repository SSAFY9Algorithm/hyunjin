package class5;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// two pointer
// 부분합

public class Main_소수의연속합_1644_g3_284ms {
	static boolean[] composite = new boolean[4000001];
	static List<Integer> prime = new ArrayList<Integer>();
	static int n;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();

		if (n == 1) {
			System.out.println(0);
			return;
		}

		findPrime();

		int cnt = 0, left = 0, right = 0, sum = 0;
		sum = prime.get(0);
		while (left <= right) {
			if (sum == n) {
				cnt++;
				sum -= prime.get(left);
				left++;
				right++;
				if (right == prime.size())
					break;
				sum += prime.get(right);
			} else if (sum > n) {
				sum -= prime.get(left);
				left++;
			} else {
				right++;
				if (right == prime.size())
					break;
				sum += prime.get(right);
			}
		}

		System.out.println(cnt);
	}

	public static void findPrime() {
		for (int i = 2; i <= Math.sqrt(n); i++) {
			if (composite[i])
				continue;
			int idx = i * i;
			while (idx <= n) {
				composite[idx] = true;
				idx += i;
			}
		}
		for (int i = 2; i <= n; i++) {
			if (!composite[i])
				prime.add(i);
		}
	}
}

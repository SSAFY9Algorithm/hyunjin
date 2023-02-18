package 완전탐색;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main_백준_삼삼한수_17252_실버3 {
	static int[] res;
	static Set<Integer> set = new HashSet<>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();

		int idx = 0;
		int div = n;
		while (div > 0) {
			div /= 3;
			idx++;
		}

		for (int i = 1; i <= idx; i++) {
			res = new int[i];
			getCombination(idx, i, 0, 0);
		}
		
		if (set.contains(n))
			System.out.println("YES");
		else
			System.out.println("NO");
	}

	public static void getCombination(int n, int k, int depth, int start) {
		if (depth == k) {
			int sum = 0;
			for (int i = 0; i < res.length; i++) {
				sum += Math.pow(3, res[i]);
			}
			set.add(sum);
			return;
		}

		for (int i = start; i < n; i++) {
			res[depth] = i;
			getCombination(n, k, depth + 1, i + 1);
		}
	}
}

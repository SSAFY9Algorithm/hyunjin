package _4월_4주차;

import java.util.*;
import java.io.*;

public class Main_백준_1565_수학_g4_88ms {
	static int d, m;
	static int[] drr, mrr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		d = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		drr = new int[d];
		mrr = new int[m];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < d; i++) {
			drr[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < m; i++) {
			mrr[i] = Integer.parseInt(st.nextToken());
		}

		System.out.println(solve());

	}

	private static int solve() {
		int GCD = mrr[0];
		for (int i = 1; i < m; i++) {
			GCD = gcd(GCD, mrr[i]);
		}
		return findDivisor(GCD);
	}

	private static int findDivisor(int gcd) {
		List<Integer> divisors = new ArrayList<Integer>();
		int len = (int) Math.sqrt(gcd);
		for (int i = 1; i <= len; i++) {
			if (gcd % i == 0) {
				divisors.add(i);
				if (gcd / i != i)
					divisors.add(gcd / i);
			}
		}
		int cnt = 0;
		for (int i = 0; i < divisors.size(); i++) {
			int j = 0;
			for (; j < d; j++) {
				if (divisors.get(i) % drr[j] != 0)
					break;
			}
			if (j == d)
				cnt++;
		}
		return cnt;
	}

	private static int gcd(int a, int b) {
		if (b == 0)
			return a;
		else
			return gcd(b, a % b);
	}

}

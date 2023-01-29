package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main_백준_11051_s2 {
	static int mod = 10007;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		// 페르마의 소정리
		// modInv(K, MOD) = K^(MOD-2)%MOD
		// (N/K) % MOD = (N * modInv(K, MOD-2))%MOD
//		System.out.println(n * getPower(k, mod - 2) % mod);
		System.out.println(getPower(2, 3));

	} // end of main

	// a^b
	public static int getPower(int a, int b) {
		// 빠른 모듈러 거듭제곱
		// https://ko.khanacademy.org/computing/computer-science/cryptography/modarithmetic/a/fast-modular-exponentiation
		// 1. 지수를 이진수로 변환
		// 2. 2의 거듭제곱 이용
		String binaryString = Integer.toBinaryString(b);

		int[] exponents = new int[binaryString.length()];
		exponents[0] = 1;
		exponents[1] = a % mod;
		for (int i = 2; i < exponents.length; i *= 2) {
			exponents[i] = exponents[i / 2] * exponents[i / 2] % mod;
			for (int j = 1; j < i; j++) {
				if (i + j == exponents.length)
					break;
				exponents[i + j] = exponents[i] * (int) Math.pow(a, j) % mod;
			}
		}
		System.out.println(Arrays.toString(exponents));

		int res = 1;
		for (int i = 1; i <= exponents.length; i++) {
			if (binaryString.charAt(exponents.length - i) == '1') {
				res *= exponents[i - 1];
				res %= mod;
				System.out.println(res);
			}
		}

		return res % mod;
	}
} // end of class

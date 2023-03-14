package class5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 1. 모든 구간의 누적 합 구하기
// 2. 합이 t가 되는 누적 합 찾기
// 	1) lower bound, upper bound 구현
// 	2) 투 포인터 사용

public class Main_두배열의합_2143_g3_ms {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int t = Integer.parseInt(br.readLine());

		int lenA = Integer.parseInt(br.readLine());
		int[] arr = new int[lenA + 1];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i < arr.length; i++) {
			arr[i] = arr[i - 1] + Integer.parseInt(st.nextToken());
		}

		int lenB = Integer.parseInt(br.readLine());
		int[] brr = new int[lenB + 1];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i < brr.length; i++) {
			brr[i] = brr[i - 1] + Integer.parseInt(st.nextToken());
		}

		// 모든 구간의 누적 합 구하기
		int[] sumA = new int[lenA * (lenA + 1) / 2];
		int[] sumB = new int[lenB * (lenB + 1) / 2];
		int idx = 0;
		for (int i = 0; i <= lenA; i++) {
			for (int j = i + 1; j <= lenA; j++) {
				sumA[idx] = arr[j] - arr[i];
				idx++;
			}
		}
		Arrays.sort(sumA);

		idx = 0;
		for (int i = 0; i <= lenB; i++) {
			for (int j = i + 1; j <= lenB; j++) {
				sumB[idx] = brr[j] - brr[i];
				idx++;
			}
		}
		Arrays.sort(sumB);

		System.out.printf("%d", twoPointer(sumA, sumB, t));
	}

	public static long twoPointer(int[] sumA, int[] sumB, int t) {
		long cnt = 0;
		int sum;
		int pA = 0, pB = sumB.length - 1;
		while (pA < sumA.length && pB >= 0) {
			sum = sumA[pA] + sumB[pB];
			if (sum == t) {
				int a = sumA[pA];
				int b = sumB[pB];
				long aCnt = 0;
				long bCnt = 0;

				while (pA < sumA.length && sumA[pA] == a) {
					aCnt++;
					pA++;
				}
				while (pB >= 0 && sumB[pB] == b) {
					bCnt++;
					pB--;
				}
				cnt += aCnt * bCnt;
			} else if (sum > t) {
				pB--;
			} else {
				pA++;
			}
		}
		return cnt;
	}
}

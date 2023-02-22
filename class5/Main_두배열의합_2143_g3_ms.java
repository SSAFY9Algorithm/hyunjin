package class5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// binary search

public class Main_두배열의합_2143_g3_ms {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int t = Integer.parseInt(br.readLine());

		int lenA = Integer.parseInt(br.readLine());
		int[] arr = new int[lenA];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < arr.length; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int lenB = Integer.parseInt(br.readLine());
		int[] brr = new int[lenB];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < brr.length; i++) {
			brr[i] = Integer.parseInt(st.nextToken());
		}

		// 1. A 배열에서 포인터 이동
		// 2. 포인터 구간의 합이 타겟 수보다 작을 때
		// 3. B 배열에서 포인터 이동하며 합이 타겟 수가 되는 구간 찾기 -> cnt++
		// 4. B 배열 탐색 끝나면 A 배열 포인터 이동

		int cnt = 0;
		int leftA = 0, rightA = 0, sumA = arr[0];
		while (leftA <= rightA) {
			int leftB = 0, rightB = 0, sumB = brr[0];
			boolean isFind = false;
			while (leftB <= rightB) {

				if (sumA + sumB == t) {
					cnt++;
					isFind = true;
					sumB -= brr[leftB];
					leftB++;
					if (leftB == lenB)
						break;
					rightB++;
					if (rightB == lenB)
						break;
					sumB += brr[rightB];
				} else if (sumA + sumB < t) {
					rightB++;
					if (rightB == lenB)
						break;
					sumB += brr[rightB];
				} else {
					if (leftB == rightB) {
						rightB++;
						if (rightB == lenB)
							break;
						sumB += brr[rightB];
					}
					sumB -= brr[leftB];
					leftB++;
					if (leftB == lenB)
						break;
				}
			}
//			System.out.println(leftA < rightB);

			if ((isFind && leftA < rightA) || sumA >= t) {
				sumA -= arr[leftA];
				leftA++;
				if (leftA == lenA)
					break;
			} else {
				rightA++;
				if (rightA == lenA)
					break;
				sumA += arr[rightA];
			}

		}

		System.out.println(cnt);
	}
}

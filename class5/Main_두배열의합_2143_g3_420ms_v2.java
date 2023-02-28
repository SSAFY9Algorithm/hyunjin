package class5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

// 1. 모든 구간의 누적 합 구하기
// 2. 합이 t가 되는 누적 합 찾기
// 	1) lower bound, upper bound 구현
// 	2) 투 포인터 사용
//	3) map 사용 -> 시간 단축 bbbb

public class Main_두배열의합_2143_g3_420ms_v2 {
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

		// A 배열에 대해 모든 부분합 구하기
		// key:A 배열 부분합 / value:부분합 값이 같은 경우의 개수
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();

		int idx = 0;
		for (int i = 0; i <= lenA; i++) {
			for (int j = i + 1; j <= lenA; j++) {
				// 메서드 참조
				// (a) -> Integer.sum(a) 대신 Integer::sum
				map.merge(arr[j] - arr[i], 1, Integer::sum);
			}
		}

		// B 배열에 대해 모든 부분합 구하
		long cnt = 0;
		for (int i = 0; i <= lenB; i++) {
			for (int j = i + 1; j <= lenB; j++) {
				// 합이 t가 되는 경우 찾기
				// 이때 map 사용
				int key = t - (brr[j] - brr[i]);
				if (map.containsKey(key))
					cnt += map.get(key);
			}
		}

		System.out.printf("%d", cnt);
	}

}

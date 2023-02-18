package 순열_조합_집합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;

public class Main_백준_9375_s3 {
	public static int[] output;
	public static List<Integer> numOfClothes;
	public static int[][] C = new int[32][32];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken());

		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			Map<String, List<String>> clothes = new HashMap<>();

			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				String clothe = st.nextToken();
				String type = st.nextToken();
				List<String> list;
				if (clothes.get(type) == null) {
					list = new ArrayList<>();
				} else {
					list = clothes.get(type);
				}
				list.add(clothe);
				clothes.put(type, list);
			}

			numOfClothes = new ArrayList<>();
			for (Entry<String, List<String>> entry : clothes.entrySet()) {
				numOfClothes.add(entry.getValue().size());
			}

			// (nCx_1 + 1) * (nCx_2 + 1) * ... * (nCx_k + 1)
			int res = 1;
			getCombination();
			for (int i : numOfClothes) {
				res *= C[i + 1][1];
			}

			System.out.println(res - 1);
		}

	} // end of main

	public static void getCombination() {
		for (int i = 0; i < 31; i++) {
			for (int j = 0; j <= i; j++) {
				if (j == 0 || i == j)
					C[i][j] = 1;
				else
					C[i][j] = C[i - 1][j] + C[i - 1][j - 1];
			}
		}
	}

} // end of class

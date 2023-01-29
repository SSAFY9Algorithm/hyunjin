package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_백준_2615_s1 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int[][] map = new int[19][19];
		for (int i = 0; i < map.length; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < map.length; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// row
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				if (map[i][j] == 1 || map[i][j] == 2) {
					int idx = 0;
					while (j + idx + 1 < 19) {
						if (map[i][j + idx] != map[i][j + idx + 1])
							break;
						idx++;
					}
					if (idx == 4) {
						System.out.println(map[i][j]);
						System.out.printf("%d %d", i + 1, j + 1);
						return;
					}
					j += idx;
				}
			}
		}

		// col
		for (int j = 0; j < map.length; j++) {
			for (int i = 0; i < map.length; i++) {
				if (map[i][j] == 1 || map[i][j] == 2) {
					int idx = 0;
					while (i + idx + 1 < 19) {
						if (map[i + idx][j] != map[i + idx + 1][j])
							break;
						idx++;
					}
					if (idx == 4) {
						System.out.println(map[i][j]);
						System.out.printf("%d %d", i + 1, j + 1);
						return;
					}
					i += idx;
				}
			}
		}

		// diagonal right
		int[][] map_copy = new int[19][19];
		for (int i = 0; i < 19; i++) {
			map_copy[i] = map[i].clone();
		}
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				if (map_copy[i][j] == 1 || map_copy[i][j] == 2) {
					int idx = 0;
					while (i + idx + 1 < 19 && j + idx + 1 < 19) {
						if (map_copy[i + idx][j + idx] != map_copy[i + idx + 1][j + idx + 1])
							break;
						map_copy[i + idx][j + idx] = 0;
						idx++;
					}
					if (idx == 4) {
						System.out.println(map[i][j]);
						System.out.printf("%d %d", i + 1, j + 1);
//						for (int[] ks : map) {
//							System.out.println(Arrays.toString(ks));
//						}
						return;
					}
				}
			}
		}

		// diagonal left
//		map_copy = new int[19][19];
		for (int i = 0; i < 19; i++) {
			map_copy[i] = map[i].clone();
		}
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				if (map_copy[i][j] == 1 || map_copy[i][j] == 2) {
					int idx = 0;
					while (i + idx + 1 < 19 && j - idx - 1 >= 0) {
						if (map_copy[i + idx][j - idx] != map_copy[i + idx + 1][j - idx - 1])
							break;
						map_copy[i + idx][j - idx] = 0;
						idx++;
					}
//					System.out.println(idx);
					if (idx == 4) {
						System.out.println(map[i][j]);
						System.out.printf("%d %d", i + idx + 1, j - idx + 1);
						return;
					}
				}
			}
		}

		System.out.println(0);

	} // end of main
} // end of class

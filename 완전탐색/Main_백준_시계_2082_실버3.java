package 완전탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_백준_시계_2082_실버3 {
	static boolean isFind = false;
	static int[] res = new int[4];
	static String[] time = new String[5];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		for (int i = 0; i < 5; i++) {
			st = new StringTokenizer(br.readLine());
			String str = "";
			for (int j = 0; j < 4; j++) {
				str += st.nextToken();
			}
			time[i] = str;
		}

		getPermutation(0);
//		System.out.println(isFind);

	}

	public static void getPermutation(int depth) {
		if (isFind)
			return;
		if (depth == 4) {
			for (int i = 0; i < 5; i++) {
				String str = "";
				for (int j = 0; j < 4; j++) {
					str += getNumberStr(res[j], i);
				}

				for (int j = 0; j < str.length(); j++) {
					if (str.charAt(j) == '.' && time[i].charAt(j) == '#')
						return;
				}
			}
			isFind = true;
			System.out.printf("%d%d:%d%d", res[0], res[1], res[2], res[3]);
			return;
		}

		if (depth == 0) {
			for (int i = 0; i < 3; i++) {
				res[depth] = i;
				getPermutation(depth + 1);
			}
		} else if (depth == 2) {
			for (int i = 0; i < 6; i++) {
				res[depth] = i;
				getPermutation(depth + 1);
			}
		} else {
			for (int i = 0; i < 10; i++) {
				res[depth] = i;
				getPermutation(depth + 1);
			}
		}
	}

	public static String getNumberStr(int target, int height) {
		String str = "";

		if (height == 0) {
			switch (target) {
			case 1:
				str += "..#";
				break;
			case 4:
				str += "#.#";
				break;
			default:
				str += "###";
				break;
			}
		} else if (height == 1) {
			switch (target) {
			case 1:
			case 2:
			case 3:
			case 7:
				str += "..#";
				break;
			case 5:
			case 6:
				str += "#..";
				break;
			default:
				str += "#.#";
				break;
			}
		} else if (height == 2) {
			switch (target) {
			case 1:
			case 7:
				str += "..#";
				break;
			case 0:
				str += "#.#";
				break;
			default:
				str += "###";
				break;
			}
		} else if (height == 3) {
			switch (target) {
			case 2:
				str += "#..";
				break;
			case 0:
			case 6:
			case 8:
				str += "#.#";
				break;
			default:
				str += "..#";
				break;
			}
		} else {
			switch (target) {
			case 1:
			case 4:
			case 7:
				str += "..#";
				break;
			default:
				str += "###";
				break;
			}
		}

		return str;
	}

}

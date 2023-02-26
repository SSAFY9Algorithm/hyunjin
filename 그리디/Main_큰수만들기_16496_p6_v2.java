package 그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_큰수만들기_16496_p6_v2 {
	static String[] input;
	static String[] res;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		input = new String[n];
		boolean isZero = true;
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			input[i] = st.nextToken();
			if (isZero && !input[i].equals("0"))
				isZero = false;
		}

		// 답이 0일 때
		if (isZero) {
			System.out.println(0);
			return;
		}

		// merget sort
		res = new String[n];
		mergeSort(0, n - 1);
		StringBuilder maxNum = new StringBuilder();
		for (String str : res) {
			maxNum.append(str);
		}

		System.out.println(maxNum);

	} // end of main

	public static void mergeSort(int left, int right) {
		if (left >= right) {
			res[left] = input[left];
			return;
		}

		int mid = left + (right - left) / 2;
		mergeSort(left, mid);
		mergeSort(mid + 1, right);

		int idx = 0;
		int i = 0, j = 0;
		String[] temp = new String[right - left + 1];
		while (idx < temp.length) {
			if (mid + 1 + j > right) {
				temp[idx] = res[left + i];
				i++;
				idx++;
				continue;
			}
			if (left + i > mid) {
				temp[idx] = res[mid + 1 + j];
				j++;
				idx++;
				continue;
			}

			if (compareNum(res[left + i], res[mid + 1 + j])) {
				temp[idx] = res[left + i];
				i++;
			} else {
				temp[idx] = res[mid + 1 + j];
				j++;
			}
			idx++;
		}
		for (i = 0; i < temp.length; i++) {
			res[left + i] = temp[i];
		}
	}

	// true: str1 >= str2 / false: str1 > str2
	public static boolean compareNum(String str1, String str2) {
		String temp1 = str1.concat(str2), temp2 = str2.concat(str1);
		for (int j = 0; j < temp1.length(); j++) {
			if (temp1.charAt(j) > temp2.charAt(j))
				return true;
			else if (temp1.charAt(j) < temp2.charAt(j)) {
				return false;
			}
		}
		return true;
	}

} // end of class

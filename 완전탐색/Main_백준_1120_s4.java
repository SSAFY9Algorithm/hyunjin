package study;

import java.util.Scanner;

public class Main_백준_1120_s4 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		char[] str1 = sc.next().toCharArray();
		char[] str2 = sc.next().toCharArray();

		int max = 0;
		for (int i = 0; i <= str2.length - str1.length; i++) {
			int cnt = 0;
			for (int j = 0; j < str1.length; j++) {
				if (str2[i + j] == str1[j])
					cnt++;
			}
			max = Math.max(max, cnt);
		} // end of comparing same words
		System.out.println(str1.length - max);

	} // end of main
} // end of class

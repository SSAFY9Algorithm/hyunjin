package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_백준_2503_s3 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());

		ArrayList<String> list = new ArrayList<String>();
		for (int i = 123; i <= 987; i++) {
			if (i % 10 != 0 && i % 100 > 9) {
				int p = i / 100;
				int q = (i % 100) / 10;
				int r = (i % 100 % 10);
				if (p != q && p != r && q != r)
					list.add(String.valueOf(i));
			}
		}

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			String num = st.nextToken();
			int strike = Integer.parseInt(st.nextToken());
			int ball = Integer.parseInt(st.nextToken());

			for (int j = list.size() - 1; j >= 0; j--) {
				int s = 0;
				int b = 0;
				for (int k = 0; k < 3; k++) {
					if (list.get(j).charAt(k) == num.charAt(k))
						s++;
					else if (list.get(j).contains(num.subSequence(k, k + 1)))
						b++;
				}
				if (strike != s || ball != b)
					list.remove(j);
			}
		}

		System.out.println(list);

	} // end of main
} // end of class

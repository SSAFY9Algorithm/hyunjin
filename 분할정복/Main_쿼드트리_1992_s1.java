package 분할정복;

import java.util.Scanner;

public class Main_쿼드트리_1992_s1 {
	static char[][] map;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();

		map = new char[n][n];
		for (int i = 0; i < map.length; i++) {
			map[i] = sc.next().toCharArray();
		}

		System.out.println(checkNum(0, n - 1, 0, n - 1));
	}

	public static String checkNum(int x1, int x2, int y1, int y2) {
		// 한 칸의 경우
		if (x1 == x2)
			return map[x1][y1] + "";

		// 4칸 이상의 경우
		// 네 부분이 동일한 값인지 비교
		int midX = x1 + (int) ((x2 - x1) / 2);
		int midY = y1 + (int) ((y2 - y1) / 2);
		String s1 = checkNum(x1, midX, y1, midY);
		String s2 = checkNum(x1, midX, midY + 1, y2);
		String s3 = checkNum(midX + 1, x2, y1, midY);
		String s4 = checkNum(midX + 1, x2, midY + 1, y2);

		// 네 부분이 동일하다면 압축
		// (...) <- 이런 형태로 동일한 경우가 있어서 length 조건 추가.
		if (s1.equals(s2) && s2.equals(s3) && s3.equals(s4) && s1.length() == 1)
			return s1;
		// 네 부분이 동일하지 않다면
		// (...) 형태로 반환
		else
			return "(" + s1 + s2 + s3 + s4 + ")";
	}
}

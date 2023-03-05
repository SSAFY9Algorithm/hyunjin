package 삼성기출;

import java.io.*;
import java.util.*;

public class Main_백준_14891_톱니바퀴_골드5_128ms {
	// 0:N 1:S
	static int[][] gear = new int[4][8];
	static boolean[] isRotated;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		for (int i = 0; i < 4; i++) {
			String str = br.readLine();
			for (int j = 0; j < str.length(); j++) {
				gear[i][j] = str.charAt(j) - '0';
			}
		}

		int k = Integer.parseInt(br.readLine());
		while (k-- > 0) {
			st = new StringTokenizer(br.readLine());
			int no = Integer.parseInt(st.nextToken()) - 1;
			int dir = Integer.parseInt(st.nextToken());
			isRotated = new boolean[4];
			if (dir == 1)
				clockWise(no);
			else
				counterClockWise(no);
		}

		int score = 0;
		for (int i = 0; i < 4; i++) {
			if (gear[i][0] == 1)
				score += Math.pow(2, i);
		}

		System.out.println(score);
	}

	// 2:오른쪽 6:왼쪽
	private static void clockWise(int no) {
		// TODO 시계방향으로 톱니 회전
		// 이미 회전인 경우
		if (isRotated[no])
			return;
		isRotated[no] = true;
		// 양 옆 톱니 회전
		if (no > 0 && gear[no - 1][2] != gear[no][6])
			counterClockWise(no - 1);
		if (no < 3 && gear[no + 1][6] != gear[no][2])
			counterClockWise(no + 1);
		// 회전시키기
		int temp = gear[no][7];
		for (int i = 6; i >= 0; i--) {
			gear[no][i + 1] = gear[no][i];
		}
		gear[no][0] = temp;
	}

	private static void counterClockWise(int no) {
		// TODO 반시계방향으로 톱니 회전
		// 이미 회전인 경우
		if (isRotated[no])
			return;
		isRotated[no] = true;
		// 양 옆 톱니 회전
		if (no > 0 && gear[no - 1][2] != gear[no][6])
			clockWise(no - 1);
		if (no < 3 && gear[no + 1][6] != gear[no][2])
			clockWise(no + 1);
		// 회전시키기
		int temp = gear[no][0];
		for (int i = 1; i < 8; i++) {
			gear[no][i - 1] = gear[no][i];
		}
		gear[no][7] = temp;
	}
}

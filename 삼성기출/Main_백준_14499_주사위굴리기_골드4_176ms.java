package 삼성기출;

import java.io.*;
import java.util.*;

public class Main_백준_14499_주사위굴리기_골드4_176ms {
	static class Dice {
		int x, y;
		int up = 0, east = 2, north = 1;
		int[] num = { 0, 0, 0, 0, 0, 0 };

		public Dice(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	static int n, m;
	static Dice dice;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		dice = new Dice(x, y);
		map = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine());
		while (k-- > 0) {
			int dir = Integer.parseInt(st.nextToken());
			if (move(dir))
				operate();
		}

	}

	private static boolean move(int dir) {
		// TODO 주사위 이동
		// 1:동 2:서 3:북 4:남
		switch (dir) {
		case 1:
			if (dice.y == m - 1)
				return false;
			dice.y++;
			// 굴리기
			int west = 5 - dice.east;
			dice.east = dice.up;
			dice.up = west;
			break;
		case 2:
			if (dice.y == 0)
				return false;
			dice.y--;
			// 굴리기
			int down = 5 - dice.up;
			dice.up = dice.east;
			dice.east = down;
			break;
		case 3:
			if (dice.x == 0)
				return false;
			dice.x--;
			// 굴리기
			int south = 5 - dice.north;
			dice.north = dice.up;
			dice.up = south;
			break;
		case 4:
			if (dice.x == n - 1)
				return false;
			dice.x++;
			// 굴리기
			int down1 = 5 - dice.up;
			dice.up = dice.north;
			dice.north = down1;
			break;
		}
		System.out.println(dice.num[dice.up]);
		return true;
	}

	private static void operate() {
		// TODO 명령 수행
		// 이동한 칸에 쓰여 있는 수가 0일 때
		int down = 5 - dice.up;
		if (map[dice.x][dice.y] == 0) {
			// 주사위의 바닥면에 쓰여 있는 수가 칸에 복사
			map[dice.x][dice.y] = dice.num[down];
		}
		// 이동한 칸에 쓰여 있는 수가 0이 아닐 때
		else {
			// 칸에 쓰여 있는 수가 주사위의 바닥면으로 복사
			// 칸에 쓰여 있는 수는 0이 된다.
			dice.num[down] = map[dice.x][dice.y];
			map[dice.x][dice.y] = 0;
		}
	}
}

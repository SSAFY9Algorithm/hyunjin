package 삼성기출;

import java.io.*;
import java.util.*;

public class Main_백준_14503_로봇청소기_골드5_132ms {
	static class Robot {
		int x, y, d;

		public Robot(int x, int y, int d) {
			super();
			this.x = x;
			this.y = y;
			this.d = d;
		}
	}

	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	static int n, m;
	static int[][] map;
	static Robot robot;
	static int cnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		robot = new Robot(x, y, d);

		map = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		while (true) {
			clean();
			if (!move())
				break;
		}

		System.out.println(cnt);

	}

	private static void clean() {
		// TODO 현재 칸이 아직 청소되지 않은 경우, 현재 칸을 청소한다.
		if (map[robot.x][robot.y] == 0) {
			map[robot.x][robot.y] = 2;
			cnt++;
		}
	}

	private static boolean move() {
		// 주변에 청소되지 않은 빈칸이 있는지 찾기
		int i;
		for (i = 0; i < 4; i++) {
			int nx = robot.x + dx[i];
			int ny = robot.y + dy[i];
			if (nx < 0 || nx >= n || ny < 0 || ny >= m)
				continue;
			if (map[nx][ny] == 0)
				break;
		}
		// TODO 1. 주변 4칸 중 청소되지 않은 빈칸이 없는 경우
		if (i == 4) {
			// 바라보는 방향을 유지한 채로 한 칸 후진할 수 있다면 한 칸 후진
			// 0:north 1:east 2:south 3:west
			switch (robot.d) {
			case 0:
				if (robot.x == n - 1 || map[robot.x + 1][robot.y] == 1)
					return false;
				else
					robot.x++;
				break;
			case 1:
				if (robot.y == 0 || map[robot.x][robot.y - 1] == 1)
					return false;
				else
					robot.y--;
				break;
			case 2:
				if (robot.x == 0 || map[robot.x - 1][robot.y] == 1)
					return false;
				else
					robot.x--;
				break;
			case 3:
				if (robot.y == m - 1 || map[robot.x][robot.y + 1] == 1)
					return false;
				else
					robot.y++;
				break;
			}
		}
		// TODO 2. 주변 4칸 중 청소되지 않은 빈칸이 있는 경우
		else {
			// 반시계 방향으로 90도 회전
			// 0:north 1:east 2:south 3:west
			robot.d = (robot.d + 3) % 4;
			// 앞쪽 칸이 청소되지 않은 빈 칸인 경우 한 칸 전진
			switch (robot.d) {
			case 0:
				if (robot.x > 0 && map[robot.x - 1][robot.y] == 0)
					robot.x--;
				break;
			case 1:
				if (robot.y < m - 1 && map[robot.x][robot.y + 1] == 0)
					robot.y++;
				break;
			case 2:
				if (robot.x < n - 1 && map[robot.x + 1][robot.y] == 0)
					robot.x++;
				break;
			case 3:
				if (robot.y > 0 && map[robot.x][robot.y - 1] == 0)
					robot.y--;
				break;
			}
		}
		return true;
	}
}

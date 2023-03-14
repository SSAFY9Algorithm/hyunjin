package class5;

import java.util.Scanner;

public class Main_알파벳_1987_g4_1324ms {
	static int r, c;
	static char[][] map;
	static int max = 0;

	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		r = sc.nextInt();
		c = sc.nextInt();

		map = new char[r][c];
		for (int i = 0; i < map.length; i++) {
			map[i] = sc.next().toCharArray();
		}
		
		dfs(0,0,0,0);
		System.out.println(max);
	}

	public static void dfs(int x, int y, int visited, int cnt) {
		if (x >= r || x < 0 || y >= c || y < 0 || (visited & 1 << map[x][y] - 'A') > 0) {
			max = Math.max(max, cnt);
			return;
		}

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			dfs(nx, ny, visited | 1 << map[x][y] - 'A', cnt + 1);
		}
	}
}

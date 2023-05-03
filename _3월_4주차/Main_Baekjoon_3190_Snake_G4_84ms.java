package _3월_4주차;

import java.io.*;
import java.util.*;

public class Main_Baekjoon_3190_Snake_G4_84ms {
	static int n, k, l, time = 1;
	static int[][] map;
	static char[] rotateTime = new char[10001];
	// 우 하 좌 상
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };

	static class Snake {
		int[] start = { 0, 0 }, end = { 0, 0 };
		int dir = 1, len = 1;
	}

	static Snake snake = new Snake();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		k = Integer.parseInt(br.readLine());

		map = new int[n][n];
		map[0][0] = 1;
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			map[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1] = -1;
		}
		
		l = Integer.parseInt(br.readLine());
		for (int i = 0; i < l; i++) {
			st = new StringTokenizer(br.readLine());
			rotateTime[Integer.parseInt(st.nextToken())] = st.nextToken().charAt(0);
		}

		solution();

		System.out.println(time);
	}

	private static void solution() {
		// TODO 문제풀이 함수
		while (true) {
			if (!move())
				break;
			rotate();
			time++;
		}
	}

	private static boolean move() {
		// TODO 뱀 이동시키기
		snake.start[0] += dx[snake.dir - 1];
		snake.start[1] += dy[snake.dir - 1];
		if (checkEnd())
			return false;
		// 사과 먹었을 때
		if (map[snake.start[0]][snake.start[1]] == -1) {
			snake.len++;
		} else {
			int dir = map[snake.end[0]][snake.end[1]];
			map[snake.end[0]][snake.end[1]] = 0;
			snake.end[0] += dx[dir - 1];
			snake.end[1] += dy[dir - 1];
		}
		map[snake.start[0]][snake.start[1]] = snake.dir;
		return true;
	}

	private static boolean checkEnd() {
		// TODO 종료조건 체크
		// 벽에 부딪혔을 때
		if (snake.start[0] >= n || snake.start[0] < 0 || snake.start[1] >= n || snake.start[1] < 0)
			return true;
		// 자기 몸과 부딪혔을 때
		if (map[snake.start[0]][snake.start[1]] > 0)
			return true;
		return false;
	}

	private static void rotate() {
		// TODO 방향 회전하기
		switch (rotateTime[time]) {
		case 'D':
			snake.dir = snake.dir % 4 + 1;
			map[snake.start[0]][snake.start[1]] = snake.dir;
			break;
		case 'L':
			snake.dir = (snake.dir + 2) % 4 + 1;
			map[snake.start[0]][snake.start[1]] = snake.dir;
			break;
		default:
			break;
		}
	}
}

package _4월_1주차;

import java.io.*;
import java.util.*;

public class Main_Baekjoon_20057_WizardSharkAndTornado_G3_544ms {
	static int n;
	static int[][] map;
	static int[][] seq;
	static int totalOutAmount;
	static int[][] dir = { { 0, -1 }, { 1, 0 }, { 0, 1 }, { -1, 0 } };
	static int[][][] percent = 
				{{{-1, 0, 1},{1, 0, 1},{-2, -1, 2},{2, -1, 2},{-1, -1, 7},{1, -1, 7},{-1, -2, 10},{1, -2, 10},{0, -3, 5}},
				 {{0, -1, 1},{0, 1, 1},{1, -2, 2},{1, 2, 2},{1, -1, 7},{1, 1, 7},{2, -1, 10},{2, 1, 10},{3, 0, 5}},
				 {{-1, 0, 1},{1, 0, 1},{-2, 1, 2},{2, 1, 2},{-1, 1, 7},{1, 1, 7},{-1, 2, 10},{1, 2, 10},{0, 3, 5}},
				 {{0, -1, 1},{0, 1, 1},{-1, -2, 2},{-1, 2, 2},{-1, -1, 7},{-1, 1, 7},{-2, -1, 10},{-2, 1, 10},{-3, 0, 5}}};
	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		seq = new int[n * n][3];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		solution();
		System.out.println(totalOutAmount);
	}

	private static void solution() {
		// TODO 문제풀이 함수
		makeSequence();
		for (int i = 0; i < seq.length - 1; i++) {
			moveTornado(i);
		}
	}

	private static void moveTornado(int no) {
		// TODO 토네이도 이동
		int r = seq[no][0], c = seq[no][1], d=seq[no][2], nr=seq[no+1][0], nc=seq[no+1][1];
		int amount = map[nr][nc];
		map[nr][nc] = 0;
		
		int spreadAmount = 0;
		// 비율만큼 이동하기
		for (int i = 0; i < percent[d].length; i++) {
			int x = r + percent[d][i][0];
			int y = c + percent[d][i][1];
			int p = percent[d][i][2];
			int sandAmount = p*amount/100;
			spreadAmount += sandAmount;
			// 격자 밖으로 나갔을 때
			if(x >= n || x < 0 || y >= n || y < 0) 
				totalOutAmount += sandAmount;
			// 격자 안에 일 때
			else 
				map[x][y] += sandAmount;
			
		}
		// 남은 모래 이동하기
		nr += dir[d][0];
		nc += dir[d][1];
		// 격자 밖으로 나갔을 때
		if(nr >= n || nr < 0 || nc >= n || nc < 0)
			totalOutAmount += amount - spreadAmount;
		// 격자 안에 일 때
		else 
			map[nr][nc] += amount - spreadAmount;
	}

	private static void makeSequence() {
		// TODO 회오리 인덱스 리스트 만들기
		int r = n / 2, c = n / 2, d = 0, nextD = 1;
		boolean[][] visited = new boolean[n][n];
		visited[r][c] = true;
		seq[0] = new int[] {r, c, d};
		for (int i = 1; i < n * n; i++) {
			r += dir[d][0];
			c += dir[d][1];
			visited[r][c] = true;
			seq[i][0] = r;
			seq[i][1] = c;
			if (r == 0 && c == 0)
				break;
			if (!visited[r + dir[nextD][0]][c + dir[nextD][1]]) {
				d = nextD;
				nextD = (nextD + 1) % 4;
			}
			seq[i][2] = d;
		}
	}

}

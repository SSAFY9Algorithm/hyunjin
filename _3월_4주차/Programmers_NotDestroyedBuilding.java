package _3월_4주차;

import java.util.*;

public class Programmers_NotDestroyedBuilding {
	public static void main(String[] args) {
		int[][] board = { { 5, 5, 5, 5, 5 }, { 5, 5, 5, 5, 5 }, { 5, 5, 5, 5, 5 }, { 5, 5, 5, 5, 5 } };
		int[][] skill = { { 1, 0, 0, 3, 4, 4 }, { 1, 2, 0, 2, 3, 2 }, { 2, 1, 0, 3, 1, 2 }, { 1, 0, 1, 3, 3, 1 } };
		System.out.println(solution(board, skill));
	}

	public static int solution(int[][] board, int[][] skill) {
		int answer = 0;
		int n = board.length, m = board[0].length;
		int[][] cal = new int[n][m];

		for (int i = 0; i < skill.length; i++) {
			int degree = skill[i][0] == 1 ? -skill[i][5] : skill[i][5];
            cal[skill[i][1]][skill[i][2]] += degree;
            if (skill[i][3] + 1 < n)
                cal[skill[i][3] + 1][skill[i][2]] += -degree;
            if (skill[i][4] + 1 < m)
                cal[skill[i][1]][skill[i][4] + 1] += -degree;
            if (skill[i][3] + 1 < n && skill[i][4] + 1 < m)
                cal[skill[i][3] + 1][skill[i][4] + 1] += degree;
		}

		for (int x = 0; x < n; x++) {
			int sum = 0;
			for (int y = 0; y < m; y++) {
				sum += cal[x][y];
                cal[x][y] = sum;
			}
		}
        for (int y = 0; y < m; y++) {
			int sum = 0;
			for (int x = 0; x < n; x++) {
				sum += cal[x][y];
                cal[x][y] = sum;
                if(cal[x][y] + board[x][y] > 0) answer++;
			}
		}

		return answer;
	}
}

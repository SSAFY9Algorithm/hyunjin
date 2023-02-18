package 백트래킹;
import java.util.Scanner;

public class Main_NQueen_9663_g4_3416ms {
	// index: row, element: col
	static int[] row;
	static int n;
	static int cnt = 0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		row = new int[n];
		dfs(0, 0);
		System.out.println(cnt);
	}

	public static void dfs(int depth, int visited) {
		if (depth == n) {
			cnt++;
			return;
		}

		for (int i = 0; i < n; i++) {
			// 이미 퀸이 놓인 열 일 때
			if ((visited & 1 << i) > 0)
				continue;
			
			// 이미 퀸이 놓인 대각선 일 때
			int j;
			for (j = 1; j <= depth; j++) {
				if (i == row[depth - j] + j || i == row[depth - j] - j)
					break;
			}
			if (j <= depth)
				continue;
			
			visited = visited | 1 << i;
			row[depth] = i;
			dfs(depth + 1, visited);
			visited = visited & ~(1 << i);
		}
	}

}

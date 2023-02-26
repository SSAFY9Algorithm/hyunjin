package dfs_bfs;
import java.util.Scanner;

public class Main_숫자고르기_2668_g5 {
	static int[] visited;
	static int[] arr;
	static int n;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();
		visited = new int[n + 1];
		arr = new int[n + 1];
		for (int i = 1; i < arr.length; i++) {
			arr[i] = sc.nextInt();
		}

		int idx = 0;
		int[] res = new int[100];
		for (int i = 1; i < arr.length; i++) {
			// 자기 자신으로 돌아오는 원소인지 확인하기
			if (dfs(i, i, 0)) {
				res[idx] = i;
				idx++;
			}
		}

		StringBuilder sb = new StringBuilder(idx + "\n");
		for (int i = 0; i < idx; i++) {
			sb.append(res[i] + "\n");
		}
		System.out.println(sb);
	}

	public static boolean dfs(int start, int cur, int depth) {
		// 자기 자신으로 돌아오는 경우
		if (depth > 0 && start == cur) {
			return true;
		}
		// 자기 자신으로 돌아오지 못하는 경우
		if (depth == n) {
			return false;
		}
		return dfs(start, arr[cur], depth + 1);
	}
}

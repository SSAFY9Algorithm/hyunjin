package 삼성기출;

import java.io.*;
import java.util.*;

// bfs + subset

public class Main_백준_17471_게리맨더링_골드4_132ms {
	public static int n, min = Integer.MAX_VALUE;
	public static int[] population;
	public static int[][] adj;

	public static int[] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());

		population = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < population.length; i++) {
			population[i] = Integer.parseInt(st.nextToken());
		}

		adj = new int[n][];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int k = Integer.parseInt(st.nextToken());
			adj[i] = new int[k];
			for (int j = 0; j < k; j++) {
				adj[i][j] = Integer.parseInt(st.nextToken()) - 1;
			}
		}

		dp = new int[1 << n];
		subset();

		System.out.println(min == Integer.MAX_VALUE ? -1 : min);

	}

	// 부분집합
	public static void subset() {
		for (int isSelected = 1; isSelected < 1 << n - 1; isSelected++) {
			// memoization
			if (dp[isSelected] == 0) {
				dp[isSelected] = isConnected(isSelected);
			}
			int other = (1 << n) - 1 - isSelected;
			if (dp[other] == 0) {
				dp[other] = isConnected(other);
			}

			// 2개의 set이 모두 연결되는 set인지 확인
			if (dp[isSelected] > 0 && dp[other] > 0) {
				// 2개의 부분집합이 각각 연결되어 있는 상태라면 min값 갱신
				min = Math.min(min, Math.abs(dp[isSelected] - dp[other]));
			}
		}
	}

	// bfs
	public static int isConnected(int isSelected) {
		// 출발지점 찾기
		int start;
		for (start = 0; start < n; start++) {
			if ((isSelected & 1 << start) > 0)
				break;
		}

		Queue<Integer> queue = new ArrayDeque<Integer>();
		queue.offer(start);
		int visited = 0 | 1 << start, sum = population[start];
		int cur;
		while (!queue.isEmpty()) {
			cur = queue.poll();
			for (int next : adj[cur]) {
				// 이미 방문한 정점이거나 subset으로 선택되지 않은 정점일 경우 패스
				if ((visited & 1 << next) != 0 || (isSelected & 1 << next) == 0)
					continue;
				queue.add(next);
				visited |= 1 << next;
				sum += population[next];
			}
			// 모두 연결된 subset인 경우
			if (visited == isSelected)
				return sum;
		}

		return -1;
	}
}

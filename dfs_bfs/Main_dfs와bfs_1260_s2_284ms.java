package dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Main_dfs와bfs_1260_s2_284ms {
	static List<Integer>[] map;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int v = Integer.parseInt(st.nextToken());

		map = new ArrayList[n + 1];
		visited = new boolean[n + 1];

		int a, b;
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			if (map[a] == null)
				map[a] = new ArrayList<Integer>();
			if (map[b] == null)
				map[b] = new ArrayList<Integer>();
			map[a].add(b);
			map[b].add(a);
		}
		for (int i = 1; i < map.length; i++) {
			if (map[i] == null)
				continue;
			Collections.sort(map[i]);
		}

		// 간선이 없는 노드일 때
		if (map[v] == null) {
			System.out.println(v + "\n" + v);
			return;
		}

		System.out.println(dfs(v) + "\n" + bfs(v));

	}

	public static String dfs(int cur) {
		StringBuilder res = new StringBuilder();
		if (visited[cur])
			return "";

		visited[cur] = true;
		res.append(cur).append(" ");
		for (int next : map[cur]) {
			if (visited[next])
				continue;
			res.append(dfs(next));
		}

		return res.toString();
	}

	public static String bfs(int start) {
		StringBuilder res = new StringBuilder();

		Deque<Integer> dq = new ArrayDeque<Integer>();
		boolean[] visited = new boolean[1001];
		visited[start] = true;
		dq.addLast(start);
		res.append(start).append(" ");

		int cur;
		while (dq.size() > 0) {
			cur = dq.pollFirst();
			for (int next : map[cur]) {
				if (visited[next])
					continue;
				dq.addLast(next);
				visited[next] = true;
				res.append(next).append(" ");
			}
		}

		return res.toString();
	}
}

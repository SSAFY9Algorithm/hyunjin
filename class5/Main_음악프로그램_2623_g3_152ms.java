package class5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

// 위상정렬

public class Main_음악프로그램_2623_g3_152ms {
	public static class Node {
		int indegree = 0;
		List<Integer> children = new ArrayList<Integer>();
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		Node[] adj = new Node[n + 1];
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int cnt = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken()), b;
			if (adj[a] == null)
				adj[a] = new Node();
			for (int j = 1; j < cnt; j++) {
				b = Integer.parseInt(st.nextToken());
				if (adj[b] == null)
					adj[b] = new Node();
				adj[a].children.add(b);
				adj[b].indegree++;
				a = b;
			}
		}

		Queue<Integer> queue = new ArrayDeque<Integer>();
		for (int i = 1; i < adj.length; i++) {
			if (adj[i] == null || adj[i].indegree == 0)
				queue.offer(i);
		}

		int cur;
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i < adj.length; i++) {
			if (queue.size() == 0) {
				System.out.println(0);
				return;
			}

			cur = queue.poll();
			sb.append(cur).append("\n");
			if (adj[cur] == null)
				continue;
			
			for (int next : adj[cur].children) {
				if (--adj[next].indegree == 0)
					queue.offer(next);
			}
		}

		System.out.println(sb);
	}
}

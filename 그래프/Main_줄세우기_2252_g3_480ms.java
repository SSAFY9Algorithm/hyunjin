package 그래프;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

// topology sort
public class Main_줄세우기_2252_g3_480ms {
	public static class Node {
		int indegree;
		List<Integer> children = new ArrayList<Integer>();
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		Node[] nodes = new Node[n + 1];
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if (nodes[a] == null)
				nodes[a] = new Node();
			if (nodes[b] == null)
				nodes[b] = new Node();
			nodes[a].children.add(b);
			nodes[b].indegree++;
		}

		Deque<Integer> dq = new ArrayDeque<Integer>();
		for (int i = 1; i < nodes.length; i++) {
			if (nodes[i] == null || nodes[i].indegree == 0)
				dq.addLast(i);
		}

		int cur;
		for (int i = 1; i < nodes.length; i++) {
			cur = dq.pollFirst();
			sb.append(cur).append(" ");
			
			if (nodes[cur] == null)
				continue;

			for (int next : nodes[cur].children) {
				if (--nodes[next].indegree == 0)
					dq.addLast(next);
			}
		}
		
		System.out.println(sb);
		
	}
}

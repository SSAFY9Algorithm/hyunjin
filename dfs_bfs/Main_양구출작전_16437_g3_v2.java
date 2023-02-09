package dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_양구출작전_16437_g3_v2 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());
		// c
		Node[] island = new Node[n + 1];
		island[1] = new Node();
		for (int i = 2; i < island.length; i++) {
			st = new StringTokenizer(br.readLine());
			if (island[i] == null)
				island[i] = new Node();

			if (st.nextToken().equals("W"))
				island[i].wolf = Integer.parseInt(st.nextToken());
			else
				island[i].sheep = Integer.parseInt(st.nextToken());

			int parent = Integer.parseInt(st.nextToken());
			if (island[parent] == null)
				island[parent] = new Node();
			island[parent].children.add(i);
			island[i].parent = parent;
		}

		// dfs 풀이
		Stack<Integer> stack = new Stack<>();
		stack.push(1);
		while (stack.size() > 0) {
			int cur = stack.peek();
			if (island[cur].children.isEmpty()) {
				cur = stack.pop();
				if(cur == 1) break;
				// 지금까지 계산한 것 부모 노드에 반영
				if (island[cur].sheep > 0) {
					// 지나갈 수 있는 양이 있는 경우
					// 부모 노드에 지나갈 수 있는 양의 수 더하기
					if(island[cur].sheep > island[island[cur].parent].wolf) {
						island[island[cur].parent].sheep += island[cur].sheep - island[island[cur].parent].wolf;
						island[island[cur].parent].wolf = 0;
					} 
					// 지나갈 수 있는 양이 없는 경우
					// 부모 노드에서 배부른 늑대의 수 빼기
					else {
						island[island[cur].parent].wolf -= island[cur].sheep;
					}
				} 
			} 
			else {
				stack.push(island[cur].children.poll());
			}
		} // end of while

		System.out.println(island[1].sheep);

	} // end of main
} // end of class

class Node {
	int parent;
	Queue<Integer> children = new LinkedList<Integer>();
	// int 범위 초과함
	long sheep, wolf;
}

package 트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_트리_1068_g5 {
	public static int cnt = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		Node[] nodes = new Node[n];
		Node root = null;

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			// 노드 생성 안 된 경우 노드 생성
			if (nodes[i] == null)
				nodes[i] = new Node();

			int parent = Integer.parseInt(st.nextToken());
			if (parent != -1) {
				// 부모 노드 생성 안 된 경우 노드 생성
				if (nodes[parent] == null)
					nodes[parent] = new Node();
				nodes[i].parent = nodes[parent];

				// 부모 노드에 자식 추가
				if (nodes[parent].children == null)
					nodes[parent].children = new ArrayList<Node>();
				nodes[parent].children.add(nodes[i]);

			} else {
				// 루트 노드일 때
				root = nodes[i];
			}
		}

		st = new StringTokenizer(br.readLine());
		int deleted = Integer.parseInt(st.nextToken());
		findLeaves(root, nodes[deleted]);

		System.out.println(cnt);

	} // end of main

	public static void findLeaves(Node cur, Node deleted) {
		if (cur == deleted)
			return;
		if (cur.children == null) {
			cnt++;
			return;
		}
		// 일렬로 된 트리의 경우
		if (cur.children.size() == 1 && cur.children.get(0) == deleted) {
			cnt++;
			return;
		}
		
		for (Node next : cur.children)
			findLeaves(next, deleted);
	} // end of findLeaves

} // end of class

class Node {
	List<Node> children;
	Node parent;
}

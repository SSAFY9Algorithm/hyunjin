package 트리;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Main_트리의지름_1967_g4 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		
		// 노드 1개인 경우
		if (n == 1) {
			System.out.println(0);
			return;
		}

		// 트리 생성하기
		Node[] nodes = new Node[n + 1];
		for (int i = 0; i < n - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int cur = Integer.parseInt(st.nextToken());
			int child = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());

			if (nodes[cur] == null)
				nodes[cur] = new Node(cur, null, 0);

			// 자식 노드 생성 안 된 경우
			if (nodes[child] == null)
				nodes[child] = new Node(child, nodes[cur], dist);
			// 자식 노드 이미 생성된 경우
			else {
				nodes[child].parent = nodes[cur];
				nodes[child].dist = dist;
			}
			nodes[cur].children.add(nodes[child]);
		}

		// 리프 노드 확인 -> 리프 노드의 부모 노드를 큐에 넣기
		Queue<Node> queue = new LinkedList<Node>();
		boolean[] visited = new boolean[n + 1];
		for (Node node : nodes) {
			if (node != null && node.children.size() == 0) {
				visited[node.id] = true;
				// 자식 노드보다 먼저 큐에 들어가는 경우 방지
				boolean isOkay = true;
				for (Node c : node.parent.children) {
					if (!visited[c.id]) {
						isOkay = false;
						break;
					}
				}
				if (isOkay) {
					queue.add(node.parent);
					visited[node.parent.id] = true;
				}
			}
		}

		int res = 0;
		while (queue.size() > 0) {
			Node cur = queue.poll();
			if (cur.parent != null && !visited[cur.parent.id]) {
				// 자식 노드보다 먼저 큐에 들어가는 경우 방지
				boolean isOkay = true;
				for (Node c : cur.parent.children) {
					if (!visited[c.id]) {
						isOkay = false;
						break;
					}
				}
				if (isOkay) {
					queue.add(cur.parent);
					visited[cur.parent.id] = true;
				}
			}

			// 자식들의 maxLen 내림차순 정렬
			List<Integer> list = new ArrayList<>();
			for (Node c : cur.children) {
				list.add(c.dist + c.maxLen);
			}
			Collections.sort(list, (a, b) -> -a.compareTo(b));
			
			// 트리 지름 계산 -> 트리 지름 최대값 res에 저장
			cur.maxLen = list.get(0);
			if (list.size() > 1) {
				res = Math.max(res, list.get(0) + list.get(1));
			} else {
				res = Math.max(res, list.get(0));
			}

		}

		System.out.println(res);

	}
}

class Node {
	int id, maxLen, dist;
	Node parent;
	List<Node> children = new ArrayList<Node>();

	public Node() {
	}

	public Node(int id, Node parent, int dist) {
		this.id = id;
		this.parent = parent;
		this.dist = dist;
	}
}

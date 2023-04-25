package _3월_4주차;

import java.util.*;

public class Programmers_SheepAndWolf {
	public static void main(String[] args) {
		int[] Info = { 0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1 };
		int[][] Edges = { { 0, 1 }, { 1, 2 }, { 1, 4 }, { 0, 8 }, { 8, 7 }, { 9, 10 }, { 9, 11 }, { 4, 3 }, { 6, 5 },
				{ 4, 6 }, { 8, 9 } };
//		int[] Info = { 0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0 };
//		int[][] Edges = { { 0, 1 }, { 0, 2 }, { 1, 3 }, { 1, 4 }, { 2, 5 }, { 2, 6 }, { 3, 7 }, { 4, 8 }, { 6, 9 },
//				{ 9, 10 } };
		System.out.println(solution(Info, Edges));
	}

	static class Node {
		Node left, right;
		int type, idx; // type -> 0:양 1:늑대

		public Node(int type, int idx) {
			this.type = type;
			this.idx = idx;
		}

	}

	static int n;
	static Node[] nodeList;
	static int[] info;
	static int[][] edges;

	public static int solution(int[] Info, int[][] Edges) {
		info = Info;
		edges = Edges;
		n = info.length;

		makeTree();
		
		// 1. 지금까지 방문한 경로
		// 2. 앞으로 갈 수 있는 정점들
		// 3. 만난 양, 늑대 개수
		// 이거 3개 정보 갖고 다니면서 큐에 넣기
		// 4. bfs 하기
		// cur ->
		// 1) for문 돌리면서 앞으로 갈 수 있는 정점들 방문 경로에 추가
		// 2) 추가된 정점에 대해 앞으로 갈 수 있는 정점 추가
		return doBfs();
	}

	private static int doBfs() {
		int maxSheepCnt = 0;

		Queue<int[]> queue = new ArrayDeque<int[]>();
		boolean[] visited = new boolean[1 << 17];

		// root 노드 넣기
		int childList = 0;
		childList |= 1 << nodeList[0].left.idx;
		if (nodeList[0].right != null)
			childList |= 1 << nodeList[0].right.idx;
		queue.offer(new int[] { 1, childList, 1, 0 });

		int[] cur;
		while (!queue.isEmpty()) {
			cur = queue.poll();
			if (visited[cur[0]])
				continue;
			visited[cur[0]] = true;
			for (int i = 0; i < n; i++) {
				// i: 방문할 정점 인덱스
				if ((cur[1] & 1 << i) != 0) {
					int path = cur[0] | 1 << i;
					int cand = cur[1] & ~(1 << i);
					int sheepCnt = cur[2], wolfCnt = cur[3];
					if (nodeList[i].type == 0)
						sheepCnt++;
					else
						wolfCnt++;

					maxSheepCnt = Math.max(maxSheepCnt, sheepCnt);
					// 더 이상 갈 수 없을 때
					if (wolfCnt >= sheepCnt) {
						visited[path] = true;
						continue;
					}
					// 더 갈 수 있을 때
					if (nodeList[i].left != null)
						cand |= 1 << nodeList[i].left.idx;
					if (nodeList[i].right != null)
						cand |= 1 << nodeList[i].right.idx;
					queue.offer(new int[] { path, cand, sheepCnt, wolfCnt });
				}
			}
		}

		return maxSheepCnt;
	}

	// 노드 트리 만들기
	public static void makeTree() {
		nodeList = new Node[n];
		for (int i = 0; i < n - 1; i++) {
			int parent = edges[i][0];
			int child = edges[i][1];
			if (nodeList[parent] == null)
				nodeList[parent] = new Node(info[parent], parent);
			if (nodeList[child] == null)
				nodeList[child] = new Node(info[child], child);
			if (nodeList[parent].left == null)
				nodeList[parent].left = nodeList[child];
			else
				nodeList[parent].right = nodeList[child];
		}
	}

}

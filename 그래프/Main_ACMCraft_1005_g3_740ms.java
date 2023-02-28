package 그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

// 위상정렬
// 1. 큐에 indegree = 0 인 노드 넣기
// 2. 큐 head에서 노드 뺀 뒤에 해당 노드에서 나가는 간선을 삭제
// 3. indegree = 0 인 노드를 큐에 넣기
// 4. 2-3 과정을 노드 개수만큼 반복
// 큐에서 빼는 노드 순서 = 위상 정렬 결과

public class Main_ACMCraft_1005_g3_740ms {
	public static class Node {
		// indegree: 들어오는 간선 수
		// time: 해당 건물을 짓는데 필요한 시간
		// maxT: 이전까지 필요한 최대 건설 시간의 총 합
		int indegree, time, maxT;
		// 자식 node id
		List<Integer> children = new ArrayList<Integer>();
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		while (T-- > 0) {

			st = new StringTokenizer(br.readLine(), " ");
			int n = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());

			Node[] nodes = new Node[n + 1];

			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 1; i < nodes.length; i++) {
				nodes[i] = new Node();
				nodes[i].time = Integer.parseInt(st.nextToken());
			}

			while (k-- > 0) {
				st = new StringTokenizer(br.readLine(), " ");
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());

				nodes[x].children.add(y);
				nodes[y].indegree += 1;
			}

			int w = Integer.parseInt(br.readLine());

			Deque<Integer> dq = new ArrayDeque<Integer>();

			// 1. 큐에 indegree = 0 인 노드 넣기
			for (int i = 1; i < nodes.length; i++) {
				if (nodes[i].indegree == 0)
					dq.add(i);
			}

			int id;
			int totalT = Integer.MAX_VALUE;
			for (int i = 1; i < nodes.length; i++) {
//				if(pq.size() == 0) : 위상정렬 불가능
				id = dq.poll();

				if (id == w) {
					totalT = nodes[w].time + nodes[w].maxT;
					break;
				}

				// 이전까지 최대로 걸리는 건설 시간의 총 합 찾기
				for (int cId : nodes[id].children) {
					nodes[cId].maxT = Math.max(nodes[cId].maxT, nodes[id].time + nodes[id].maxT);
					// 2. 큐 head에서 노드 뺀 뒤에 해당 노드에서 나가는 간선을 삭제
					// 3. indegree = 0 인 노드를 큐에 넣기
					if (--nodes[cId].indegree == 0)
						dq.add(cId);
				}
			}

			sb.append(totalT).append("\n");

		} // end of tc

		System.out.println(sb);

	}
}

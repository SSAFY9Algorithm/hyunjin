package _4월_2주차;

import java.util.*;

public class 프로그래머스_코딩테스트공부 {
	// Dijkstra
	static class Node implements Comparable<Node> {
		int time, al, co;

		public Node(int time, int al, int co) {
			this.time = time;
			this.al = al;
			this.co = co;
		}

		@Override
		public int compareTo(Node o) {
			return this.time == o.time ? this.al == o.al ? this.co - o.co : this.al - o.al : this.time - o.time;
		}
	}

	static int alp, cop, maxAlp, maxCop;
	static int[][] problems;

	public int solution(int Alp, int Cop, int[][] Problems) {
		alp = Alp;
		cop = Cop;
		problems = Problems;

		for (int i = 0; i < problems.length; i++) {
			maxAlp = Math.max(maxAlp, problems[i][0]);
			maxCop = Math.max(maxCop, problems[i][1]);
		}

		return solution();
	}

	int solution() {
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		pq.offer(new Node(0, alp, cop));
		boolean[][] visited = new boolean[1000][1000];
		Node cur;
		while (!pq.isEmpty()) {
			cur = pq.poll();
			// 방문 정보 저장하기, 재방문 안하기!!!!!!!!!!!!!!!!!
			if (visited[cur.al][cur.co])
				continue;
			visited[cur.al][cur.co] = true;

			if (cur.al >= maxAlp && cur.co >= maxCop)
				return cur.time;
			pq.offer(new Node(cur.time + 1, cur.al + 1, cur.co));
			pq.offer(new Node(cur.time + 1, cur.al, cur.co + 1));
			for (int i = 0; i < problems.length; i++) {
				if (cur.al >= problems[i][0] && cur.co >= problems[i][1])
					pq.offer(new Node(cur.time + problems[i][4], cur.al + problems[i][2], cur.co + problems[i][3]));
			}
		}

		return -1;
	} // end of solution
}

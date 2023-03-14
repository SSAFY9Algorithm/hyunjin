package 최소신장트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// MST
// kruskal
// sparse graph에 적합
// 1. 간선을 가중치로 오름차순 정렬
// 2. 모든 간선에 대해 가중치가 낮은 것 부터 선택
// 3. 만약 사이클을 이룬다면 해당 간선 버림
// 사이클을 형성하는지 판단하기 위해 union-find 사용
// 간선을 이루는 두 정점이 같은 집합에 포함된다면 cycle 형성 
// 시간복잡도: O(ElogE)

public class Main_도시건설_21924_g4_1140ms_v2 {
	public static class Edge implements Comparable<Edge> {
		int start, end;
		double dist;

		public Edge(int start, int end, double dist) {
			super();
			this.start = start;
			this.end = end;
			this.dist = dist;
		}

		@Override
		public int compareTo(Edge o) {
			return (int) (this.dist - o.dist);
		}

	}

	public static class Road implements Comparable<Road> {
		int dest;
		double dist;

		public Road(int dest, double dist) {
			super();
			this.dest = dest;
			this.dist = dist;
		}

		@Override
		public int compareTo(Road o) {
			return (int) (this.dist - o.dist);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
		double totalDist = 0, minDist = 0;
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());
			pq.offer(new Edge(a, b, dist));
			totalDist += dist;
		}

		int cnt = 0;
		int[] parents = new int[n + 1];
		for (int i = 1; i < parents.length; i++) {
			parents[i] = i;
		}
		Edge cur;
		while (pq.size() > 0) {
			cur = pq.poll();
			if (union(cur.start, cur.end, parents)) {
				minDist += cur.dist;
				cnt++;
			}
		}

		System.out.printf("%.0f", cnt != n-1 ? -1 : totalDist - minDist);
	}

	public static int find(int a, int[] parents) {
		int parent = parents[a];
		if (parent != a)
			parents[a] = find(parents[a], parents);
		return parents[a];
	}

	public static boolean union(int a, int b, int[] parents) {
		int pA = find(a, parents);
		int pB = find(b, parents);
		if (pA == pB)
			return false;
		else if (pA < pB)
			parents[pB] = pA;
		else
			parents[pA] = pB;
		return true;
	}
}

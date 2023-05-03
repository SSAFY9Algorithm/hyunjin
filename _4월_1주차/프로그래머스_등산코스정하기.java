package _4월_1주차;

import java.util.*;

public class 프로그래머스_등산코스정하기 {
	public static void main(String[] args) {
		int N = 6;
		int[][] Paths = { {} };
		int[] Gates = { 5 }, Summits = { 5, 3 };
		System.out.println(Arrays.toString(solution(N, Paths, Gates, Summits)));
	}

	static int n;
	static int[][] paths;
	static int[] gates, summits;
	static List<int[]>[] adjList;

	static int minIntensity = Integer.MAX_VALUE, summitNo;
	static int[] intensities;

	public static int[] solution(int N, int[][] Paths, int[] Gates, int[] Summits) {

		n = N;
		paths = Paths;
		gates = Gates;
		summits = Summits;

		Arrays.sort(summits);

		adjList = new ArrayList[n + 1];
		for (int i = 1; i < n + 1; i++) {
			adjList[i] = new ArrayList<int[]>();
		}
		for (int i = 0; i < paths.length; i++) {
			adjList[paths[i][0]].add(new int[] { paths[i][1], paths[i][2] });
			adjList[paths[i][1]].add(new int[] { paths[i][0], paths[i][2] });
		}

		for (int i = 0; i < summits.length; i++) {
			doBFS(i);
			for (int j = 0; j < gates.length; j++) {
				if (minIntensity > intensities[j]) {
					minIntensity = intensities[j];
					summitNo = i;
				}
			}
		}

		return new int[] { minIntensity, summitNo };
	}

	static void doBFS(int summit) {
		intensities = new int[n + 1];
		intensities[summit] = -1;

		PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a, b) -> a[1] - b[1]);
		int[] cur;
		for (int i = 0; i < adjList[summit].size(); i++) {
			pq.offer(adjList[summit].get(i));
			intensities[adjList[summit].get(i)[0]] = adjList[summit].get(i)[1];
		}
		while (!pq.isEmpty()) {
			cur = pq.poll();
			for (int i = 0; i < adjList[cur[0]].size(); i++) {
				int next = adjList[cur[0]].get(i)[0];
				if (next == summit)
					continue;
				int itns = adjList[cur[0]].get(i)[1];
				itns = Math.max(intensities[cur[0]], itns);
				if (intensities[next] == 0 | intensities[next] > itns) {
					pq.offer(new int[] { next, itns });
					intensities[next] = itns;
				}
			}
		}
	}
}

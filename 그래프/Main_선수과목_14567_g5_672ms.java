package 그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Main_선수과목_14567_g5_672ms {

	public static class Node {
		List<Integer> children = new ArrayList<Integer>();
		int indegree, prevCnt = 1;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		Node[] course = new Node[n + 1];
		for (int i = 1; i < course.length; i++) {
			course[i] = new Node();
		}
		
		int a, b;
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			course[b].indegree++;
			course[a].children.add(b);
		}

		Deque<Integer> dq = new ArrayDeque<Integer>();

		// 1. indegree가 0인 노드를 큐에 넣기
		for (int i = 1; i < course.length; i++) {
			if (course[i].indegree == 0)
				dq.addLast(i);
		}

		int cur;
		for (int i = 1; i < course.length; i++) {
			cur = dq.pollFirst();
			for (int next : course[cur].children) {
				// 최대 과목 이수 횟수 찾기
				course[next].prevCnt = Math.max(course[next].prevCnt, course[cur].prevCnt + 1);
				// 2. cur에서 연결된 간선 삭제하기
				// 3. indegree가 0인 경우 큐에 넣기
				if (--course[next].indegree == 0)
					dq.addLast(next);
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 1; i < course.length; i++) {
			sb.append(course[i].prevCnt + " ");
		}
		System.out.println(sb);
	}

}

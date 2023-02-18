package dfs_bfs;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_케빈베이컨의6단계법칙_1389_s1 {
	static int n, m, sum;
	static List<Integer>[] people;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		people = new ArrayList[n + 1];
		int a, b;
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			if(people[a] == null)
				people[a] = new ArrayList<>();
			if(people[b] == null)
				people[b] = new ArrayList<>();
			people[a].add(b);
			people[b].add(a);
		}

		// 모든 사람에 대해 bfs 하여 최단 경로 합 구하기
		// target: 최단 경로 합이 가장 작은 사람
		int min = Integer.MAX_VALUE, target=-1, sum;
		for (int i = 1; i < people.length; i++) {
			sum = bfs(i);
			if(min > sum) {
				min = sum;
				target = i;
			}
		}
		
		System.out.println(target);
	}

	public static int bfs(int start) {
		Queue<Integer> queue = new LinkedList<Integer>();
		int[] visited = new int[n + 1];
		queue.add(start);
		visited[start] = 1;

		int sum = 0, cur;
		while (queue.size() > 0) {
			cur = queue.poll();
			for (int p : people[cur]) {
				if (visited[p] > 0 || p == start)
					continue;
				queue.add(p);
				visited[p] = visited[cur] + 1;
				sum += visited[p];
			}
		}

		return sum;
	}
}

package 그래프;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 강한 연결 요소(Strong Connected Component: SCC)
public class Main_즉흥여행_26146_g1_1540ms {
	static int no;
	static List<Integer>[] nodes;

	static int[] dfsn;
	static boolean[] finished;
	static int[] stack = new int[200000];
	static int top = -1;

	static int sccCnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		nodes = new ArrayList[n + 1];
		while (m-- > 0) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if (nodes[a] == null)
				nodes[a] = new ArrayList<Integer>();
			nodes[a].add(b);
		}

		dfsn = new int[n + 1];
		finished = new boolean[n + 1];
		dfs(1);
		System.out.println(sccCnt == n ? "Yes" : "No");
	}

	public static int dfs(int cur) {
		// 노드 방문 표시(방문 순서)
		dfsn[cur] = ++no;
		// 스택에 현재 노드 넣기
		stack[++top] = cur;

		// 현재 노드의 dfsn, 자식 노드의 dfsn 또는 res 중 가장 작은 값을 res에 저장
		int res = dfsn[cur];
		if (nodes[cur] != null) {
			for (int next : nodes[cur]) {
				// 아직 방문하지 않은 자식 노드
				if (dfsn[next] == 0)
					res = Math.min(res, dfs(next));
				// 방문은 했지만 아직 scc로 추출되지 않은 자식 노드
				else if (finished[next] == false)
					res = Math.min(res, dfsn[next]);
			}
		}

		// res가 현재 노드의 dfsn
		// = 자기, 자손 노드들이 도달할 수 있는 제일 높은 정점이 자신
		// = 더이상 조상 노드로 갈 수 없음
		// = scc
		if (res == dfsn[cur]) {
			// 스택에서 자신이 나올 때까지 pop
			sccCnt = 0;
			while (true) {
				int node = stack[top--];
				finished[node] = true;
				sccCnt++;
				if (node == cur)
					break;
			}
		}

		return res;
	}
}

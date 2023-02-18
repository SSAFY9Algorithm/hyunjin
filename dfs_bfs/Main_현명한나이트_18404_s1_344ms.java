package dfs_bfs;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main_현명한나이트_18404_s1_344ms {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		// 나이트 좌표
		int[] kCoord = new int[2];
		kCoord[0] = Integer.parseInt(st.nextToken());
		kCoord[1] = Integer.parseInt(st.nextToken());

		// 상대편 말의 좌표
		int[][] coords = new int[m][2];
		for (int i = 0; i < coords.length; i++) {
			st = new StringTokenizer(br.readLine());
			coords[i][0] = Integer.parseInt(st.nextToken());
			coords[i][1] = Integer.parseInt(st.nextToken());
		}

		// bfs 진행
		Deque<int[]> dq = new ArrayDeque<int[]>();
		int[][] visited = new int[n + 1][n + 1];
		dq.addLast(kCoord);
		visited[kCoord[0]][kCoord[1]] = 1;
		int[] coord;
		int[] dx = { -2, -2, -1, -1, 1, 1, 2, 2 };
		int[] dy = { -1, 1, -2, 2, -2, 2, -1, 1 };
		while (dq.size() > 0) {
			coord = dq.pollFirst();
			for (int i = 0; i < 8; i++) {
				int nx = coord[0] + dx[i];
				int ny = coord[1] + dy[i];
				if (nx <= n && nx > 0 && ny <= n && ny > 0 && visited[nx][ny] == 0) {
					visited[nx][ny] = visited[coord[0]][coord[1]] + 1;
					dq.addLast(new int[] { nx, ny });
				}

			}
		}

		StringBuilder sb = new StringBuilder();
		for (int[] c : coords) {
			sb.append(visited[c[0]][c[1]]-1 + " ");
		}
		System.out.println(sb);
	}
}

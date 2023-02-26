package dfs_bfs;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_빵집_3109_g2 {
	static int r, c, cnt = 0;
	static char[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		map = new char[r][c];
		for (int i = 0; i < map.length; i++) {
			map[i] = br.readLine().toCharArray();
		}

		for (int i = 0; i < r; i++) {
			// 시작 지점 체크하며 가능한 경로 모두 탐색
			if (dfs(i, 0))
				cnt++;
		}

		System.out.println(cnt);
	}

	public static boolean dfs(int x, int y) {
		// 불가능한 경로, false 반환
		if (x < 0 || x == r || map[x][y] == 'x')
			return false;
		// 목적지 도착, true 반환
		if (y == c - 1 && map[x][y] == '.') {
			map[x][y] = 'x';
			return true;
		}
		
		// 해당 지점 방문 표시
		map[x][y] = 'x';

		// 오른쪽 위 대각선, 오른쪽, 오른쪽 아래 대각선 방문
		// 만약 세 방향 다 목적지 도착 못하는 경우 false 반환
		int[] dx = { -1, 0, 1 };
		int[] dy = { 1, 1, 1 };
		boolean isPossible = false;
		for (int i = 0; i < 3; i++) {
			isPossible = isPossible || dfs(x + dx[i], y + dy[i]);
		}

//		이거 있으면 시간초과 남
//		이유) 
//		다른 지점에서 해당 지점으로 와도 해당 지점에서 목적지까지 가는 경로 막힘
//		= 해당 지점에서 목적지까지 가는 경로 이미 모두 방문
//		= 해당 시점 다시 볼 필요 없음
//		if (!isPossible)
//			map[x][y] = '.';

		return isPossible;
	}
}

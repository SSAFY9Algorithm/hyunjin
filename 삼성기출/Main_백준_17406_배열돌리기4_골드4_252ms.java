package 삼성기출;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_백준_17406_배열돌리기4_골드4_252ms {
	// input
	static int[][] map;
	// 회전연산 배열
	static int[][] rList;
	
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static int min = Integer.MAX_VALUE;
	static int[][] copyMap;
	static int[] idx;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		map = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		rList = new int[k][3];
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			rList[i][0] = Integer.parseInt(st.nextToken()) - 1;
			rList[i][1] = Integer.parseInt(st.nextToken()) - 1;
			rList[i][2] = Integer.parseInt(st.nextToken());
		}

		copyMap = new int[n][m];
		idx = new int[k];
		getPermutations(k, 0, 0);

		System.out.println(min);

	}

	public static void getPermutations(int k, int depth, int visited) {
		if (depth == k) {
			// map deepcopy
			for (int i = 0; i < map.length; i++) {
				System.arraycopy(map[i], 0, copyMap[i], 0, map[i].length);
			}

			// 가능한 회전연산 시퀸스에 대해 최소가 되는 행 합 계산
			int x1, x2, y1, y2;
			for (int i = 0; i < k; i++) {
				if ((visited & 1 << i) == 0)
					continue;
				x1 = rList[idx[i]][0] - rList[idx[i]][2];
				y1 = rList[idx[i]][1] - rList[idx[i]][2];
				x2 = rList[idx[i]][0] + rList[idx[i]][2];
				y2 = rList[idx[i]][1] + rList[idx[i]][2];
				rotate(x1, y1, x2, y2);
			}
			findMin();

			return;
		}

		for (int i = 0; i < k; i++) {
			if ((visited & 1 << i) > 0)
				continue;
			visited = visited | 1 << i;
			idx[depth] = i;
			getPermutations(k, depth + 1, visited);
			visited = visited & ~(1 << i);
		}
	}

	// 회전 연산
	public static void rotate(int x1, int y1, int x2, int y2) {
		if (x1 == x2)
			return;

		int len = x2 - x1;
		int temp = copyMap[x1][y1];
		
		int cx = x1, cy = y1, nx = x1, ny = y1;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < len; j++) {
				nx += dx[i];
				ny += dy[i];
				copyMap[cx][cy] = copyMap[nx][ny];
				cx = nx;
				cy = ny;
			}
		}
		copyMap[x1][y1 + 1] = temp;

		rotate(x1 + 1, y1 + 1, x2 - 1, y2 - 1);
	}

	// 행 합 중 최소값 찾기
	public static void findMin() {
		int sum;
		for (int[] row : copyMap) {
//			System.out.println(Arrays.toString(row));
			sum = 0;
			for (int i = 0; i < row.length; i++) {
				sum += row[i];
			}
			min = Math.min(min, sum);
		}
	}
}

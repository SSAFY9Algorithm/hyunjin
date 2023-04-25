package _3월_4주차;

import java.io.*;
import java.util.*;

public class Main_Baekjoon_21609_SharkMiddleSchool_G2_160ms {
	static int n, m;
	static int[][] map;
	static int[][] checked;
	static int score = 0;
	static int[] dx = { 1, 0, -1, 0 }, dy = { 0, 1, 0, -1 };

	static class Block implements Comparable<Block> {
		int idx, r, c, cnt, rCnt;

		public Block(int idx, int r, int c, int cnt, int rCnt) {
			super();
			this.idx = idx;
			this.r = r;
			this.c = c;
			this.cnt = cnt;
			this.rCnt = rCnt;
		}

		@Override
		public int compareTo(Block o) {
			if (this.cnt == o.cnt) {
				if (this.rCnt == o.rCnt) {
					if (this.r == o.r) {
						return o.c - this.c;
					}
					return o.r - this.r;
				}
				return o.rCnt - this.rCnt;
			}
			return o.cnt - this.cnt;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][n];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		while (true) {
			Block block = findBlock();
			if (block == null)
				break;
			score += block.cnt * block.cnt;
			deleteBlock(block);
			appliedGravity();
			rotatateCounterClockwise();
			appliedGravity();
		}

		System.out.println(score);
	}

	private static Block findBlock() {
		// TODO 1. 가장 크기가 큰 블록 찾기
		// TODO 2. 무지개 블록 수 가장 많은 것 찾기
		// TODO 3. 기준 블록 행이 가장 큰 것
		// TODO 4. 기준 블록 열이 가장 큰 것
		// 기준블록 : 무지개 블록x, 행/열 가장 작은 것
		PriorityQueue<Block> pq = new PriorityQueue<Block>();
		checked = new int[n][n];
		int idx = 1;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (checked[i][j] != 0 || map[i][j] == -2 || map[i][j] == -1 || map[i][j] == 0)
					continue;
				idx = doBFS(i, j, checked, pq, idx);
			}
		}
		if (idx != 1) {
			return pq.poll();
		}
		return null;
	}

	private static int doBFS(int r, int c, int[][] checked, PriorityQueue<Block> pq, int idx) {
		// TODO bfs로 블록 찾기
		int cnt = 1, rCnt = 0, target = map[r][c];
		Queue<int[]> queue = new ArrayDeque<int[]>();
		queue.offer(new int[] { r, c });
		checked[r][c] = idx;
		int[] cur;
		while (!queue.isEmpty()) {
			cur = queue.poll();
			for (int i = 0; i < 4; i++) {
				int nx = cur[0] + dx[i];
				int ny = cur[1] + dy[i];
				if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
					// 무지개 블록은 중복으로 사용 가능!
					if (map[nx][ny] == 0 && checked[nx][ny] != idx) {
						checked[nx][ny] = idx;
						cnt++;
						rCnt++;
						queue.offer(new int[] { nx, ny });
					} else if (checked[nx][ny] == 0 && map[nx][ny] == target) {
						checked[nx][ny] = idx;
						cnt++;
						queue.offer(new int[] { nx, ny });
					}
				}
			}
		}
		if (cnt >= 2) {
			pq.offer(new Block(idx, r, c, cnt, rCnt));
			return ++idx;
		}
		return idx;
	}

	private static void deleteBlock(Block block) {
		// TODO 블록 삭제하기 (bfs탐색하기)
		int target = map[block.r][block.c];
		Queue<int[]> queue = new ArrayDeque<int[]>();
		queue.offer(new int[] { block.r, block.c });
		int[] cur;
		while (!queue.isEmpty()) {
			cur = queue.poll();
			for (int i = 0; i < 4; i++) {
				int nx = cur[0] + dx[i];
				int ny = cur[1] + dy[i];
				if (nx >= 0 && nx < n && ny >= 0 && ny < n && (map[nx][ny] == target || map[nx][ny] == 0)) {
					map[nx][ny] = -2;
					queue.offer(new int[] { nx, ny });
				}
			}
		}
	}

	private static void appliedGravity() {
		// TODO 중력 작용하기
		for (int col = 0; col < n; col++) {
			int bottom = n - 1, top = n - 2;

			while (top >= 0) {
				if (map[bottom][col] != -2 || map[bottom][col] == -1) {
					bottom--;
					if (top == bottom)
						top--;
				} else if (map[top][col] == -2) {
					top--;
				} else if (map[top][col] == -1) {
					bottom = top - 1;
					top -= 2;
				} else {
					int temp = map[bottom][col];
					map[bottom][col] = map[top][col];
					map[top][col] = temp;
					bottom--;
					top--;
				}
			}

		}
	}

	private static void rotatateCounterClockwise() {
		// TODO 반시계 방향으로 90도 회전하기
		int[][] newMap = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				newMap[i][j] = map[j][n - i - 1];
			}
		}
		map = newMap;
	}

}

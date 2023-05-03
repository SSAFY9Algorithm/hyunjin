package _3월_4주차;

import java.io.*;
import java.util.*;

public class Main_Baekjoon_21611_WizardSharkAndBlizzard_G1_176ms {
	static int n, m, mid, end;
	static int[][] map;
	static int[][] seq;
	static int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	static int[] cnt = new int[4];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		mid = n / 2;
		map = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		getSequence();

		// d_i: 1,2,3,4(위,아래,왼,오)
		// s_i: 거리
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int di = Integer.parseInt(st.nextToken());
			int si = Integer.parseInt(st.nextToken());
			doBlizzard(di, si);
			fillMap();
			while (explode()) {
				fillMap();
			}
			changeMarble();
		}

		System.out.println(cnt[1] + 2 * cnt[2] + 3 * cnt[3]);
	}

	private static void getSequence() {
		// TODO 회전 순서 만들기
		seq = new int[n * n - 1][2];
		int maxCnt = n - 1;
		int cnt = 1;
		int[][] dir = { { 0, -1 }, { 1, 0 }, { 0, 1 }, { -1, 0 } };
		int d = 0, x = mid, y = mid, idx = 0;
		while (cnt < maxCnt) {
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < cnt; j++) {
					x += dir[d][0];
					y += dir[d][1];
					seq[idx][0] = x;
					seq[idx][1] = y;
					idx++;
				}
				d = (d + 1) % 4;
			}
			cnt++;
		}
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < cnt; j++) {
				x += dir[d][0];
				y += dir[d][1];
				seq[idx][0] = x;
				seq[idx][1] = y;
				idx++;
			}
			d = (d + 1) % 4;
		}
	}

	private static void doBlizzard(int di, int si) {
		// TODO 블리자드 마법 시전
		di = di - 1;
		int x = mid, y = mid;
		for (int i = 0; i < si; i++) {
			x += dir[di][0];
			y += dir[di][1];
			map[x][y] = 0;
		}
	}

	private static void fillMap() {
		// TODO 빈칸 구슬 채우기
		int left = 0, right = 1;
		while (right < seq.length) {
			int x1 = seq[left][0];
			int y1 = seq[left][1];
			int x2 = seq[right][0];
			int y2 = seq[right][1];
			if (map[x1][y1] != 0) {
				left++;
				if (left == right)
					right++;
			} else if (map[x2][y2] == 0) {
				right++;
			} else {
				swap(x1, y1, x2, y2);
			}
		}
		end = left - 1;
	}

	private static boolean explode() {
		// TODO 구슬 폭발
		boolean isExist = false;
		int cur = 0;
		while (cur <= end) {
			int i = 1, target = map[seq[cur][0]][seq[cur][1]];
			while (cur + i <= end) {
				if (cur + i > end || map[seq[cur + i][0]][seq[cur + i][1]] != target)
					break;
				i++;
			}
			if (i >= 4) {
				isExist = true;
				int idx = map[seq[cur][0]][seq[cur][1]];
				cnt[idx] += i;
				for (int j = 0; j < i; j++) {
					map[seq[cur + j][0]][seq[cur + j][1]] = 0;
				}
			}
			cur += i;
		}
		return isExist;
	}

	private static void swap(int x, int y, int nx, int ny) {
		// TODO 위치 교환
		int temp = map[x][y];
		map[x][y] = map[nx][ny];
		map[nx][ny] = temp;
	}

	private static void changeMarble() {
		// TODO 구슬 변화시키기
		int[][] newMap = new int[n][n];
		int idx = 0, nIdx = 0;
		while (idx <= end && nIdx < seq.length) {
			int no = map[seq[idx][0]][seq[idx][1]];
			int i = 0;
			while (idx + i <= end) {
				if (idx + i > end || map[seq[idx + i][0]][seq[idx + i][1]] != no)
					break;
				i++;
			}
			newMap[seq[nIdx][0]][seq[nIdx][1]] = i;
			nIdx++;
			newMap[seq[nIdx][0]][seq[nIdx][1]] = no;
			nIdx++;
			idx += i;
		}
		map = newMap;
	}
}

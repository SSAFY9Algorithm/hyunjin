package _4월_1주차;

import java.io.*;
import java.util.*;

public class Main_Baekjoon_20056_WizardSharkAndFireBall_G4_564ms {
	static int n, m, k;
	static List<FireBall>[][] map;
	static int[][] dir = { { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 }, { 0, -1 }, { -1, -1 } };

	static class FireBall {
		int r, c, m, s, d;

		public FireBall(int r, int c, int m, int s, int d) {
			this.r = r;
			this.c = c;
			this.m = m;
			this.s = s;
			this.d = d;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		map = new ArrayList[n][n];
		initMap(map);
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			FireBall fireBall = new FireBall(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1,
					Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()));
			map[fireBall.r][fireBall.c].add(fireBall);
		}

		System.out.println(solution());
	}

	private static int solution() {
		// TODO 문제풀이 함수
		while (k-- > 0) {
			moveFireBall();
			checkFireBall();
		}
		return calTotalMass();
	}

	private static int calTotalMass() {
		// TODO 남아있는 파이어볼 질량 합 계산
		int totalM = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < map[i][j].size(); k++) {
					totalM += map[i][j].get(k).m;
				}
			}
		}
		return totalM;
	}

	private static void initMap(List<FireBall>[][] map) {
		// TODO map 초기화 하기
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				map[i][j] = new ArrayList<FireBall>();
			}
		}
	}

	private static void moveFireBall() {
		// TODO 파이어볼 았는 칸 체크
		List<FireBall>[][] newMap = new ArrayList[n][n];
		initMap(newMap);
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (map[i][j].size() > 0)
					move(newMap, i, j);
			}
		}
		map = newMap;
	}

	private static void move(List<FireBall>[][] newMap, int r, int c) {
		// TODO 파이어볼 이동하기
		for (int i = 0; i < map[r][c].size(); i++) {
			FireBall fireBall = map[r][c].get(i);
			fireBall.r += fireBall.s * dir[fireBall.d][0];
			fireBall.c += fireBall.s * dir[fireBall.d][1];
			if (fireBall.r >= n)
				fireBall.r %= n;
			else if (fireBall.r < 0) {
				while (fireBall.r < 0)
					fireBall.r += n;
				fireBall.r %= n;
			}
			if (fireBall.c >= n)
				fireBall.c %= n;
			else if (fireBall.c < 0) {
				while (fireBall.c < 0)
					fireBall.c += n;
				fireBall.c %= n;
			}
			newMap[fireBall.r][fireBall.c].add(fireBall);
		}
	}

	private static void checkFireBall() {
		// TODO 2개 이상 파이어볼 있는 칸 체크
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (map[i][j].size() > 1) {
					combineAndDivide(i, j);
				}
			}
		}
	}

	private static void combineAndDivide(int r, int c) {
		// TODO 파이어볼 합치고 나누기
//		1. 파이어볼은 4개의 파이어볼로 나누어진다.
//		2. 나누어진 파이어볼의 질량, 속력, 방향은 다음과 같다.
//			질량은 ⌊(합쳐진 파이어볼 질량의 합)/5⌋이다.
//			속력은 ⌊(합쳐진 파이어볼 속력의 합)/(합쳐진 파이어볼의 개수)⌋이다.
//			합쳐지는 파이어볼의 방향이 모두 홀수이거나 모두 짝수이면, 방향은 0, 2, 4, 6이 되고, 그렇지 않으면 1, 3, 5, 7이 된다.
//			질량이 0인 파이어볼은 소멸되어 없어진다.
		List<FireBall> list = map[r][c];
		int totalM = 0, totalS = 0, totalD = 0;
		int dirStart = 0;
		for (int i = 0; i < list.size(); i++) {
			totalM += list.get(i).m;
			totalS += list.get(i).s;
			totalD += list.get(i).d;
			if (list.get(i).d % 2 == 0)
				dirStart++;
		}

		int m = totalM / 5;
		int s = totalS / list.size();
		if (dirStart == list.size() || dirStart == 0)
			dirStart = 0;
		else
			dirStart = 1;
		List<FireBall> newList = new ArrayList<FireBall>();
		if (m == 0) {
			map[r][c] = newList;
			return;
		}
		for (int i = 0; i < 4; i++) {
			FireBall fireBall = new FireBall(r, c, m, s, dirStart + 2 * i);
			newList.add(fireBall);
		}
		map[r][c] = newList;
	}
}

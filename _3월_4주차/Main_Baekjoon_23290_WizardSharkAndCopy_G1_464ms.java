package _3월_4주차;

import java.io.*;
import java.util.*;

public class Main_Baekjoon_23290_WizardSharkAndCopy_G1_464ms {
	static class Fish {
		int x, y, d;

		public Fish(int x, int y, int d) {
			this.x = x;
			this.y = y;
			this.d = d;
		}
	}

	static List<int[]> initFish = new ArrayList<int[]>();
	static List<Fish>[][] fishMap = new ArrayList[5][5];
	static int sharkX, sharkY;
	static int smellCnt = 1;
	static int[][] smellMap = new int[5][5];
	static int[][] dir = { { 0, -1 }, { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 } };
	static int[][] path = new int[64][];

	private static void getPermutation() {
		// 상어 경로 생성
		int idx = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				for (int k = 0; k < 4; k++) {
					path[idx++] = new int[] { i, j, k };
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int m = Integer.parseInt(st.nextToken());
		int s = Integer.parseInt(st.nextToken());
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			initFish.add(new int[] { Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()) - 1 });
		}

		st = new StringTokenizer(br.readLine());
		sharkX = Integer.parseInt(st.nextToken());
		sharkY = Integer.parseInt(st.nextToken());

		getPermutation();
		solution(m, s);

		int total = 0;
		for (int i = 1; i < 5; i++) {
			for (int j = 1; j < 5; j++) {
				total += fishMap[i][j].size();
			}
		}

		System.out.println(total);
	}

	private static void solution(int m, int s) {
		// TODO 문제 풀이 함수
		initFishMap(fishMap);
		copyFish();
		while (smellCnt <= s) {
			moveFish();
			moveShark();
			vanishSmell();
			copyFish();
			setInitFish();
			smellCnt++;
		}
	}

	private static void initFishMap(List<Fish>[][] fishMap) {
		// TODO fishMap 초기화
		for (int i = 1; i < 5; i++) {
			for (int j = 1; j < 5; j++) {
				fishMap[i][j] = new ArrayList<Fish>();
			}
		}
	}

	private static void copyFish() {
		// TODO 물고기 복제
		for (int i = 0; i < initFish.size(); i++) {
			int[] f = initFish.get(i);
			fishMap[f[0]][f[1]].add(new Fish(f[0], f[1], f[2]));
		}
	}
	
	private static void setInitFish() {
		// TODO initFish 초기화
		// 현재 맵의 모든 물고기 저장하기
		initFish = new ArrayList<int[]>();
		for (int i = 1; i < 5; i++) {
			for (int j = 1; j < 5; j++) {
				for (int k = 0; k < fishMap[i][j].size(); k++) {
					Fish fish = fishMap[i][j].get(k);
					initFish.add(new int[] { fish.x, fish.y, fish.d });
				}
			}
		}
	}

	private static void moveFish() {
		// TODO 물고기 이동
		// 상어 있는 칸, 물고기 냄새 있는 칸 이동 불가능
		List<Fish>[][] newFishMap = new ArrayList[5][5];
		initFishMap(newFishMap);
		for (int i = 1; i < 5; i++) {
			for (int j = 1; j < 5; j++) {
				for (int x = 0; x < fishMap[i][j].size(); x++) {
					Fish fish = fishMap[i][j].get(x);
					int k = 0;
					// 가능한 방향 찾을 때까지 45도 반시계 회전
					for (; k < 8; k++) {
						int nx = fish.x + dir[fish.d][0];
						int ny = fish.y + dir[fish.d][1];
						if (nx <= 4 && nx >= 1 && ny <= 4 && ny >= 1) {
							if ((sharkX != nx || sharkY != ny) && smellMap[nx][ny] == 0) {
								fish.x = nx;
								fish.y = ny;
								newFishMap[nx][ny].add(fish);
								break;
							}
						}
						fish.d = (fish.d + 7) % 8;
					}
					// 이동할 수 없는 물고기인 경우
					if (k == 8) {
						newFishMap[fish.x][fish.y].add(fish);
					}
				}
			}
		}
		fishMap = newFishMap;
	}

	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, -1, 0, 1 };

	private static void moveShark() {
		// TODO 상어 이동
		// 물고기 가장 많은 경로 찾기
		// 상어가 물고기 있는 곳 가면 물고기 사라짐
		// 사라진 위치에는 물고기 냄새 남음
		int[] maxPath = new int[3];
		findMaxPath(maxPath);
		for (int i = 0; i < 3; i++) {
			sharkX += dx[maxPath[i]];
			sharkY += dy[maxPath[i]];
			if (fishMap[sharkX][sharkY].size() > 0) {
				smellMap[sharkX][sharkY] = smellCnt;
				fishMap[sharkX][sharkY] = new ArrayList<>();
			}
		}
	}

	private static void findMaxPath(int[] maxPath) {
		int[][] sizeMap = new int[5][5];
		int maxCnt = -1;
		for (int i = 0; i < 64; i++) {
			int nx = sharkX, ny = sharkY;
			getSizeMap(sizeMap);
			int j = 0, cnt = 0;
			for (; j < 3; j++) {
				nx = nx + dx[path[i][j]];
				ny = ny + dy[path[i][j]];
				if (nx < 1 || nx > 4 || ny < 1 || ny > 4)
					break;
				cnt += sizeMap[nx][ny];
				sizeMap[nx][ny] = 0;
			}
			if (j == 3 && maxCnt < cnt) {
				maxCnt = cnt;
				System.arraycopy(path[i], 0, maxPath, 0, 3);
			}
		}
	}

	private static void getSizeMap(int[][] sizeMap) {
		// TODO 이차원배열 복사
		for (int i = 1; i < 5; i++) {
			for (int j = 1; j < 5; j++) {
				sizeMap[i][j] = fishMap[i][j].size();
			}
		}
	}

	private static void vanishSmell() {
		// TODO 물고기 냄새 삭제
		// 2번 연습 전에 생긴 물고기 냄새가 격자에서 사라짐
		for (int i = 1; i < 5; i++) {
			for (int j = 1; j < 5; j++) {
				if (smellMap[i][j] + 2 == smellCnt)
					smellMap[i][j] = 0;
			}
		}
	}
}

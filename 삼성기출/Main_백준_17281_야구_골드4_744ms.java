package 삼성기출;

import java.io.*;
import java.util.*;

// 8! = 40320
public class Main_백준_17281_야구_골드4_744ms {
	static int n;
	static int[][] info;

	static int[] seq = new int[9];

	static int totalScore;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		info = new int[n][9];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++) {
				info[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		seq[3] = 0;
		getPermutation(0, 1);
		System.out.println(totalScore);
	}

	public static void getPermutation(int depth, int visited) {
		if (depth == 9) {
			totalScore = Math.max(totalScore, playGame());
			return;
		}

		if (depth == 3) {
			getPermutation(depth + 1, visited);
		} else {
			for (int i = 0; i < 9; i++) {
				if ((visited & 1 << i) != 0)
					continue;
				seq[depth] = i;
				getPermutation(depth + 1, visited | 1 << i);
			}
		}
	}

	private static int playGame() {
		// TODO 야구 게임 진행
		// 타자 리스트
		Queue<Integer> hitterList = new ArrayDeque<Integer>();
		for (int j = 0; j < 9; j++) {
			hitterList.offer(seq[j]);
		}
		// 주자 리스트
		int[] baseList;
		// 점수 합계
		int score = 0;

		for (int i = 0; i < n; i++) {
			
			// 주자 초기화
			baseList = new int[] { -1, -1, -1 };
			// 아웃 초기화
			int outCnt = 0;
			// 타자
			int hitter;

			// 이닝 
			while (true) {
				hitter = hitterList.poll();
				hitterList.offer(hitter);

				// 아웃일 때
				if (info[i][hitter] == 0) {
					outCnt++;
					if (outCnt == 3)
						break;
					else
						continue;
				}

				// 아웃 아닐 때
				for (int j = 2; j >= 0; j--) {
					if (baseList[j] != -1) {
						// 홈 도착
						if (j + info[i][hitter] > 2)
							score++;
						// 주자 진루
						else
							baseList[j + info[i][hitter]] = baseList[j];
						baseList[j] = -1;
					}
				}
				// 홈런일 경우
				if (info[i][hitter] == 4)
					score++;
				// 홈런 아닐 경우
				else
					baseList[info[i][hitter] - 1] = hitter;

			} // end of while(이닝)

		} // end of for

		return score;
	}
}

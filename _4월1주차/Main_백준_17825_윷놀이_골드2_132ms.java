package _4월1주차;

import java.io.*;
import java.util.*;

public class Main_백준_17825_윷놀이_골드2_132ms {
	static class Node {
		Node red, blue;
		int idx, score;
		boolean isHorseExist = false;

		public Node(int idx, int score) {
			this.idx = idx;
			this.score = score;
		}
	}

	static Node[] map = new Node[33]; // idx:0 -> 시작
	static int[] dice = new int[10];
	static int[] horse = new int[4]; // 말들의 위치
	static int maxScore;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 10; i++) {
			dice[i] = st.nextToken().charAt(0) - '0';
		}

		solution();
		System.out.println(maxScore);
	}

	private static void solution() {
		// TODO 문제풀이
		makeMap();
		doGame(0, 0);
	}

	private static void makeMap() {
		// TODO 게임판 만들기
		// 1. red
		map[0] = new Node(0, 0); // idx:0 값0 시작노드
		int i = 1;
		for (; i <= 20; i++) {
			map[i] = new Node(i, i * 2);
			map[i - 1].red = map[i];
		}
		i--;
		map[i + 1] = new Node(i + 1, map[i / 2].score + 2);
		map[i].red = map[i + 1];
		i++; // idx:22, 값22
		map[i + 1] = new Node(i + 1, map[i].score + 2);
		map[i].red = map[i + 1];
		i++; // idx:23, 값24
		map[i + 1] = new Node(i + 1, map[i].score + 1);
		map[i].red = map[i + 1];
		i++; // idx:24, 값25
		map[i + 1] = new Node(i + 1, map[i].score + 5);
		map[i].red = map[i + 1];
		i++; // idx:25, 값30
		map[i + 1] = new Node(i + 1, map[i].score + 5);
		map[i].red = map[i + 1];
		i++; // idx:26, 값35
		map[i].red = map[20];
		i++;
		map[i] = new Node(i, 13); // idx:26 값13
		map[i + 1] = new Node(i + 1, map[i].score + 3);
		map[i].red = map[i + 1];
		i++; // idx:27, 값16
		map[i + 1] = new Node(i + 1, map[i].score + 3);
		map[i].red = map[i + 1];
		i++; // idx:28, 값19
		map[i].red = map[23];
		i++;
		map[i] = new Node(i, 28); // idx:29 값28
		map[i + 1] = new Node(i + 1, map[i].score - 1);
		map[i].red = map[i + 1];
		i++; // idx:30, 값27
		map[i + 1] = new Node(i + 1, map[i].score - 1);
		map[i].red = map[i + 1];
		i++; // idx:31, 값26
		map[i].red = map[23];
		i++;
		map[i] = new Node(i, 0); // idx:32 값0 도착노드
		map[20].red = map[32];
		// 2. blue
		map[5].blue = map[26];
		map[10].blue = map[21];
		map[15].blue = map[29];
	}

	private static void doGame(int depth, int score) {
		// TODO 윷놀이 진행
		if (depth == 10) {
			maxScore = Math.max(maxScore, score);
			return;
		}
		for (int i = 0; i < 4; i++) {
			// 도착 칸에 도착한 말일 경우
			if (horse[i] == 32)
				continue;

			Node cur = moveHorse(map[horse[i]], depth);

			// 이미 말이 있는 칸일 경우
			if (cur.idx < 32 && map[cur.idx].isHorseExist) {
				continue;
			}

			int tempPos = horse[i];
			horse[i] = cur.idx;
			map[tempPos].isHorseExist = false;
			// 도착 칸인 경우
			if (horse[i] == 32) {
				doGame(depth + 1, score);
			}
			// 도착하지 못한 경우
			else {
				map[horse[i]].isHorseExist = true;
				doGame(depth + 1, score + map[horse[i]].score);
				map[horse[i]].isHorseExist = false;
			}
			horse[i] = tempPos;
			map[tempPos].isHorseExist = true;
		}
	}

	private static Node moveHorse(Node cur, int depth) {
		// TODO 말 이동시키기
		cur = cur.blue != null ? cur.blue : cur.red;
		for (int i = 1; i < dice[depth]; i++) {
			if (cur.red == null) 
				break;
			cur = cur.red;
		}
		return cur;
	}
}

package dfs_bfs;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_연산자끼워넣기_14888_s1_148ms {
	static int[] opCnt = new int[4];
	static int[] numList;
	static int n, max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());

		numList = new int[n];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < n; i++) {
			numList[i] = Integer.parseInt(st.nextToken());
		}
		opCnt = new int[4];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < 4; i++) {
			opCnt[i] = Integer.parseInt(st.nextToken());
		}

		findMax(0, numList[0]);
		findMin(0, numList[0]);
		System.out.println(max);
		System.out.println(min);
	}

	// 최소값 구할 때
	public static void findMin(int depth, int res) {
		if (depth == n - 1) {
			min = Math.min(min, res);
			return;
		}

		for (int i = 0; i < 4; i++) {
			if (opCnt[i] == 0)
				continue;
			opCnt[i]--;
			findMin(depth + 1, calc(res, i, numList[depth + 1]));
			opCnt[i]++;
		}
	}

	// 최대값 구할 때
	public static void findMax(int depth, int res) {
		if (depth == n - 1) {
			max = Math.max(max, res);
			return;
		}

		for (int i = 0; i < 4; i++) {
			if (opCnt[i] == 0)
				continue;
			opCnt[i]--;
			findMax(depth + 1, calc(res, i, numList[depth + 1]));
			opCnt[i]++;
		}
	}

	public static int calc(int a, int op, int b) {
		switch (op) {
		case 0:
			a += b;
			break;
		case 1:
			a -= b;
			break;
		case 2:
			a *= b;
			break;
		default:
			a /= b;
		}
		return a;
	}
}

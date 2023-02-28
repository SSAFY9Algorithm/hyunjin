package dfs_bfs;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_연산자끼워넣기_14888_s1_136ms {
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

		find(0, numList[0]);
		System.out.println(max);
		System.out.println(min);
	}

	public static void find(int depth, int res) {
		if (depth == n - 1) {
			min = Math.min(min, res);
			max = Math.max(max, res);
			return;
		}

		for (int i = 0; i < 4; i++) {
			if (opCnt[i] == 0)
				continue;
			opCnt[i]--;
			find(depth + 1, calc(res, i, numList[depth + 1]));
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

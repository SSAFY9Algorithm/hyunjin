package class5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// union-find

public class Main_사이클게임_20040_g4_536ms {
	static int[] parents;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		parents = new int[n];
		Arrays.fill(parents, -1);
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			if (parents[a] == -1)
				parents[a] = a;
			if (parents[b] == -1)
				parents[b] = b;

			if (union(a, b)) {
				System.out.println(i + 1);
				return;
			}
		}

		System.out.println(0);
	}

	public static int find(int a) {
		if (parents[a] != a)
			parents[a] = find(parents[a]);
		return parents[a];
	}

	public static boolean union(int a, int b) {
		int pA = find(a);
		int pB = find(b);
		if (pA == pB)
			return true;
		else if (pA < pB)
			parents[pB] = pA;
		else
			parents[pA] = pB;
		return false;
	}

}

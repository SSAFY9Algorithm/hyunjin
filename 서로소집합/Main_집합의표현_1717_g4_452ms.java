package 서로소집합;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_집합의표현_1717_g4_452ms {
	static int[] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		parent = new int[n + 1];
		Arrays.fill(parent, -1);
		int op, a, b;
		while (m-- > 0) {
			st = new StringTokenizer(br.readLine(), " ");
			op = Integer.parseInt(st.nextToken());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			if (parent[a] == -1)
				parent[a] = a;
			if (parent[b] == -1)
				parent[b] = b;

			// union
			if (op == 0) {
				union(a, b);
			}
			// find
			else {
				sb.append(find(a) == find(b) ? "YES\n" : "NO\n");
			}
		}

		System.out.println(sb);

	}

	public static int find(int a) {
		if (parent[a] != a)
			parent[a] = find(parent[a]);
		return parent[a];
	}

	public static void union(int a, int b) {
		a = find(a);
		b = find(b);
		if (a < b)
			parent[b] = a;
		else
			parent[a] = b;
	}

}

package 백트래킹;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 비트마스킹, 백트래킹

public class Main_가르침_1062_g4_420ms_v2 {
	static int n, k, maxCnt;
	static String[] words;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		// a,c,i,n,t 배울 수 없을 때
		if (k < 5) {
			System.out.println(0);
			return;
		}

		words = new String[n];
		for (int i = 0; i < n; i++) {
			words[i] = br.readLine();
		}

		visited = new boolean[n];
		visited['a'-'a']=true;
		visited['c'-'a']=true;
		visited['i'-'a']=true;
		visited['n'-'a']=true;
		visited['t'-'a']=true;
		getCombinations(0, 0);
		System.out.println(maxCnt);
	}

	public static void getCombinations(int depth, int start) {
		if (depth == k - 5) {
			int cnt = 0;
			for (int i = 0; i < n; i++) {
				int j;
				for (j = 0; j < words[i].length(); j++) {
					if (!visited[words[i].charAt(j) - 'a'])
						break;
				}
				if (j == words[i].length())
					cnt++;
			}

			maxCnt = Math.max(maxCnt, cnt);
			return;
		}

		for (int i = start; i < 26; i++) {
			if(visited[i]) continue;
			visited[i] = true;
			getCombinations(depth + 1, start + 1);
			visited[i] = false;
		}
	}
}

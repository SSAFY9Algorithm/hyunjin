package 백트래킹;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 비트마스킹, 백트래킹
// 비트마스킹 한게 더 빨라야할 것 같은데 시간초과 남
// 이유를 모르게따
public class Main_가르침_1062_g4_ms {
	static int n, k, maxCnt;
	static int[][] usedAlphaList;

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

		usedAlphaList = new int[n][2];
		for (int i = 0; i < n; i++) {
			String str = br.readLine();
			usedAlphaList[i][0] = 532741;
			getUsedAlphaList(str.substring(4, str.length() - 4), i);
		}

		getCombinations(0, 0, k -5, 532741);
		System.out.println(maxCnt);
	}

	public static void getUsedAlphaList(String str, int idx) {
		// 해당 단어를 읽기 위해 배워야 하는 문자 개수
		for (int i = 0; i < str.length(); i++) {
			if ((usedAlphaList[idx][0] & 1 << str.charAt(i) - 'a') == 0) {
				// 단어에서 사용된 문자 리스트 찾기(중복x)
				usedAlphaList[idx][0] = usedAlphaList[idx][0] | 1 << str.charAt(i) - 'a';
				// 단어에서 사용된 문자 개
				usedAlphaList[idx][1]++;
				// 배워야 하는 문자 개수가 배울 수 있는 문자 개수보다 많다면
				// 해당 단어 읽을 필요 x
				if (usedAlphaList[idx][1] > k - 5) {
					usedAlphaList[idx][0] = 0;
					return;
				}
			}

		}
	}

	public static void getCombinations(int depth, int start, int last, int flag) {
		if (depth == last) {
			int cnt = 0;
			for (int i = 0; i < n; i++) {
				// 읽을 수 없는 단어
				if (usedAlphaList[i][0] == 0)
					continue;
				// 읽을 수 있는 단어인지 판단
				if (flag == (flag | usedAlphaList[i][0]))
					cnt++;
			}

			
//			System.out.println(last);
//			System.out.println(cnt);
			maxCnt = Math.max(maxCnt, cnt);
			return;
		}

		for (int i = start; i < 26; i++) {
			if ((flag & 1 << i) == 0)
				getCombinations(depth + 1, start + 1, last, flag | 1 << i);
		}
	}
}

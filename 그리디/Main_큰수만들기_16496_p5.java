package 그리디;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_큰수만들기_16496_p5 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		String[] input;
		StringBuilder res = new StringBuilder();

		input = new String[n];
		boolean isZero = true;
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			input[i] = st.nextToken();
			if (isZero && !input[i].equals("0"))
				isZero = false;
		}

		// 답이 0일 때
		if (isZero) {
			System.out.println(0);
			return;
		}

		String max;
		int idx, cnt = n;
		boolean[] visited = new boolean[n];
		while (cnt-- > 0) {
			idx = 0;
			max = "";
			for (int i = 0; i < n; i++) {
				// 이미 사용한 문자열인 경우
				if (visited[i])
					continue;
				// max 초기화
				if (max.length() == 0) {
					max = input[i];
					idx = i;
				}

				// 더 큰 수 찾기
				// 버블정렬
				String temp1 = max.concat(input[i]), temp2 = input[i].concat(max);
				for (int j = 0; j < max.length() + input[i].length(); j++) {
					if (temp1.charAt(j) == temp2.charAt(j))
						continue;
					if (temp1.charAt(j) < temp2.charAt(j)) {
						max = input[i];
						idx = i;
					}
					break;
				}
			}

			visited[idx] = true;
			res.append(max);

		} // end of while

		System.out.println(res);

	} // end of main
} // end of class

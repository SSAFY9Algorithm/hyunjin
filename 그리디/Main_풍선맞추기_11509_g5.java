package 그리디;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 이게 시간초과가 안나네...
public class Main_풍선맞추기_11509_g5 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] h = new int[n];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < h.length; i++) {
			h[i] = Integer.parseInt(st.nextToken());
		}

		int startH = 0, cnt = 0;
		for (int i = 0; i < h.length; i++) {
			// 이미 터진 풍선을 만난 경우
			if (h[i] == 0)
				continue;
			
			// 남은 풍선 중 제일 높은 풍선 만난 경우
			if (h[i] >= startH) {
				cnt++;
				startH = h[i];
				h[i] = 0;
			}
			
			// 뒤의 풍선들 중 가능한 풍선들 터트리기
			int idx = 1;
			while (i + idx < n && startH > 0) {
				if (h[i + idx] == startH - 1) {
					h[i + idx] = 0;
					startH--;
				}
				idx++;
			}
			startH = 0;
		}

		System.out.println(cnt);
	}
}

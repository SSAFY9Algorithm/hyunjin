package 스택;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

class Main_옥상정원꾸미기_6198_g5 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		long[] bHeight = new long[n];
		for (int i = 0; i < bHeight.length; i++) {
			bHeight[i] = Long.parseLong(new StringTokenizer(br.readLine()).nextToken());
		}

		// 이전 빌딩들의 높이
		Stack<Long> stack = new Stack<>();
		// 이전 빌딩들에서 봤던 옥상 개수
		Stack<Long> sum = new Stack<>();
		stack.push(bHeight[n - 1]);
		sum.push(0L);
		
		long res = 0;
		for (int i = n - 2; i >= 0; i--) {
			// 현재 빌딩 높이가 더 높은 경우
			if (stack.peek() < bHeight[i]) {
				// 이전 빌딩들에서 봤던 옥상 개수 합하기
				long canSee = 0;
				while (!stack.isEmpty() && stack.peek() < bHeight[i]) {
					stack.pop();
					canSee += sum.pop() + 1;
				}
				res += canSee;
				sum.push(canSee);
			} 
			// 현재 빌딩 높이가 낮은 경우
			else {
				sum.push(0L);
			}
			stack.push(bHeight[i]);
		}

		System.out.println(res);

	}
}

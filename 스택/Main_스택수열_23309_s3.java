package 스택;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_스택수열_23309_s3 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		
		int num = 1;
		StringBuilder sb = new StringBuilder();
		Stack<Integer> stack = new Stack<>();
		while (n-- > 0) {
			st = new StringTokenizer(br.readLine());
			int target = Integer.parseInt(st.nextToken());
			
			if (!stack.isEmpty() && stack.peek() == target) {
				stack.pop();
				sb.append("-\n");
			} 
			// 스택에 원하는 수가 아직 들어있지 않은 경우
			else if ((stack.isEmpty() || stack.peek() < target) && num <= target) {
				while(num!=target) {
					stack.push(num);
					sb.append("+\n");
					num++;
				}
				// push and pop
				sb.append("+\n-\n");
				num++;
			} 
			else {
				System.out.println("NO");
				return;
			}
		}
		
		System.out.println(sb);

	} // end of main
} // end of class

package 스택;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_키로거_5397_s2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		for (int ts = 0; ts < T; ts++) {
			st = new StringTokenizer(br.readLine());
			String str = st.nextToken();

			Stack<Character> stack1 = new Stack<>();
			Stack<Character> stack2 = new Stack<>();
			for (int i = 0; i < str.length(); i++) {
				if (str.charAt(i) == '<') {
					if (!stack1.isEmpty())
						stack2.push(stack1.pop());
				} else if (str.charAt(i) == '>') {
					if (!stack2.isEmpty())
						stack1.push(stack2.pop());
				} else if (str.charAt(i) == '-') {
					if (!stack1.isEmpty())
						stack1.pop();
				} else {
					stack1.push(str.charAt(i));
				}
			}
			
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < stack1.size() ; i++) {
				sb.append(stack1.get(i));
			}
			for (int i = stack2.size()-1; i >= 0 ; i--) {
				sb.append(stack2.get(i));
			}
			System.out.println(sb);

		} // end of test case
	} // end of main
} // end of class

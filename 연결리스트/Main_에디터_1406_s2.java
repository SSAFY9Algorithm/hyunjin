package 연결리스트;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_에디터_1406_s2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		Stack<Character> front = new Stack<>();
		Stack<Character> back = new Stack<>();
		String str = st.nextToken();
		for (char c : str.toCharArray()) {
			front.push(c);
		}

		int n = Integer.parseInt(br.readLine());
		while (n-- > 0) {
			st = new StringTokenizer(br.readLine());
			char op = st.nextToken().charAt(0);
			if (op == 'L') {
				if (!front.isEmpty())
					back.push(front.pop());
			} else if (op == 'D') {
				if (!back.isEmpty())
					front.push(back.pop());
			} else if (op == 'B') {
				if (!front.isEmpty())
					front.pop();
			} else {
				char c = st.nextToken().charAt(0);
				front.push(c);
			}
		}

		StringBuilder sb = new StringBuilder();
		while (!front.isEmpty())
			back.push(front.pop());
		while (!back.isEmpty())
			sb.append(back.pop());
		System.out.println(sb);

	}
}

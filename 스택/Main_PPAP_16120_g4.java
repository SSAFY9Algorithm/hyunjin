package 스택;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_PPAP_16120_g4 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		char[] ppap = { 'P', 'P', 'A', 'P' };
		Stack<Character> stack = new Stack<>();
		Stack<Character> temp = new Stack<>();
		char[] str = st.nextToken().toCharArray();

		for (int i = 0; i < str.length; i++) {
			stack.push(str[i]);
			if (str[i] == 'P') {
				// ppap 문자열 확인
				for (int j = 3; j >= 0; j--) {
					if (stack.isEmpty() || stack.peek() != ppap[j])
						break;
					temp.add(stack.pop());
				}
				// ppap 문자열일 경우 p로 치환
				if (temp.size() == 4) {
					stack.add('P');
					temp.clear();
				}
				// ppap 문자열이 아닌 경우
				else {
					while (!temp.isEmpty())
						stack.add(temp.pop());
				}
			}
		} // end of for

		if (stack.size() == 1 && stack.peek() == 'P')
			System.out.println("PPAP");
		else
			System.out.println("NP");

	} // end of main

} // end of main

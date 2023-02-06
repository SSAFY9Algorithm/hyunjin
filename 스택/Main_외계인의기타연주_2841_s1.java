package 스택;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_외계인의기타연주_2841_s1 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int p = Integer.parseInt(st.nextToken());

		List<Stack<Integer>> stacks = new ArrayList<Stack<Integer>>();
		for (int i = 0; i < 6; i++) {
			stacks.add(new Stack<Integer>());
		}

		int cnt = 0;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int line = Integer.parseInt(st.nextToken());
			int pret = Integer.parseInt(st.nextToken());

			while (!stacks.get(line - 1).isEmpty() && stacks.get(line - 1).peek() > pret) {
				stacks.get(line - 1).pop();
				cnt++;
			}

			if (stacks.get(line - 1).isEmpty() || stacks.get(line - 1).peek() != pret) {
				stacks.get(line - 1).push(pret);
				cnt++;
			}
		}

		System.out.println(cnt);

	} // end of main
} // end of class

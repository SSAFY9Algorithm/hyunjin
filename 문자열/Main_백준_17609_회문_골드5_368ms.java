package 문자열;
import java.io.*;
import java.util.*;

public class Main_백준_17609_회문_골드5_368ms {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		while (T-- > 0) {
			String str = br.readLine();
			int left = 0, right = str.length() - 1;
			boolean isPassed = false;
			while (left < right) {
				if (str.charAt(left) == str.charAt(right)) {
					left++;
					right--;
				} else if (!isPassed && str.charAt(left + 1) == str.charAt(right)) {
					isPassed = true;
					left += 2;
					right--;
				} else {
					break;
				}
			}
			if (left >= right) {
				sb.append(isPassed ? 1 : 0).append("\n");
				continue;
			}
			left = 0;
			right = str.length() - 1;
			isPassed = false;
			while (left < right) {
				if (str.charAt(left) == str.charAt(right)) {
					left++;
					right--;
				} else if (!isPassed && str.charAt(left) == str.charAt(right - 1)) {
					isPassed = true;
					left++;
					right -= 2;
				} else {
					sb.append(2).append("\n");
					break;
				}
			}
			if (left >= right) {
				sb.append(isPassed ? 1 : 0).append("\n");
			}
		}
		System.out.println(sb);
	}
}

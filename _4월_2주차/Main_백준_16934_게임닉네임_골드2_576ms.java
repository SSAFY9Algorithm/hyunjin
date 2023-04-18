package _4월_2주차;

import java.io.*;
import java.util.*;

public class Main_백준_16934_게임닉네임_골드2_576ms {
	static Map<String, Integer> cnt = new HashMap<>();
	static Map<String, String> map = new HashMap<String, String>();
	static String prefix;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int n = Integer.parseInt(br.readLine());
		for (int i = 0; i < n; i++) {
			String nickname = br.readLine();
//			1. 동일한 nickname이 있을 때
			if (cnt.get(nickname) != null) {
				cnt.put(nickname, cnt.get(nickname) + 1);
				prefix = nickname + cnt.get(nickname);
				map.put(prefix, nickname);
				sb.append(prefix).append("\n");
				continue;
			}
			prefix = nickname.substring(0, 1);
			if (findPrefix(map.get(prefix), nickname)) {
				map.put(prefix, nickname);
			}
			cnt.put(nickname, 1);
			sb.append(prefix).append("\n");
		}

		System.out.println(sb);
	}

	private static boolean findPrefix(String str, String nickname) {
		// TODO prefix 찾기
		if (str == null)
			return true;

		int len = Math.min(str.length(), nickname.length());
		for (int k = 0; k < len; k++) {
			if (str.charAt(k) != nickname.charAt(k)) {
				prefix = nickname.substring(0, k + 1);
				return findPrefix(map.get(prefix), nickname);
			}
		}

//		2. nickname이 str의 접두사일 때
		if (nickname.length() == len) {
			prefix = nickname;
			return false;
		}

//		3. str이 nickname의 접두사일 때
		prefix = nickname.substring(0, len + 1);
		return findPrefix(map.get(prefix), nickname);
	}
}

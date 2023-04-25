package _4월_4주차;

import java.util.*;

public class 프로그래머스_메뉴리뉴얼_lv2 {

	String[] orders;
	Map<Integer, List<String>> course;
	Map<Integer, Integer> courseCnt = new HashMap<>();
	Map<Integer, Character> map = new HashMap<>();
	int charCnt;
	List<String> ans = new ArrayList<String>();

	public String[] solution(String[] Orders, int[] Course) {
		// String[] answer = {};
		orders = Orders;
		course = new HashMap<Integer, List<String>>();
		for (int i = 0; i < Course.length; i++) {
			course.put(Course[i], new ArrayList<>());
			courseCnt.put(Course[i], 0);
		}

		makeMap();
		solve();
		for (int i = 0; i < Course.length; i++) {
			ans.addAll(course.get(Course[i]));
		}
		Collections.sort(ans);
		// System.out.println(ans);
		return ans.toArray(new String[ans.size()]);
	}

	void makeMap() {
		boolean[] exist = new boolean[26];
		for (int i = 0; i < orders.length; i++) {
			char[] str = orders[i].toCharArray();
			for (int j = 0; j < str.length; j++) {
				if (exist[str[j] - 'A'])
					continue;
				exist[str[j] - 'A'] = true;
				charCnt++;
			}
		}
		int idx = 0;
		for (int i = 0; i < 26; i++) {
			if (exist[i]) {
				map.put(idx, (char) ('A' + i));
				idx++;
			}
		}
	}

	void solve() {
		int len = 1 << charCnt;
		for (int i = 0; i < len; i++) {
			int bitCnt = Integer.bitCount(i);
			if (course.get(bitCnt) != null) {
				int prevCnt = courseCnt.get(bitCnt);
				int cnt = countOrder(i);
				if (prevCnt < cnt && cnt > 1) {
					courseCnt.put(bitCnt, cnt);
					List<String> list = new ArrayList<>();
					list.add(makeString(i));
					course.put(bitCnt, list);
				} else if (cnt != 0 && prevCnt == cnt) {
					List<String> list = course.get(bitCnt);
					list.add(makeString(i));
					course.put(bitCnt, list);
				}
			}
		}
	}

	int countOrder(int selected) {
		int cnt = 0, bitCnt = Integer.bitCount(selected);
		for (int i = 0; i < orders.length; i++) {
			int j = 0;
			if (bitCnt > orders[i].length())
				continue;
			for (; j < charCnt; j++) {
				if ((selected & 1 << j) != 0) {
					char c = map.get(j);
					if (orders[i].indexOf(c) == -1)
						break;
				}
			}
			if (j == charCnt)
				cnt++;
		}
		return cnt;
	}

	String makeString(int selected) {
		String tmp = "";
		for (int i = 0; i < charCnt; i++) {
			if ((selected & 1 << i) != 0)
				tmp += map.get(i);
		}
		return tmp;
	}
}

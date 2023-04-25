package _4월_4주차;

import java.util.*;

public class 프로그래머스_표편집_lv3 {
	String[] cmd;

	class Node {
		int idx;
		Node prev, next;

		public Node(int idx) {
			this.idx = idx;
		}
	}

	Node cur;
	Stack<Node> saveData = new Stack<Node>();
	StringBuilder sb = new StringBuilder();

	public String solution(int n, int k, String[] Cmd) {
		cmd = Cmd;

		Node node = new Node(0);
		if (k == 0)
			cur = node;
		Node prev = node;
		sb.append("O");
		for (int i = 1; i < n; i++) {
			node = new Node(i);
			node.prev = prev;
			prev.next = node;
			if (i == k)
				cur = node;
			prev = node;
			sb.append("O");
		}

		/*
		 * "U X": 현재 선택된 행에서 X칸 위에 있는 행을 선택합니다. "D X": 현재 선택된 행에서 X칸 아래에 있는 행을 선택합니다.
		 * "C" : 현재 선택된 행을 삭제한 후, 바로 아래 행을 선택합니다. 단, 삭제된 행이 가장 마지막 행인 경우 바로 윗 행을 선택합니다.
		 * "Z" : 가장 최근에 삭제된 행을 원래대로 복구합니다. 단, 현재 선택된 행은 바뀌지 않습니다.
		 */
		/*
		 * - 표의 범위를 벗어나는 이동은 입력으로 주어지지 않습니다. - 원래대로 복구할 행이 없을 때(즉, 삭제된 행이 없을 때) "Z"가
		 * 명령어로 주어지는 경우는 없습니다. - "이름"열에는 서로 다른 이름들이 중복없이 채워져 있다 - cmd에 등장하는 모든 X들의 값을 합친
		 * 결과가 1,000,000 이하인 경우만 입력으로 주어집니다.
		 */
		solve();
		return sb.toString();
	}

	void solve() {
		int cnt = 0;
		for (int i = 0; i < cmd.length; i++) {
			String[] c = cmd[i].split(" ");
			switch (c[0]) {
			case "U":
				// up
				cnt -= Integer.parseInt(c[1]);
				break;
			case "D":
				// down
				cnt += Integer.parseInt(c[1]);
				break;
			case "C":
				// delete
				if (cnt < 0) {
					cnt = Math.abs(cnt);
					while (cnt-- > 0)
						cur = cur.prev;
				} else {
					while (cnt-- > 0)
						cur = cur.next;
				}
				cnt = 0;

				saveData.push(cur);
				sb.replace(cur.idx, cur.idx + 1, "X");
				if (cur.next == null) {
					cur = cur.prev;
					cur.next = null;
				} else if (cur.prev == null) {
					cur = cur.next;
					cur.prev = null;
				} else {
					cur.prev.next = cur.next;
					cur.next.prev = cur.prev;
					cur = cur.next;
				}
				break;
			case "Z":
				// ctrl z
				if (cnt < 0) {
					cnt = Math.abs(cnt);
					while (cnt-- > 0)
						cur = cur.prev;
				} else {
					while (cnt-- > 0)
						cur = cur.next;
				}
				cnt = 0;

				Node data = saveData.pop();
				Node prev = data.prev;
				if (prev != null) {
					Node tmp = prev.next;
					prev.next = data;
					data.next = tmp;
					if (tmp != null)
						tmp.prev = data;
				} else {
					Node next = data.next;
					Node tmp = next.prev;
					next.prev = data;
					data.prev = tmp;
					if (tmp != null)
						tmp.next = data;
				}
				sb.replace(data.idx, data.idx + 1, "O");
				break;
			}
		}
	}
}

package 서로소집합;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

// union-find

public class Main_친구네트워크_4195_g2_624ms {
	static Map<String, Person> people;
	
	public static class Person {
		int idx, friendsCnt;
		String name;
		Person parent;

		public Person(int idx, int friendsCnt, String name) {
			this.idx = idx;
			this.friendsCnt = friendsCnt;
			this.name = name;
			this.parent = this;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int tc = 0; tc < T; tc++) {
			// tc별 초기화
			people = new HashMap<String, Person>();

			int f = Integer.parseInt(br.readLine());

			int idx = 0;
			for (int i = 0; i < f; i++) {
				st = new StringTokenizer(br.readLine());
				String a = st.nextToken();
				String b = st.nextToken();
				if (people.get(a) == null) {
					people.put(a, new Person(idx, 1, a));
					idx++;
				}
				if (people.get(b) == null) {
					people.put(b, new Person(idx, 1, b));
					idx++;
				}
				union(a, b);
				sb.append(find(a).friendsCnt).append("\n");
			}

		} // end of tc

		System.out.println(sb);

	} // end of main

	public static Person find(String a) {
		Person p = people.get(a);
		if (!p.parent.equals(p)) {
			p.parent = find(p.parent.name);
		}
		return p.parent;
	}

	public static void union(String a, String b) {
		Person p1 = find(a);
		Person p2 = find(b);
		if (p1.idx < p2.idx) {
			p2.parent = p1;
			p1.friendsCnt += p2.friendsCnt;
		} else {
			p1.parent = p2;
			if (!p1.equals(p2))
				p2.friendsCnt += p1.friendsCnt;
		}
	}
}

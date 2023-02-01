package 연결리스트;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_철도공사_23309_g5 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		// 고유번호로 역 객체 저장할 배열
		Station[] stations = new Station[1000001];

		st = new StringTokenizer(br.readLine());

		// 역 객체 생성
		Station cur, prev, start;
		int id = Integer.parseInt(st.nextToken());
		cur = new Station(id);
		stations[id] = cur;
		start = cur;
		for (int i = 1; i < n; i++) {
			prev = cur;
			id = Integer.parseInt(st.nextToken());
			cur = new Station(id);
			stations[id] = cur;
			// 연결 리스트 만들기
			prev.next = cur;
			cur.prev = prev;
		}
		// 첫번째 역과 마지막 역 이어주기
		cur.next = start;
		start.prev = cur;

		// 공사 진행
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			String op = st.nextToken();
			Station target = stations[Integer.parseInt(st.nextToken())];

			if (op.equals("BN")) {
				int newId = Integer.parseInt(st.nextToken());
				sb.append(target.buildNext(newId)).append("\n");
			} else if (op.equals("BP")) {
				int newId = Integer.parseInt(st.nextToken());
				sb.append(target.buildPrev(newId)).append("\n");
			} else if (op.equals("CN")) {
				sb.append(target.closeNext()).append("\n");
			} else {
				sb.append(target.closePrev()).append("\n");
			}
		}

		System.out.println(sb);

	} // end of main
} // end of class

class Station {
	public int id;
	public Station next;
	public Station prev;

	public Station(int id) {
		this.id = id;
	}

	// 고유 번호 i를 가진 역의 다음 역의 고유 번호를 출력하고, 그 사이에 고유 번호 j인 역을 설립한다.
	public int buildNext(int newId) {
		int ret = this.next.id;
		Station newStation = new Station(newId);
		newStation.next = this.next;
		this.next.prev = newStation;
		this.next = newStation;
		newStation.prev = this;
		return ret;
	}

	// 고유 번호 i를 가진 역의 이전 역의 고유 번호를 출력하고, 그 사이에 고유 번호 j인 역을 설립한다.
	public int buildPrev(int newId) {
		int ret = this.prev.id;
		Station newStation = new Station(newId);
		newStation.prev = this.prev;
		this.prev.next = newStation;
		this.prev = newStation;
		newStation.next = this;
		return ret;
	}

	// 고유 번호 i를 가진 역의 다음 역을 폐쇄하고 그 역의 고유 번호를 출력한다.
	public int closeNext() {
		int ret = this.next.id;
		this.next.next.prev = this;
		this.next = this.next.next;
		return ret;
	}

	// 고유 번호 i를 가진 역의 이전 역을 폐쇄하고 그 역의 고유 번호를 출력한다.
	public int closePrev() {
		int ret = this.prev.id;
		this.prev.prev.next = this;
		this.prev = this.prev.prev;
		return ret;
	}
}
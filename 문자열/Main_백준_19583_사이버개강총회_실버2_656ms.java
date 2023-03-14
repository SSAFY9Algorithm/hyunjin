package 문자열;
import java.io.*;
import java.util.*;

public class Main_백준_19583_사이버개강총회_실버2_656ms {
	static class Time {
		int hour, min;
		boolean check;

		public Time(String time) {
			String[] t = time.split(":");
			this.hour = Integer.parseInt(t[0]);
			this.min = Integer.parseInt(t[1]);
		}

		public int compare(Time time) {
			return this.hour == time.hour ? this.min - time.min : this.hour - time.hour;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		Time S = new Time(st.nextToken());
		Time E = new Time(st.nextToken());
		Time Q = new Time(st.nextToken());

		int cnt = 0;
		Map<String, Time> check = new HashMap<>();
		while (true) {
			String str = br.readLine();
			if (str == null)
				break;
			st = new StringTokenizer(str);

			Time time = new Time(st.nextToken());
			String nickname = st.nextToken();
			Time target = check.get(nickname);

			// 처음 채팅 남김
			if (target == null) {
				// 개강총회 시작하기 전 채팅 남김
				if (time.compare(S) <= 0) {
					check.put(nickname, time);
				}
			}
			// 다시 채팅 남김
			else {
				// 개강총회 끝나고 채팅 남김
				// 개강총회 종료시간 <= time <= 스트리밍 종료시간
				if (time.compare(E) >= 0 && time.compare(Q) <= 0 && !target.check) {
					target.check = true;
					cnt++;
				}
			}
		} // end of while

		System.out.println(cnt);
	}
}

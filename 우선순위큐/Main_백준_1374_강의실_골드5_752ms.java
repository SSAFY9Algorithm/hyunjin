package 우선순위큐;
import java.io.*;
import java.util.*;

public class Main_백준_1374_강의실_골드5_752ms {
	static class Lecture implements Comparable<Lecture> {
		int no, start, end;

		public Lecture(int no, int start, int end) {
			super();
			this.no = no;
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Lecture o) {
			return this.start == o.start ? this.end - o.end : this.start - o.start;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());
		Lecture[] lectures = new Lecture[n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int no = Integer.parseInt(st.nextToken());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			lectures[i] = new Lecture(no, start, end);
		}

		Arrays.sort(lectures);

		PriorityQueue<Lecture> pq = new PriorityQueue<Lecture>((a, b) -> a.end - b.end);
		int classCnt = 0;
		int lecCnt = 0;
		pq.offer(lectures[lecCnt]);
		classCnt++;
		lecCnt++;
		while (lecCnt < n) {
			if (pq.peek().end <= lectures[lecCnt].start) {
				pq.poll();
			} else {
				classCnt++;
			}
			pq.offer(lectures[lecCnt]);
			lecCnt++;
		}

		System.out.println(classCnt);
	}
}

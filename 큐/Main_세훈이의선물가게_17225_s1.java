package 큐;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

class Main_세훈이의선물가게_17225_s1 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());

		Deque<Integer> sangmin = new ArrayDeque<>();
		Deque<Integer> jisu = new ArrayDeque<>();
		while (n-- > 0) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			// 상민 포장 타임라인
			if (st.nextToken().equals("B")) {
				int giftNum = Integer.parseInt(st.nextToken());
				// 포장할 선물이 없는 경우
				if (sangmin.isEmpty() || sangmin.peekLast() + a <= start) {
					makeTimeline(sangmin, start, giftNum, a);
				}
				// 포장이 끝나고 새로운 선물 포장 시작
				else {
					makeTimeline(sangmin, sangmin.peekLast() + a, giftNum, a);
				}
			}
			// 지수 포장 타임라인
			else {
				int giftNum = Integer.parseInt(st.nextToken());
				// 포장할 선물이 없는 경우
				if (jisu.isEmpty() || jisu.peekLast() + b <= start) {
					makeTimeline(jisu, start, giftNum, b);
				}
				// 포장이 끝나고 새로운 선물 포장 시작
				else {
					makeTimeline(jisu, jisu.peekLast(), giftNum, b);
				}
			}
		} // end of while

		Deque<Integer> resS = new ArrayDeque<>();
		Deque<Integer> resJ = new ArrayDeque<>();
		int idx = 1;
		// 포장한 선물 번호 정하기
		while (true) {
			if(jisu.isEmpty()) {
				if(sangmin.isEmpty()) break;
				sangmin.poll();
				resS.add(idx);
				idx++;
				continue;
			} else if(sangmin.isEmpty()) {
				jisu.poll();
				resJ.add(idx);
				idx++;
				continue;
			}
			
			if (sangmin.peek() < jisu.peek()) {
				sangmin.poll();
				resS.add(idx);
				idx++;
			} else if (sangmin.peek() > jisu.peek()) {
				jisu.poll();
				resJ.add(idx);
				idx++;
			} else {
				sangmin.poll();
				resS.add(idx);
				idx++;
				jisu.poll();
				resJ.add(idx);
				idx++;
			}
		} // end of while

		System.out.println(resS.size());
		for (Integer res : resS) {
			System.out.print(res + " ");
		}
		System.out.println();
		System.out.println(resJ.size());
		for (Integer res : resJ) {
			System.out.print(res + " ");
		}

	}

	public static void makeTimeline(Deque<Integer> dq, int start, int num, int intv) {
		int idx = 0;
		while (idx < num) {
			dq.add(start + idx * intv);
			idx++;
		}
	}
}

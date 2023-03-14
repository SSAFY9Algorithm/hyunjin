package class5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_벡터매칭_1007_g2_ms {
	public static class Spot {
		int x, y, connCnt;

		public Spot(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static class Vector {
		int startIdx, endIdx;
		double dist;

		public Vector(int startIdx, int endIdx, double dist) {
			this.startIdx = startIdx;
			this.endIdx = endIdx;
			this.dist = dist;
		}
	}

	public static void main(String[] args) throws IOException {
//		20 * 17 / 3 = 170
		
		
//		MST -> 생략 가능한 벡터 존재
//		1. MST 찾기
//		2. 특정 점에서 나가는 벡터 개수 찾기
//		3. MST 이루는 벡터 중에서 길이가 긴 순서 찾기
//		4. 벡터를 이루는 두 점이 가지는 벡터 개수가 2개 이상이면 삭제

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		while (T-- > 0) {

			int n = Integer.parseInt(br.readLine());
			Spot[] spots = new Spot[n];
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				spots[i] = new Spot(x, y);
			}

			// col0:spot idx, col2:distance
			PriorityQueue<Vector> pq = new PriorityQueue<Vector>((a, b) -> (int) (a.dist - b.dist));
			PriorityQueue<Vector> vectorList = new PriorityQueue<Vector>((a, b) -> (int) (b.dist - a.dist));
			int cnt = 0, visited = 0;
			double totalDist = 0;
			Vector cur;
			pq.add(new Vector(0, 0, 0));
			while (cnt < n) {
				cur = pq.poll();
				if ((visited & 1 << cur.endIdx) > 0)
					continue;

				visited = visited | 1 << cur.endIdx;
				cnt++;
				totalDist += cur.dist;
				spots[cur.endIdx].connCnt++;
				vectorList.add(cur);

				for (int i = 0; i < n; i++) {
					if ((visited & 1 << i) > 0)
						continue;
					pq.add(new Vector(cur.endIdx, i,
							getDist(spots[cur.endIdx].x, spots[cur.endIdx].y, spots[i].x, spots[i].y)));
				}
			}

			boolean flag = false;
			while (vectorList.size() > 0) {
				cur = vectorList.poll();
				flag = false;
				if (spots[cur.startIdx].connCnt > 1 && spots[cur.endIdx].connCnt > 1) {
					spots[cur.startIdx].connCnt--;
					spots[cur.endIdx].connCnt--;
					totalDist -= cur.dist;
					flag = true;
				}
				if(!flag) break;
			}

			System.out.println(totalDist);

		} // end of tc

	}

	public static double getDist(int x1, int y1, int x2, int y2) {
		return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
	}
}

package _3월_4주차;

public class Programmers_DeliveryBox {
	public static void main(String[] args) {
		int cap = 5;
		int n = 3;
		int[] deliveries = {5, 0, 5};
		int[] pickups = {0, 1, 10};

		System.out.println(solution(cap, n, deliveries, pickups));
	}

	static int[] d, p;
	static int N;
	static int Cap;

	public static long solution(int cap, int n, int[] deliveries, int[] pickups) {
		long answer = 0;

		Cap = cap;
		N = n;
		d = deliveries;
		p = pickups;

		int idx = N-1;
		while ((idx = findLastHouse(idx)) != -1) {
			deliveryBox(idx);
			pickupBox(idx);
			answer += (idx + 1) * 2;
		}

		return answer;
	}

	public static int findLastHouse(int last) {
		// 가야하는 제일 마지막 집 위치 찾기
		int idx = last + 1;
		while (--idx > -1) {
			if (d[idx] != 0)
				return idx;
			if (p[idx] != 0)
				return idx;
		}
		return idx;
	}

	public static void deliveryBox(int idx) {
		// 택배 배달하기
		int deliveryBoxCnt = 0;
		for (int i = idx; i >= 0; i--) {
			if (d[i] != 0) {
				if (deliveryBoxCnt + d[i] > Cap) {
					d[i] -= Cap - deliveryBoxCnt;
					return;
				} else {
					deliveryBoxCnt += d[i];
					d[i] = 0;
				}
			}
		}
	}

	public static void pickupBox(int idx) {
		// 택배 수거하기
		int pickupBoxCnt = 0;
		for (int i = idx; i >= 0; i--) {
			if (p[i] != 0) {
				if (pickupBoxCnt + p[i] > Cap) {
					p[i] -= Cap - pickupBoxCnt;
					return;
				} else {
					pickupBoxCnt += p[i];
					p[i] = 0;
				}
			}
		}
	}
}

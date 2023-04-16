package _4월1주차;

public class 프로그래머스_두큐합같게만들기 {
	public static void main(String[] args) {
		int[] Queue1 = { 1,1 };
		int[] Queue2 = { 1,3 };
		System.out.println(solution(Queue1, Queue2));
	}

	static int minCnt = 4000000;
	static int[] queue1, queue2;

	public static int solution(int[] Queue1, int[] Queue2) {
		queue1 = Queue1;
		queue2 = Queue2;

		int[] arr = new int[queue1.length + queue2.length];
		System.arraycopy(queue1, 0, arr, 0, queue1.length);
		System.arraycopy(queue2, 0, arr, queue1.length, queue2.length);

		long total = 0;
		for (int i = 0; i < arr.length; i++) {
			total += arr[i];
		}
		
		if (total % 2 != 0)
			return -1;
		total /= 2;

//		2, 29번의 경우는 각 큐의 합이 같은것을 판단하는 시점과 카운트를 올리는 시점에 대해서 고민
		// sliding window
		int left = 0, right = 0;
		long sum = arr[0];
		while (left < arr.length) {
			if (total > sum) {
				right++;
				if (right == arr.length)
					right %= arr.length;
				sum += arr[right];
			} else if (total < sum) {
				sum -= arr[left];
				left++;
			} else {
				countWork(left, right);
				right++;
				if (right == arr.length)
					right %= arr.length;
				sum += arr[right];
				sum -= arr[left];
				left++;
			}
		}

		return minCnt == 4000000 ? -1 : minCnt;
	}

	static void countWork(int left, int right) {
		int cnt = 0;
		if (left < queue1.length) {
			if (right < queue1.length) {
				// input으로 주어진 큐가 조건을 만족할 때
				if (left == 0 && right == queue1.length - 1) {
					cnt = 0;
				} else {
					cnt += right + 1;
					cnt += left + queue2.length;
				}
			} else {
				cnt += left;
				cnt += right - queue1.length + 1;
			}
		} else {
			if (right < queue1.length) {
				cnt += right - queue1.length + 1;
				cnt += left;
			} else {
				// input으로 주어진 큐가 조건을 만족할 때
				if (left == queue1.length && right == queue2.length - 1) {
					cnt = 0;
				} else {
					cnt += left - queue1.length;
					cnt += right + 1;
				}
			}
		}
		minCnt = Math.min(minCnt, cnt);
	}
}

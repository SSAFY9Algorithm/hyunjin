package 큐;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_트럭_13335_s1 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int w = Integer.parseInt(st.nextToken());
		int l = Integer.parseInt(st.nextToken());

		// get inputs
		Truck[] trucks = new Truck[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			trucks[i] = new Truck(Integer.parseInt(st.nextToken()));
		}

		int time = 0;
		int weight = 0;
		int truckIdx = 0;
		Queue<Truck> bridge = new LinkedList<>();
		int end = 0;
		while (true) {
			// 다리 건넘
			if (!bridge.isEmpty() && bridge.peek().onBridgeDay == w) {
				weight -= bridge.poll().weight;
				end++;
			}
			// 다리 무게 초과하지 않을 때
			if (bridge.size() < w && truckIdx < n && weight + trucks[truckIdx].weight <= l) {
				bridge.add(trucks[truckIdx]);
				weight += trucks[truckIdx].weight;
				truckIdx++;
			}
			// 다리 위에 올라간 시간 체크
			for (Truck truck : bridge) {
				truck.onBridgeDay += 1;
			}

			time++;

			// 모두 다리를 건넌 경우
			if (end == n)
				break;
		}

		System.out.println(time);

	} // end of main
} // end of class

class Truck {
	int onBridgeDay = 0;
	int weight;

	public Truck(int weight) {
		this.weight = weight;
	}
}

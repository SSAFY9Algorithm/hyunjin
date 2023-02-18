package 큐;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_앵무새_14713_s2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());

		// 앵무새들이 말한 문장 저장
		// 문장마다 큐 생성
		int totalLen = 0;
		List<Queue<String>> queueList = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			Queue<String> queue = new LinkedList<String>();
			while (st.hasMoreElements()) {
				queue.add(st.nextToken());
				totalLen++;
			}
			queueList.add(queue);
		}

		// 가능한 문장인지 판단
		st = new StringTokenizer(br.readLine());
		boolean isOkay = false;
		int strLen = 0;
		while (st.hasMoreElements()) {
			String str = st.nextToken();
			isOkay = false;
			// 각 문장의 큐에서 해당되는 단어 존재하는지 확인
			for (int i = 0; i < n; i++) {
				if (!queueList.get(i).isEmpty() && queueList.get(i).peek().equals(str)) {
					queueList.get(i).poll();
					isOkay = true;
					break;
				}
			}
			strLen++;
			if (!isOkay)
				break;
		}

		if (isOkay && totalLen == strLen)
			System.out.println("Possible");
		else
			System.out.println("Impossible");

	}
}

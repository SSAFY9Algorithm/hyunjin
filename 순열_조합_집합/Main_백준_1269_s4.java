package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main_백준_1269_s4 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int nA = Integer.parseInt(st.nextToken());
		int nB = Integer.parseInt(st.nextToken());

		Set<Integer> setA = new HashSet<Integer>();
		Set<Integer> setB = new HashSet<Integer>();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < nA; i++) {
			setA.add(Integer.parseInt(st.nextToken()));
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < nB; i++) {
			setB.add(Integer.parseInt(st.nextToken()));
		}

		setA.retainAll(setB);
		System.out.println(nA + nB - setA.size() * 2);

	} // end of main
} // end of class

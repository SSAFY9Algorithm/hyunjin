<<<<<<< HEAD
package study;
=======
package 순열_조합_집합;
>>>>>>> hyunjin

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main_백준_10972_s3 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 13524 -> 13542
		// 52341 -> 52413
		// 54312 -> 54321
		int n = Integer.parseInt(st.nextToken());
		int[] nums = new int[n];
		st = new StringTokenizer(br.readLine());

		boolean isLastOne = true;
		for (int i = 0; i < n; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
			if (nums[i] != n - i)
				isLastOne = false;
		}

		if (isLastOne) {
			System.out.println(-1);
			return;
		}

		ArrayList<Integer> arr = new ArrayList<Integer>();
		int last = 0;
		for (int i = n - 1; i >= 0; i--) {
			arr.add(nums[i]);
			if (nums[i] > nums[i - 1]) {

				Collections.sort(arr);
				for (int j = 0; j < arr.size(); j++) {
					if (arr.get(j) > nums[i - 1]) {
						int temp = arr.get(j);
						arr.set(j, nums[i - 1]);
						nums[i - 1] = temp;
						break;
					}
				}
				last = i;
				break;
			}
		}

		for (int i = 0; i < arr.size(); i++) {
			nums[last + i] = arr.get(i);
		}

		for (int num : nums) {
			System.out.printf("%d ", num);
		}

	} // end of main
} // end of class

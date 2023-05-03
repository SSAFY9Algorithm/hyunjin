package _5월_1주차;

import java.util.Arrays;

public class 프로그래머스_표현가능한이진트리 {
	public static void main(String[] args) {
		System.out.println(Arrays.toString(solution(new long[] {42})));
	}
	
	static long[] pow2;
    public static int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        
        pow2 = new long[51];
        pow2[0] = 1;
        for(int i=1; i<51; i++) {
            pow2[i] = pow2[i-1] * 2; 
        }
        
        for(int i=0; i<numbers.length; i++) {
            answer[i] = solve(numbers[i]);
        }
        
        return answer;
    }
    
    static int flag;
    static int solve(long num) {
        int cnt = 0; // 노드 개수
        for(; cnt<51; cnt++) { 
            if(pow2[cnt]-1 >= num) break;
        }
        int h = 0; // 트리 높이
        for(; h<51; h++) {
            if(pow2[h]-1 >= cnt) break;
        }
        cnt = (int)(pow2[h]-1);
        String nodes = Long.toBinaryString(num);
        int len = nodes.length();
        for(int i=0; i<cnt-len; i++) nodes = "0" + nodes;
        
        flag = 1;
        check(0, cnt-1, nodes);
        return flag;
    }
    
    static int check(int start, int end, String nodes) {
        if(flag==0) return -1;
        if(start >= end) return nodes.charAt(start) - '0';
        
        int mid = start + (end-start)/2;
        int left = check(start, mid-1, nodes);
        int right = check(mid+1, end, nodes);
        if(nodes.charAt(mid) == '0' && (left==1 || right==1)) flag = 0;
        
        if(left==1) return 1;
        else if(nodes.charAt(mid) == '1') return 1;
        else if(right==1) return 1;
        return 0;
    }
}

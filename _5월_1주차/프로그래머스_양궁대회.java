package _5월_1주차;

class 프로그래머스_양궁대회 {
    int n, max;
    int[] info;
    int[] answer = new int[11];
    
    public int[] solution(int N, int[] Info) {
        n = N;
        info = Info;
        int[] selected = new int[11];
        combination(0,0,selected);
        return max == 0 ? new int[]{-1} : answer;
    }
    
    void combination(int depth, int start, int[] selected) {
        if(depth == n) {
            check(selected);
            return;
        }
        for(int i=start; i<11; i++) {
            selected[i]++;
            combination(depth+1, i, selected);
            selected[i]--;
        }
    }
    
    void check(int[] selected) {
        int sub = 0;
        for(int i=0; i<11; i++) {
            if(info[i] < selected[i]) {
                sub += 10-i;
            } else if(info[i] != 0) {
                sub -= 10-i;
            }
        }
        // 점수 차이가 최대
        if(sub > 0) {
            if(max < sub) {
                max = sub;
                System.arraycopy(selected, 0, answer, 0, 11);
            } else if(max == sub) {
                //  가장 낮은 점수를 더 많이 맞힌 경우
                for(int i=10; i>=0; i--) {
                    if(selected[i] > answer[i]) {
                        System.arraycopy(selected, 0, answer, 0, 11);
                        break;
                    } 
                    else if(selected[i] < answer[i]) break;
                }
            }
        }
    }
    
}
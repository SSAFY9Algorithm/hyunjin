package _5월_1주차;

class 프로그래머스_이모티콘할인행사 {
    int[][] users;
    int[] emoticons;
    int maxCnt, maxTotal;
    
    public int[] solution(int[][] Users, int[] Emoticons) {
        int[] answer = {};
        users = Users;
        emoticons = Emoticons;
        
        // 1. 이모티콘 할인 경우의 수 만들기
        // 2. 모든 유저에 대해 플러스 가입 수 계산
        // 3. 가입 수 동일한 경우 매출액 최대 선택
        
        int[] selected = new int[emoticons.length];
        permutation(0, selected);
        
        return new int[]{maxCnt, maxTotal};
    }
    
    void permutation(int depth, int[] selected) {
        if(depth == emoticons.length) {
            check(selected);
            return;
        }
        for(int i=1; i<=4; i++) {
            selected[depth] = i*10;
            permutation(depth+1, selected);
        }
    }
    
    void check(int[] selected) {
        int[] e = new int[emoticons.length];
        // 이모티콘 할인 계산
        for(int i=0; i<emoticons.length; i++) {
            e[i] = (int)(emoticons[i]*(100 - selected[i])/100);
        }
        int cnt = 0, total = 0;
        for(int i=0; i<users.length; i++) {
            int sum = 0;
            for(int j=0; j<emoticons.length; j++) {
                if(users[i][0] <= selected[j]) {
                    sum += e[j];
                }
            }
            if(sum >= users[i][1]) cnt++;
            else total += sum;
        }
        if(cnt > maxCnt) {
            maxCnt = cnt;
            maxTotal = total;
        } else if(cnt==maxCnt && total > maxTotal) {
            maxTotal = total;
        }
    }
}

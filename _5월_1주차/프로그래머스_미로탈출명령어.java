package _5월_1주차;

public class 프로그래머스_미로탈출명령어 {
    int n, m, x, y, r, c, k;
    String answer = "";
    public String solution(int N, int M, int X, int Y, int R, int C, int K) {
        n = N; m = M; x = X-1; y = Y-1; r = R-1; c = C-1; k = K;
        dfs(0, x, y, "");
        return "".equals(answer)? "impossible" : answer;
    }
    
    int[] dx = {1, 0, 0, -1};
    int[] dy = {0, -1, 1, 0};
    char[] dir = {'d', 'l', 'r', 'u'};
    void dfs(int depth, int i, int j, String path) {
        if(!"".equals(answer)) return;
        if(depth == k) {
            if(i==r && j==c) answer = path;
            return;
        }
        int dist = Math.abs(i-r)+Math.abs(j-c);
        int cnt = k-depth;
        // 남은 거리가 갈 수 있는 횟수보다 클 때
        if(dist > cnt) return;
        // 남은 거리와 갈 수 있는 횟수의 차이가 홀수 일 때
        if(Math.abs(dist-cnt)%2 == 1) return;
        
        for(int d=0; d<4; d++) {
            int nx = i+dx[d];
            int ny = j+dy[d];
            if(nx<0||nx>=n||ny<0||ny>=m) continue;
            dfs(depth+1, nx, ny, path+dir[d]);
        }
    }
}

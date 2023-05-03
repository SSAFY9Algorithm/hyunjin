package _5월_1주차;

import java.util.*;
import java.io.*;

public class Main_백준_22255_호석사우르스_골드2_176ms {
    static int n, m, sx, sy, ex, ey;
    static int[][] map;

    static class Node implements Comparable<Node> {
        int x, y, no, w;

        public Node(int x, int y, int no, int w) {
            this.x = x;
            this.y = y;
            this.no = no;
            this.w = w;
        }

        @Override
        public int compareTo(Node o) {
            return this.w - o.w;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        sx = Integer.parseInt(st.nextToken()) - 1;
        sy = Integer.parseInt(st.nextToken()) - 1;
        ex = Integer.parseInt(st.nextToken()) - 1;
        ey = Integer.parseInt(st.nextToken()) - 1;

        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(solve());

    }

    static int[] dx = { 1, -1, 0, 0 };
    static int[] dy = { 0, 0, 1, -1 };

    private static int solve() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(sx, sy, 1, 0));
        int[][][] visited = new int[n][m][3];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                Arrays.fill(visited[i][j], -1);
            }
        }
        visited[sx][sy][1] = 0;
        Node cur;
        while (!pq.isEmpty()) {
            cur = pq.poll();
            if (visited[cur.x][cur.y][cur.no] != -1 && visited[cur.x][cur.y][cur.no] < cur.w) {
                continue;
            }
            for (int i = 0; i < 4; i++) {
                if (cur.no == 1 && i > 1) {
                    continue;
                } else if (cur.no == 2 && i < 2) {
                    continue;
                }
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (nx < 0 || nx >= n || ny < 0 || ny >= m || map[nx][ny] == -1)
                    continue;
                int no = (cur.no + 1) % 3;
                if (visited[nx][ny][no] == -1 || visited[nx][ny][no] > cur.w + map[nx][ny]) {
                    visited[nx][ny][no] = cur.w + map[nx][ny];
                    pq.offer(new Node(nx, ny, no, visited[nx][ny][no]));
                }
            }
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < 3; i++) {
            if (visited[ex][ey][i] == -1)
                continue;
            min = Math.min(min, visited[ex][ey][i]);
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }
}
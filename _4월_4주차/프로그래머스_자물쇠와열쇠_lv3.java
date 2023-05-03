package _4월_4주차;

import java.util.*;

public class 프로그래머스_자물쇠와열쇠_lv3 {
	public static void main(String[] args) {}
}

class Solution {
    int m, n, prevN;
    int[][] key, lock;
    int sX, sY, lenX, lenY;
    public boolean solution(int[][] Key, int[][] Lock) {
        key = Key;
        lock = Lock;
        m = key.length;
        prevN = n;
        n = lock.length;
        return solve();
    }
    
    boolean solve() {
        padding();
        if(check()) return true;
        for(int i=0; i<3; i++) {
            rotate();
            if(check()) return true;
        }
        return false;
    }
    
    // 2m만큼 패딩
    void padding(){
        int size = n+2*m;
        int[][] newLock = new int[size][size];
        for(int i=0; i<size; i++) {
            for(int j=0; j<size; j++) {
                if(i>=m && i<m+n && j>=m && j<m+n) 
                    newLock[i][j] = lock[i-m][j-m];
                else
                    newLock[i][j] = 0;
            }
        }
        lock = newLock;
        n = size;
    }
    
    void rotate() {
        int[][] newKey = new int[m][m];
        for(int i=0; i<m; i++) {
            for(int j=0; j<m; j++) {
                newKey[j][m-1-i] = key[i][j];
            }
        }
        key = newKey;
    }
    
    // sliding window
    boolean check() {
        for(int i=0; i<=n-m; i++) {
            for(int j=0; j<=n-m; j++) {
                // key 체크
                int x=m;
                checkKey:for(; x<prevN+m; x++) {
                    for(int y=m; y<prevN+m; y++) {
                        // 일치하지 않을 경우
                        // 자물쇠 범위 벗어나는 경우는 체크 안함
                        int dx = x-i, dy = y-j;
                        if(dx>=0 && dx<m && dy>=0 && dy<m) {
                            if(lock[x][y]==key[dx][dy]) break checkKey;
                        } 
                        // 자물쇠 나머지 영역 채워져 있는지 확인
                        else {
                            if(lock[x][y]==0) break checkKey;
                        }
                    }
                }
                if(x==m+prevN) return true;
            }
        }
        return false;
    }

}

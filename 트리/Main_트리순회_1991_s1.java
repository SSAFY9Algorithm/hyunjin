package 트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_트리순회_1991_s1 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		BNode[] nodes = new BNode[26];
		while (n-- > 0) {
			st = new StringTokenizer(br.readLine());
			char c = st.nextToken().charAt(0);
			char left = st.nextToken().charAt(0);
			char right = st.nextToken().charAt(0);

			if (nodes[c - 'A'] == null) {
				nodes[c - 'A'] = new BNode(c);
			}
			// 왼쪽 노드 추가
			if (left != '.') {
				if (nodes[left - 'A'] == null) {
					nodes[left - 'A'] = new BNode(left);
				}
				nodes[c - 'A'].children[0] = nodes[left - 'A'];
			}
			// 오른쪽 노드 추가
			if (right != '.') {
				if (nodes[right - 'A'] == null) {
					nodes[right - 'A'] = new BNode(right);
				}
				nodes[c - 'A'].children[1] = nodes[right - 'A'];
			}
		} // end of while;
		
		preT(nodes[0]);
		System.out.println();
		inT(nodes[0]);
		System.out.println();
		postT(nodes[0]);
		System.out.println();
		

	} // end of main

	// 전위 순회
	public static void preT(BNode cur) {
		System.out.print(cur.c);
		if(cur.children[0] != null)
			preT(cur.children[0]);
		if(cur.children[1] != null)
			preT(cur.children[1]);
	}
	
	// 중위 순회
	public static void inT(BNode cur) {
		if(cur.children[0] != null)
			inT(cur.children[0]);
		System.out.print(cur.c);
		if(cur.children[1] != null)
			inT(cur.children[1]);
	}
	
	// 후위 순회
	public static void postT(BNode cur) {
		if(cur.children[0] != null)
			postT(cur.children[0]);
		if(cur.children[1] != null)
			postT(cur.children[1]);
		System.out.print(cur.c);
	}

} // end of class

class BNode {
	BNode[] children = new BNode[2];
	BNode parent;
	char c;

	public BNode(char c) {
		this.c = c;
	}
}

package com.algorithms.unionfind;

public class QuickFindUF {
    private int[] id;

    public QuickFindUF(int N) {
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
    }

    public boolean connected(int p, int q) {
        return id[p] == id[q];
    }

    public void union(int p, int q) {
        int pid = id[p];
        int qid = id[q];

        for (int i = 0; i < id.length; i++) {
            if (id[i] == pid) {
                id[i] = qid;
            }
        }
    }
    
    public static void main(String[] args) {
        QuickFindUF q = new QuickFindUF(10);
        
        q.union(9, 4);
        q.union(2, 4);
        
        q.union(3, 5);
        q.union(1, 2);
        
        q.union(8, 1);
        q.union(0, 3);
    }
}

// rank

public class UnionFind6 implements UF {
    private int[] parent;
    private int[] rank; // rank[i]只是一个参考
    public UnionFind6(int size) {
        parent = new int[size];
        rank = new int[size];
        for(int i = 0; i < size; i++) {
            parent[i] = i;
            rank[i] = 1;
        }

    }

    @Override
    public int getSize() {
        return parent.length;
    }

    // 查找过程，查找元素p所对应的集合编号
    // O(h)复杂度，h为树的高度
    private int find(int p) {
        if(p < 0 && p >= parent.length)
            throw new IllegalArgumentException("p is out of bound.");


        if(p != parent[p])
            parent[p] = find(parent[p]);
        return parent[p];
    }


    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    // 根据两个元素所在树的rank不同判断合并方向
    // 将rank低的集合合并到rank高的集合上
    @Override
    public void unionElements(int p , int q) {
        int pRoot = find(p);
        int qRoot = find(q);

        if(pRoot == qRoot)
            return;

        if(rank[pRoot] < rank[qRoot])
            parent[pRoot] = qRoot;
        else if (rank[qRoot] < rank[pRoot])
            parent[qRoot] = pRoot;
        else { // rank[qRoot] == rank[pRoot]
            parent[qRoot] = pRoot;
            rank[pRoot] +=1;
        }
    }
}

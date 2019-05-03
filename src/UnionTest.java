import java.util.Random;

public class UnionTest {
    private static double testUF(UF uf , int m) {
        int size = uf.getSize();
        Random random = new Random();
        long startTime = System.nanoTime();
        // 进行m次合并操作
        for(int i = 0; i < m ; i ++) {
            int a = random.nextInt(size);
            int b = random.nextInt(size);
            uf.unionElements(a,b);
        }

        // 进行m次find操作
        for(int i =0; i < m; i ++) {
            int a = random.nextInt(size);
            int b = random.nextInt(size);
            uf.isConnected(a,b);
        }

        long endTime = System.nanoTime();

        return (endTime - startTime) / 1000000000.0;
    }

    public static void main(String[] arg) {

        int size = 10000000;
        int m = 10000000;

        UnionFind3 unionFind3 = new UnionFind3(size);
        System.out.println("UnionFind3 :" + testUF(unionFind3,m) + " s");

        UnionFind4 unionFind4 = new UnionFind4(size);
        System.out.println("UnionFind4 :" + testUF(unionFind4,m) + " s");

        UnionFind5 unionFind5 = new UnionFind5(size);
        System.out.println("UnionFind4 :" + testUF(unionFind5,m) + " s");

        // 递归是有开销的
        UnionFind6 unionFind6 = new UnionFind6(size);
        System.out.println("UnionFind4 :" + testUF(unionFind6,m) + " s");

    }
}

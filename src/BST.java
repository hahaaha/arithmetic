import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BST<E extends  Comparable<E>> {
    private class Node{
        public E e;
        public Node left,right;

        public Node(E e) {
            this.e = e;
            left = null;
            right = null;
        }
    }

    private Node root;
    private int size;

    public BST() {
        root = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(E e) {
        root = add(root,e);
    }

    private Node add(Node node,E e) {
        if(node == null) {
            size++;
            return new Node(e);
        }
        if(e.compareTo(node.e) < 0)
            node.left = add(node.left, e);
        else if(e.compareTo(node.e) > 0)
            node.right = add(node.right, e);
        return node;
    }
    // 二分搜索树中是否包含元素e
    public boolean contains(E e) {
        return contains(root,e);
    }

    // 深度优先
    // 以node为根的二分搜索树中是否包含元素e，递归算法
    private boolean contains(Node node,E e) {
        if(node == null)
            return false;
        if(e.equals(node.e))
            return true;
        else if(e.compareTo(node.e) > 0)
            return contains(node.right,e);
        else // e.compareTo(node.e) < 0
            return contains(node.left,e);
    }
    // 二分搜索树的前序遍历
    public void preOrder() {
        preOrder(root);
    }

    private void preOrder(Node node) {
        // 递归终止条件
        if(node == null)
            return;
        // 递归
        System.out.println(node.e);
        preOrder(node.left);
        preOrder(node.right);
    }
    //二分搜索树的中序遍历
    //中序遍历出来的元素是顺序的
    public void inOrder() {
        inOrder(root);
    }

    private void inOrder(Node node) {
        if(node == null)
            return;
        inOrder(node.left);
        System.out.println(node.e);
        inOrder(node.right);

    }
    // 二分搜索树的前序遍历非递归方法
    public void preOrderNR() {
        preOrderNR(root);
    }

    private void preOrderNR(Node node) {
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            System.out.println(cur.e);
            if(cur.right != null)
                stack.push(cur.right);
            if(cur.left != null)
                stack.push(cur.left);
        }
    }


    // 二分搜索树的后序搜索树
    // 应用：为二分搜索树释放内存
    public void postOrder() {
        postOrder(root);
    }

    private void postOrder(Node node) {
        if(node == null)
            return;
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.e);
    }

    // 层序遍历 广度优先遍历
    // 广度优先能更快的找到问题的解
    // 使用队列
    public void levelOrder() {
        Queue<Node>  q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()) {
            Node cur = q.remove();
            System.out.println(cur.e);
            if(cur.left != null)
                q.add(cur.left);
            if(cur.right != null)
                q.add(cur.right);
        }
    }

    // 寻找二分搜索树的最小值
    public E minimum() {
        if(size ==0)
            throw new IllegalArgumentException("BST is empty!");
        return minimum(root).e;
    }

    // 返回以node为根的二分搜索树的最小值所在的节点
    private Node minimum(Node node) {
        if(node.left == null)
            return node;
        return minimum(node.left);
    }

    public E maximum() {
        if(size == 0)
            throw new IllegalArgumentException("BST is empty");
        return maximum(root).e;
    }

    private Node maximum(Node node) {
        if(node.right == null)
            return node;
        return maximum(node.right);
    }

    // 从二分搜索树中删除最小值所在节点，并返回最小值
    public E removeMin() {
        E ret = minimum();
        root = removeMin(root);
        return ret;
    }

    // 删除点以node为根的二分搜索树中的最小节点
    // 返回删除节点后新额二分搜索树的根
    private Node removeMin(Node node) {
        if(node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            size --;
            return rightNode;
        }
        node.left = removeMin(node.left);
        return node;
    }


    public E removeMax() {
        E ret = maximum();
        root = removeMax(root);
        return ret;
    }

    public Node removeMax(Node node) {
        if(node.right == null) {
            Node leftNode = node.left;
            node.left = null;
            size --;
            return leftNode;
        }
        node.right = removeMax(node.right);
        return node;
    }

    public void remove(E e) {
        root = remove(root, e);
    }

    private Node remove(Node node, E e) {
        if(node == null)
            return null;
        if(e.compareTo(node.e) < 0) {
            node.left = remove(node.left,e);
            return node;
        }
        else if(e.compareTo(node.e) > 0) {
            node.right = remove(node.right,e);
            return node;
        }
        else {  // e == node.e
            if(node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size --;
                return rightNode;
            }

            if(node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size --;
                return leftNode;
            }

            // 左右子树不为空
            Node successor = minimum(node.right);
            successor.right = removeMin(node.right);
            successor.left = node.left;

            node.left = node.right = null;

            return successor;

        }
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        generateBSTString(root,0,res);
        return res.toString();
    }

    // 生成以node为根节点，深度为depth的描述二叉树的字符串
    private void generateBSTString(Node node,int depth,StringBuilder res) {
        if(node == null) {
            res.append(generateDepthString(depth) + "null\n");
            return;
        }

        res.append(generateDepthString(depth) + node.e + "\n");
        generateBSTString(node.left,depth+1,res);
        generateBSTString(node.right,depth+1,res);
    }

    private String generateDepthString(int depth) {
        StringBuilder res = new StringBuilder();
        for(int i =0; i < depth; i++ )
            res.append("--");
        return res.toString();
    }
}

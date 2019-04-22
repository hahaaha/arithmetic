import java.util.TreeMap;

public class Trie {
    private class Node{
        public boolean isWord;
        public TreeMap<Character,Node> next;

        public Node(boolean isWord) {
            this.isWord = isWord;
            next = new TreeMap<>();
        }

        public Node() {
            this(false);
        }
    }


    private Node root;
    private int size;

    public Trie() {
        root = new Node();
        size = 0;
    }

    // 获得trie中存储的单词数量
    public int getSize() {
        return size;
    }

//     向trie中添加一个新的单词word
    public void add(String word) {
        Node cur = root;
        for(int i =0; i < word.length(); i++) {
            char c = word.charAt(i);
            if(cur.next.get(c) == null)
                cur.next.put(c, new Node());
            cur = cur.next.get(c);
        }
        if(!cur.isWord) {
            cur.isWord = true;
            size ++;
        }
    }

//    public void add(String word) {
//        root = add(root,word,0);
//    }
//
//    private Node add(Node node,String word,int index) {
//        if(index <0 || index >= word.length())
//            throw new IllegalArgumentException("index error");
//        Node cur = node;
//        char c = word.charAt(index);
//        if(cur.next.get(c) == null)
//            cur.next.put(c,new Node());
//        cur = cur.next.get(c);
//        if(index == word.length() -1){
//            cur.isWord = true;
//            size ++;
//            return node;
//        }
//
//        node = add(node,word,index + 1);
//        return node;
//    }

    // 查询单词word是否在trie中
    public boolean contains(String word) {
        Node cur = root;
        for(int i =0; i < word.length(); i++) {
            char c = word.charAt(i);
            if(cur.next.get(c) == null)
                return false;
            cur = cur.next.get(c);
        }
        return cur.isWord;
    }
}

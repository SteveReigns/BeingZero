import java.util.*;

public class Trie {
    private Node root;

    public Trie() {
        root = new Node();
    }

    public void insert(String word) {
        Node node = root;
        for (char ch : word.toCharArray()) {
            int ind = ch - 'a';
            if (node.ch[ind] == null) {
                node.ch[ind] = new Node();
            }
            node = node.ch[ind];
            node.wc++;
        }
        node.ended = true;
    }

    public boolean search(String word) {
        Node node = root;
        for (char ch : word.toCharArray()) {
            int ind = ch - 'a';
            if (node.ch[ind] == null) return false;
            node = node.ch[ind];
        }
        return node.ended;
    }

    void help(Node root, List<String> list, String word) {
        if (root.ended) {
            list.add(word);
        }
        for (int i = 0; i < 26; i++) {
            if (root.ch[i] != null) {
                char ch = (char) (i + 'a');
                help(root.ch[i], list, word + ch);
            }
        }
    }

    public List<String> getAll() {
        List<String> list = new ArrayList<>();
        help(root, list, "");
        return list;
    }

    public List<String> prefix(String pre) {
        List<String> list = new ArrayList<>();
        Node node = root;
        for (char ch : pre.toCharArray()) {
            int ind = ch - 'a';
            if (node.ch[ind] == null) return list;
            node = node.ch[ind];
        }
        help(node, list, pre);
        return list;
    }

    class Node {
        Node[] ch = new Node[26];
        boolean ended = false;
        int wc = 0;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
    
        trie.insert("ace");
        trie.insert("acer");
        trie.insert("abacus");
        trie.insert("bag");
        trie.insert("back");
    
        System.out.println("search 'ace': " + trie.search("ace"));
        System.out.println("search 'acer': " + trie.search("acer"));
        System.out.println("search 'abacus': " + trie.search("abacus"));
        System.out.println("search 'bag': " + trie.search("bag"));
        System.out.println("search 'back': " + trie.search("back"));
    
        System.out.println("\n all words in trie:");
        for (String wd : trie.getAll()) {
            System.out.println(wd);
        }
    
        System.out.println("\n words with prefix 'ba':");
        for (String wd : trie.prefix("ba")) {
            System.out.println(wd);
        }
    }
    
}

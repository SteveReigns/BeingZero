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
        Scanner s = new Scanner(System.in);
        Trie trie = new Trie();

        while (true) {
            System.out.println("\nchoose operation:");
            System.out.println("1. insert a word");
            System.out.println("2. search a word");
            System.out.println("3. get all words");
            System.out.println("4. get words with prefix");
            System.out.println("5. exit");
            System.out.print("enter your choice: ");
            int choice = s.nextInt();
            s.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("enter word to insert: ");
                    String word = s.nextLine().toLowerCase();
                    trie.insert(word);
                    System.out.println("inserted.");
                    break;

                case 2:
                    System.out.print("enter word to search: ");
                    String toSearch = s.nextLine().toLowerCase();
                    System.out.println("found? " + trie.search(toSearch));
                    break;

                case 3:
                    List<String> allWords = trie.getAll();
                    System.out.println("all words: " + allWords);
                    break;

                case 4:
                    System.out.print("enter prefix: ");
                    String prefix = s.nextLine().toLowerCase();
                    List<String> prefixWords = trie.prefix(prefix);
                    System.out.println("words with prefix '" + prefix + "': " + prefixWords);
                    break;

                case 5:
                    System.out.println("exiting...");
                    s.close();
                    return;

                default:
                    System.out.println("invalid choice. try again.");
            }
        }
    }
}

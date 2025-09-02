package trie;

public class TrieMain {
    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("frodo");
        trie.insert("front");
        trie.insert("frost");
        trie.insert("frozen");
        trie.insert("frame");
        trie.insert("kakao");

        trie.printTrie();
        System.out.println(trie.find("fro??"));
//        System.out.println(trie.search("replay"));
//        System.out.println(trie.search("repl"));
    }

}

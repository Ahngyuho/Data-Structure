package trie;

import java.util.Map;

public class Trie {

    private TrieNode root;

    public Trie() {
        root = new TrieNode(null);
    }

    //단어를 입력받아서 반환하는 건 없는 insert 기능
    public void insert(String word) {
        int len = word.length();
        //루트에서부터 시작!
        TrieNode cur = root;
        for(int i = 0; i < len; i++) {
            if(cur.getChildren().containsKey(word.charAt(i))){
                cur = cur.getChildren().get(word.charAt(i));
            }else{
                TrieNode newNode = new TrieNode(word.charAt(i));
                newNode.setCurCnt(newNode.getCurCnt() + 1);
                cur.getChildren().put(word.charAt(i), newNode);
                cur = newNode;
            }

        }

        cur.setLeaf(true);
        cur.setLeafCnt(cur.getLeafCnt() + 1);
    }

    //탐색
    public int search(String word) {
        int len = word.length();
        int containsCharCnt = 0;

        TrieNode cur = root;

        for(int i = 0; i < len; i++) {
            if(cur.getChildren().containsKey(word.charAt(i))){
                cur = cur.getChildren().get(word.charAt(i));
                containsCharCnt++;
            }
        }

        if(containsCharCnt == len){
            return cur.getLeafCnt();
        }else{
            return 0;
        }

    }

    public int find(String word) {
        int len = word.length();
        int containsCharCnt = 0;
        TrieNode cur = root;
        int ans = -1;

        if(word.charAt(0) != '?'){
            for(int i = 0; i < len; i++) {
                if(word.charAt(i) == '?'){
                    if(cur == null) break;
                    cur = cur.getChildren().get(word.charAt(i));
                    containsCharCnt++;
                    continue;
                }
                if(cur.getChildren().containsKey(word.charAt(i))){
                    ans = cur.getCurCnt();
                    cur = cur.getChildren().get(word.charAt(i));
                    containsCharCnt++;
                } else if(!cur.getChildren().containsKey(word.charAt(i))){
                    ans = -1;
                    break;
                }
            }
        }else{

            for(int i = 0; i < len; i++) {
                if(word.charAt(i) == '?'){
                    if(cur == null) break;
                    cur = cur.getChildren().get(word.charAt(i));
                    containsCharCnt++;
                    continue;
                }

                if(cur.getChildren().containsKey(word.charAt(i))){
                    ans = cur.getCurCnt();
                    cur = cur.getChildren().get(word.charAt(i));
                    containsCharCnt++;
                } else{
                    ans = -1;
                    break;
                }
            }
        }

        if(containsCharCnt == len){
            return ans;
        }else{
            return -1;
        }
    }

    public void printTrie() {
        printTrie(this.root, "", true);
    }

    private void printTrie(TrieNode currentNode, String prefix, boolean isTail) {
        if (currentNode.getData() != null) {
            System.out.println(
                    prefix + (isTail ? "└── " : "├── ") + currentNode.getData() +
                            (currentNode.isLeaf() ? " (end:"+currentNode.getLeafCnt()+")" : ""));
        }
        Map<Character, TrieNode> childNodes = currentNode.getChildren();
        int size = childNodes.size();
        int i = 0;
        for (Map.Entry<Character, TrieNode> entry : childNodes.entrySet()) {
            i++;
            printTrie(entry.getValue(), prefix + (isTail ? "    " : "│   "), i == size);
        }
    }
}

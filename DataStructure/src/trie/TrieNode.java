package trie;

import java.util.HashMap;
import java.util.Map;

public class TrieNode {
    private Character data;
    private Map<Character,TrieNode> children;
    private boolean isLeaf;
    private int leafCnt;
    private int curCnt;

    public TrieNode() {}

    public TrieNode(Character data) {
        this.data = data;
        this.children = new HashMap<Character,TrieNode>();
        this.isLeaf = false;
    }

    public Character getData() {
        return data;
    }

    public void setData(Character data) {
        this.data = data;
    }

    public Map<Character, TrieNode> getChildren() {
        return children;
    }

    public void setChildren(Map<Character, TrieNode> children) {
        this.children = children;
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    public void setLeaf(boolean leaf) {
        isLeaf = leaf;
    }

    public int getLeafCnt() {
        return leafCnt;
    }

    public void setLeafCnt(int leafCnt) {
        this.leafCnt = leafCnt;
    }

    public int getCurCnt() {
        return curCnt;
    }

    public void setCurCnt(int curCnt) {
        this.curCnt = curCnt;
    }
}

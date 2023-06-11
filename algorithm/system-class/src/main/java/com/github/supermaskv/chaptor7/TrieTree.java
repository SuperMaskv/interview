package com.github.supermaskv.chaptor7;

import java.util.HashMap;
import java.util.Map;

/**
 * @author supermaskv
 * <p>
 * 前缀树
 */
public class TrieTree {
    public static void main(String[] args) {
        TrieTree tree = new TrieTree();
        tree.insert("abc");
        tree.insert("absk");
        System.out.println();
    }

    private static class Node {
        int pass = 0;
        int end = 0;
        Map<Integer, Node> paths = new HashMap<>();
    }

    Node root = new Node();

    public void insert(String word) {
        if (word == null) return;
        Node p = root;
        p.pass++;
        int pathIdx;
        char[] chars = word.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            pathIdx = chars[i];
            if (!p.paths.containsKey(pathIdx)) {
                p.paths.put(pathIdx, new Node());
            }
            p = p.paths.get(pathIdx);
            p.pass++;
        }
        p.end++;
    }

    public void delete(String word) {

    }

    public int count(String word) {

        return 0;
    }

    public int countPrefix(String prefix) {
        return 0;
    }
}

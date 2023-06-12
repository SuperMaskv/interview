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
        tree.insert("abc");
        tree.insert("absk");
        tree.delete("abc");
        tree.delete("ab");
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
        if (count(word) == 0) return;
        char[] chars = word.toCharArray();
        Node p = root;
        p.pass--;
        int pathIdx;
        for (char c : chars) {
            pathIdx = c;
            if (--p.paths.get(pathIdx).pass == 0) {
                p.paths.remove(pathIdx);
                return;
            }
            p = p.paths.get(pathIdx);
        }
        p.end--;
    }

    public int count(String word) {
        if (word == null) return 0;
        char[] chars = word.toCharArray();
        Node p = root;
        int pathIdx;
        for (char c : chars) {
            pathIdx = c;
            if (!p.paths.containsKey(pathIdx)) return 0;
            p = p.paths.get(pathIdx);
        }
        return p.end;
    }

    public int countPrefix(String prefix) {
        if (prefix == null) return 0;
        char[] chars = prefix.toCharArray();
        Node p = root;
        int pathIdx;
        for (char c : chars) {
            pathIdx = c;
            if (!p.paths.containsKey(pathIdx)) return 0;
            p = p.paths.get(pathIdx);
        }
        return p.pass;
    }
}

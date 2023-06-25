package com.github.supermaskv.chaptor11;

import java.util.List;

/**
 * @author supermaskv
 * @see <a href="https://leetcode.cn/problems/encode-n-ary-tree-to-binary-tree"/>将N叉树编码为二叉树</a>
 */
public class NAry2BTree {
    public static void main(String[] args) {

    }

    private static class Node {
        int val;
        List<Node> children;

        Node() {
        }

        Node(int _val) {
            val = _val;
        }

        Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    private static class Codec {
        public TreeNode encode(Node root) {
            return null;
        }

        public Node decode(TreeNode root) {
            return null;
        }
    }
}

package com.github.supermaskv.chaptor11;

/**
 * @author supermaskv
 * <p>
 * 递归遍历二叉树
 */
public class RecursiveBTreeTraversal {
    private static class Node {
        int val;
        Node left;
        Node right;
    }

    private static void traverse(Node root) {
        if (root == null) return;
        // 先序遍历
        traverse(root.left);
        // 中序遍历
        traverse(root.right);
        // 后序遍历
    }
}

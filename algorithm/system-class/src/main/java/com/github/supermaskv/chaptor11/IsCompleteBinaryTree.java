package com.github.supermaskv.chaptor11;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author supermaskv
 * @see <a href="https://leetcode.cn/problems/check-completeness-of-a-binary-tree/">958. 二叉树的完全性检验</a>
 */
public class IsCompleteBinaryTree {
    public static void main(String[] args) {
        TreeNode root = new TreeNode();
        root.left = new TreeNode();
        root.right = new TreeNode();
        root.left.left = new TreeNode();
        root.right.left = new TreeNode();
        root.right.right = new TreeNode();
        System.out.println(isCompleteTree(root));
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    private static boolean isCompleteTree(TreeNode root) {
        if (root == null) return false;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        boolean leaf = false;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            TreeNode left = node.left;
            TreeNode right = node.right;
            if ((leaf && (left != null || right !=null)) || (left ==null && right != null)) return false;
            if (left != null) queue.offer(left);
            if (right != null) queue.offer(right);
            if (left == null || right == null) leaf = true;
        }
        return true;
    }
}

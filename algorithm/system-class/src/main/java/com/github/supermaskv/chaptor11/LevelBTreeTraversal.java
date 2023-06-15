package com.github.supermaskv.chaptor11;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author supermaskv
 * <p>
 * 二叉树的层级遍历
 */
public class LevelBTreeTraversal {
    public static void main(String[] args) {
        Node root = new Node(1);
        root.right = new Node(2);
        root.right.left = new Node(3);
        levelTraverse(root);
    }

    private static class Node {
        int val;
        Node left;
        Node right;

        public Node() {
        }

        public Node(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }
    }

    private static void levelTraverse(Node root) {
        if (root == null) return;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            System.out.println(node.val);
            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }
    }
}

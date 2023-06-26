package com.github.supermaskv.chaptor11;

/**
 * @author supermaskv
 * 格式化打印二叉树
 */
public class PrintBinaryTree {
    public static void main(String[] args) {
        Node root = new Node(0);
        root.left = new Node(1666);
        root.right = new Node(2);
        root.left.left = new Node(3);
        root.left.right = new Node(555);
//        root.right.left = new Node(5);
        root.right.right = new Node(6);
        print(root, 7);
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

    private static void print(Node root, int len) {
        if (root == null) return;
        inorderTraversal(root, 1, getLevel(len));
    }

    private static void inorderTraversal(Node root, int level, int maxLevel) {
        if (root == null) {
            if (level <= maxLevel) System.out.println(getSpace(level - 1) + "null");
            return;
        }
        inorderTraversal(root.left, level + 1, maxLevel);
        System.out.println(getSpace(level - 1) + root.val);
        inorderTraversal(root.right, level + 1, maxLevel);
    }

    private static String getSpace(int num) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < num; i++) {
            sb.append("\t");
        }
        return sb.toString();
    }

    private static int getLevel(int cur) {
        return ((int) Math.ceil(Math.log(cur + 1) / Math.log(2)));
    }
}

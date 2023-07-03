package com.github.supermaskv.chaptor11;

/**
 * @author supermaskv
 * 判断是否是平衡二叉树
 * 1.左子树和右子树均为平衡二叉树
 * 2.左子树和右子树的高度差不超过1
 */
public class IsBalancedBinaryTree {
    public static void main(String[] args) {
        Node root = new Node(0);
        root.left = new Node(1);
        root.right = new Node(2);
        root.left.left = new Node(3);
        root.left.right = new Node(4);
        root.left.left.left = new Node(5);
        System.out.println(isBalanced(root));
    }

    private static class Node {
        int val;
        Node left;
        Node right;

        public Node(int val) {
            this.val = val;
        }
    }

    private static class Info {
        boolean isBalanced;
        int height;

        public Info(boolean isBalanced, int height) {
            this.isBalanced = isBalanced;
            this.height = height;
        }
    }

    private static boolean isBalanced(Node root) {
        return func(root).isBalanced;
    }

    private static Info func(Node node) {
        if (node == null) return new Info(true, 0);
        Info leftInfo = func(node.left);
        Info rightInfo = func(node.right);
        boolean isBalanced = leftInfo.isBalanced && rightInfo.isBalanced;
        if (Math.abs(leftInfo.height - rightInfo.height) > 1) isBalanced = false;
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        return new Info(isBalanced, height);
    }
}

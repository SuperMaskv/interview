package com.github.supermaskv.chaptor11;

/**
 * @author supermaskv
 * <p>
 * 判断是否是满二叉树
 */
public class IsFullBinaryTree {
    public static void main(String[] args) {
        Node root = new Node(0);
        root.left = new Node(1);
        root.right = new Node(2);
        root.left.left = new Node(3);
        root.left.right = new Node(4);
        root.right.left = new Node(5);
        root.right.right = new Node(6);
        System.out.println(isFull(root));
        root.right.left = null;
        System.out.println(isFull(root));
    }

    private static class Node {
        int val;
        Node left;
        Node right;

        public Node(int val) {
            this.val = val;
        }
    }

    private static boolean isFull(Node root) {
        Info info = func(root);
        return (1 << info.height) - 1 == info.nodes;
    }

    private static class Info {
        int height;
        int nodes;

        public Info(int height, int nodes) {
            this.height = height;
            this.nodes = nodes;
        }
    }

    private static Info func(Node node) {
        if (node == null) return new Info(0, 0);
        Info leftInfo = func(node.left);
        Info rightInfo = func(node.right);
        return new Info(
                Math.max(leftInfo.height, rightInfo.height) + 1,
                leftInfo.nodes + rightInfo.nodes + 1
        );
    }
}

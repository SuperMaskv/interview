package com.github.supermaskv.chaptor11;

/**
 * @author supermaskv
 * <p>
 * 找到二叉树上最远两点间的距离
 */
public class MaxDistance {
    public static void main(String[] args) {
        Node root = new Node(0);
        root.left = new Node(1);
        root.left.left = new Node(2);
        root.left.left.left = new Node(3);
        root.left.left.right = new Node(4);
        root.left.left.right.left = new Node(5);
        System.out.println(getMaxDistance(root));
    }

    private static class Node {
        int val;
        Node left;
        Node right;

        public Node(int val) {
            this.val = val;
        }
    }

    private static int getMaxDistance(Node root) {
        return func(root).maxDistance;
    }

    private static class Info {
        int maxDistance;
        int height;

        public Info(int maxDistance, int height) {
            this.maxDistance = maxDistance;
            this.height = height;
        }
    }

    private static Info func(Node node) {
        if (node == null) return new Info(0, 0);
        Info leftInfo = func(node.left);
        Info rightInfo = func(node.right);
        int maxDistance = Math.max(Math.max(leftInfo.maxDistance, rightInfo.maxDistance), leftInfo.height + rightInfo.height);
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        return new Info(maxDistance, height);
    }
}

package com.github.supermaskv.chaptor11;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author supermaskv
 * 计算一棵二叉树的最大宽度，null节点不计入宽度
 */
public class TreeMaxWidth {
    public static void main(String[] args) {
        Node root = new Node(0);
        root.left = new Node(1);
        root.right = new Node(2);
        root.left.left = new Node(3);
        root.left.right = new Node(4);
        root.right.left = new Node(5);
        root.left.right.left = new Node(6);
        root.left.right.right = new Node(7);
        System.out.println(getMaxWidth(root));
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

    private static int getMaxWidth(Node root) {
        if (root == null) return 0;
        Queue<Node> queue = new LinkedList<>();
        Queue<Node> counter = new LinkedList<>();
        queue.add(root);
        int ans = 1;
        while (!queue.isEmpty()) {
            while (!queue.isEmpty()) {
                Node node = queue.poll();
                if (node.left != null) counter.add(node.left);
                if (node.right != null) counter.add(node.right);
            }
            ans = Math.max(ans, counter.size());
            Queue<Node> temp = counter;
            counter = queue;
            queue = temp;
        }
        return ans;
    }
}

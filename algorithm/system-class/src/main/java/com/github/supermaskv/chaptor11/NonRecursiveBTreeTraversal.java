package com.github.supermaskv.chaptor11;

import java.util.Stack;

/**
 * @author supermaskv
 * <p>
 * 非递归实现前中后序遍历二叉树
 */
public class NonRecursiveBTreeTraversal {
    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);

        // pre
        preTraversal(root);
        System.out.println("==================");
        // post
        postTraversal(root);
        System.out.println("==================");
        // inorder
        inorderTraversal(root);
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

    private static void preTraversal(Node root) {
        if (root == null) return;
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            System.out.println(node.val);
            if (node.right != null) stack.push(node.right);
            if (node.left != null) stack.push(node.left);
        }
    }

    private static void postTraversal(Node root) {
        if (root == null) return;
        Stack<Node> stack = new Stack<>();
        Stack<Node> printStack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            printStack.push(node);
            if (node.left != null) stack.push(node.left);
            if (node.right != null) stack.push(node.right);
        }
        while (!printStack.isEmpty()) System.out.println(printStack.pop().val);
    }

    private static void inorderTraversal(Node root) {
        if (root == null) return;
        Stack<Node> stack = new Stack<>();
        Node cur = root;
        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                Node node = stack.pop();
                System.out.println(node.val);
                cur = node.right;
            }
        }
    }
}

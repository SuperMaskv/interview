package com.github.supermaskv.chaptor11;

import java.util.ArrayList;
import java.util.List;

/**
 * @author supermaskv
 * 判断是否是二叉搜索树
 */
public class IsBinarySearchTree {
    public static void main(String[] args) {
        Node root = new Node(3);
        root.left = new Node(2);
        root.right = new Node(4);
        root.left.left = new Node(1);
        root.right.right = new Node(5);
        System.out.println(isBinarySearchTree(root));
    }

    private static class Node{
        int val;
        Node left;
        Node right;

        public Node(int val) {
            this.val = val;
        }
    }

    private static boolean isBinarySearchTree(Node root) {
        if (root == null) return false;
        List<Node> inorder = new ArrayList<>();
        traverseInorder(root, inorder);
        for (int i = 1; i < inorder.size(); i++) {
            if (inorder.get(i).val <= inorder.get(i - 1).val) return false;
        }
        return true;
    }

    private static void traverseInorder(Node node, List<Node> inorder) {
        if (node == null) return;
        traverseInorder(node.left, inorder);
        inorder.add(node);
        traverseInorder(node.right, inorder);
    }
}

package com.github.supermaskv.chaptor11;

/**
 * @author supermaksv
 * 找到二叉树中序遍历的下一个节点
 */
public class NextInorderNode {
    public static void main(String[] args) {
        Node root = new Node(0);
        Node node1 = new Node(1);
        node1.parent = root;
        root.left = node1;
        Node node2 = new Node(2);
        node2.parent = root;
        root.right = node2;
        Node node3 = new Node(3);
        node3.parent = node1;
        node1.left = node3;
        Node node4 = new Node(4);
        node4.parent = node2;
        node2.left = node4;
        System.out.println(getNextInorderNode(node1).val);
        System.out.println(getNextInorderNode(node2));
        System.out.println(getNextInorderNode(node3).val);
        System.out.println(getNextInorderNode(node4).val);
    }

    private static class Node {
        int val;
        Node left;
        Node right;
        Node parent;

        public Node() {
        }

        public Node(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
            this.parent = null;
        }
    }

    private static Node getNextInorderNode(Node node) {
        if (node == null) return null;
        if (node.right != null) {
            Node cur = node.right;
            while (cur.left != null) cur = cur.left;
            return cur;
        } else {
            if (node.parent.right == node) return null;
            return node.parent;
        }
    }
}

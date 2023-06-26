package com.github.supermaskv.chaptor11;

import java.util.ArrayList;
import java.util.List;

/**
 * @author supermaskv
 * @see <a href="https://leetcode.cn/problems/encode-n-ary-tree-to-binary-tree"/>将N叉树编码为二叉树</a>
 */
public class NAry2BTree {
    public static void main(String[] args) {
        Node nAryRoot = new Node(0);
        nAryRoot.children = new ArrayList<>();
        nAryRoot.children.add(new Node(1));
        nAryRoot.children.add(new Node(2));
        nAryRoot.children.add(new Node(3));
        nAryRoot.children.get(0).children = new ArrayList<>();
        nAryRoot.children.get(0).children.add(new Node(4));
        nAryRoot.children.get(0).children.add(new Node(5));
        nAryRoot.children.get(0).children.add(new Node(6));
        Codec codec = new Codec();
        TreeNode btreeRoot = codec.encode(nAryRoot);
        Node newNAryRoot = codec.decode(btreeRoot);
        System.out.println();
    }

    private static class Node {
        int val;
        List<Node> children;

        Node() {
        }

        Node(int _val) {
            val = _val;
        }

        Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    private static class Codec {
        public TreeNode encode(Node root) {
            if (root == null) return null;
            TreeNode btreeRoot = new TreeNode(root.val);
            btreeRoot.left = en(root);
            return btreeRoot;
        }

        private TreeNode en(Node root) {
            if (root == null || root.children == null) return null;
            Node nAryHead = root.children.get(0);
            TreeNode head = new TreeNode(nAryHead.val);
            head.left = en(nAryHead);
            TreeNode p = head;
            for (int i = 1; i < root.children.size(); i++) {
                Node node = root.children.get(i);
                TreeNode treeNode = new TreeNode(node.val);
                treeNode.left = en(node);
                p.right = treeNode;
                p = treeNode;
            }
            return head;
        }

        public Node decode(TreeNode root) {
            if (root == null) return null;
            Node ans = new Node(root.val);
            ans.children = de(root);
            return ans;
        }

        private List<Node> de(TreeNode root) {
            if (root == null || root.left == null) return null;
            List<Node> children = new ArrayList<>();
            TreeNode btreeHead = root.left;
            while (btreeHead != null) {
                Node node = new Node(btreeHead.val);
                node.children = de(btreeHead);
                children.add(node);
                btreeHead = btreeHead.right;
            }
            return children;
        }
    }
}

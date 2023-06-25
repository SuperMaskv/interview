package com.github.supermaskv.chaptor11;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author supermaskv
 * <p>
 * 序列化与反序列化二叉树
 */
public class SerializeAndReconstructBTree {
    public static void main(String[] args) {
        Node root = new Node(0);
        root.left = new Node(1);
        root.right = new Node(2);
        root.left.right = new Node(3);
        root.right.left = new Node(4);
        Queue<Integer> preSerial = preSerial(root);
        System.out.println(preSerial.toString());
        System.out.println(inSerial(root).toString());
        Queue<Integer> posSerial = posSerial(root);
        System.out.println(posSerial.toString());
        Node pre = buildFromPreSerial(preSerial);
        Node pos = buildFromPosSerial(posSerial);
        Queue<Integer> levelSerial = levelSerial(root);
        System.out.println(levelSerial.toString());
        Node level = buildFromLevelSerial(levelSerial);
        System.out.println();
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

    private static void traverseBTree(Node root, Queue<Integer> ans, int position) {
        if (root == null) {
            ans.add(null);
        } else {
            if (position == 0) ans.add(root.val);
            traverseBTree(root.left, ans, position);
            if (position == 1) ans.add(root.val);
            traverseBTree(root.right, ans, position);
            if (position == 2) ans.add(root.val);
        }
    }

    private static Queue<Integer> preSerial(Node root) {
        if (root == null) return null;
        Queue<Integer> ans = new LinkedList<>();
        traverseBTree(root, ans, 0);
        return ans;
    }

    private static Queue<Integer> inSerial(Node root) {
        if (root == null) return null;
        Queue<Integer> ans = new LinkedList<>();
        traverseBTree(root, ans, 1);
        return ans;
    }

    private static Queue<Integer> posSerial(Node root) {
        if (root == null) return null;
        Queue<Integer> ans = new LinkedList<>();
        traverseBTree(root, ans, 2);
        return ans;
    }

    private static Node buildFromPreSerial(Queue<Integer> preSerial) {
        if (preSerial == null || preSerial.isEmpty()) return null;
        return doPreBuild(preSerial);
    }

    private static Node doPreBuild(Queue<Integer> preSerial) {
        Integer value = preSerial.poll();
        if (value == null) return null;
        Node node = new Node(value);
        node.left = doPreBuild(preSerial);
        node.right = doPreBuild(preSerial);
        return node;
    }

    private static Node buildFromPosSerial(Queue<Integer> posSerial) {
        if (posSerial == null || posSerial.isEmpty()) return null;
        Stack<Integer> stack = new Stack<>();
        while (!posSerial.isEmpty()) {
            stack.push(posSerial.poll());
        }
        return doPosBuild(stack);
    }

    private static Node doPosBuild(Stack<Integer> posSerial) {
        Integer value = posSerial.pop();
        if (value == null) return null;
        Node node = new Node(value);
        node.right = doPosBuild(posSerial);
        node.left = doPosBuild(posSerial);
        return node;
    }

    private static Queue<Integer> levelSerial(Node root) {
        if (root == null) return null;
        Queue<Integer> ans = new LinkedList<>();
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (node == null) ans.offer(null);
            else {
                ans.offer(node.val);
                queue.offer(node.left);
                queue.offer(node.right);
            }
        }
        return ans;
    }

    private static Node buildFromLevelSerial(Queue<Integer> levelSerial) {
        if (levelSerial == null || levelSerial.isEmpty()) return null;
        Queue<Node> queue = new LinkedList<>();
        Integer root = levelSerial.poll();
        if (root == null) return null;
        Node rootNode = new Node(root);
        queue.offer(rootNode);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (node == null) continue;
            Integer left = levelSerial.poll();
            Integer right = levelSerial.poll();
            node.left = left == null ? null : new Node(left);
            node.right = right == null ? null : new Node(right);
            queue.offer(node.left);
            queue.offer(node.right);
        }
        return rootNode;
    }
}

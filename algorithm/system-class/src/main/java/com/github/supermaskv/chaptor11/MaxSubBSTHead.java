package com.github.supermaskv.chaptor11;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author supermaskv
 * <p>
 * 找到二叉树中最大的二叉搜索子树，并返回该子树的根节点
 */
public class MaxSubBSTHead {
    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<>();
        queue.addAll(Arrays.asList(11, 4, 9, 2, null, 8, 10, 1, 3, null, null, null, null, null, null));
        Node root = buildFromLevelSerial(queue);
        System.out.println(getMaxSubBSTHead(root).val);
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

    private static class Node {
        int val;
        Node left;
        Node right;

        public Node(int val) {
            this.val = val;
        }
    }

    private static Node getMaxSubBSTHead(Node root) {
        if (root == null) return null;
        return func(root).maxSubBSTHead;
    }

    private static class Info {
        int max;
        int min;
        int maxSubBSTSize;
        Node maxSubBSTHead;

        public Info(int max, int min, int maxSubBSTSize, Node maxSubBSTHead) {
            this.max = max;
            this.min = min;
            this.maxSubBSTSize = maxSubBSTSize;
            this.maxSubBSTHead = maxSubBSTHead;
        }
    }

    private static Info func(Node node) {
        if (node == null) return null;
        Info leftInfo = func(node.left);
        Info rightInfo = func(node.right);
        int max = node.val;
        int min = node.val;
        if (leftInfo != null) {
            max = Math.max(max, leftInfo.max);
            min = Math.min(min, leftInfo.min);
        }
        if (rightInfo != null) {
            max = Math.max(max, rightInfo.max);
            min = Math.min(min, rightInfo.min);
        }
        boolean isLeftBST = leftInfo == null || leftInfo.maxSubBSTHead == node.left;
        boolean isRightBST = rightInfo == null || rightInfo.maxSubBSTHead == node.right;
        int maxSubBSTSize;
        Node maxSubBSTHead;
        if (
                isLeftBST && isRightBST
                        && (leftInfo == null || leftInfo.max < node.val)
                        && (rightInfo == null || rightInfo.min > node.val)
        ) {
            maxSubBSTSize = (leftInfo == null ? 0 : leftInfo.maxSubBSTSize)
                    + (rightInfo == null ? 0 : rightInfo.maxSubBSTSize) + 1;
            maxSubBSTHead = node;
        } else {
            maxSubBSTSize = Math.max(
                    (leftInfo == null ? 0 : leftInfo.maxSubBSTSize)
                    , (rightInfo == null ? 0 : rightInfo.maxSubBSTSize)
            );
            if (leftInfo == null) maxSubBSTHead = rightInfo.maxSubBSTHead;
            else if (rightInfo == null) maxSubBSTHead = leftInfo.maxSubBSTHead;
            else
                maxSubBSTHead = leftInfo.maxSubBSTSize == maxSubBSTSize ? leftInfo.maxSubBSTHead : rightInfo.maxSubBSTHead;
        }
        return new Info(max, min, maxSubBSTSize, maxSubBSTHead);
    }
}

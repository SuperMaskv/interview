package com.github.supermaskv.chaptor10;

import java.util.HashMap;
import java.util.Map;

/**
 * @author supermaskv
 * @see <a href="https://leetcode.cn/problems/copy-list-with-random-pointer/">138. 复制带随机指针的链表</a>
 */
public class CopyRandomList {
    public static void main(String[] args) {
        int[][] arr = new int[][]{
                new int[]{1, 1},
                new int[]{2, 1}
        };
        Node list = createRandomLinkedList(arr);
        System.out.println();
    }

    private static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public Node copyRandomList(Node head) {
        if (head == null) return null;
        Node cur = head;
        Map<Node, Node> map = new HashMap<>();
        while (cur != null) {
            map.put(cur, new Node(cur.val));
            cur = cur.next;
        }
        cur = head;
        Node newCur = map.get(cur);
        while (cur != null) {
            newCur.next = map.get(cur.next);
            newCur.random = map.get(cur.random);
            cur = cur.next;
            newCur = newCur.next;
        }
        return map.get(head);
    }

    public Node copyRandomList2(Node head) {
        if (head == null) return null;
        Node p = head;
        while (p != null) {
            Node tempNode = new Node(p.val);
            tempNode.next = p.next;
            p.next = tempNode;
            p = tempNode.next;
        }
        p = head;
        while (p != null) {
            if (p.random != null) {
                p.next.random = p.random.next;
            } else {
                p.next.random = null;
            }
            p = p.next.next;
        }
        p = head;
        Node ans = head.next;
        Node pAns = ans;
        while (p != null) {
            p.next = p.next.next;
            if (pAns.next != null) pAns.next = pAns.next.next;
            p = p.next;
            pAns = pAns.next;
        }
        return ans;
    }

    private static Node createRandomLinkedList(int[][] arr) {
        if (arr == null || arr.length == 0) return null;
        Node head = new Node(arr[0][0]);
        Node p = head;
        Map<Integer, Node> indexMap = new HashMap<>();
        indexMap.put(0, head);
        for (int i = 1; i < arr.length; i++) {
            p.next = new Node(arr[i][0]);
            indexMap.put(i, p.next);
            p = p.next;
        }
        for (int i = 0; i < arr.length; i++) {
            indexMap.get(i).random = indexMap.get(arr[i][1]);
        }
        return indexMap.get(0);
    }
}

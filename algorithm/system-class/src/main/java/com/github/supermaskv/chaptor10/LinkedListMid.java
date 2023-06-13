package com.github.supermaskv.chaptor10;

import java.util.stream.IntStream;

/**
 * @author supermaskv
 * <p>
 * 找到链表中间位置
 */
public class LinkedListMid {
    public static void main(String[] args) {
        int[] arr = IntStream.range(0, 6).toArray();
        Node head = createLinkedList(arr);
        System.out.println(findPreMidOrPreDownMid(head).val);
    }

    private static class Node {
        int val;
        Node next;

        public Node() {
        }

        public Node(int val) {
            this.val = val;
        }

        public Node(Node next) {
            this.next = next;
        }
    }

    private static Node createLinkedList(int[] arr) {
        if (arr == null || arr.length == 0) return null;
        Node head = new Node(arr[0]);
        Node p = head;
        for (int i = 1; i < arr.length; i++) {
            Node node = new Node(arr[i]);
            p.next = node;
            p = p.next;
        }
        return head;
    }

    /**
     * 输入单向链表的头结点，如果链表长度为奇数，输出中间节点，如果链表长度为偶数，输出下标较小的中间节点
     * e.g.
     * input [0,1,2]
     * output 1
     * e.g.
     * input [0,1,2,3]
     * output 1
     *
     * @param head 链表头结点
     * @return 链表的中间节点
     */
    private static Node findMidOrUpMid(Node head) {
        if (head == null) return null;
        Node newHead = new Node(head);
        Node pSlow = newHead;
        Node pFast = newHead;
        while (pFast != null && pFast.next != null) {
            pFast = pFast.next.next;
            pSlow = pSlow.next;
        }
        return pSlow;
    }

    /**
     * 输入单向链表的头结点，如果链表长度为奇数，输出中间节点，如果链表长度为偶数，输出下标较大的中间节点
     * e.g.
     * input [0,1,2]
     * output 1
     * e.g.
     * input [0,1,2,3]
     * output 2
     *
     * @param head 链表头结点
     * @return 链表的中间节点
     */
    private static Node findMidOrDownMid(Node head) {
        if (head == null) return null;
        Node pSlow = head;
        Node pFast = head;
        while (pFast != null && pFast.next != null) {
            pFast = pFast.next.next;
            pSlow = pSlow.next;
        }
        return pSlow;
    }

    /**
     * 输入单向链表的头结点，如果链表长度为奇数，输出中间节点的前一节点，如果链表长度为偶数，输出下标较小的中间节点的前一节点
     * e.g.
     * input [0,1,2]
     * output 0
     * e.g.
     * input [0,1,2,3]
     * output 0
     *
     * @param head 链表头结点
     * @return 链表的中间节点的前一节点
     */
    private static Node findPreMidOrPreUpMid(Node head) {
        // 3 nodes at least
        if (head == null || head.next == null || head.next.next == null) return null;
        Node newHead = new Node(head);
        Node pSlow = newHead;
        Node pFast = newHead.next.next;
        while (pFast != null && pFast.next != null) {
            pFast = pFast.next.next;
            pSlow = pSlow.next;
        }
        return pSlow;
    }

    /**
     * 输入单向链表的头结点，如果链表长度为奇数，输出中间节点的前一节点，如果链表长度为偶数，输出下标较大的中间节点的前一节点
     * e.g.
     * input [0,1,2]
     * output 0
     * e.g.
     * input [0,1,2,3]
     * output 1
     *
     * @param head 链表头结点
     * @return 链表的中间节点的前一节点
     */
    private static Node findPreMidOrPreDownMid(Node head) {
        if (head == null || head.next == null || head.next.next == null) return null;
        Node pSlow = head;
        Node pFast = head;
        pFast = pFast.next.next;
        while (pFast != null && pFast.next != null) {
            pFast = pFast.next.next;
            pSlow = pSlow.next;
        }
        return pSlow;
    }
}

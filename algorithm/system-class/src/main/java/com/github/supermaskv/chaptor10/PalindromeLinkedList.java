package com.github.supermaskv.chaptor10;

import java.util.Stack;

/**
 * @author supermaskv
 * <p>
 * 判断一个链表是否为回文链表
 */
public class PalindromeLinkedList {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 2, 1, 2};
        Node head = createLinkedList(arr);
        System.out.println(isPalindrome3(head));
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
            p.next = new Node(arr[i]);
            p = p.next;
        }
        return head;
    }

    private static boolean isPalindrome(Node head) {
        // extra O(n) space
        if (head == null) return false;
        Stack<Node> stack = new Stack<>();
        Node p = head;
        while (p != null) {
            stack.push(p);
            p = p.next;
        }
        p = head;
        while (p != null) {
            if (stack.pop().val != p.val) return false;
            p = p.next;
        }
        return true;
    }

    private static boolean isPalindrome2(Node head) {
        // extra O(n/2) space
        if (head == null) return false;
        Node newHead = new Node(head);
        Node pSlow = newHead;
        Node pFast = newHead;
        while (pFast != null && pFast.next != null) {
            pFast = pFast.next.next;
            pSlow = pSlow.next;
        }
        pSlow = pSlow.next;
        Stack<Node> stack = new Stack<>();
        while (pSlow != null) {
            stack.push(pSlow);
            pSlow = pSlow.next;
        }
        Node p = head;
        while (!stack.isEmpty()) {
            if (p.val != stack.pop().val) return false;
            p = p.next;
        }
        return true;
    }

    private static boolean isPalindrome3(Node head) {
        // extra O(1) space
        if (head == null) return false;
        if (head.next == null) return true;
        // find mid
        Node newHead = new Node(head);
        Node pSlow = newHead;
        Node pFast = newHead;
        while (pFast != null && pFast.next != null) {
            pFast = pFast.next.next;
            pSlow = pSlow.next;
        }
        Node pMid = pSlow.next;

        // reverse the right part of linked list
        Node pPre = pMid;
        Node pPost = pPre.next;
        pPre.next = null;
        while (pPost != null) {
            Node tempNext = pPost.next;
            pPost.next = pPre;
            pPre = pPost;
            pPost = tempNext;
        }

        // check if two parts is equal
        Node pLeft = head;
        Node pRight = pPre;
        while (pLeft != null && pRight != null) {
            if (pLeft.val != pRight.val) return false;
            pLeft = pLeft.next;
            pRight = pRight.next;
        }

        // recover the right part of linked list
        pPost = pPre.next;
        pPre.next = null;
        while (pPost != null) {
            Node tempNext = pPost.next;
            pPost.next = pPre;
            pPre = pPost;
            pPost = tempNext;
        }
        return true;
    }
}

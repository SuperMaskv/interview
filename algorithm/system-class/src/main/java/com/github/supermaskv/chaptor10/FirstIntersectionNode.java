package com.github.supermaskv.chaptor10;

import java.util.HashMap;
import java.util.Map;

/**
 * @author supermaskv
 * <p>
 * 判断两个可能有环的单向链表是否相交，如果相交返回第一个相交的节点，反之返回null
 */
public class FirstIntersectionNode {
    public static void main(String[] args) {
        int[][] arr1 = new int[][]{
                new int[]{0, 1},
                new int[]{1, 2},
                new int[]{2, 3},
                new int[]{3, 4},
                new int[]{4, 5},
                new int[]{5, 6},
                new int[]{6, 3}
        };

        int[][] arr2 = new int[][]{
                new int[]{7, 8},
                new int[]{8, 9},
                new int[]{9, 7}
        };
        Node[] nodes = createLinkedList(arr1, arr2);
        Node head1 = nodes[0];
        Node head2 = nodes[1];
        Node ans = findFirstIntersectionNode(head1, head2);
        System.out.println();
    }

    private static class Node {
        int val;
        Node next;

        public Node() {
        }

        public Node(int val) {
            this.val = val;
            this.next = null;
        }
    }

    private static Node[] createLinkedList(int[][] arr1, int[][] arr2) {
        if (arr1 == null || arr2 == null) return null;
        Node head1 = new Node(arr1[0][0]);
        Node head2 = new Node(arr2[0][0]);
        Map<Integer, Node> indexMap = new HashMap<>();
        indexMap.put(arr1[0][0], head1);
        indexMap.put(arr2[0][0], head2);
        for (int i = 1; i < arr1.length; i++) {
            indexMap.put(arr1[i][0], new Node(arr1[i][0]));
        }
        for (int i = 1; i < arr2.length; i++) {
            indexMap.put(arr2[i][0], new Node(arr2[i][0]));
        }

        for (int i = 0; i < arr1.length; i++) {
            indexMap.get(arr1[i][0]).next = indexMap.getOrDefault(arr1[i][1], null);
        }
        for (int i = 0; i < arr2.length; i++) {
            indexMap.get(arr2[i][0]).next = indexMap.getOrDefault(arr2[i][1], null);
        }

        return new Node[]{head1, head2};
    }

    private static Node findFirstIntersectionNode(Node head1, Node head2) {
        if (head1 == null || head2 == null) return null;
        Node loopEntry1 = findLoopEntry(head1);
        Node loopEntry2 = findLoopEntry(head2);
        if (loopEntry1 == null && loopEntry2 == null) return noLoop(head1, head2);
        if (loopEntry1 != null && loopEntry2 != null) return bothLoop(head1, head2, loopEntry1, loopEntry2);
        return null;
    }

    private static Node findLoopEntry(Node head) {
        if (head == null || head.next == null) return null;
        Node pSlow = head.next;
        Node pFast = head.next.next;
        while (pFast != pSlow) {
            if (pFast == null || pFast.next == null) return null;
            pFast = pFast.next.next;
            pSlow = pSlow.next;
        }
        pFast = head;
        while (pFast != pSlow) {
            pFast = pFast.next;
            pSlow = pSlow.next;
        }
        return pSlow;
    }

    private static Node noLoop(Node head1, Node head2) {
        if (head1 == null || head2 == null) return null;
        Node p1 = head1;
        Node p2 = head2;
        int len1 = 1;
        int len2 = 1;
        while (p1.next != null) {
            len1++;
            p1 = p1.next;
        }
        while (p2.next != null) {
            len2++;
            p2 = p2.next;
        }
        if (p1 != p2) return null;
        Node longer = len1 > len2 ? head1 : head2;
        Node shorter = len1 > len2 ? head2 : head1;
        int cut = Math.abs(len1 - len2);
        while (cut-- > 0) longer = longer.next;
        while (longer != shorter) {
            longer = longer.next;
            shorter = shorter.next;
        }
        return longer;
    }

    private static Node bothLoop(Node head1, Node head2, Node loopEntry1, Node loopEntry2) {
        if (loopEntry1 == loopEntry2) {
            // have a common loop entry
            Node p1 = head1;
            Node p2 = head2;
            int len1 = 0;
            int len2 = 0;
            while (p1 != loopEntry1) {
                len1++;
                p1 = p1.next;
            }
            while (p2 != loopEntry2) {
                len2++;
                p2 = p2.next;
            }
            Node longer = len1 > len2 ? head1 : head2;
            Node shorter = len1 > len2 ? head2 : head1;
            int cut = Math.abs(len1 - len2);
            while (cut-- > 0) longer = longer.next;
            while (shorter != longer) {
                shorter = shorter.next;
                longer = longer.next;
            }
            return longer;
        } else {
            // have different loop entries
            Node p = loopEntry1.next;
            while (p != loopEntry1) {
                if (p == loopEntry2) return loopEntry1;
                p = p.next;
            }
            return null;
        }
    }
}

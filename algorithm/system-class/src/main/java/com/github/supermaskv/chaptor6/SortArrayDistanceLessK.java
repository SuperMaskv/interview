package com.github.supermaskv.chaptor6;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author supermaskv
 * 对数组排序，数组的每个元素和他对应的排序后的位置不超过K
 */
public class SortArrayDistanceLessK {
    public static void main(String[] args) {
        int[] arr = new int[]{3, 4, 1, 2, 5};
        sort(arr, 2);
        System.out.println(Arrays.toString(arr));
    }

    private static void sort(int[] arr, int k) {
        if (k == 0 || arr == null || arr.length < 2) return;
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int idx = 0;
        for (; idx < Math.min(arr.length, k); idx++) {
            heap.add(arr[idx]);
        }
        int i = 0;
        for (; idx < arr.length; i++, idx++) {
            heap.add(arr[idx]);
            arr[i] = heap.poll();
        }
        while (!heap.isEmpty()) {
            arr[i++] = heap.poll();
        }
    }
}

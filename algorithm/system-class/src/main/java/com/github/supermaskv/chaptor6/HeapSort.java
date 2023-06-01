package com.github.supermaskv.chaptor6;

import java.util.Arrays;

/**
 * @author supermaskv
 */
public class HeapSort {
    public static void main(String[] args) {
        int[] arr = new int[]{-2, 3, -5};
        System.out.println(Arrays.toString(new HeapSort().sortArray(arr)));
    }

    public int[] sortArray(int[] nums) {
        heapSort(nums);
        return nums;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static void heapInsert(int[] heap, int insertIdx) {
        if (insertIdx == 0) return;
        while (insertIdx != 0 && heap[(insertIdx - 1) >> 1] < heap[insertIdx]) {
            swap(heap, (insertIdx - 1) >> 1, insertIdx);
            insertIdx = (insertIdx - 1) >> 1;
        }
    }

    private static void heapify(int[] heap, int idx, int heapSize) {
        if (heapSize < 2) return;
        int left = idx * 2 + 1;
        while (left < heapSize) {
            int biggerChild = left + 1 < heapSize && heap[left + 1] > heap[left] ? left + 1 : left;
            if (heap[biggerChild] <= heap[idx]) return;
            swap(heap, biggerChild, idx);
            idx = biggerChild;
            left = idx * 2 + 1;
        }
    }

    private static void heapSort(int[] arr) {
        // build heap
        for (int i = 1; i < arr.length; i++) {
            heapInsert(arr, i);
        }
        // sort
        for (int i = 0; i < arr.length; i++) {
            swap(arr, 0, arr.length - i - 1);
            heapify(arr, 0, arr.length - i - 1);
        }
    }
}

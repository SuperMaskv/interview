package com.github.supermaskv.chaptor4;

import java.util.Arrays;

/**
 * @author supermaskv
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] arr = new int[]{9, 1234, 56, 46, 45, 37, 347};
        sort(arr, 0, arr.length);
        System.out.println(Arrays.toString(arr));
    }

    private static void sort(int[] arr, int left, int right) {
        if (arr == null || right - left < 2) return;
        int mid = left + ((right - left) >> 1);
        sort(arr, left, mid);
        sort(arr, mid, right);
        merge(arr, left, mid, right);
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int pLeft = left;
        int pRight = mid;
        int[] helper = new int[right - left];
        int cur = 0;
        while (pLeft < mid && pRight < right) {
            if (arr[pLeft] < arr[pRight]) helper[cur++] = arr[pLeft++];
            else helper[cur++] = arr[pRight++];
        }
        while (pLeft < mid) helper[cur++] = arr[pLeft++];
        while (pRight < right) helper[cur++] = arr[pRight++];
        System.arraycopy(helper, 0, arr, left, helper.length);
    }
}

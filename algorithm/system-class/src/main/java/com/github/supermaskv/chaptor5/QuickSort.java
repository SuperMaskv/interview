package com.github.supermaskv.chaptor5;

import java.util.Arrays;

/**
 * @author supermaskv
 * @see <a href="https://leetcode.cn/problems/sort-an-array/">912. 排序数组</a>
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = new int[]{-4, 0, 7, 4, 9, -5, -1, 0, -7, -1};
        sort(arr, 0, arr.length);
        System.out.println(Arrays.toString(arr));
    }

    private static void sort(int[] arr, int left, int right) {
        if (arr == null || right - left < 2) return;
        swap(arr, right - 1, (int) (Math.random() * (right - left)) + left);
        // [equalBounds[0],equalBounds[1])
        int[] equalBounds = partition(arr, left, right);
        sort(arr, left, equalBounds[0]);
        sort(arr, equalBounds[1], right);
    }

    private static int[] partition(int[] arr, int left, int right) {
        if (right - left < 1) return new int[]{left, left};
        if (right - left == 1) return new int[]{left, right};

        int pLess = left;
        int pMore = right - 1;
        int target = arr[pMore];
        int cur = left;
        while (cur < pMore) {
            if (arr[cur] < target) {
                swap(arr, cur++, pLess++);
            } else if (arr[cur] > target) {
                swap(arr, cur, --pMore);
            } else {
                cur++;
            }
        }
        swap(arr, cur, right - 1);
        return new int[]{pLess, pMore};
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}

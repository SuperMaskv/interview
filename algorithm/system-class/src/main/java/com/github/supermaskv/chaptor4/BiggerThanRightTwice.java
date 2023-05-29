package com.github.supermaskv.chaptor4;

import java.util.SortedMap;

/**
 * @author supermaskv
 * 记录右边一共有多少个数乘以2仍小于当前数
 * e.g.
 * input [5, 2, 6, 1]
 * output 3
 */
public class BiggerThanRightTwice {
    public static void main(String[] args) {
        int[] case1 = new int[]{5, 2, 6, 1};
        System.out.println(new BiggerThanRightTwice().sort(case1, 0, case1.length));
    }


    private int sort(int[] arr, int left, int right) {
        if (arr == null || right - left < 2) return 0;
        int mid = left + ((right - left) >> 1);
        return sort(arr, left, mid) + sort(arr, mid, right) + merge(arr, left, mid, right);
    }

    private int merge(int[] arr, int left, int mid, int right) {
        int pLeft = left;
        int pRight = mid;
        int[] helper = new int[right - left];
        int cur = 0;
        int ans = 0;
        while (pLeft < mid && pRight < right) {
            if (arr[pLeft] > arr[pRight] * 2) {
                helper[cur++] = arr[pRight++];
            } else {
                helper[cur++] = arr[pLeft++];
                ans += pRight - mid;
            }
        }
        while (pLeft < mid) {
            helper[cur++] = arr[pLeft++];
            ans += pRight - mid;
        }
        while (pRight < right) {
            helper[cur++] = arr[pRight++];
        }
        System.arraycopy(helper, 0, arr, left, helper.length);
        return ans;
    }
}

package com.github.supermaskv.chaptor4;

import java.util.Arrays;

/**
 * @author supermaskv
 * <p>
 * 求每个数左侧小于这个数的和
 */
public class SmallSum {
    public static void main(String[] args) {
        int[] case1 = new int[]{};
        smallSum(case1, 0, case1.length);
        System.out.println(ans);
        System.out.println(Arrays.toString(case1));
    }

    private static int ans;

    private static void smallSum(int[] arr, int left, int right) {
        if (arr == null || right - left < 2) return;
        int mid = left + ((right - left) >> 1);
        smallSum(arr, left, mid);
        smallSum(arr, mid, right);
        merge(arr, left, mid, right);
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int pLeft = left;
        int pRight = mid;
        int sumTemp = 0;
        int cur = 0;
        int[] helper = new int[right - left];
        while (pLeft < mid && pRight < right) {
            if (arr[pLeft] < arr[pRight]) {
                helper[cur++] = arr[pLeft];
                sumTemp += arr[pLeft++];
            } else {
                helper[cur++] = arr[pRight++];
                ans += sumTemp;
            }
        }
        while (pLeft < mid) {
            helper[cur++] = arr[pLeft++];
        }

        while (pRight < right) {
            helper[cur++] = arr[pRight++];
            ans += sumTemp;
        }
        System.arraycopy(helper, 0, arr, left, helper.length);
    }
}

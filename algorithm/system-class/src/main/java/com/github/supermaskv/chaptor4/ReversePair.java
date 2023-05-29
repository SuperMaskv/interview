package com.github.supermaskv.chaptor4;

import java.util.Arrays;

/**
 * @author supermaskv
 * @see <a href="https://leetcode.cn/problems/shu-zu-zhong-de-ni-xu-dui-lcof/">剑指 Offer 51. 数组中的逆序对</a>
 */
public class ReversePair {
    public static void main(String[] args) {
        int[] case1 = new int[]{1, 3, 2, 3, 1};
        System.out.println(new ReversePair().reversePairs(case1));
        System.out.println(Arrays.toString(case1));
    }

    public int reversePairs(int[] nums) {
        if (nums == null || nums.length < 2) return 0;
        return sort(nums, 0, nums.length);
    }

    private int sort(int[] arr, int left, int right) {
        if (right - left < 2) return 0;
        int mid = left + ((right - left) >> 1);
        return sort(arr, left, mid) + sort(arr, mid, right) + merge(arr, left, mid, right);
    }

    private int merge(int[] arr, int left, int mid, int right) {
        int pLeft = mid - 1;
        int pRight = right - 1;
        int[] helper = new int[right - left];
        int cur = helper.length - 1;
        int ans = 0;
        while (pLeft >= left && pRight >= mid) {
            if (arr[pLeft] > arr[pRight]) {
                ans += pRight - mid + 1;
                helper[cur--] = arr[pLeft--];
            } else {
                helper[cur--] = arr[pRight--];
            }
        }
        while (pLeft >= left) {
            helper[cur--] = arr[pLeft--];
        }
        while (pRight >= mid) {
            helper[cur--] = arr[pRight--];
        }
        System.arraycopy(helper, 0, arr, left, helper.length);
        return ans;
    }
}

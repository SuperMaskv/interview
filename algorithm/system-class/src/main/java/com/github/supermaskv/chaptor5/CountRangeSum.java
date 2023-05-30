package com.github.supermaskv.chaptor5;

/**
 * @author supermaskv
 * @see <a href="https://leetcode.cn/problems/count-of-range-sum/">327. 区间和的个数</a>
 */
public class CountRangeSum {
    public static void main(String[] args) {
        int[] nums = new int[]{-2147483647, 0, -2147483647, 2147483647};
        int lower = -564, upper = 3864;
        System.out.println(new CountRangeSum().countRangeSum(nums, lower, upper));
    }

    public int countRangeSum(int[] nums, int lower, int upper) {
        if (nums == null || nums.length == 0) return 0;
        // 前缀和数组用long，防止溢出
        long[] sums = new long[nums.length];
        sums[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sums[i] = sums[i - 1] + nums[i];
        }
        return count(sums, 0, sums.length, lower, upper);
    }

    private int count(long[] sums, int left, int right, int lower, int upper) {
        if (right - left == 1) {
            if (sums[left] >= lower && sums[left] <= upper) return 1;
            return 0;
        }
        int mid = left + ((right - left) >> 1);
        return count(sums, left, mid, lower, upper)
                + count(sums, mid, right, lower, upper)
                + merge(sums, left, right, lower, upper);
    }

    private int merge(long[] sums, int left, int right, int lower, int upper) {
        int wLeft = left;
        int wRight = left;
        int count = 0;
        int mid = left + ((right - left) >> 1);
        for (int i = mid; i < right; i++) {
            // 滑动窗口右边界找到第一个不满足条件的位置
            while (wRight < mid && sums[wRight] <= sums[i] - lower) wRight++;
            // 滑动窗口左边界找到第一个满足条件的位置
            while (wLeft < mid && sums[wLeft] < sums[i] - upper) wLeft++;
            count += Math.max(0, wRight - wLeft);
        }
        int pLeft = left;
        int pRight = mid;
        long[] helper = new long[right - left];
        int cur = 0;
        while (pLeft < mid && pRight < right) {
            if (sums[pLeft] < sums[pRight]) helper[cur++] = sums[pLeft++];
            else helper[cur++] = sums[pRight++];
        }
        while (pLeft < mid) helper[cur++] = sums[pLeft++];
        while (pRight < right) helper[cur++] = sums[pRight++];
        System.arraycopy(helper, 0, sums, left, helper.length);
        return count;
    }
}

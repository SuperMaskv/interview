package com.github.supermaskv.chaptor4;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author supermaskv
 * @see <a href="https://leetcode.cn/problems/count-of-smaller-numbers-after-self/">计算右侧小于当前元素的个数</a>
 */
public class CountSmaller {
    public static void main(String[] args) {
        int[] case1 = new int[]{5, 2, 6, 1};
        System.out.println(new CountSmaller().countSmaller(case1));
    }

    int[] ans;
    int[] tempIdx;

    public List<Integer> countSmaller(int[] nums) {
        if (nums == null) return null;
        if (nums.length < 2) return Collections.nCopies(nums.length, 0);
        ans = new int[nums.length];
        tempIdx = IntStream.range(0, nums.length).toArray();
        sort(nums, 0, nums.length);
        return Arrays.stream(ans).boxed().collect(Collectors.toList());
    }

    private void sort(int[] arr, int left, int right) {
        if (right - left < 2) return;
        int mid = left + ((right - left) >> 1);
        sort(arr, left, mid);
        sort(arr, mid, right);
        merge(arr, left, mid, right);
    }

    private void merge(int[] arr, int left, int mid, int right) {
        int pLeft = left;
        int pRight = mid;
        int[] helper = new int[right - left];
        int[] idxHelper = new int[right - left];
        int cur = 0;
        while (pLeft < mid && pRight < right) {
            if (arr[pLeft] > arr[pRight]) {
                helper[cur] = arr[pRight];
                idxHelper[cur] = this.tempIdx[pRight];
                cur++;
                pRight++;
            } else {
                this.ans[this.tempIdx[pLeft]] += pRight - mid;
                helper[cur] = arr[pLeft];
                idxHelper[cur] = this.tempIdx[pLeft];
                cur++;
                pLeft++;
            }
        }
        while (pLeft < mid) {
            this.ans[this.tempIdx[pLeft]] += pRight - mid;
            helper[cur] = arr[pLeft];
            idxHelper[cur] = this.tempIdx[pLeft];
            cur++;
            pLeft++;
        }
        while (pRight < right) {
            helper[cur] = arr[pRight];
            idxHelper[cur] = this.tempIdx[pRight];
            cur++;
            pRight++;
        }
        System.arraycopy(helper, 0, arr, left, helper.length);
        System.arraycopy(idxHelper, 0, this.tempIdx, left, idxHelper.length);
    }
}

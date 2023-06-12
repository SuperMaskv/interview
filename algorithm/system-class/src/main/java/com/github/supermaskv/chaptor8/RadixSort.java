package com.github.supermaskv.chaptor8;

import java.util.Arrays;

/**
 * @author supermaskv
 * <p>
 * 基数排序 一般只对自然数进行排序，如果需要包含负数，应该将所有负数转换成自然数再排序，排序后还原
 */
public class RadixSort {
    public static void main(String[] args) {
        int[] arr = new int[]{25, 30, 25, 35, 31, 143, 123};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    private static void sort(int[] arr) {
        if (arr == null || arr.length == 0) return;
        int max = Arrays.stream(arr).max().getAsInt();
        int maxDigits = maxDigits(max);
        int[] helper = new int[arr.length];
        for (int i = 0; i < maxDigits; i++) {
            int[] counter = new int[10];
            for (int j = 0; j < arr.length; j++) {
                counter[getDigit(arr[j], i)]++;
            }
            for (int j = 1; j < counter.length; j++) {
                counter[j] += counter[j - 1];
            }
            for (int j = arr.length - 1; j >= 0; j--) {
                helper[--counter[getDigit(arr[j], i)]] = arr[j];
            }
            System.arraycopy(helper, 0, arr, 0, helper.length);
        }
    }

    private static int maxDigits(int num) {
        int digits = 0;
        while (num != 0) {
            num /= 10;
            digits++;
        }
        return digits;
    }

    private static int getDigit(int num, int digit) {
        return (int) ((num / Math.pow(10, digit)) % 10);
    }
}

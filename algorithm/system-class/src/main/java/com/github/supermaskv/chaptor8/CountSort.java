package com.github.supermaskv.chaptor8;

import java.util.Arrays;

/**
 * @author supermaskv
 * <p>
 * 计数排序 对排序的样本有严格的要求，如果不能映射为某一固定区间就无法使用
 * 对员工的年龄进行排序，员工年龄在[0,200)范围上
 * input [25,30,25,35,31,143,123]
 * output [25,25,30,31,35,123,143]
 */
public class CountSort {
    public static void main(String[] args) {
        int[] arr = new int[]{25, 30, 25, 35, 31, 143, 123};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    private static void sort(int[] arr) {
        if (arr == null || arr.length == 0) return;
        int[] helper = new int[200];
        for (int i = 0; i < arr.length; i++) {
            helper[arr[i]]++;
        }
        int index = 0;
        for (int i = 0; i < helper.length; i++) {
            while (helper[i] > 0) {
                arr[index++] = i;
                helper[i]--;
            }
        }
    }
}

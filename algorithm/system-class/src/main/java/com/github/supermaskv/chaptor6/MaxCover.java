package com.github.supermaskv.chaptor6;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author supermaskv
 * <p>
 * 最大重合线段
 * input [[1,2], [2,5], [3,4]]
 * 数组中的每个元素表示一个线段，线段使用[start,end]来表示
 * [1,2]与[2,5]没有重合，[1,2]与[3,4]也没有重合，[2,5]与[3,4]重合
 * output 2
 */
public class MaxCover {
    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {1, 2},
                {2, 5},
                {3, 4}
        };
        System.out.println(maxCover(matrix));
    }

    private static int maxCover(int[][] matrix) {
        Arrays.sort(matrix, (a, b) -> a[0] - b[0]);
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int max = 0;

        for (int i = 0; i < matrix.length; i++) {
            while (!heap.isEmpty() && heap.peek() <= matrix[i][0]) heap.poll();
            heap.add(matrix[i][1]);
            max = Math.max(max, heap.size());
        }

        return max;
    }
}

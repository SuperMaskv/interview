package com.github.supermaskv.chaptor12;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * @author supermaskv
 * 现在需要把一根金条分割成对应的长度，每次分割都需要付出当前这部分金条长度的数值作为成本,金条的长度总是刚好能被完全分割
 * e.g.
 * input
 * [10,20,30]
 * output
 * 60+30=90
 */
public class MinCostSplitGold {
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            int[] testCase = generateTestCase();
            if (greedySolve(testCase) != forceSolve(testCase))
                System.out.println(Arrays.toString(testCase));
        }
    }

    private static int greedySolve(int[] pieces) {
        if (pieces == null || pieces.length < 2) return 0;
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int piece : pieces) {
            queue.add(piece);
        }
        int ans = 0;
        while (queue.size() >= 2) {
            int sum = queue.poll() + queue.poll();
            ans += sum;
            queue.add(sum);
        }
        return ans;
    }

    private static int forceSolve(int[] pieces) {
        if (pieces == null || pieces.length < 2) return 0;
        return recursiveFunc(pieces, 0);
    }

    private static int recursiveFunc(int[] pieces, int cost) {
        if (pieces == null || pieces.length < 2) return cost;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < pieces.length; i++) {
            for (int j = i + 1; j < pieces.length; j++) {
                min = Math.min(recursiveFunc(copyAndMerge(pieces, i, j), cost + pieces[i] + pieces[j]), min);
            }
        }
        return min;
    }

    private static int[] copyAndMerge(int[] pieces, int idxA, int idxB) {
        if (pieces == null || pieces.length < 2) return null;
        int sum = pieces[idxA] + pieces[idxB];
        int[] copied = new int[pieces.length - 1];
        copied[0] = sum;
        for (int i = 0, j = 1; i < pieces.length; i++) {
            if (i == idxA || i == idxB) continue;
            copied[j++] = pieces[i];
        }
        return copied;
    }

    private static int[] generateTestCase() {
        Random random = new Random();
        int len = random.nextInt(9) + 1;
        int[] testCase = new int[len];
        for (int i = 0; i < len; i++) {
            testCase[i] = random.nextInt(999) + 1;
        }
        return testCase;
    }
}

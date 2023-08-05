package com.github.supermaskv.chaptor12;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

/**
 * @author xugr
 * 有许多会议要在同一间会议室进行，计算能安排的最多的会议数量
 * e.g.
 * input
 * [[1,2],[1,3],[2,4]]
 * output
 * 2
 */
public class MaxConferenceCapacityCalculator {
    public static void main(String[] args) {
        for (int i = 0; i < 5000; i++) {
            Conference[] testCase = generateTestCase();
            if (greedyAlgorithmSolve(testCase) != forceAlgorithmSolve(testCase))
                System.out.println(Arrays.toString(testCase));
        }
    }

    private static class Conference {
        int start;
        int end;

        public Conference(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return "Conference{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }
    }

    private static int greedyAlgorithmSolve(Conference[] conferences) {
        if (conferences == null || conferences.length == 0) return 0;
        int timePoint = 0;
        int counter = 0;
        Arrays.sort(conferences, Comparator.comparingInt(c -> c.end));
        for (Conference conference : conferences) {
            if (conference.start >= timePoint) {
                counter++;
                timePoint = conference.end;
            }
        }
        return counter;
    }

    private static int forceAlgorithmSolve(Conference[] conferences) {
        if (conferences == null || conferences.length == 0) return 0;
        return recursiveMethod(conferences, 0, 0);
    }

    private static int recursiveMethod(Conference[] conferences, int arranged, int timePoint) {
        if (conferences.length == 0) return arranged;
        int max = arranged;
        for (int i = 0; i < conferences.length; i++) {
            if (conferences[i].start >= timePoint) {
                Conference[] copied = copyAndDeleteByIndex(conferences, i);
                max = Math.max(recursiveMethod(copied, arranged + 1, conferences[i].end), max);
            }
        }
        return max;
    }

    private static Conference[] copyAndDeleteByIndex(Conference[] conferences, int remove) {
        if (conferences == null || conferences.length == 0) return null;
        Conference[] copied = new Conference[conferences.length - 1];
        for (int i = 0, j = 0; i < conferences.length; i++) {
            if (i == remove) continue;
            copied[j++] = conferences[i];
        }
        return copied;
    }

    private static Conference[] generateTestCase() {
        Random random = new Random();
        int len = random.nextInt(9) + 1;
        Conference[] conferences = new Conference[len];
        for (int i = 0; i < len; i++) {
            int start = random.nextInt(1000);
            int end = random.nextInt(1001 - start + 1) + start + 1;
            conferences[i] = new Conference(start, end);
        }
        return conferences;
    }
}

package com.github.supermaskv.chaptor12;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author supermaskv
 * <p>
 * 现在有起始资金W，通过完成K个项目，来实现获取利润的最大化；
 * 每个项目都有自己的成本cost和利润profit；
 * 一次只能完成一个项目，并且不能选择成本高于起始资金的项目
 * e.g.
 * input
 * w=1,k=3
 * [[5,1],[1,3],[2,5],[6,4]]
 * output
 * 1+3+5+4=13
 */
public class ProjectMaxProfit {
    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            Project[] testCase = generateTestCase();
            int k = ThreadLocalRandom.current().nextInt(1, 10);
            int w = ThreadLocalRandom.current().nextInt(1, 100);
            if (greedySolve(testCase, k, w) != forceSolve(testCase, k, w)) {
                System.out.println(Arrays.toString(testCase));
            }
        }
    }

    private static class Project {
        int cost;
        int profit;

        public Project(int cost, int profit) {
            this.cost = cost;
            this.profit = profit;
        }

        @Override
        public String toString() {
            return "Project{" +
                    "cost=" + cost +
                    ", profit=" + profit +
                    '}';
        }
    }

    private static int greedySolve(Project[] projects, int k, int w) {
        if (projects == null || projects.length == 0) return 0;
        PriorityQueue<Project> smallerQueue = new PriorityQueue<>((p1, p2) -> p1.cost - p2.cost);
        PriorityQueue<Project> biggerQueue = new PriorityQueue<>((p1, p2) -> p2.profit - p1.profit);
        Collections.addAll(smallerQueue, projects);
        for (int i = 0; i < k; i++) {
            while (!smallerQueue.isEmpty() && smallerQueue.peek().cost <= w) biggerQueue.add(smallerQueue.poll());
            if (biggerQueue.isEmpty()) return w;
            w += biggerQueue.poll().profit;
        }
        return w;
    }

    private static int forceSolve(Project[] projects, int k, int w) {
        if (projects == null || projects.length == 0) return 0;
        return recursiveFunc(projects, k, w);
    }

    private static int recursiveFunc(Project[] projects, int k, int w) {
        if (projects == null || projects.length == 0 || k == 0) return w;
        int max = w;
        for (int i = 0; i < projects.length; i++) {
            if (projects[i].cost <= w) {
                Project[] copied = copyAndDelByIdx(projects, i);
                int money = recursiveFunc(copied, k - 1, w + projects[i].profit);
                max = Math.max(max, money);
            }
        }
        return max;
    }

    private static Project[] copyAndDelByIdx(Project[] projects, int remove) {
        if (projects == null || projects.length <= remove) return null;
        int len = projects.length - 1;
        Project[] copied = new Project[len];
        for (int i = 0, j = 0; i < projects.length; i++) {
            if (i == remove) continue;
            copied[j++] = projects[i];
        }
        return copied;
    }

    private static Project[] generateTestCase() {
        int len = ThreadLocalRandom.current().nextInt(1, 10);
        Project[] testCase = new Project[len];
        for (int i = 0; i < len; i++) {
            int cost = ThreadLocalRandom.current().nextInt(1, 100);
            int profit = ThreadLocalRandom.current().nextInt(1, 100);
            testCase[i] = new Project(cost, profit);
        }
        return testCase;
    }
}

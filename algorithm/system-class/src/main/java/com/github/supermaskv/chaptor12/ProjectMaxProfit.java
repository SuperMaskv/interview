package com.github.supermaskv.chaptor12;

import java.util.Collections;
import java.util.PriorityQueue;

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
        Project[] projects = {
                new Project(5, 1),
                new Project(1, 3),
                new Project(2, 5),
                new Project(6, 4)
        };
        System.out.println(greedySolve(projects, 3, 1));
    }

    private static class Project {
        int cost;
        int profit;

        public Project(int cost, int profit) {
            this.cost = cost;
            this.profit = profit;
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
}

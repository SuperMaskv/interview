package com.github.supermaskv.chaptor11;

/**
 * @author supermaskv
 * <p>
 * 对一张纸条沿着同一方向对折n次，从头到尾依次输出纸条上的折痕
 * e.g.
 * input n=2
 * output down down up
 */
public class FoldingPaper {
    public static void main(String[] args) {
        print(2);
    }

    private static void print(int n) {
        if (n < 1) return;
        print(1, n, false);
        System.out.println();
    }

    private static void print(int i, int n, boolean flag) {
        if (i > n) return;
        print(i + 1, n, false);
        System.out.printf(flag ? "up " : "down ");
        print(i + 1, n, true);
    }
}

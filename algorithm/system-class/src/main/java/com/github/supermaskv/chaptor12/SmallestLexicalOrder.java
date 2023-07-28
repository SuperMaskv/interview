package com.github.supermaskv.chaptor12;

import java.util.Arrays;

/**
 * @author xugr
 * <p>
 * 最小字典序
 * 对若干字符串进行组合排列，使结果的字典序最小
 * e.g.
 * input ["ab","b"]
 * output "aba"
 */
public class SmallestLexicalOrder {
    public static void main(String[] args) {
        String[] strings = {"ab", "b", "a"};
        System.out.println(buildSmallestLexicalStr(strings));
    }

    private static String buildSmallestLexicalStr(String[] strings) {
        if (strings == null || strings.length == 0) return null;
        Arrays.sort(strings, (s1, s2) -> (s1 + s2).compareTo(s2 + s1));
        StringBuilder sb = new StringBuilder();
        for (String s : strings) {
            sb.append(s);
        }
        return sb.toString();
    }
}

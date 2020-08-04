package com.hestech.main;

/**
 * -----------------------------------------
 * Author: JESSE HE
 * Date:   8/4/2020 11:11 PM
 * Desc:   给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 * -----------------------------------------
 */
public class Code005 {
    public String longestPalindrome(String s) {
        // 传入字符串不为空串
        if (s == null || s.length() == 0) {
            return "";
        }
        // 初始化参数

        int strLen = s.length();
        int left = 0;
        int right = 0;
        int len = 1;
        int maxStart = 0;
        int maxLen = 0;

        for (int i = 0; i < strLen; i++) {
            // 左右指针各扩1
            left = i - 1;
            right = i + 1;
            // 当左边不越界时，且左和当前位相等
            while (left >= 0 && s.charAt(left) == s.charAt(i)) {
                len++;
                left--;
            }
            // 当右边不越界时，且右和当前相等
            while (right < strLen && s.charAt(right) == s.charAt(i)) {
                len++;
                right++;
            }
            // 当两边都不越界时，左右两指针值相等时
            while (left >= 0 && right < strLen && s.charAt(right) == s.charAt(left)) {
                len = len + 2;
                left--;
                right++;
            }
            // 结果赋值
            if (len > maxLen) {
                maxLen = len;
                maxStart = left;
            }
            len = 1;
        }
        return s.substring(maxStart + 1, maxStart + maxLen + 1);
    }

    /**-----------------------------------------
     * Author:  Jesse HE
     * Date:    8/3/2020
     * Desc:    中心扩散的方法，其实做了很多重复计算。动态规划就是为了减少重复计算的问题。动态规划听起来很高大上。
     * 其实说白了就是空间换时间，将计算结果暂存起来，避免重复计算。作用和工程中用 redis 做缓存有异曲同工之妙。
     * 我们用一个 boolean dp[l][r] 表示字符串从 i 到 j 这段是否为回文。
     * 试想如果 dp[l][r]=true，我们要判断 dp[l-1][r+1] 是否为回文。只需要判断字符串在(l-1)和（r+1)两个位置是否为相同的字符，是不是减少了很多重复计算。
     * 进入正题，动态规划关键是找到初始状态和状态转移方程。
     * 初始状态，l=r 时，此时 dp[l][r]=true。
     * 状态转移方程，dp[l][r]=true 并且(l-1)和（r+1)两个位置为相同的字符，此时 dp[l-1][r+1]=true
     -----------------------------------------*/
    public String longestPalindromeNew(String s) {
        // 判断入参不为空
        if(null == s || s.length() < 2) {
            return s;
        }
        // 初始化参数
        int strLen = s.length(), maxStart = 0, maxEnd = 0, maxLen = 1;

        // 动态规划记录数组，减少重复计算
        boolean[][] dp = new boolean[strLen][strLen];

        // 遍历
        for (int right = 1; right < strLen; right++) {
            for (int left = 0; left < right; left++) {
                // 当前左右指针相等 且扩散一位后的dp值为
                if(s.charAt(left) == s.charAt(right) && (right - left <= 2 || dp[left+1][right-1])) {
                    dp[left][right] = true;
                    if(right - left + 1 > maxLen) {
                        maxLen = right - left + 1;
                        maxStart = left;
                        maxEnd = right;
                    }
                }
            }
        }
        return s.substring(maxStart, maxEnd + 1);
    }
}

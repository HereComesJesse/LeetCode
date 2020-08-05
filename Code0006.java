package com.hestech.solutions;

import java.util.ArrayList;
import java.util.List;

/**
 * -----------------------------------------
 * Author: JESSE HE
 * Date:   8/4/2020 11:16 PM
 * Desc:   将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
 *
 * 比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
 *
 * L   C   I   R
 * E T O E S I I G
 * E   D   H   N
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。
 *
 * 请你实现这个将字符串进行指定行数变换的函数：
 *
 * string convert(string s, int numRows);
 * -----------------------------------------
 */
public class Code0006 {
    public String convert(String s, int numRows) {

        // 如果只有一行  返回原字符串
        if (numRows == 1) return s;

        // 存放每一行的结果
        List<StringBuilder> rows = new ArrayList<>();

        // 初始化每行
        for (int i = 0; i < Math.min(numRows, s.length()); i++)
            rows.add(new StringBuilder());

        int curRow = 0;
        boolean goingDown = false;

        // 每次放置一个字符到指定行
        for (char c : s.toCharArray()) {
            rows.get(curRow).append(c);
            // 当走到第一行或者最后一行时 翻转行递进的方向
            if (curRow == 0 || curRow == numRows - 1) goingDown = !goingDown;
            // 根据当前递进方向更改下一行指针
            curRow += goingDown ? 1 : -1;
        }

        // 返回结果
        StringBuilder ret = new StringBuilder();
        for (StringBuilder row : rows) ret.append(row);
        return ret.toString();
    }
}

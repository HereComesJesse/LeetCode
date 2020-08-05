package com.hestech.solutions;

/**
 * -----------------------------------------
 * Author: JESSE HE
 * Date:   8/5/2020 11:33 PM
 * Desc:   给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 123
 * 输出: 321
 *  示例 2:
 * <p>
 * 输入: -123
 * 输出: -321
 * 示例 3:
 * <p>
 * 输入: 120
 * 输出: 21
 * -----------------------------------------
 * <p>
 * Integer.MAX_VALUE = 2147483647
 * Integer.MIN_VALUE = -2147483648
 */
public class Code0007 {
    public int reverse(int x) {
        // 结果 和 下一位数字
        int result = 0, pop;
        // 循环每一位
        while (x != 0) {
            pop = x % 10;
            // 判断是否大于MAX_VALUE
            if(result > Integer.MAX_VALUE / 10 || (result == Integer.MAX_VALUE && pop > 7)) return 0;
            // 判断是否小于MIN_VALUE
            if(result < Integer.MIN_VALUE / 10 || (result == Integer.MIN_VALUE && pop < -8)) return 0;
            // 未溢出
            result = result * 10 + pop;
            // 去除已翻转过去的位
            x /= 10;
        }
        return result;
    }
}

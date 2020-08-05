package com.hestech.solutions;

/**
 * -----------------------------------------
 * Author: JESSE HE
 * Date:   8/4/2020 11:13 PM
 * Desc:   给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。
 *
 * 请你找出这两个正序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 *
 * 你可以假设 nums1 和 nums2 不会同时为空。
 * -----------------------------------------
 */
public class Code0004 {


    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // 两数组长度
        int num1Len = nums1.length;
        int num2Len = nums2.length;

        // 始终保持num1Len为最小的那个数组长度，即计算最小值
        if (num1Len > num2Len)
            return findMedianSortedArrays(nums2, nums1);

        // midPoint为要求的位置，也就是要求第midPoint小的数字
        int midPoint = (num1Len + num2Len + 1) / 2;

        // left 为num1在midPoint左边的长度， right为num1在midPoint右边的长度
        // 初始时 left 和 right刚好包含了整个较小的数组+1
        int left = 0;
        int right = num1Len;

        // num1LeftCount为较小数组在midPoint左边的个数 num2LeftCount为较大数组在其左边的个数
        int num1LeftCount, num2LeftCount;

        // 当较小数组的左边指针与右边指针未重合时循环
        while (left < right) {
            // 二分法 将较小数组当前指针范围内1/2时放到左边
            num1LeftCount = left + (right - left) / 2;

            // 较大数组个数可以直接减法求得
            num2LeftCount = midPoint - num1LeftCount;

            // 当较小数组右边的第一个值，小于较大数组左边的最后一个值时
            if (nums1[num1LeftCount] < nums2[num2LeftCount - 1])
                // 说明当前放置是符合的，较小数组左边指针变为 已经放过去子数组的下一位
                left = num1LeftCount + 1;
            else
                // 否则说明当前放法不符合，较小数组右边指针变为 当前放过去子数组的最后一位
                right = num1LeftCount;

            // 继续二分法进行放置，直到left和right重合，此时left的值就是较小数组中放过去的个数
        }

        // 已找到符合的位置，计算出两数组分别放过去数字的个数
        num1LeftCount = left;
        num2LeftCount = midPoint - left;

        // 第一个，中位数值为两数组放在左边的两个最大值中，最大的一个
        int median1 = Math.max(num1LeftCount == 0 ? Integer.MIN_VALUE : nums1[num1LeftCount - 1],
                num2LeftCount == 0 ? Integer.MIN_VALUE : nums2[num2LeftCount - 1]);

        // 如果两数组之和个数为奇数，直接返回唯一的median1
        if ((num1Len + num2Len) % 2 == 1)
            return median1;

        // 如果两数组之和为偶数，需要计算第二个中位数。 为右边两数组分别最小的数中，最小的一个
        int median2 = Math.min(num1LeftCount == num1Len ? Integer.MAX_VALUE : nums1[num1LeftCount],
                num2LeftCount == num2Len ? Integer.MAX_VALUE : nums2[num2LeftCount]);

        // 返回最终中位数结果
        return (median1 + median2) * 0.5;
    }
}

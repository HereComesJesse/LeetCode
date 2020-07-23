package com.hestech.raw;

import java.util.HashMap;

/**
 * -----------------------------------------
 * Author: HEJIE
 * Date:   2020/7/23 19:12
 * Desc:   给定一个整数数组 nums 和一个目标值 target，
 *         请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 *
 * Solution: 借助新的数据结构Map，通过空间复杂度换时间复杂度
 * -----------------------------------------
 */
public class Code0001 {

    public int[] twoSum(int[] nums, int target) {

        // 借助HashMap 其中 kEY=当前值  VALUE=KEY值在原数组中的坐标
        HashMap<Integer, Integer> indexMap = new HashMap<>();

        // 存放当前值与target的差值
        int diff;
        // 最终答案
        int[] answer = new int[2];

        for (int i = 0; i < nums.length; i++) {
            diff = target - nums[i];
            if(indexMap.containsKey(diff)) {
                // Map中存在匹配的差值
                answer[0] = indexMap.get(diff);
                answer[1] = i;

                return answer;
            } else {
                // 不存在则放入Map
                indexMap.put(nums[i], i);
            }
        }

        return null;
    }

    public static void main(String[] args) {

        // 测试数据
        int[] testArr = {222,13,466,123,32,55,333,211,23,111,543,3,12,4345,2};
        int testTarget = 666;

        int[] ints = new Code0001().twoSum(testArr, testTarget);

        if(null != ints) {
            System.out.println("[" + ints[0] + ", " + ints[1] + "]");
        } else {
            System.out.println("当前数组不存在匹配目标值的坐标。");
        }
    }
}

package com.hestech.raw;

import java.util.ArrayList;

/**
 * -----------------------------------------
 * Author: HEJIE
 * Date:   2020/7/27 23:22
 * Desc:
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，
 * 并且它们的每个节点只能存储 一位 数字。
 * <p>
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * <p>
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * -----------------------------------------
 */
public class Code0002 {
    /**-----------------------------------------
     * Author       HEJIE
     * @Date        2020/7/28
     * @Param       [l1, l2]
     * @Return      com.hestech.raw.Code0002.ListNode
     * Desc         更新版
     *
     * 执行用时：2 ms , 在所有 Java 提交中击败了 99.89% 的用户
     * 内存消耗： 39.8 MB , 在所有 Java 提交中击败了 94.26% 的用户
     -----------------------------------------*/
    public static ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        // 头结点
        ListNode listNode = new ListNode(0);
        ListNode header = listNode;

        // sum存储每次相加结果  carry标记是否存在进位
        int sum, carry = 0;

        // 当任何链表还有后续节点，或者进位不为0时，需要新增结果链表的节点
        while (null != l1 || null != l2 || carry > 0) {

            // 需要存储的位 = val1 + val2 + carry
            sum = (null != l1 ? l1.val : 0) + (null != l2 ? l2.val : 0) + carry;
            if (sum >= 10) {
                sum %= 10;
                carry = 1;
            } else {
                carry = 0;
            }

            // 放入新节点
            listNode.next = new ListNode(sum);
            listNode = listNode.next;

            // 指针后移时需要先判空，否则NullPointerException
            l1 = null != l1 ? l1.next : null;
            l2 = null != l2 ? l2.next : null;
        }
        return header.next;
    }

    /**
     * -----------------------------------------
     * Author       HEJIE
     *
     * @Date 2020/7/28
     * @Param [l1, l2]
     * @Return com.hestech.raw.Code0002.ListNode
     * Desc     首次炸心态版 【内存超标】
     * -----------------------------------------
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode l1CurrentNode = l1;
        ListNode l2CurrentNode = l2;
        ArrayList<Integer> integers = new ArrayList<>();
        int flag = 0;
        while (null != l1CurrentNode && null != l2CurrentNode) {
            int singleSum = l1CurrentNode.val + l2CurrentNode.val + flag;
            if (singleSum >= 10) {
                singleSum %= 10;
                integers.add(singleSum);
                flag = 1;
                //System.out.println("Add: " + singleSum);
            } else {
                integers.add(singleSum);
                flag = 0;
                //System.out.println("Add:" + singleSum);
            }

            l1CurrentNode = l1CurrentNode.next;
            l2CurrentNode = l2CurrentNode.next;
        }

        if (null == l1CurrentNode) {
            if (l2CurrentNode != null) {
                do {
                    integers.add(l2CurrentNode.val + flag);
                    flag = 0;
                } while (null == l2CurrentNode.next);
            }
        } else {
            if (l1CurrentNode != null) {
                do {
                    integers.add(l1CurrentNode.val + flag);
                    flag = 0;
                } while (null == l1.next);
            }
        }

        if (flag == 1) {
            integers.add(1);
        }
/*        System.out.println(integers.size());
        for (int i = 0; i < integers.size(); i++) {
            System.out.println(integers.get(i));
        }*/

        ListNode result = new ListNode(integers.get(0));
        //System.out.println("1st.val: " + integers.get(integers.size() -1 ));
        ListNode curNode = result;
        for (int i = 1; i < integers.size(); i++) {
            curNode.next = new ListNode(integers.get(i));
            curNode = curNode.next;
        }

        return result;
    }

    public static void main(String[] args) {
        ListNode l11 = new ListNode(5);
        ListNode l12 = new ListNode(1);
        ListNode l13 = new ListNode(1);
        ListNode l21 = new ListNode(5);
        ListNode l22 = new ListNode(3);
        ListNode l23 = new ListNode(4);

/*        l11.next = l12;
        l12.next = l13;

        l21.next = l22;
        l22.next = l23;*/

        ListNode listNode = addTwoNumbers2(l11, l21);
        System.out.println(listNode.val);
        System.out.println(listNode.next.val);
        //System.out.println(listNode.next.next.val);


    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            this.val = x;
        }
    }
}

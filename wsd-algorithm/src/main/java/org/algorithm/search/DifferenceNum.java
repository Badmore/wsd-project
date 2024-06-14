package org.algorithm.search;

import java.util.Arrays;

/**
 * <h3>wsd-project</h3>
 * <p>差分数组</p>
 * 差分数组的作用是能快速的对区间更新。区间更新是指对于数组num，长度为n，想要对区间[l, r]做更新，比如都加上一个数x，或者都减去一个数y。
 * 常规的实现肯定遍历[l, r]然后对每个元素做更新，这是线性时间O(n)的，
 * 而用差分数组可以在常数时间完成区间更新
 *
 * 特性： 原数组是差分数组的前缀和
 * 所以给差分数组的 index = i 位置 + x，其逆向原数组相当于 i  ~ n  全部都加了 x；
 *
 * 应用：频繁对区间内数据做增减操作；
 *
 *
 *
 * @author : 王松迪
 * 2024-05-30 09:42
 **/
public class DifferenceNum {

    private final int[] diffNum;

    public DifferenceNum(int[] num) {
        assert num.length > 0;
        this.diffNum = new int[num.length];
        diffNum[0] = num[0];

        for(int i = 1; i < num.length; i++) {
            diffNum[i] = num[i] - num[i - 1];
        }

    }

    public void increment(int begin, int end, int val) {
        diffNum[begin] += val;
        if(end + 1< diffNum.length) {
            diffNum[end] -= val;
        }
    }

    public int[] result() {
        int[] result = new int[diffNum.length];
        result[0] = diffNum[0];
        for(int i = 1; i < diffNum.length; i++) {
            result[i] = result[i - 1] + diffNum[i];
        }
        return result;
    }

    public static void main(String[] args) {

        int[] one = new int[]{1, 2, 3, 4, 4, 5, 6, 7, 8, 9, 10};
        DifferenceNum differenceNum = new DifferenceNum(one);
        System.out.println(Arrays.toString(differenceNum.result()));
        differenceNum.increment(3, 7, 10);
        System.out.println(Arrays.toString(differenceNum.result()));
        differenceNum.increment(3, 7, 10);
        System.out.println(Arrays.toString(differenceNum.result()));


    }
}




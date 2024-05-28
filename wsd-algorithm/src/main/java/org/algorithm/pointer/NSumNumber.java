package org.algorithm.pointer;

import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.List;

/**
 * <h3>wsd-project</h3>
 * <p>利用双指针计算多数之和</p>
 *
 * @author : 王松迪
 * 2024-05-28 11:33
 **/
public class NSumNumber {


    /**
     * 1. 额外空间：利用 hash 存储键值对，根据差值查找。
     * 2. 原地查找：利用左右指针，计算和 = sum 的值；
     * @param arr 输入的数组
     * @param sum 和
     * @return 两个数
     */
    public static List<Integer[]> twoSum(int[] arr, int sum, int begin, int end) {

        List<Integer[]> result = Lists.newArrayList();
        int l = begin, r = end;
        while(r < arr.length && l < r) {
            if(arr[l] + arr[r] == sum) {
                result.add(new Integer[]{arr[l], arr[r]});
            }
            if(arr[l] + arr[r] < sum) {
                l++;
            } else {
                r--;
            }
        }

        return result;
    }

    /**
     * 三数之和，思路是简化为求两数之和。求子集的解。
     * @param arr 输入的数组
     * @param sum 和
     */
    public static int[][] threeSum(int[] arr, int sum) {

        int[][] result = new int[arr.length][3];
        int countIndex = 0;
        for (int i = 0; i < arr.length; i++) {
            int flag = arr[i];
            int twoSum = sum - flag;
            List<Integer[]> integers = twoSum(arr, twoSum, i + 1, arr.length - 1);
            for (Integer[] ints : integers) {
                if(flag + ints[0] + ints[1] == sum) {
                    result[countIndex++] = new int[]{flag, ints[0], ints[1]};
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {

        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int sum = 17;
        int[][] ints = threeSum(arr, sum);
        System.out.println(Arrays.deepToString(ints));
    }
}

package org.algorithm.bit;

import java.util.Arrays;

/**
 * <h3>wsd-project</h3>
 * <p>寻找数组中确实的整数</p>
 *
 * @author : 王松迪
 * 2024-03-12 08:40
 **/
public class BitLostNum {

    /**
     * 数组中有两个整数出现了奇数次，其他整数出现了偶数次，找到这两个整数
     *
     * 1.使用异或运算，判断数组中相同的数字出现的次数，偶数次为 0。 异或运算结果，遇到相同的数效果等同于想减，遇到不同的数想过等同于相加
     * 例如
     * 1 ^ 1 = 0;
     * 1 ^ 2 = 3;
     * 3 ^ 4 = 7
     *
     * 2.利用两个不同的数异或结果为操作，再使用 & 运算从低位判断是哪位不同后，将数组分治，即与与运算结果做异或判断 相同的，再将结果异或运算。
     * 分解 ： 与运算 1&1=1；1&0=0；0&0=0；0&1=0；
     *
     *
     */
    public static int[] findLostNum(int[] array) {
        //用于存储 2 个出现奇数次的整数
        int[] result = new int[2];
        //第一次进行整体异或运算
        int xorResult = 0;
        for (int i : array) {
            System.out.println("i : " +  Integer.toBinaryString(i) + " xorResult : " + Integer.toBinaryString(xorResult) + " result : " + Integer.toBinaryString(i ^ xorResult));
            xorResult = xorResult ^ i;
        }
        //如果进行异或运算的结果为 0，则说明输入的数组不符合题目要求
        if(xorResult == 0) {
            return null;
        }

        //确定2个整数的不同位，用词来做分组。
        int separator = 1;
        while (0 == (xorResult & separator)) {
            separator = separator << 1;
        }

        //第 2 次分组进行异或运算
        for (int j : array) {
            if (0 == (j & separator)) {
                result[0] = result[0] ^ j;
            } else {
                result[1] = result[1] ^ j;
            }
        }
        return  result;
    }

    public static void main(String[] args) {

        int[] array = {4,1,2,2,5,1,4,3};
        int[] lostNum = findLostNum(array);
        System.out.println(Arrays.toString(lostNum));
    }
}

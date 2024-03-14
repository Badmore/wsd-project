package org.algorithm.bit;

/**
 * <h3>wsd-project</h3>
 * <p>位运算之最大公约数</p>
 *
 * @author : 王松迪
 * 2024-03-05 08:42
 **/
public class BitGcd {

    /**
     * 定理：欧几里得算法， “两个正整数a和b（a>b），它们的最大公约数等于a除以b的余数c和b之间的最大公约数。”
     * 例如： “0和25，25除以10商2余5，那么10和25的最大公约数，等同于10和5的最大公约数”
     * 问题：当两个整数较大时，取模运算的性能会比较差。
     */
    public static int gcd1(int a, int b) {

        int big = Math.max(a, b);
        int min = Math.min(a, b);
        if(big % min == 0) {
            return min;
        }
        return gcd1(a, b);
    }

    /**
     * 定理：九章算数，更相减损术，“两个正整数a和b（a>b），它们的最大公约数等于a-b的差值c和较小数b的最大公约数”
     * 例如 ： “例如10和25，25减10的差是15，那么10和25的最大公约数，等同于10和15的最大公约数。
     * 问题： “更相减损术依靠两数求差的方式来递归，运算次数肯定远大于辗转相除法的取模方式”，10000 和 1需要递归 9999次。
     */
    public static int gcd2(int a, int b) {
        if(a == b) {
            return a;
        }

        int big = Math.max(a, b);
        int min = Math.min(a, b);

        return gcd2(big - min, min);

    }

    /**
     * 结合 “欧几里得算法” 与 “更相减损术”
     * 适用位运算，提高运算效率。
     *
     * 定理 ： 假设a和b都为偶数，可以表示为a = 2 * a1和b = 2 * b1，
     * 其中a1和b1都为奇数。根据最大公约数的定义，gcd(a, b) = gcd(a1, b1) * 2。
     * 通过右移一位，可以得到a1和b1的最大公约数gcd(a1, b1)，然后将该最大公约数左移一位，得到gcd(a, b)
     *
     * 递归求出最大公约数
     */
    public static int gcd(int a , int b) {

        System.out.println("count a "  + a +  " b " + b);
        //跳出递归条件
        if(a == b) {
            return a;
        }

        //奇偶数判断
        if((a & 1) == 0 && (b & 1) == 0) {
            return gcd(a >> 1, b >> 1) << 1;
        } else if ((a & 1) == 0 && (b & 1) != 0) {
            return gcd(a >> 1, b);
        } else if ((a & 1 ) != 0 && (b & 1) == 0) {
            return gcd(a, b >> 1);
        } else {
            int big = Math.max(a, b);
            int small = Math.min(a, b);
            return gcd(big - small, small);
        }
    }

    public static void main(String[] args) {

        int gcd = gcd(8, 4);
        System.out.println(gcd);
    }
}

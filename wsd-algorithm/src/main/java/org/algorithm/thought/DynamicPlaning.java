package org.algorithm.thought;

import java.util.Arrays;

/**
 * <h3>wsd-project</h3>
 * <p>动态规划</p>
 *
 * @author : 王松迪
 * 2024-03-07 10:42
 **/
public class DynamicPlaning {


    /**
     * 动态规划
     * @param w 人数
     * @param n 金矿数量
     * @param p 挖矿所需人数
     * @param g 矿所需含金量
     */
    public static int getBest(int w, int n, int[] p, int[] g, int[] memo) {
        if(n ==0 || w == 0) {
            return 0;
        }

        if(n >= 1 && memo[n - 1] != Integer.MIN_VALUE) {
            System.out.println("meme  n : " +  (n - 1) + "  value : " +  memo[n - 1]);
            return memo[n - 1];
        }

        //当人数小于挖矿所需人数
        if(w < p[n - 1]) {
            return memo[n - 1] = getBest(w, n - 1, p, g, memo);

        }
        //计算挖与不挖此矿的最优解，即适用当前资源不采集与采集的区别
        return memo[n - 1] = Math.max(getBest(w, n-1, p, g, memo), getBest(w-p[n-1], n-1, p, g, memo) + g[n-1]);
    }

    public static void main(String[] args) {
        int w = 10;
        //挖矿所需的人数
        int[] p = {5, 5, 3, 4, 3};
        //金矿的含金量
        int[] g = {400, 500, 200, 300, 350};

        int[] memo = new int[p.length];
        Arrays.fill(memo, Integer.MIN_VALUE);

        System.out.println(getBest(w, g.length, p ,g, memo));
    }
}

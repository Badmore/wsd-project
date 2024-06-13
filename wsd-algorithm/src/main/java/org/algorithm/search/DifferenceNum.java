package org.algorithm.search;

import java.util.Arrays;

/**
 * <h3>wsd-project</h3>
 * <p>差分数组</p>
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
        // 利用差分数组和前缀和的方式
        int[] d = new int[]{1,2,3,4,5,6,7,8};
        // {1,1,1,1,1,1,1,1}
        int[] f = new int[d.length];
        f[0] = d[0];
        for(int i = 1; i < d.length; i++) {
            f[i] = d[i] - d[i - 1];
        }



    }
}

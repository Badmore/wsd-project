package org.algorithm.search;

import java.util.Arrays;
import java.util.Random;

/**
 * <h3>wsd-project</h3>
 * <p>前缀和</p>
 *
 *  适用于原始数组不会被修改的情况下，频繁查询区间和的场景。例如查询某个分数段一共有多少个学生
 *  可以做到 O （1）
 * @author : 王松迪
 * 2024-05-30 09:06
 **/
public class PrefixSum {

    private final int[] prefixSum;

    public PrefixSum(int[] nums) {
        int n = nums.length;
        prefixSum = new int[n+1];
        for (int i = 1; i <= n; i++) {
            prefixSum[i] = prefixSum[i-1] + nums[i-1];
        }
    }

    public int sumRange(int i, int j) {
        return prefixSum[j+1] - prefixSum[i];
    }

    public static void main(String[] args) {
        int[] ints = new int[]{51, 55, 60, 65, 70, 75, 80, 85, 90, 95, 100};
        Scorer scorer = new Scorer(ints);
        System.out.println("区间人数" + scorer.getRangeCount(50, 60));

    }
}


/**
 * 这个技巧在生活中运用也挺广泛的，比方说，你们班上有若干同学，每个同学有一个期末考试的成绩（满分 100 分），那么请你实现一个 API，输入任意一个分数段，返回有多少同学的成绩在这个分数段内。
 */
class Scorer {

    private final int[] countSum;

    public Scorer(int[] scores) {
        int[] count = new int[100 + 1];
        for (int score : scores) {
            count[score]++;
        }

        countSum = new int[count.length + 1];
        countSum[0] = 0;
        for (int i = 1;  i < count.length; i++) {
            countSum[i] = countSum[i - 1] + count[i - 1];
        }

    }

    int getRangeCount(int begin ,int end) {
        return countSum[end + 1] - countSum[begin];
    }

}

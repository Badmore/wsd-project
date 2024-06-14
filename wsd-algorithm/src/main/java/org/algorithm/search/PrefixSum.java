package org.algorithm.search;

/**
 * <h3>wsd-project</h3>
 * <p>前缀和</p>
 *
 * 给定一个列表num，长度是n，如果想求出子列表[l, r]，都是闭区间的子列表的和，就可以用前缀和。
 * 朴素做法很容易想到，就是从遍历区间[l, r]累加即可，显然这是O(n)复杂度，如果查询就一次两次的，当然 也可以，但如果查询次数多了，显然效率差，如果查询m次，时间复杂度会上升到O(mn)。
 *
 * 特性：sum(l, r) = nums[l] + nums[l + 1] + ... + nums[r-1] + nums[r]
 *            = nums[0] + nums[1] +... + nums[l-1] + nums[l] + ... nums[r-1] + nums[r] - (nums[0] + nums[1] +...+ nums[l-1])
 *            = preSum[r] - preSum[l-1]
 *
 *
 *
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
        for (int i = 1;  i <= count.length; i++) {
            countSum[i] = countSum[i - 1] + count[i - 1];
        }

    }

    int getRangeCount(int begin ,int end) {
        return countSum[end + 1] - countSum[begin];
    }

    public static void main(String[] args) {
        int[] ints = new int[]{51, 55, 60, 65, 70, 75, 80, 85, 90, 95, 100};
        Scorer scorer = new Scorer(ints);
        System.out.println("50 ～ 60 区间人数" + scorer.getRangeCount(50, 60));
        System.out.println("50 ～ 90 区间人数" + scorer.getRangeCount(50, 90));
        System.out.println("90 ～ 100 区间人数" + scorer.getRangeCount(90, 100));

    }

}

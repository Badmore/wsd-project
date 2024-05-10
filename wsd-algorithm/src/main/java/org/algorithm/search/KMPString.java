package org.algorithm.search;

/**
 * <h3>wsd-project</h3>
 * <p>kmp 字符串搜索</p>
 *
 *
 * @author : 王松迪
 * 2024-05-10 08:39
 **/
public class KMPString {


    public static void main(String[] args) {
        String pat = "ababc";
        System.out.println(new KMP(pat).search("abababcababc"));
    }
}

class KMP{

    /**
     * KMP 的精髓在于 使用 dp 数组记录当前值在 pat 中是否可以进行下一次匹配的状态转移机制。
     * 在比较进行中时也是将 模式串 前缀比较，使用相同前缀的状态转移来控制。
     * 可以理解为 后面的状态转移使用前面相同前缀的状态转移。所以可以作为动态规划的一种。
     */
    private final int[][] dp;

    private final String pat;

    public KMP(String pat) {
        this.pat = pat;

        int M = pat.length();
        this.dp = new int[M][256];
        // 初始化 ，只有知道 pat 的第一个字符 状态才会转移下一个。
        dp[0][pat.charAt(0)] = 1;

        int X = 0;
        for (int j = 1; j < M; j++) {
            for (int c = 0; c < 256; c++) {

                if (c == pat.charAt(j)) {
                    dp[j][c] = j + 1;
                } else {
                    dp[j][c] = dp[X][c];
                }
            }

            System.out.println( pat.charAt(j) + " ... " + X + " ... " + dp[X][pat.charAt(j)]);
            X = dp[X][pat.charAt(j)];
        }
    }

    public int search(String txt) {
        int M = this.pat.length();
        int N = txt.length();

        int j = 0;
        for (int i = 0; i < N; i++) {
            j = this.dp[j][txt.charAt(i)];
            if(j == M) {
                return i - M + 1;
            }
        }
        return -1;
    }


}

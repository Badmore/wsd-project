package org.algorithm.pointer;

/**
 * <h3>wsd-project</h3>
 * <p>利用双指针查询最长回文子串</p>
 *
 * @author : 王松迪
 * 2024-05-28 14:17
 **/
public class LongestPalindrome {


    /**
     * 思路是从头开始遍历，每次遍历以遍历的字符为中心向两边扩散。
     */
    public static String longestPalindrome(String s) {

        String res = "";
        for (int i = 0; i < s.toCharArray().length; i++) {

            String even = palindrome(s, i,
                    //奇偶数判断
                    i % 2 == 0 ? i : i + 1);
            if (even.length() > res.length()) {
                res = even;
            }

        }
        return res;
    }

    /**
     * 双指针从相等字符串开始，向两边扩散。
     */
    private static String palindrome(String s, int l, int r) {

        char[] charArray = s.toCharArray();
        while((l >= 0 && r < charArray.length) && charArray[l] == charArray[r]) {
            l--;
            r++;
        }
        return s.substring(l + 1, r);
    }

    public static void main(String[] args) {
        String s = "sdl;fjsdklfjjklsdhsjasdsakhdfjkghdfhgjdhfaskldjioewurowie4utkldjfg1";
        System.out.println(longestPalindrome(s));
    }
}

package org.algorithm.thought;

/**
 * <h3>wsd-project</h3>
 * <p>删除数字后得到最大数字</p>
 *
 * @author : 王松迪
 * 2024-03-07 09:01
 **/
public class Greedy {

    /**
     * 贪心算法寻求逐步最优解，并且无法回溯
     * 计算输入数字中，去除几个数字后最小的数
     *
     */
    public static String removeDigits(int num, int k) {
        String numStr = String.valueOf(num);
        int numLength = numStr.length();
        int newLength = numLength - k;
        char[] stack = new char[numLength];
        int top = 0;
        for (char c : numStr.toCharArray()) {

            //当栈顶数字出栈（相当于删除数字）
            while(top > 0 && stack[top -1] > c && k > 0) {
                top -= 1;
                k -= 1;
            }
            //遍历到当前的数字栈
            stack[top++] = c;
        }

        int offset = 0;
        while(offset < newLength && stack[offset] == '0') {
                offset++;
        }
        return offset == newLength ? "0" : new String(stack, offset, newLength);
    }


    /**
     * 背包问题
     * 核心思想是计算密度
     *
     *
     */
    public static String pack() {

        return null;
    }
    public static void main(String[] args) {

        System.out.println(removeDigits(6057983, 3));
    }
}

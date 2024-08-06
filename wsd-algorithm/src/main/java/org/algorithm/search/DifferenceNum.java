package org.algorithm.search;

import java.util.Arrays;

/**
 * <h3>wsd-project</h3>
 * <p>差分数组</p>
 * 差分数组的作用是能快速的对区间更新。区间更新是指对于数组num，长度为n，想要对区间[l, r]做更新，比如都加上一个数x，或者都减去一个数y。
 * 常规的实现肯定遍历[l, r]然后对每个元素做更新，这是线性时间O(n)的，
 * 而用差分数组可以在常数时间完成区间更新
 *
 * 特性： 原数组是差分数组的前缀和
 * 所以给差分数组的 index = i 位置 + x，其逆向原数组相当于 i  ~ n  全部都加了 x；
 *
 * 应用：频繁对区间内数据做增减操作；
 *
 *
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

        int[] one = new int[]{0, 1, 2, 3, 4, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23};
        DifferenceNum differenceNum = new DifferenceNum(one);
        //不服 20点后
        differenceNum.increment(20, 23, 1);
        //西瓜 19点后
        differenceNum.increment(19, 23, 1);
        //土豆 19点后
        System.out.println(Arrays.toString(differenceNum.result()));
        differenceNum.increment(3, 7, 10);
        System.out.println(Arrays.toString(differenceNum.result()));


    }
}

class GameTimeCalc {
    private final int[][] gameTimeDiff;

    public GameTimeCalc() {
        this.gameTimeDiff = new int[7][24];

//        for (int j = 0 ; j < 7; j++) {
//            gameTimeDiff[j][0] = num[j][0];
//
//            for(int i = 1; i < num[j].length; i++) {
//                gameTimeDiff[j][i] = num[j][i] - num[j][i - 1];
//            }
//        }


    }

    public void increment(int[] day, int begin, int end) {
        for (int d: day ) {
            gameTimeDiff[d - 1][begin] += 1;
            if(end + 1< gameTimeDiff[d - 1].length) {
                gameTimeDiff[d - 1][end] -= 1;
            }
        }
    }

    public int[][] result() {
        int[][] result = new int[7][24];
        for(int j = 0; j < 7; j++) {
            result[j][0] = gameTimeDiff[j][0];
            for(int i = 1; i < gameTimeDiff[j].length; i++) {
                result[j][i] = result[j][i - 1] + gameTimeDiff[j][i];
            }
        }
        return result;
    }


    public static void main(String[] args) {
        GameTimeCalc gameTimeCalc = new GameTimeCalc();
        //不服 工作日晚8点半后，周末随时
        gameTimeCalc.increment(new int[]{1,2,3,4,5}, 21, 23);
        gameTimeCalc.increment(new int[]{6,7}, 0,23);
        //西瓜 工作日七点后，周末随时
        gameTimeCalc.increment(new int[]{1,2,3,4,5}, 19, 23);
        gameTimeCalc.increment(new int[]{6,7}, 0,23);
        //土豆 周一到周四 晚 7 点后 周末十点后
        gameTimeCalc.increment(new int[]{1,2,3,4}, 19, 23);
        gameTimeCalc.increment(new int[]{6,7}, 22,23);
        //白又白黑又黑  周三，周六随时
        gameTimeCalc.increment(new int[]{3}, 0, 23);
        gameTimeCalc.increment(new int[]{6}, 0,23);
        //小小o元素  周三 周六随时
        gameTimeCalc.increment(new int[]{3}, 0, 23);
        gameTimeCalc.increment(new int[]{6}, 0,23);
        // 樱花劫 晚7点后
        gameTimeCalc.increment(new int[]{1,2,3,4,5,6,7}, 19, 23);
        //池鱼 工作日七点后 周末晚上随时
        gameTimeCalc.increment(new int[]{1,2,3,4,5}, 19, 23);
        gameTimeCalc.increment(new int[]{6,7}, 0,23);
        //工作日七到八点和九点半以后 ，周末除八到九点半以
        gameTimeCalc.increment(new int[]{1,2,3,4,5}, 19, 20);
        gameTimeCalc.increment(new int[]{1,2,3,4,5}, 22, 23);
        gameTimeCalc.increment(new int[]{6,7}, 0,20);
        gameTimeCalc.increment(new int[]{6,7}, 22,23);
        //羚羊 工作日八点半后都可以
//        gameTimeCalc.increment(new int[]{1,2,3,4,5}, 21, 23);
//        gameTimeCalc.increment(new int[]{6,7}, 0,23);
        //曦曦，替补白天都有时间
        gameTimeCalc.increment(new int[]{1,2,3,4,5,6,7}, 0, 23);


        System.out.println(Arrays.deepToString(gameTimeCalc.result()));

        TimeSum timeSum = new TimeSum(gameTimeCalc.result());

        for (int i = 0; i < 7; i++) {
            int rangeCount= timeSum.getRangeCount(i, 19, 21);
            if(rangeCount >= 8 * 2) {
                System.out.println("周" + (i + 1) + "    19～21点 ==== " + rangeCount);
            }
            rangeCount= timeSum.getRangeCount(i, 21, 23);
            if(rangeCount >= 8 * 2) {
                System.out.println("周" + (i + 1) + "    21～23点 ==== " + rangeCount);
            }
            rangeCount =timeSum.getRangeCount(i, 22, 23);
            if(rangeCount >= 16) {
                System.out.println("周" + (i + 1) + "    22～点 ==== " + rangeCount);

            }

        }
    }
}




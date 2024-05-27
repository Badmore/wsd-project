package org.algorithm.sort;

import java.util.Arrays;

/**
 * <h3>wsd-project</h3>
 * <p>基数排序</p>
 *
 * @author : 王松迪
 * 2024-05-23 10:17
 **/
public class RadixSort {

    private static final int ASCII_RANGE = 128;


    private static void radixSort(int[] array) {

        //第一位 0～9，第二位按照最大为数组中所有数据都放到同一个桶里
        int[][] buckets = new int[10][array.length];
        int[] bockedCounter = new int[10];

        //计算出数组中所有数的最大值的位数。
        int max = array[0];
        for (int i : array) {
            if(max < i){
                max = i;
            }
        }
        int maxLength = (max + "").length();

        //构建大循环体，从低位向高位遍历,n += 10,用来取模 了；例如 （128 / 1） % 10 = 个位为 8 ； （128 / 10） % 10 = 十位为 2；
        for(int i = maxLength, n = 1; i >= 1; i--, n *= 10) {

            //做技术排序
            for (int k : array) {
                int digitIndex = (k / n) % 10;
                buckets[digitIndex][bockedCounter[digitIndex]++] = k;
            }


            //将统计完的结果从如放到原数组中。
            int index = 0;
            for(int j = 0 ; j < bockedCounter.length; j++) {
                if(bockedCounter[j] > 0) {
                    for(int k = 0; k < bockedCounter[j]; k++) {
                        array[index++] = buckets[j][k];
                    }
                }
                bockedCounter[j] = 0;
            }
            System.out.println("第" + i + "轮排序结果：" + Arrays.toString(array));
        }
    }

    public static void main(String[] args) {
        int[] array = {38, 27, 43, 3, 9, 82, 10,0 ,1,2, 1289, 129344};
        radixSort(array);
        System.out.println(Arrays.toString(array));
    }


}

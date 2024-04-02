package org.algorithm.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * <h3>wsd-project</h3>
 * <p>希尔排序</p>
 *
 * @author : 王松迪
 * 2024-03-21 11:06
 **/
public class ShellSort {

    public static void sort(int[] arr) {

        int gap = arr.length >> 1;
        while(gap > 1) {
            gap >>= 1;
            for (int i = 0; i < arr.length; i++) {
                int gapIndex = i + gap;
                while(gapIndex < arr.length) {
                    int swapValue = arr[gapIndex];
                    int gapCompareIndex = gapIndex - gap;
                    for(; gapCompareIndex >= 0 && swapValue < arr[gapCompareIndex]; gapCompareIndex -= gap) {
                        arr[gapCompareIndex + gap] = arr[gapCompareIndex];
                    }
                    arr[gapCompareIndex + gap] = swapValue;
                    gapIndex += gap;
                }
            }
        }
    }
    public static void main(String[] args) {

//        new ShellSort().run();
        int[] array = new Random(100).ints(0, 100).distinct().limit(100).toArray();
        sort(array);
        System.out.println(Arrays.toString(array));


    }

}



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

        int gap = arr.length;
        while(gap > 1) {
            gap >>= 1;
            for (int i = 0; i < arr.length; i++) {
                int ri = i + gap;
                while(ri < arr.length) {
                    int rv = arr[ri];
                    int li = ri - gap;
                    for(; li >= 0 && rv < arr[li]; li -= gap) {
                        arr[li + gap] = arr[li];
                    }
                    arr[li + gap] = rv;
                    ri += gap;
                }
            }
        }
    }
    public static void main(String[] args) {
        int[] array = new Random(100).ints(0, 100).distinct().limit(100).toArray();
        sort(array);
        System.out.println(Arrays.toString(array));
    }

}



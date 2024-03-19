package org.algorithm.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * <h3>wsd-project</h3>
 * <p>堆排序</p>
 *
 * @author : 王松迪
 * 2024-02-28 08:54
 **/

public class HeapSort {
    public static void downAdjust(int[] array, int pi, int length) {
        //保存付节点的值，用于最后的赋值
        int temp = array[pi];
        int ci = 2 * pi + 1;
        while(ci < length) {
            //如果有右子节点，且右子节点大于左子节点的值，则定位到右子节点
            if (ci + 1 < length && array[ci + 1] > array[ci]) {
                ci ++ ;
            }

            //如果父节点的值大于（等于）子节点中较大的值，则终止。
            if(temp >= array[ci]) {
                break;
            }

            array[pi] = array[ci];
            pi = ci;
            ci = 2 * ci + 1;
        }

        array[pi] = temp;
    }

    public static void heapSort(int[] array) {
        //构建大堆

        System.out.println(array.length);
        for (int i = (array.length - 1) / 2 ; i >= 0; i--) {
            System.out.println("i = " + i);
            downAdjust(array, i, array.length);
        }

    }

    public static void main(String[] args) {
        int[] array = new Random(100).ints(0, 99).limit(10).toArray();
        heapSort(array);
        System.out.println(Arrays.toString(array));

    }
}

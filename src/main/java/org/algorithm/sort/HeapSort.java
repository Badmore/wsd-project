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

        int temp = array[pi];
        int ci = 2 * pi + 1;
        while(ci < length) {
            //如果右右子节点，且右子节点大于左子节点的值，则定位到右子节点
            if (ci + 1 < length && array[ci + 1] > array[ci]) {
                ci ++ ;
            }

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
        for (int i = (array.length -2) / 2 ; i >= 0; i--) {
            downAdjust(array, i, array.length);
        }

        for (int i = array.length -1; i > 0; i--) {
            int temp = array[i];
            array[i] = array[0];
            array[0] = temp;
            //下沉调整
            downAdjust(array, 0 ,i);
        }
    }

    public static void main(String[] args) {
        int[] array = new Random(100).ints().limit(10).toArray();
        heapSort(array);
        System.out.println(Arrays.toString(array));
    }
}

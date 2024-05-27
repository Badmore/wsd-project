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

    private final int[] array;

    public HeapSort(int[] arr) {
        this.array = arr;
    }

    public void downAdjust(int pi, int length) {
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

    /**
     * 堆化
     * @param array
     */
    public void heapify(int[] array) {
        //构建大堆

        System.out.println(array.length);
        for (int i = (array.length - 1) / 2 ; i >= 0; i--) {
            System.out.println("i = " + i);
            downAdjust(i, array.length);
        }

    }


    public int getMaxValue() {
        if(array.length == 0) {
            return -1;
        }

        //从堆中获取最大值，并将堆的最后一个节点放到堆的第一个节点，然后对第一个节点进行下沉操作
        int maxValue = array[0];
        array[0] = array[array.length - 1];
        array[array.length - 1] = Integer.MIN_VALUE;
        downAdjust(0, array.length - 1);

        return maxValue;
    }

    public static void main(String[] args) {
        int[] array = new Random().ints(0, 99).limit(50).distinct().toArray();
        HeapSort heapSort = new HeapSort(array);
        heapSort.heapify(array);
        System.out.println(Arrays.toString(array));

        int count = array.length / 2;
        while(--count >= 0) {
            System.out.println(heapSort.getMaxValue());
        }

    }
}

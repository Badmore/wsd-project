package org.algorithm.sort;

import java.util.Random;

/**
 * <h3>wsd-project</h3>
 * <p>快速排序</p>
 *
 * @author : 王松迪
 * 2024-02-27 08:46
 **/
public class QuickSort {


    void quickSort(int[] array, int startIndex, int endIndex) {
        if(startIndex >= endIndex) {
            return ;
        }

        int pivotIndex = side2Partition(array, startIndex, endIndex);

        quickSort(array, startIndex, pivotIndex - 1);
        quickSort(array, pivotIndex + 1, endIndex);
    }


    //双边循环法
    int side2Partition(int[] array, int startIndex, int endIndex) {


        //取第一个位置，也可以是随机的，随机获取的效果更好
        int pivot = array[startIndex];
        int li = startIndex;
        int ri = endIndex;
        while (li != ri) {

            //控制right指针与 pivot 比较，并左移
            while (li < ri && array[ri] > pivot) {
                ri --;
            }
            //控制left 指针比较并右移
            while(li < ri && array[li] <= pivot) {
                li ++;
            }

            if(li < ri) {
                int p = array[li];
                array[li] = array[ri];
                array[ri] = p;
            }
        }

        //pivot 和 指针重合位置交换
        array[startIndex] = array[ri];
        array[ri] = pivot;

        return ri;
    }



    public static void main(String[] args) {

        Random random = new Random(100);
        int[] array = random.ints().limit(10).toArray();
        QuickSort quickSort = new QuickSort();
        long startTime = System.currentTimeMillis();
        quickSort.quickSort(array, 0, array.length - 1);
        System.out.println("cost time:" + (System.currentTimeMillis() - startTime));


    }
}

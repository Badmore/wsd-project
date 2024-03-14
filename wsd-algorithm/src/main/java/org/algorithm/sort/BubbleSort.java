package org.algorithm.sort;

import java.util.Random;

/**
 * <h3>wsd-project</h3>
 * <p>冒泡排序</p>
 *
 * @author : 王松迪
 * 2024-02-26 09:09
 **/

public class BubbleSort {


    public static void main(String[] args) {

        int rMax = 10000;
        int limit = 10000;
        Random random = new Random(rMax);

        BubbleSort bubbleSort = new BubbleSort();
        bubbleSort.sort(random.ints().limit(limit).toArray());

        bubbleSort.promotionSort(random.ints().limit(limit).toArray());

        bubbleSort.cockSort(random.ints().limit(limit).toArray());
    }

     void sort(int array[]){
         long startTime = System.currentTimeMillis();
         for (int i = 0; i < array.length; i++) {
            //level2 有序标记，每一轮的初始值都是 true, 减少有序区的遍历
            boolean isSorted = true;
            //level1 为什么是array.length - i - 1； 次区域是代表有序区域，无需遍历
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;

                    //level2 因为有元素进行交换，所以不是有序的标记变为 false
                    isSorted = false;
                }
            }
            if(isSorted) {
                break;
            }
        }

         System.out.println("BubbleSort 耗时：" + (System.currentTimeMillis() - startTime) + " ms");
    }

    void promotionSort(int array[])  {
        long startTime = System.currentTimeMillis();
        int lastExchangedIndex = 0;
        int sortBorder = array.length - 1;
        for (int i = 0; i < array.length - 1; i++) {
            boolean isSorted = true;
            for (int j = 0; j < sortBorder; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;

                    isSorted = false;
                    lastExchangedIndex = j;
                }
            }
            sortBorder = lastExchangedIndex;
            if (isSorted) {
                break;
            }
        }
        System.out.println("promotionSort 耗时：" + (System.currentTimeMillis() - startTime) + " ms");
    }

    void cockSort(int[] array) {
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < array.length / 2; i++) {

            //有序标记，每一轮的初始值都是 true
            boolean isSorted = true;
            //奇数轮，从左向右比较和交换。
            for(int j = i; j < array.length - 1 - i; j++) {
                if(array[j] > array[j+1]) {
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j + 1] = temp;

                    isSorted = false;
                }
            }

            if(isSorted) {
                break;
            }

            //在偶数轮之前，将 isSorted 重新标记为 true
            isSorted = true;
            //偶数轮，从右向左比较和交换
            for(int j = array.length - 1 - i; j > i; j--) {

                if(array[j] < array[j-1]) {
                    int temp = array[j];
                    array[j] = array[j-1];
                    array[j - 1] = temp;

                    isSorted = false;
                }
            }
            if(isSorted) {
                break;
            }

        }
        System.out.println("cockSort 耗时：" + (System.currentTimeMillis() - startTime) + " ms");
    }

}

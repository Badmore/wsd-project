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
        bubbleSort.promotionSort(random.ints().limit(limit).toArray());
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


}

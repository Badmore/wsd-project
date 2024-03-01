package org.algorithm.sort;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Stack;

/**
 * <h3>wsd-project</h3>
 * <p>快速排序</p>
 *
 * @author : 王松迪
 * 2024-02-27 08:46
 **/
public class QuickSort {

    void quickSortWithStack(int[] array, int startIndex, int endIndex){

        Stack<Map<String, Integer>> quickSortStack = new Stack<>();
        Map<String, Integer> stackNode = new HashMap<>();
        stackNode.put("startIndex", startIndex);
        stackNode.put("endIndex", endIndex);
        quickSortStack.push(stackNode);

        while (!quickSortStack.isEmpty()) {
            Map<String, Integer> pop = quickSortStack.pop();
            int pivotIndex = singleSidePartition(array, pop.get("startIndex"), pop.get("endIndex"));
            if(stackNode.get("startIndex") < pivotIndex - 1) {
                Map<String, Integer> leftNode = new HashMap<>();
                leftNode.put("startIndex", stackNode.get("startIndex"));
                leftNode.put("endIndex", pivotIndex - 1);
                quickSortStack.push(leftNode);
            }

            if(pivotIndex + 1 < stackNode.get("endIndex")) {
                Map<String, Integer> rightParam = new HashMap<>();
                rightParam.put("startIndex", pivotIndex + 1);
                rightParam.put("endIndex", stackNode.get("endIndex"));
                quickSortStack.push(rightParam);
            }
        }

    }

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


        //取第一个位置，也可以是随机的
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

    int singleSidePartition(int[] array, int startIndex, int endIndex) {
        int pivot = array[startIndex];
        int mark = startIndex;

        for(int i = startIndex ; i < endIndex; i ++ ) {
            if(array[i] < pivot) {
                mark++;
                int p = array[mark];
                array[mark] = array[i];
                array[i] = p;
            }

        }

        array[startIndex] = array[mark];
        array[mark] = pivot;
        return mark;
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

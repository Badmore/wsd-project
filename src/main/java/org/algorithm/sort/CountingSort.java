package org.algorithm.sort;

import org.algorithm_visualizer.Randomize;

import java.util.Arrays;
import java.util.Random;

/**
 * <h3>wsd-project</h3>
 * <p>计数排序</p>
 *
 * @author : 王松迪
 * 2024-02-28 09:13
 **/
public class CountingSort {

    public static int[] countSortPro(int[] arr) {
        //1 计算出数组中最大值与最小值的差
        int max = arr[0];
        int min = arr[0];
        for(int i = 1; i < arr.length; i++) {
            if(arr[i] > max) {
                max = arr[i];
            }

            if(arr[i] < min) {
                min = arr[i];
            }
        }

        int d = max - min;

        //2. 统计对应元素的个数
        int[] countArray = new int[d + 1];
        for (int j : arr) {
            countArray[j - min]++;
        }

        //3.统计数组变形，后面的元素等于前面元素之和
        for(int i = 1; i < countArray.length; i++) {
            countArray[i] += countArray[i - 1];
        }

        //倒序遍历原始数组，从统计数组找到正确位置，输出到结果数组
        int[] sortedArray = new int[arr.length];
        for(int i = arr.length - 1; i >= 0; i--) {
            int countArrIndex = arr[i] - min;
            //此处取出的值是为了作为 sort 数组的下标，所以要减 1
            //因为 sortArrIndex 表示的是数组中元素的位置，而 countArray 表示的是元素出现的次数
            int sortArrIndex = countArray[countArrIndex] - 1;
            sortedArray[sortArrIndex] = arr[i];
            countArray[countArrIndex] --;
        }
        return sortedArray;
    }


    public static int[] countSort(int[] arr) {
        //得到数列最大值
        //时间复杂度 O(n)
        int max = arr[0];
        for(int i = 1; i < arr.length; i++ ) {
            if(arr[i] > max) {
                max = arr[i];
            }
        }

        //根据数列最大值，确定统计数组的长度
        int[] cArr = new int[max + 1];
        //遍历数列，填充统计数组
        for (int i = 0; i < arr.length; i++) {
            //取出值
            int value = arr[i];
            //值作为下标的数自增 1
            cArr[value]++;
        }

        //遍历统计数组，输出结果
        int index = 0;
        int[] sortedArr = new int[arr.length];
        for (int i=0; i < cArr.length; i++) {
            for (int j=0;j < cArr[i]; j++ ){
                sortedArr[index++] = i;
            }
        }

        return sortedArr;
    }

    public static void main(String[] args) {
        int[] array = new Random().ints(0, 10).limit(10).toArray();
        int[] ints = countSortPro(array);

        System.out.println(Arrays.toString(ints));

    }
}

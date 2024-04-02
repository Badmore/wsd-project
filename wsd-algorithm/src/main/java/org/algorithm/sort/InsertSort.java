package org.algorithm.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * <h3>wsd-project</h3>
 * <p>插入排序</p>
 *
 *
 * @author : 王松迪
 * 2024-03-21 09:54
 **/
public class InsertSort{

    public static void sort(int[] arr) {

        for (int i = 1; i < arr.length; i++) {
            int swapValue = arr[i];
            int li = i - 1;
            while(li >= 0 && swapValue < arr[li]) {
                arr[li + 1] = arr[li];
                li--;
            }
            arr[li + 1] = swapValue;
        }
    }

    public static void main(String[] args) {
        int[] array = new Random(100).ints(0, 99).limit(10).toArray();
        sort(array);
        System.out.println(Arrays.toString(array));
    }
}

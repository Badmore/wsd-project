package org.algorithm.sort.bucket;

/**
 * <h3>wsd-project</h3>
 * <p>有序最大差值</p>
 *
 * @author : 王松迪
 * 2024-03-05 10:46
 **/
public class MaxSortedDistance {

    public static int getMaxSortedDistance(int[] arr) {
        int max = arr[0];
        int min = arr[0];
        for (int i : arr) {
            if (i > max) {
                max = i;
            }
            if (i < min) {
                min = i;
            }
        }
        int d = max - min;
        if(d == 0) {
            return 0;
        }

        int bucketNum = arr.length;
        int[][] bucketArr = new int[bucketNum][2];
        for (int i : arr) {
            int bucketIndex = (i - min) * (bucketNum - 1) / d;
            int x = bucketArr[bucketIndex][0];
            int n = bucketArr[bucketIndex][1];
            if(x  == 0 || i > x) {
                bucketArr[bucketIndex][0] = i;
            }
            if(n == 0 || i < n) {
                bucketArr[bucketIndex][1] = i;
            }
        }

        int tempMax = 0;
        int maxDistance = 0;
        for (int[] ints : bucketArr) {
            int x = ints[0];
            int n = ints[1];
            if(x - tempMax > maxDistance) {
                maxDistance = x;
            }
        }


        return 0;
    }

    public static void main(String[] args) {

        int[] arr = new int[]{2, 3, 10};
        getMaxSortedDistance(arr);
    }
}

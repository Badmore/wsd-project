package org.algorithm.sort.bucket;

import java.util.Arrays;

/**
 * <h3>wsd-project</h3>
 * <p>有序最大差值</p>
 *
 * @author : 王松迪
 * 2024-03-05 10:46
 **/
public class MaxSortedDistance {

    public static int getMaxSortedDistance(int[] arr) {

        //1 计算桶区间
        //min = 偏移量
        int min = arr[0];
        int max = arr[0];
        for (int i : arr) {
            if(i > max) {
                max = i;
            }
            if(i < min) {
                min = i;
            }
        }
        //d = 桶
        int d = max - min;
        System.out.println("1 :"  + d);


        //2 创建桶
        //桶的个数
        int bucketCount = Math.min(1,  d / arr.length) + 1;
        //存储桶内最大最小值 [0] = 最小值；[1] = 最大值
        int[][] thresholdStore = new int[bucketCount][2];
        for (int[] ints : thresholdStore) {
            Arrays.fill(ints,  Integer.MAX_VALUE);
        }
        for (int i : arr) {
            //fixme 计算桶索引；；重点理解
            int buckedIndex = (i - min) * (bucketCount - 1) / d;
            System.out.println("3 buckedIndex :" + buckedIndex);
            if(thresholdStore[buckedIndex][0] == Integer.MAX_VALUE ||  thresholdStore[buckedIndex][0] > i) {
                thresholdStore[buckedIndex][0] = i;
            }
            if(thresholdStore[buckedIndex][1] == Integer.MAX_VALUE || thresholdStore[buckedIndex][1] < i) {
                thresholdStore[buckedIndex][1] = i;
            }
        }

        System.out.println("4 thresholdStroe : " + Arrays.deepToString(thresholdStore));

        int maxDistance = 0;
        int leftMax = thresholdStore[0][1];
        //计算差值
        for (int[] ints : thresholdStore) {
            int in = ints[0];
            int ax = ints[1];
            //无效桶跳过
            if(in ==  Integer.MAX_VALUE && ax == Integer.MAX_VALUE) {
                continue;
            }

            if(in - leftMax > maxDistance) {
                maxDistance = in - leftMax;
            }

            leftMax = ax;
        }

        return maxDistance;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{-1, 15};
        System.out.println(getMaxSortedDistance(arr));
    }
}

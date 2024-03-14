package org.algorithm.sort.bucket;

import java.util.*;

/**
 * <h3>wsd-project</h3>
 * <p>桶排序</p>
 *
 * @author : 王松迪
 * 2024-02-29 08:34
 **/
public class BucketSort {

    double[] bucketSort(double[] arr) {

        //1. 计算数列差值
        double max = arr[0];
        double min = arr[0];
        for (double v : arr) {
            if (v > max) {
                max = v;
            }

            if (v < min) {
                min = v;
            }
        }
        double d = max - min;

        //2. 初始化桶
        int bucketNum = arr.length;
        List<LinkedList<Double>> bucketList = new ArrayList<>(bucketNum);
        for(double v : arr){
            bucketList.add(new LinkedList<>());
        }

        //3. 遍历数列，将数列元素放入桶中
        for(double v : arr) {
            /**
             * 此处是核心思想
             * 偏移量 / 区间跨度 = 桶索引
             */
            int bucketIndex = (int) ((v - min)  * (bucketNum - 1) / d);
            bucketList.get(bucketIndex).add(v);
        }

        //4.对每个桶内部进行排序
        for(LinkedList<Double> lk : bucketList) {
            Collections.sort(lk);
        }

        //5.输出全部元素
        double[] output = new double[arr.length];
        int index = 0;
        for (LinkedList<Double> l : bucketList) {
            for (Double v : l) {
                output[index++] = v;
            }
        }
        return output;
    }

    public static void main(String[] args) {
        double[] array = new Random().doubles(1, 10).limit(10).toArray();
        System.out.println(Arrays.toString(array));

        double[] doubles = new BucketSort().bucketSort(array);
        System.out.println(Arrays.toString(doubles));
    }
}


package org.algorithm.sort;

import java.rmi.server.ExportException;

/**
 * <h3>wsd-project</h3>
 * <p>希尔排序</p>
 *
 * @author : 王松迪
 * 2024-03-21 11:06
 **/
public class ShellSort extends ExampleSort {
    @Override
    protected void sort(Comparable[] a) {

        //将数组随机分治成 n 个 子数组；子数组的最大长度为 h,
        int h =  a.length  >> 2;
        int n = a.length / h;

        Comparable[][] ca = new Comparable[n][h];
        for (int i = 0; i < a.length; i++) {
            ca[(i - (i / (h - 1))) / h][i % h] = a[i];
        }

        //将子数组插入排序排序
        for (Comparable[] comparables : ca) {
            new InsertSort().sort(comparables);
        }


        //将整体进行插入排序
        for (int i = 0; i < ca.length; i++) {
            int b = i * ca[i].length;
            for (int j = 0; j < ca[i].length; j++) {
                a[b + j] = ca[i][j];
            }
        }
        new InsertSort().sort(a);
    }

    public static void main(String[] args) {

//        new ShellSort().run();


    }

    public void sortb(Comparable[] a){
        int N = a.length;
        int h = 1;
        while(h < (N / 3)) { // 1, 4, 13, 40 ,121
            h = 3*h + 1;
            while (h >= 1) {
                // 讲述变为 h 有序
                for (int i = h; i < N; i++) {
                    for (int j = i; j >= h && less(a[j], a[j - h]); j -= h) {
                        exchange(a, j, j-h);
                    }
                }
                h /= 3;
            }
        }
    }
}



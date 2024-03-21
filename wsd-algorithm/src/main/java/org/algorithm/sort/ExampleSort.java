package org.algorithm.sort;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Stream;

/**
 * <h3>wsd-project</h3>
 * <p></p>
 *
 * @author : 王松迪
 * 2024-03-21 08:51
 **/
public abstract class ExampleSort {

    protected abstract void sort(Comparable[] a);

    protected boolean less(Comparable source, Comparable target) {
        return source.compareTo(target) < 0;
    }

    protected void exchange(Comparable[] a, int i, int j) {
        Comparable<Object> comparable = a[i];
        a[i] = a[j];
        a[j] = comparable;
    }

    protected void show(Comparable[] a) {
        System.out.println(Arrays.toString(a));
    }

    protected void isSorted(Comparable[] a){
        for (int i = 1; i < a.length; i++) {
            if(less(a[i], a[i-1])){
                System.out.println("排序失败");
                return;
            }
        }
    }

    protected void run(){
        Integer[] array = new Random(10000).ints(0, 10000).distinct().limit(9999).boxed().toArray(Integer[]::new);
        long start = System.currentTimeMillis();
        System.out.println("开始: ");
        sort(array);
        System.out.println("总耗时 " + (System.currentTimeMillis() - start ));
        isSorted(array);
    }
}

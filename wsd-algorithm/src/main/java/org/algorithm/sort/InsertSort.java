package org.algorithm.sort;

/**
 * <h3>wsd-project</h3>
 * <p>插入排序</p>
 *
 * @author : 王松迪
 * 2024-03-21 09:54
 **/
public class InsertSort extends ExampleSort{
    @Override
    protected void sort(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = i ; j > 0 && less(a[j], a[j-1]); j--) {
                exchange(a, j, j - 1);
            }
        }
    }

    public static void main(String[] args) {
        InsertSort insertSort = new InsertSort();

        insertSort.run();
    }
}

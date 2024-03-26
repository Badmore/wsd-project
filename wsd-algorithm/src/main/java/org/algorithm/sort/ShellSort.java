package org.algorithm.sort;

/**
 * <h3>wsd-project</h3>
 * <p>希尔排序</p>
 *
 * @author : 王松迪
 * 2024-03-21 11:06
 **/
public class ShellSort extends ExampleSort {
    @Override
    protected void sort(Comparable[] arr) {
        //递减区间
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int j = gap; j < arr.length; j++) {
                for(int h = j ; h >= gap && less(arr[h], arr[h - gap]); h -= gap) {
                    exchange(arr, h, h - gap);
                    System.out.println("gap =" + gap + ", j = " + j +  ", h = " + h + ", h - gap =" + (h - gap));
                }
            }
        }
    }
    public static void main(String[] args) {

        new ShellSort().run();


    }

}



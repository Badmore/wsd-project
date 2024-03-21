package org.algorithm.sort;

/**
 * <h3>wsd-project</h3>
 * <p>选择排序</p>
 *
 * @author : 王松迪
 * 2024-03-21 08:47
 **/
public class SelectSort extends ExampleSort{

    /**
     *
     * 原理：从头到尾遍历，找出最小的值，跟前面的值作比较，依次递推。
     *
     * 缺点：一个已经有序的数组，或元素全部相同的数组，和一个随机数据所需要的时间是一样长。
     * 对比：其他算法更善于利用初始状态。
     * 优点：移动次数是最少的
     *
     * 总结：稳定
     * @param a
     */
    public void sort(Comparable[] a) {


        for (int i = 0; i < a.length; i++) {
            int minIndex = i;
            for (int j = i+1; j < a.length; j++){
                if(less(a[j], a[minIndex])) {
                    minIndex = j;
                }
            }
            exchange(a, i, minIndex);
        }

    }

    public static void main(String[] args) {

        SelectSort selectSort = new SelectSort();
        selectSort.run();

    }
}

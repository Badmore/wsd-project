package org.algorithm.sort;

import java.util.Arrays;
import java.util.Comparator;

/**
 * <h3>wsd-project</h3>
 * <p>堆排序</p>
 *
 * @author : 王松迪
 * 2024-02-23 08:38
 **/
public class PriorityQueue<T> {
    
    private Object[] array;

    private int size;

    private Comparator<? super T> comparator;

    public PriorityQueue(int capacity, Comparator<? super T> comparator){
        this.array = new Object[capacity];
        this.comparator = comparator;
    }
    
    public void add(T key) {
    
        if(size >=  array.length) {
            //扩容
            resize();
        }
        
        array[size++] = key;
        upAdjust();
    }

    void resize() {
        int newSize = this.size * 2;
        this.array = Arrays.copyOf(this.array, newSize);
    }

    public boolean isEmpty() {
        return size == 0;
    }
    
    public T poll()  {
        if(size <= 0 ) {
            try {
                throw new Exception("the queue is empty");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        //顶点
        T vertex = (T) array[0];
        //让最后一个元素移动到堆顶
        array[size] = null;
        array[0] = array[--size];
        downAdjust();
        return vertex;
    }

    private void upAdjust() {
        int ci = size - 1;
        int pi = (ci - 1) / 2;

        T cvT = (T) array[ci];

        while(ci > 0 && comparator.compare(cvT, (T)array[pi]) > 0) {
            array[ci] = array[pi];
            ci = pi;
            pi = pi / 2;
        }

        array[ci] = cvT;
    }

    private void downAdjust() {
        // 保存 parent 节点值。用于最后的赋值
        int pi = 0;
        T pvt = (T)array[pi];
        int ci = 1;
        while(ci < size) {

            //右子存在，且右子大于左子，则将指针转移到右子；
            if(ci + 1 < size && comparator.compare((T)array[ci + 1], (T)array[ci]) > 0) {
                ci++;
            }

            if(comparator.compare(pvt, (T)array[ci]) > 0) {
                break;
            }

            array[pi] = array[ci];
            pi = ci;
            ci = 2 * ci + 1;
        }

        array[pi] = pvt;
    }

    public static void main(String[] args) throws Exception {
        PriorityQueue<Integer> q = new PriorityQueue<>(20, Integer::compare);
        q.add(3);
        q.add(5);
        q.add(10);
        q.add(2);
        q.add(7);
        System.out.println("出队元素" + q.poll());
        System.out.println("出队元素" + q.poll());

    }
    
}

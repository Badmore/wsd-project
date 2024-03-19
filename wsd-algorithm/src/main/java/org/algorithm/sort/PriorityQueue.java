package org.algorithm.sort;

import java.util.Arrays;

/**
 * <h3>wsd-project</h3>
 * <p>堆排序</p>
 *
 * @author : 王松迪
 * 2024-02-23 08:38
 **/
public class PriorityQueue {
    
    private int[] array;
    
    private int size;

    public PriorityQueue(int capacity){
        this.array = new int[capacity];
    }
    
    void enQ(int key) {
    
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
    
    int deQ() throws Exception {
        if(size <= 0 ) {
            throw new Exception("the queue is empty");
        }

        //顶点
        int vertex = array[0];
        //让最后一个元素移动到堆顶
        array[0] = array[--size];
        downAdjust();
        return vertex;
    }

    private void upAdjust() {
        int ci = size - 1;
        int pi = (ci - 1) / 2;

        int cvT = array[ci];

        while(ci > 0 && cvT > array[pi]) {
            array[ci] = array[pi];
            ci = pi;
            pi = pi / 2;
        }

        array[ci] = cvT;
    }

    private void downAdjust() {
        // 保存 parent 节点值。用于最后的赋值
        int pi = 0;
        int pvt = array[pi];
        int ci = 1;
        while(ci < size) {

            //右子存在，且右子大于左子，则将指针转移到右子；
            if(ci + 1 < size && array[ci + 1] > array[ci]) {
                ci++;
            }

            if(pvt >= array[ci]) {
                break;
            }

            array[pi] = array[ci];
            pi = ci;
            ci = 2 * ci + 1;
        }

        array[pi] = pvt;
    }

    public static void main(String[] args) throws Exception {
        PriorityQueue q = new PriorityQueue(20);
        q.enQ(3);
        q.enQ(5);
        q.enQ(10);
        q.enQ(2);
        q.enQ(7);
        System.out.println("出队元素" + q.deQ());
        System.out.println("出队元素" + q.deQ());

    }
    
}

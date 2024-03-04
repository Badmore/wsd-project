package org.algorithm.stack;

import java.util.Arrays;
import java.util.Objects;
import java.util.Random;
import java.util.Stack;

/**
 * <h3>wsd-project</h3>
 * <p></p>
 *
 * @author : 王松迪
 * 2024-03-04 11:24
 **/
public class StackOfMin {

    Stack<Integer> s;

    Stack<Integer> ms;

    public StackOfMin(int[] arr) {
        s = new Stack<>();
        ms = new Stack<>();
        ms.push(arr[0]);
        for(int i : arr) {
            push(i);
        }
    }

    public int min() {
        return ms.peek();
    }

    public void push(int... arr) {
        for (int i : arr) {
            push(i);
        }
    }
    public void push(int i){
        s.push(i);
        if(ms.peek() > i) {
            ms.push(i);
        }
    }

    public int pop() {
        Integer pop = s.pop();
        if(Objects.equals(pop, ms.peek())) {
            ms.pop();
        }
        return pop;
    }

    public static void main(String[] args) {
        int[] array = new Random().ints(0, 10).limit(10).toArray();
        System.out.println(Arrays.toString(array));
        StackOfMin stackOfMin = new StackOfMin(array);
        System.out.println(stackOfMin.min());
        stackOfMin.push(-1);
        System.out.println(stackOfMin.min());
        System.out.println(stackOfMin.pop());
        System.out.println(stackOfMin.pop());
        System.out.println(stackOfMin.pop());

        System.out.println(stackOfMin.min());

    }



}

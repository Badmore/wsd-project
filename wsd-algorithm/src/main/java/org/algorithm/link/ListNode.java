package org.algorithm.link;

import com.google.gson.Gson;

import java.awt.dnd.DragSourceMotionListener;

public class ListNode<T extends Comparable<? super T>> {

        public T val;

        public ListNode<T> next;


        public ListNode(T val) {
            this.val = val;
        }

        public ListNode<T> add(ListNode<T> node) {
            this.next = node;
            return this.next;
        }

        public static <T extends Comparable<? super T>> ListNode<T> crete(T... object) {
            T t = object[0];
            ListNode<T> head = new ListNode<>(t);
            ListNode<T> temp = head;
            for (int i = 1; i < object.length; i++) {
                temp.next = new ListNode<>(object[i]);
                temp = temp.next;
            }
            return head;
        }

}


class ReverseLimit {

    ListNode<Integer> suffixNode = null;

    public ListNode<Integer> reverse(ListNode<Integer> node, int end) {

        //base case
        if(end == 1) {
            suffixNode = node.next;
            return node;
        }

        ListNode<Integer> last = reverse(node.next, end - 1);
        node.next.next = node;
        node.next = suffixNode;
        return last;
    }

    public ListNode<Integer> reverseLimit(ListNode<Integer> node, int beginIndex, int reverseCount) {

        //base case； 开始反转
        if(beginIndex == 1) {
            return reverse(node, reverseCount);
        }

        //fixme 这句没理解，为什么要对 node.next 进行赋值？
        node.next = reverseLimit(node.next, beginIndex - 1, reverseCount);
        return node;
    }

    public static void main(String[] args) {

        ListNode<Integer> crete = ListNode.crete(3, 4, 5, 6, 7, 8, 9);

        ListNode<Integer> node = new ReverseLimit().reverseLimit(crete, 3, 3);



        Gson gson = new Gson();
        System.out.println(gson.toJson(node));

    }
}
package org.algorithm.link.model;


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



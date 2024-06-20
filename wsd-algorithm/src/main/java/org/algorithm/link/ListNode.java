package org.algorithm.link;

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

    }
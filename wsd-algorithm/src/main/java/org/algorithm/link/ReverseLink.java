package org.algorithm.link;

import com.google.gson.Gson;

/**
 * <h3>wsd-project</h3>
 * <p>反转链表</p>
 *
 * @author : 王松迪
 * 2024-07-04 08:37
 **/
public class ReverseLink<T extends Comparable<? super T>> {



    public ListNode<T> reverseWithRecurse(ListNode<T> node) {

        //base case
        if(null == node || node.next == null) {
            return node;
        }

        ListNode<T> last = reverseWithRecurse(node.next);
        node.next.next = node;
        node.next = null;
        return last;
    }

    public ListNode<T> reverseWithIterator(ListNode<T> node) {


        ListNode<T> pre, cur, nxt;
        pre = null; cur = node; nxt = node;
        while(cur!= null) {
            nxt = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nxt;
        }

        return pre;
    }

    public static void main(String[] args) {
        Gson gson = new Gson();
        ListNode<Integer> head = ListNode.crete(1, 2, 3, 4, 5, 6, 7);
        System.out.println(gson.toJson(head));
        ListNode<Integer> reverse = new ReverseLink<Integer>().reverseWithRecurse(head);
        System.out.println(gson.toJson(reverse));


        ListNode<Integer> head1 = ListNode.crete(1, 2, 3, 4, 5, 6, 7);
        ListNode<Integer> node = new ReverseLink<Integer>().reverseWithIterator(head1);
        System.out.println(gson.toJson(node));

    }

}

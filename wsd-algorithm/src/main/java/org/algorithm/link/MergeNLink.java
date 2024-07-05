package org.algorithm.link;

import com.google.gson.Gson;
import org.algorithm.sort.PriorityQueue;

import java.util.Comparator;
import java.util.stream.Stream;

/**
 * <h3>wsd-project</h3>
 * <p>合并 k 个链表, 并按照升序排序</p>
 *
 * @author : 王松迪
 * 2024-06-21 09:49
 **/
public class MergeNLink {

    public ListNode<Integer> mergeKLists(ListNode<Integer>[] lists) {
        PriorityQueue<ListNode<Integer>> mq = new PriorityQueue<>(20, Comparator.comparing(o -> o.val));

        for (ListNode<Integer> node : lists) {
            if (node != null) {
                do {
                    mq.add(node);
                    node = node.next;
                } while(node != null);
            }
        }

        ListNode<Integer> dummy = new ListNode<>(Integer.MIN_VALUE);
        ListNode<Integer> p = dummy;
        while(mq.peek() != null) {
            p.next = mq.poll();
            p = p.next;
        }

        return dummy.next;
    }


    public static void main(String[] args) {
        MergeNLink mergeNLink = new MergeNLink();
        ListNode<Integer> l1 = new ListNode<>(56);
        l1.add(new ListNode<>(51)).add(new ListNode<>(32)).add(new ListNode<>(4)).add(new ListNode<>(1)).add(new ListNode<>(2));

        ListNode<Integer> l2 = new ListNode<>(6);
        l2.add(new ListNode<>(5)).add(new ListNode<>(3)).add(new ListNode<>(14)).add(new ListNode<>(11)).add(new ListNode<>(72));

        ListNode<Integer> listNode = mergeNLink.mergeKLists(new ListNode[]{l1, l2});
        Gson gson = new Gson();
        System.out.println(gson.toJson(listNode));


    }
}

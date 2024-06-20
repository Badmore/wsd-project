package org.algorithm.link;

import com.google.gson.Gson;

/**
 * <h3>wsd-project</h3>
 * <p>分割成两个链表后再合并成一个</p>
 *
 * @author : 王松迪
 * 2024-06-20 09:08
 **/
public class PartitionLink {

    public ListNode<Integer> partition(ListNode<Integer> original, int x) {

        ListNode<Integer> dummyMin = new ListNode<>(Integer.MIN_VALUE);
        ListNode<Integer> dummyMax = new ListNode<>(Integer.MIN_VALUE);

        ListNode<Integer> p1 = dummyMin, p2 = dummyMax;
        ListNode<Integer> temp = original;
        while(temp != null ) {
            Integer val = temp.val;
            if(val >= x) {
                p2.next = new ListNode<>(val);
                p2 = p2.next;
            } else {
                p1.next = new ListNode<>(val);
                p1 = p1.next;
            }

            temp = temp.next;
        }

        p1.next = dummyMax.next;
        return dummyMin.next;
    }


    public static void main(String[] args) {
        ListNode<Integer> l1 = new ListNode<>(6);
        l1.add(new ListNode<>(5)).add(new ListNode<>(3)).add(new ListNode<>(4)).add(new ListNode<>(1)).add(new ListNode<>(2));

        Gson gson = new Gson();
        System.out.println(gson.toJson(l1));

        PartitionLink partitionLink = new PartitionLink();
        ListNode<Integer> partition = partitionLink.partition(l1, 3);

        System.out.println(gson.toJson(partition));


    }
}

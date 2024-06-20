package org.algorithm.link;


import com.google.gson.Gson;

/**
 * <h3>wsd-project</h3>
 * <p>合并两个有序链表</p>
 *
 *
 * @author : 王松迪
 * 2024-06-18 08:37
 **/
public class Merge2Link {


    private ListNode<Integer> result ;


    public Merge2Link(ListNode<Integer> l1, ListNode<Integer> l2) {


        result = new ListNode<>(Integer.MIN_VALUE);
        ListNode<Integer> p = result;

        ListNode<Integer> p1 = l1, p2 = l2;

        while(p1 != null && p2 != null) {
            if(p1.val < p2.val) {
                p.next = p1;
                p1 = p1.next;
            } else {
                p.next = p2;
                p2 = p2.next;
            }
            p = p.next;
        }

        if(p1 != null) {
            p.next = p1;
        }
        if(p2 != null) {
            p.next = p2;
        }

    }

    public ListNode<Integer> getResult() {
        return result.next;
    }

    public static void main(String[] args) {
        ListNode<Integer> l1 = new ListNode<>(1);
        l1.add(new ListNode<>(3)).add(new ListNode<>(5));


        ListNode<Integer> l2 = new ListNode<>(2);
        l2.add(new ListNode<>(4)).add(new ListNode<>(6));



        Merge2Link merge2Link = new Merge2Link(l1, l2);

        Gson gson = new Gson();
        ListNode<Integer> result = merge2Link.getResult();

        System.out.println(gson.toJson( result));
    }
}

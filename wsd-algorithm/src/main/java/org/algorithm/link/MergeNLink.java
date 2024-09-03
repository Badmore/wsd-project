package org.algorithm.link;

import com.google.gson.Gson;
import org.algorithm.link.model.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * <h3>wsd-project</h3>
 * <p>合并 k 个链表, 并按照升序排序</p>
 *
 * @author : 王松迪
 * 2024-06-21 09:49
 **/
public class MergeNLink {

    Gson gson = new Gson();


    public ListNode<Integer> mergeKListWithResolver(ListNode<Integer>[] lists) {

        if(lists == null || lists.length == 0) {
            return null;
        }

        return doMergeTraverse(lists, 0, lists.length -1);

    }

    public ListNode<Integer> doMergeTraverse(ListNode<Integer>[] lists, int left, int right) {

        if(left == right) {
            return lists[left];
        }

        int mid = left + (right - left ) / 2;
        ListNode<Integer> leftTraverseResult = doMergeTraverse(lists, left, mid);
        ListNode<Integer> rightTraverseResult = doMergeTraverse(lists, mid + 1, right);

        System.out.printf("后续 left %s right %s%n", gson.toJson(leftTraverseResult), gson.toJson(rightTraverseResult));
        return new Merge2Link(leftTraverseResult, rightTraverseResult).getResult();
    }


    public ListNode<Integer> mergeKListsWithPriorityQueue(ListNode<Integer>[] lists) {
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
        while(!mq.isEmpty()) {
            p.next = mq.poll();
            p = p.next;
        }

        return dummy.next;
    }


    public static void main(String[] args) {

        Gson gson = new Gson();


        // 测试代码
        ListNode<Integer>[] lists = new ListNode[3];
        // 创建测试链表
        lists[0] = new ListNode<>(8);
        lists[0].next = new ListNode<>(9);
        lists[0].next.next = new ListNode<>(10);

        lists[1] = new ListNode<>(1);
        lists[1].next = new ListNode<>(2);
        lists[1].next.next = new ListNode<>(3);

        lists[2] = new ListNode<>(4);
        lists[2].next = new ListNode<>(5);
        lists[2].next.next = new ListNode<>(6);

        MergeNLink mergeNLink = new MergeNLink();
        ListNode<Integer> listNode = mergeNLink.mergeKListsWithPriorityQueue(lists);
        System.out.println("priorityQ ==== " + gson.toJson(listNode));
        ListNode<Integer> listNode1 = mergeNLink.mergeKListWithResolver(lists);
        System.out.println("traverse ===" + gson.toJson(listNode1));

    }
}

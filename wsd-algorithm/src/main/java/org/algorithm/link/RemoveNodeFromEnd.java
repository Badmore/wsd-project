package org.algorithm.link;

import org.algorithm.link.model.ListNode;

/**
 * 删除第 N 个节点，并返回头节点
 */
public class RemoveNodeFromEnd {
    public static ListNode<Integer> removeNodeFromEnd(ListNode<Integer> head, int n) {

        ListNode<Integer> dummy = new ListNode<>(-1);
        dummy.next = head;

        ListNode<Integer> x = FindFromEnd.findFromEnd(dummy, n + 1);
        //删除第 n 个节点
        x.next = x.next.next;

        return dummy.next;
    }

}
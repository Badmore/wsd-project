package org.algorithm.link;

import org.algorithm.link.model.ListNode;

/**
 * <h3>wsd-project</h3>
 * <p>查询倒数第 N 个</p>
 *
 * @author : 王松迪
 * 2024-07-02 09:05
 **/
public class FindFromEnd {

    /**
     * 找出到处第 K 个节点
     * @param head 头
     * @param k k
     * @return 第 k 个节点
     */
    public static ListNode<Integer> findFromEnd(ListNode<Integer> head, int k) {

        ListNode<Integer> p1 = head;
        for (int i = 0; i < k; i++) {
            p1 = p1.next;
        }


        ListNode<Integer> p2 = head;
        while (p1 != null) {
            p2 = p2.next;
            p1 = p1.next;
        }

        return p2.next;
    }

}



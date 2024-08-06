package org.algorithm.link;

import org.algorithm.link.model.ListNode;

/**
 * 反向限价
 *
 * @author 王松迪
 * @date 2024/08/03
 */
public class ReverseLimit {

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

}



package org.algorithm.pointer;

/**
 * <h3>wsd-project</h3>
 * <p>双指针之快慢针判断链表是否有环</p>
 *
 * @author : 王松迪
 * 2024-03-01 09:48
 **/
public class TwoPointersOfLinkedCycle {

    private static class Node {
        int data;
        Node next;
        Node(int data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {
        Node node1 = new Node(5);
        Node node2 = new Node(3);
        Node node3 = new Node(7);
        Node node4 = new Node(2);
        Node node5 = new Node(6);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node2;
        System.out.println(isCycle(node1));

        System.out.println(cycleLength(node1));

        System.out.println(getCycleStart(node1).data);
    }

    /**
     * 是否有环
     */
    private static boolean isCycle(Node head) {
        Node p1 = head;
        Node p2 = head;
        while (p2 != null && p2.next != null) {
            p1 = p1.next;
            p2 = p2.next.next;
            if(p1 == p2) {
                return true;
            }
        }
        return false;
    }

    /**
     * 环长
     */
    private static int cycleLength(Node head) {
        Node p1  = head, p2 = head;
        int lengthCounter = 0;
        boolean isCounter = false;
        while (p2 != null && p2.next != null) {
            p1 = p1.next;
            p2 = p2.next.next;
            if(isCounter) {
                lengthCounter++;
            }
            if(p1 == p2) {
                if(!isCounter) {
                    isCounter = true;
                } else {
                    return lengthCounter;
                }
            }
        }
        return lengthCounter;
    }

    /**
     * - 链表的起点，到环入点的距离为 d;
     *     - 慢指针从d ～ 相遇点的距离为 s; 当到达相遇点时，所走距离为 d + s;
     *     - 快指针从d ～ 相遇点的距离为 f; 因相遇时，快指针在环内至少走了 n(n >= 1) 圈，当到达相遇点时，所走距离为 d + n(s + f) + s
     *     - 快针所走距离 = 慢指针的 2 倍；得出2(d + s) = d + n(s + f) + s;
     * 环起点
     *   2(d + s) = d + n(s + f) + s; \\
     *     2d + 2s = d + ns + nf + s; \\
     *     d = ns + nf - s; \\
     *     d = s(n - 1) + nf; \\
     *     d = s(n - 1) + nf - f + f; \\
     *     d = s(n - 1) + f(n - 1) + f; \\
     *     d = (n - 1)(s + f) + f; \\
     *     当 n == 1时 ；d = f;
     */
    private static Node getCycleStart(Node head) {
        Node p1 = head, p2 = head, p3 = head;
        boolean isCounter = false;
        while (p2 != null && p2.next != null) {
            p1 = p1.next;
            p2 = p2.next.next;
            if(isCounter) {
                p3 = p3.next;
            }
            if(p1 == p2){
                if(!isCounter) {
                    isCounter = true;
                } else {
                    return p3;
                }
            }
        }
        return null;
    }

}

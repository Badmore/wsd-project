package org.algorithm.pointer;

import java.util.Random;
import java.util.stream.IntStream;

/**
 * <h3>wsd-project</h3>
 * <p>链表是否有环</p>
 *
 * @author : 王松迪
 * 2024-03-01 09:48
 **/
public class LinkedCycle {

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

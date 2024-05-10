package org.algorithm.search;

import java.util.Random;

/**
 * <h3>wsd-project</h3>
 * <p>跳表</p>
 *
 * @author : 王松迪
 * 2024-04-29 09:07
 **/
public class SkipList {


    private static final double PROMOTE_PATE = 0.5;

    private Node<Integer> head, tail;

    private int maxLevel;

    public SkipList() {
        this.head = new Node<>(Integer.MIN_VALUE);
        this.tail = new Node<>(Integer.MAX_VALUE);
        head.right = tail;
        tail.left = head;
    }

    public Node<Integer> search(int value) {

        return null;
    }


    /**
     * 找到当前值需要插入的前驱节点，并插入，修改其原本的前驱与后继节点指针
     * 看看当前节点是否上升
     *
     * @param value 插入的值
     */
    public void insert(int value) {
        Node<Integer> preNode = findNode(value);
        if(preNode.value.compareTo(value) == 0) {
            return ;
        }

        Node<Integer> node = new Node<>(value);
        appendNode(preNode, node);
        int currenctLevel = 0;
        //随机决定节点说否“晋升”
        Random random = new Random();
        //fixme 为什么此处需要使用 while 循环
        while(random.nextDouble() < PROMOTE_PATE ) {

            if(currenctLevel == maxLevel) {
                addLevel();
            }

            //找到上衣鞥的前置节点
            while(preNode.up == null) {
                preNode = preNode.left;
            }
            preNode = preNode.up;
            //把“晋升”的心节点查到上一层
            Node<Integer> upperNode = new Node<>(value);
            appendNode(preNode, upperNode);
            upperNode.down = node;
            node = upperNode;
            currenctLevel++;
        }
    }

    private void appendNode(Node preNode, Node node) {
        node.left = preNode;
        node.right = preNode.right;
        preNode.right.left = node;
        preNode.right = node;
    }

    private void addLevel() {
        maxLevel++;
        Node<Integer> levelHead = new Node<>(Integer.MIN_VALUE);
        Node<Integer> levelTail = new Node<>(Integer.MAX_VALUE);
        levelHead.right = levelTail;
        levelTail.left = levelHead;
        levelHead.down = head;
        head.up = levelHead;
        levelTail.down = tail;
        tail.up = levelTail;
        //fixme 没搞懂怎么修改head和tail
        head = levelHead;
        tail = levelTail;
    }

    private Node<Integer> findNode(int value) {



        return null;
    }


}

class Node<T extends Comparable<T>>{

    T value;
    Node<T> left;
    Node<T> right;
    Node<T> up;
    Node<T> down;

    public Node(T value) {
        this.value = value;
    }

    public Node() {
    }
}

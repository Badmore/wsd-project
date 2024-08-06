package org.algorithm.link;

import org.algorithm.link.model.SkipNode;

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

    private SkipNode<Integer> head, tail;

    private int maxLevel;

    public SkipList() {
        this.head = new SkipNode<>(Integer.MIN_VALUE);
        this.tail = new SkipNode<>(Integer.MAX_VALUE);
        head.right = tail;
        tail.left = head;
    }

    public SkipNode<Integer> search(int value) {

        return null;
    }


    /**
     * 找到当前值需要插入的前驱节点，并插入，修改其原本的前驱与后继节点指针
     * 看看当前节点是否上升
     *
     * @param value 插入的值
     */
    public void insert(int value) {
        SkipNode<Integer> preNode = findNode(value);
        if(preNode.value.compareTo(value) == 0) {
            return ;
        }

        SkipNode<Integer> node = new SkipNode<>(value);
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
            SkipNode<Integer> upperNode = new SkipNode<>(value);
            appendNode(preNode, upperNode);
            upperNode.down = node;
            node = upperNode;
            currenctLevel++;
        }
    }

    private void appendNode(SkipNode preNode, SkipNode node) {
        node.left = preNode;
        node.right = preNode.right;
        preNode.right.left = node;
        preNode.right = node;
    }

    private void addLevel() {
        maxLevel++;
        SkipNode<Integer> levelHead = new SkipNode<>(Integer.MIN_VALUE);
        SkipNode<Integer> levelTail = new SkipNode<>(Integer.MAX_VALUE);
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

    private SkipNode<Integer> findNode(int value) {


        return null;
    }


}


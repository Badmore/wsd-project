package org.algorithm.tree;

import java.util.Random;

/**
 * <h3>wsd-project</h3>
 * <p>二叉搜索树</p>
 *
 * @author : 王松迪
 * 2024-03-28 09:00
 **/
public class BinarySearchTree {

    public static class Node {
        int data;
        Node r;
        Node l;

        public Node(int data) {
            this.data = data;
        }
    }

    private Node root;
    public Node search(int data) {

        if(root.data == data) {
            return root;
        }

        Node target = root;
        while(target != null && target.data != data) {
            target = data > target.data ? target.r : target.l;
        }

        return target;
    }



    public boolean insert(int data) {

        if(root == null) {
            root = new Node(data);
            return true;
        }

        //找到要插入节点, 需要满足下个节点为空
        Node target = root;
        while(null != target) {
            if(data == target.data) {
                return false;
            }

            if(data > target.data) {
                if(null == target.r) {
                   target.r = new Node(data);
                   return true;
                } else {
                    target = target.r;
                }
            }  else {
                if(null == target.l) {
                    target.l = new Node(data);
                    return true;
                } else {
                    target = target.l;
                }
            }
        }

        return false;
    }

    public void delete(int data){

    }

    public static void main(String[] args) {

        BinarySearchTree binarySearchTree = new BinarySearchTree();
        new Random(1000).ints(0, 1000).distinct().limit(999).forEach(binarySearchTree::insert);

        new Random(1000).ints(0, 1000).distinct().limit(100).forEach( id -> {
            Node search = binarySearchTree.search(id);
            System.out.println(search == null ? "空" : search.data);
        });
    }
}

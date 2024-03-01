package org.algorithm.sort;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * <h3>wsd-demo-root</h3>
 * <p></p>
 *
 * @author : 王松迪
 * 2024-02-21 10:16
 **/
public class BinaryTree {

    public static class TreeNode{
        int data;
        TreeNode left;

        TreeNode right;

        public TreeNode(int data) {
            this.data = data;
        }

        public static void main(String[] args) {
            LinkedList<Integer> inputList = new LinkedList<>(Arrays.asList(new Integer[]{1,2,3,4,5,6,7,8,9}));
            BinaryTree binaryTree = new BinaryTree();
            TreeNode treeNode = binaryTree.create(inputList);
            System.out.println(" 前序边路 ：");
            binaryTree.preOrder(treeNode);
            System.out.println(" 中序边路 ：");
            binaryTree.inOrder(treeNode);
            System.out.println(" 后序边路 ：");
            binaryTree.postOrder(treeNode);
            System.out.println(" 层序边路 ：");
            binaryTree.levelOrder(treeNode);

        }
    }


    TreeNode create(LinkedList<Integer> inputList){

        TreeNode node = null;
        if(inputList == null || inputList.isEmpty()) {
            return null;
        }

        Integer data = inputList.removeFirst();
        if(null != data) {
            node = new TreeNode(data);
            node.left = create(inputList);
            node.right = create(inputList);
        }
        return node;
    }

    void preOrder(TreeNode node) {
        if(node == null) {
            return ;
        }

        System.out.println(node.data);
        preOrder(node.left);
        preOrder(node.right);
    }

    void inOrder(TreeNode node) {
        if(node == null) {
            return ;
        }
        inOrder(node.left);
        System.out.println(node.data);
        inOrder(node.right);
    }

    void postOrder(TreeNode node) {
        if(node == null) {
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.data);
    }

    void levelOrder(TreeNode node) {
        if(node == null) {
            return;
        }

        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(node);
        while(!queue.isEmpty()) {
            TreeNode out = queue.removeFirst();
            System.out.println(out.data);
            if(out.left != null) {
                queue.add(out.left);
            }
            if(out.right != null) {
                queue.add(out.right);
            }
        }
    }

    /**
     * 二叉堆上浮调整，插入，最小堆
     * @param array 带调整的堆
     */
    void upAdjust(int[] array) {
        int ci = array.length - 1;
        int pi = (ci - 1) / 2;

        //保存插入的子节点值，用于最后的赋值
        int cvTemp = array[ci];

        while(ci > 0 && cvTemp < array[pi]) {
            array[ci] = array[pi];
            ci = pi;
            pi = (pi - 1) / 2;
        }

        array[ci] = cvTemp;
    }


}




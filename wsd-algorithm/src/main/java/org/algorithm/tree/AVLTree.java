package org.algorithm.tree;

/**
 * <h3>wsd-project</h3>
 * <p>平衡二叉树</p>
 *
 * @author : 王松迪
 * 2024-04-02 09:57
 **/

public class AVLTree<T extends Comparable<T>>{

    private AVLTreeNode<T> root;


    static class AVLTreeNode<T extends Comparable<T>>{

        private T v;

        private int height;

        private  AVLTreeNode<T> left;

        private AVLTreeNode<T> right;

        public AVLTreeNode(T v, AVLTreeNode<T> left, AVLTreeNode<T> right) {
            this.v = v;
            this.left = left;
            this.right = right;
        }

    }

    public int height() {
        return root == null ? 0 : height(root);
    }

    public int larger(int a, int b) {
        return Math.max(a, b);
    }


    private int height(AVLTreeNode<T> node) {
        if(null == node) {
            return  0;
        }
        return node.height;
    }


    /**
     * k2 为失去平衡的节点，并且是左侧失去平衡，的左左情况，需要进行右旋，即左子节点顺时针旋转
     * 操作步骤
     * 1. k1 为 k2 左子节点。
     * 2. k1 左侧不动，右子节点 为 k2的左子节点
     * 3. k1 的 右子节点为 k2。
     * @param k2 失去平衡的节点
     * @return 新的节点
     */
    private AVLTreeNode<T> llt(AVLTreeNode<T> k2) {

        AVLTreeNode<T> k1;

        k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;

        k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
        k1.height = Math.max(height(k1.left), k2.height) + 1;

        return k1;
    }

    /**
     * k1 为失去平衡的节点，并且是右侧失去平衡，属于右右情况，需要进行左旋，即右子节点逆时针旋转
     * 操作步骤
     * 1. k2 为 k1的右子节点。
     * 2. k2 的右侧不动，左子节点为 k1的 右子节点。
     * 3。k2 的左子节点为 k1。
     * @param k1 失去平衡的节点
     * @return 心的节点
     */
    private AVLTreeNode<T> rrt(AVLTreeNode<T> k1) {

        AVLTreeNode<T> k2;

        k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;

        k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
        k1.height = Math.max(height(k1.left), k2.height) + 1;


        return k2;
    }

    /**
     * k3 为失去平衡的节点，并且是左侧失去平衡，的左右情况，需要进行先左旋，再右旋
     * 操作步骤
     * 1. k3的左子节点左旋。
     * 2. k3右旋。
     * @param k3 失去平衡的节点
     * @return 新的节点
     */
    private AVLTreeNode<T>  lrt(AVLTreeNode<T> k3) {
        k3.left = rrt(k3.left);
        return llt(k3);
    }

    /**
     * k4 为失去平衡的节点，并且是右侧失去平衡，的右左情况，需要子节点先左旋，再右旋。
     * 操作步骤
     * 1. k4的右子节点，左左情况，右旋。
     * 2. k4的节点由于右右情况，左旋。
     * @param k4 失去平衡的节点
     * @return 心的节点
     */
    private AVLTreeNode<T> rlt(AVLTreeNode<T> k4) {
        k4.right = llt(k4.right);
        return rrt(k4);
    }

    public void insert(T insertValue) {
        root = insert(root, insertValue);
    }

    /**
     * 插入
     * @param node 根节点
     * @param inserValue  插入的值
     * @return 根节点
     */
    private AVLTreeNode<T> insert(AVLTreeNode<T> node, T inserValue)  {

        if(null == node) {
            node = new AVLTreeNode<>(inserValue, null, null);
            node.height = 1;
            return node;
        }

        int cpt = inserValue.compareTo(node.v);
        if(cpt > 0) {
            node.right = insert(node.right, inserValue);
            //失衡
            if(height(node.right) - height(node.left)  == 2 ) {

                if(inserValue.compareTo(node.right.v) > 0) {
                    node = rrt(node);
                } else {
                    node = rlt(node);
                }
            }
        } else if(cpt < 0){
            node.left = insert(node.left, inserValue);
            if(height(node.left) - height(node.right) == 2) {
                if(inserValue.compareTo(node.left.v) < 0) {
                    node = llt(node);
                } else {
                    node = lrt(node);
                }
            }
        } else {
            System.out.println("重复值无法插入");
        }

        node.height = Math.max(height(node.left), height(node.right)) + 1;

        return node;
    }



    public void remove(T value) {

        //search
        //remove
    }

    private AVLTreeNode<T> remove(AVLTreeNode<T> tree, T value) {
        if(null == tree || null == value){
            return null;
        }


        int cpt = value.compareTo(tree.v);
        //大于 0 代表是要删除右边
        if(cpt > 0) {
            tree = remove(tree.right, value);
            //删除右边，左边高度一定比右边要大
            if(height(tree.left) - height(tree.right) == 2) {
                AVLTreeNode<T> l = tree.left;
                //用来判断删除的是哪边的子树。
                if(height(l.left) > height(l.right)) {
                    tree = llt(tree);
                } else {
                    tree = lrt(tree);
                }
            }
        } else if(cpt < 0) {
            tree = remove(tree.left, value);
            if(height(tree.right) - height(tree.left) == 2) {
                AVLTreeNode<T> r = tree.right;
                if(height(r.right) > height(r.left)) {
                    tree = rrt(tree);
                } else {
                    tree = rlt(tree);
                }

            }
        } else {
            /*
             * 氛围两种情况
             * 1. 左右子节点都有值，此时需要找出一个合适节点直接替换，参考二叉堆的删除操作。
             *
             * 2。左右子节点缺少一支，
             */


            if(null != tree.right && null != tree.left){
                if(height(tree.left) > height(tree.right)) {
                    AVLTreeNode<T> maximum = maximum(tree.left);
                    tree.v = maximum.v;
                    tree.left = remove(tree.left, maximum.v);
                } else {
                    AVLTreeNode<T> minimum = minimum(tree.right);
                    tree.v = minimum.v;
                    tree.right = remove(tree.right, minimum.v);
                }
            } else {

                //需要将无效的路径释放，内存泄露问题
                AVLTreeNode<T> temp = tree;
                tree = (null == tree.left) ? tree.right : tree.left;
                temp = null;
            }
        }

        return tree;
    }

    private AVLTreeNode<T> maximum(AVLTreeNode<T> node){

        if(null == node ) {
            return null;
        }

        while(null != node.right) {
            node = node.right;
        }

        return node;
    }

    public T maximum() {

        AVLTreeNode<T> maximum = maximum(root);
        if(null == maximum) {
            return null;
        }

        return maximum.v;

    }

    private AVLTreeNode<T> minimum(AVLTreeNode<T> node) {

        if(null == node) {
            return null;
        }

        while(null != node.left) {
            node = node.left;
        }
        return node;
    }
    public T minimum() {
        AVLTreeNode<T> minimum = minimum(root);
        if(null == minimum) {
            return null;
        }

        return minimum.v;
    }
    /*
     * 前序遍历"AVL树"
     */
    private void preOrder(AVLTreeNode<T> tree) {
        if(tree != null) {
            System.out.print(tree.v+" ");
            preOrder(tree.left);
            preOrder(tree.right);
        }
    }

    public void preOrder() {
        preOrder(root);
    }

    /*
     * 中序遍历"AVL树"
     */
    private void inOrder(AVLTreeNode<T> tree) {
        if(tree != null)
        {
            inOrder(tree.left);
            System.out.print(tree.v + " ");
            inOrder(tree.right);
        }
    }

    public void inOrder() {
        inOrder(root);
    }

    /*
     * 后序遍历"AVL树"
     */
    private void postOrder(AVLTreeNode<T> tree) {
        if(tree != null) {
            postOrder(tree.left);
            postOrder(tree.right);
            System.out.print(tree.v + " ");
        }
    }

    public void postOrder() {
        postOrder(root);
    }

    /*
     * (递归实现)查找"AVL树x"中键值为key的节点
     */
    private AVLTreeNode<T> search(AVLTreeNode<T> x, T key) {
        if (x==null)
            return x;

        int cmp = key.compareTo(x.v);
        if (cmp < 0)
            return search(x.left, key);
        else if (cmp > 0)
            return search(x.right, key);
        else
            return x;
    }

    public AVLTreeNode<T> search(T key) {
        return search(root, key);
    }

    /*
     * 销毁AVL树
     */
    private void destroy(AVLTreeNode<T> tree) {
        if (tree==null)
            return ;

        if (tree.left != null)
            destroy(tree.left);
        if (tree.right != null)
            destroy(tree.right);

        tree = null;
    }

    public void destroy() {
        destroy(root);
    }

    /*
     * 打印"二叉查找树"
     *
     * key        -- 节点的键值
     * direction  --  0，表示该节点是根节点;
     *               -1，表示该节点是它的父结点的左孩子;
     *                1，表示该节点是它的父结点的右孩子。
     */
    private void print(AVLTreeNode<T> tree, T key, int direction) {
        if(tree != null) {
            if(direction==0)    // tree是根节点
                System.out.printf("%2d is root\n", tree.v, key);
            else                // tree是分支节点
                System.out.printf("%2d is %2d's %6s child\n", tree.v, key, direction==1?"right" : "left");

            print(tree.left, tree.v, -1);
            print(tree.right,tree.v,  1);
        }
    }

    public void print() {
        if (root != null)
            print(root, root.v, 0);
    }



        private static final int[] arr = {3,2,1,4,5,6,7,16,15,14,13,12,11,10,8,9};

        public static void main(String[] args) {
            int i;
            AVLTree<Integer> tree = new AVLTree<>();

            System.out.print("== 依次添加: ");
            for(i=0; i<arr.length; i++) {
                System.out.printf("%d ", arr[i]);
                tree.insert(arr[i]);
            }

            System.out.print("\n== 前序遍历: ");
            tree.preOrder();

            System.out.print("\n== 中序遍历: ");
            tree.inOrder();

            System.out.print("\n== 后序遍历: ");
            tree.postOrder();
            System.out.print("\n");

            System.out.printf("== 高度: %d\n", tree.height());
            System.out.printf("== 最小值: %d\n", tree.minimum());
            System.out.printf("== 最大值: %d\n", tree.maximum());
            System.out.print("== 树的详细信息: \n");
            tree.print();

            i = 8;
            System.out.printf("\n== 删除根节点: %d", i);
            tree.remove(i);

            System.out.printf("\n== 高度: %d", tree.height());
            System.out.print("\n== 中序遍历: ");
            tree.inOrder();
            System.out.print("\n== 树的详细信息: \n");
            tree.print();

            // 销毁二叉树
            tree.destroy();
        }

}




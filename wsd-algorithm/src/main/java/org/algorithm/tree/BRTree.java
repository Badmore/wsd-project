package org.algorithm.tree;

/**
 * <h3>wsd-project</h3>
 * <p>红黑树</p>
 *
 * @author : 王松迪
 * 2024-04-10 09:10
 **/
public class BRTree<T extends Comparable<T>> {

    private BRNode<T> mRoot;

    public static class BRNode<T extends Comparable<T>> {

        private static final boolean RED   = false;
        private static final boolean BLACK = true;

        private boolean color;

        private T key;

        private BRNode<T> left;

        private BRNode<T> right;

        private BRNode<T> parent;

        public BRNode(boolean color, T value, BRNode<T> left, BRNode<T> right, BRNode<T> parent) {
            this.color = color;
            this.key = value;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }
    }


    /**
     * 左旋，将<code>p</code> 的右子节点替代自己，将其中的付节点、子节点、索引替换。
     * 左旋示意图(对节点x进行左旋)：
     *      px                              px
     *     /                               /
     *    p                               y
     *   /  \       --(左旋)-.           / \
     *  lx   y                         p   ry
     *     /   \                      /     \
     *    ly   ry                    lx     ly
     *
     * 1. p.right作为旋转支点 作为 c。 修改起对应的指针 c.left -> p.right。 c.left.parent = p;
     * 2. 将 p.parent 指针 赋值给 c；
     * 3. c.left  = p; p.parent = c;
     *
     *
     * @param p 节点
     */
    private void lt(BRNode<T> p) {

        //步骤一
        BRNode<T> c = p.right;
        p.right = c.left;
        if(null != c.left) {
            c.left.parent = p;
        }

        //第二部，父节点变更。
        c.parent = p.parent;
        if(null == p.parent) {
            this.mRoot = c;
        } else {
             if(p.parent.left == p) {
                 p.parent.left = c;
             } else {
                 p.parent.right = c;
             }
        }
        //不周三
        c.left = p;
        p.parent = c;
    }


   /**
     * 对红黑树的节点(p)进行右旋转
     * 右旋示意图(对节点y进行左旋)：
     *            py                               py
     *           /                                /
     *          p                                x
     *         /  \      --(右旋)-.            /  \                     #
     *        x   ry                           lx   p
     *       / \                                   / \                   #
     *      lx  rx                                rx  ry
     * 1. p.left 作为旋转支点 作为 c。 修改起对应的指针 c.right -> p.left。 c.right.parent = p;
     * 2. 将 p.parent 指针 赋值给 c；
     * 3. c.right = p; p.parent = c;
     */
    private void rt(BRNode<T> p) {

        BRNode<T> c = p.left;
        p.left = c.right;
        if(null != c.right) {
            c.right.parent = p;
        }

        c.parent = p.parent;
        if(null == p.parent) {
            this.mRoot = c;
        } else {
            if(p.parent.left == p) {
                p.parent.left = c;
            } else {
                p.parent.right = c;
            }
        }

        c.right = p;
        p.parent = c;
    }

    private void insert(BRNode<T> node) {

        if(null == mRoot) {
            mRoot = node;
            return ;
        }

        BRNode<T> temp = mRoot;
        while(true) {
            node.parent = temp;
            temp = node.key.compareTo(temp.key) > 0 ? temp.right : temp.left;
            if(null == temp) {
                temp = node;
                temp.color = BRNode.RED;
                break;
            }
        }

        //调整
        insertFixUp(node);
    }

    /**
     * 三种情况需要考虑
     *
     * 修复条件：父节点是红色
     * 修复要点：保证链路上黑色节点数量不变
     * 可推断出：祖父节点一定是黑色，否则无法满足红黑树的性质
     *
     * 1. 插入的根节点，颜色为黑色，直接返回
     * 2. 插入的节点的父节点为黑色，直接返回
     * 3. 插入的节点的父节点为红色，则需要调整
     *
     *
     * @param node
     */
    private void insertFixUp(BRNode<T> node) {

        /**
         * 情况一：插入节点为根节点
         */
        if(acquireFixRoot(node)) {
            return ;
        }

        if(needFixUp(node)) {
            innerFix(node);
        }



    }

    /**
     * 调整节点为根节点
     */
    private boolean acquireFixRoot(BRNode<T> node) {
        if(null == mRoot) {
            node.color = BRNode.BLACK;
            this.mRoot = node;
            return true;
        }
        return false;
    }

    /**
     * 情况二，父节点为黑色，则直接插入 无需任何调整
     */
    private boolean needFixUp(BRNode<T> node) {
        return null != node && node.parent.color == BRNode.BLACK ;
    }


    /**
     * 调整
     * 1. 调整条件：父节点是红色，叔叔节点是黑色。
     * 2. 调整要点：保证链路上的黑色节点数量，满足红黑树的特性 5
     * 3. 推论：祖父节点一定是黑色，否则无法满足红黑树的性质
     * 调整因素：横向颜色一致，纵向单边调整、黑色节点保持一致。
     *
     * case 分析：当前节点的位置2种， 父节点的的位置有 2 种；共有 4 种 case
     * case
     * P(parent) = 父节点; GP(grandeParent) = 父节点的父节点；U（uncle）= 叔叔节点; c(current) = 当前节点
     * case1. P 是 红色，P 是 GP 的 left, U 是空或黑，c 是 P的 left
     *  调整方式： 祖父节点右旋，祖父节点变为红色，父节点为黑色。调整指针
     * case2. P 是 红色，P 是 GP 的 right，U 是空或黑，c 是 P的 right
     *  调整方式： 祖父节点左旋，祖父节点变为红色，父节点为黑色。调整指针
     * case3. P 是 红色，P 是 GP 的 left， U 是空或黑， c 是 P 的 right
     *  调整方式：父节点左旋，父节点与当前节点值互换，再进行 case1
     * case4. P 是 红色，P 是 GP 的 right，U 是空或黑，c 是 P 的 left；
     *  父节点右旋，父节点与当前节点值互换，再进行 case2
     *
     * @param c 调整的节点
     */
    private void innerFix(BRNode<T> c) {

        while(null != c && c.parent != null && c.parent.color == BRNode.RED) {
            BRNode<T> p = c.parent;
            BRNode<T> gp = p.parent;
            //case1
            if(gp.left == p && gp.right != null && gp.right.color == BRNode.BLACK && c == p.left) {
                rt(gp);
                gp.color = BRNode.RED;
                p.color = BRNode.BLACK;
                c = gp;
            }

            //case2
            if(gp.right == p && gp.left != null && gp.left.color == BRNode.BLACK && c == p.right) {
                lt(gp);
                gp.color = BRNode.RED;
                p.color = BRNode.BLACK;
                c = gp;
            }

            //case3
            if(p == gp.left && gp.right != null && gp.right.color == BRNode.BLACK && c == p.right) {
                lt(p);
                T key = c.key;
                c.key = p.key;
                p.key = key;
                c = p;
            }

            //case4
            if(p == gp.right && gp.left != null && gp.left.color == BRNode.BLACK && c == p.left) {
                rt(p);
                T key = c.key;
                c.key = p.key;
                p.key = key;
                c = p;
            }
        }
    }




    public void insert(T value)  {

        insert(new BRNode<T>(BRNode.BLACK, value, null, null, null));
    }

}

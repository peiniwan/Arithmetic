package arithmetic.ly.com.arithmetic.tree;

import java.util.ArrayList;
import java.util.Stack;

public class TreeOther {

    public TreeNode root = null;


    /**
     * 获取二叉树的结点数
     * 返回左子树和右子树个数的和，然后加上一个根节点view
     */
    public int getSize() {
        return getSize(root);
    }


    /**
     * 动态规划：都是从底部往上算
     * 讲一下这里的递归原理(深度)：当遍历到C和E时，左子树node.getLeftChild()和
     * 右子树node.getRightChild()返回0+1，此时深度为1，
     * 当到B和D时，B和D的深度都为1，此时返回1+1=2，
     * 同理，一步一步往回退，直到左右子树遍历一遍得到左右子树的深度然后进行比较
     * 返回最大的值+1就是整棵树的深度。
     * 那么求二叉树的所有节点的个数，递归原理与此相同。
     */
    private int getSize(TreeNode node) {
        if (node == null) {
            return 0;
        } else {
            return 1 + getSize(node.getLeft()) + getSize(node.getRight());
        }
    }

    /**
     * 求二叉树的高度
     * <p>
     * 1.判断根节点是否为空
     * 2.递归获取左子树的深度
     * 3.递归获取右子树的深度
     */
    public int getHeight() {
        return getHeight(root);
    }

    private int getHeight(TreeNode node) {
        if (node == null) {
            return 0;
        } else {
            int i = getHeight(node.getLeft()); //递归求出当前根节点左子树和右子树的深度
            int j = getHeight(node.getRight());
            return (i < j) ? j + 1 : i + 1;//从底向上递归返回左右子树深度的较大值，加1即当前根节点的深度
        }
    }

    /**
     * 前序遍历
     * 根左右
     *
     * @param root
     */
    public void preOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.print(root.getValue());
        preOrder(root.getLeft());
        preOrder(root.getRight());
    }

    /**
     * 中序遍历
     * 左根右
     */
    public void inOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        inOrder(root.getLeft());
        System.out.print(root.getValue() + " ");
        inOrder(root.getRight());
    }


    /**
     * 后序遍历
     * 左右根
     */
    public void postOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        postOrder(root.getLeft());
        postOrder(root.getRight());
        System.out.print(root.getValue());
    }

    /**
     * 翻转二叉树or镜像二叉树
     *
     * @param
     * @return
     */
    public TreeNode mirrorTreeNode(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode left = mirrorTreeNode(root.getLeft());
        TreeNode right = mirrorTreeNode(root.getRight());
        root.setLeft(right);
        root.setRight(left);
        return root;
    }


    /**
     * 39.输入一棵二叉树，判断该二叉树是否是平衡二叉树。
     * 平衡二叉树（Balanced Binary Tree），具有以下性质：它是一棵空树或它的左右两个子树的高度差的绝对值不超过1，并且左右两个子树都是一棵平衡二叉树。
     */
    public boolean isBalanced(TreeNode root) {
        return getDepth(root) != -1;  //在下面的获取树深度的方法中，如果返回值为-1说明不平衡，为0或者正整数就是平衡
    }

    private int getDepth(TreeNode root) {  //这种方法运用了“剪枝”的思想，即二叉树从底部到顶部递归返回时，每个节点最多只遍历到一次，一旦条件不满足立刻取消遍历，而不是从顶向下每判断一个当前根节点就每次都把下面所有子树节点遍历一遍
        if (root == null) return 0;  //如果当前根节点为空，深度返回0
        int leftDepth = getDepth(root.getLeft());  //递归到树底部计算当前根节点左子树的深度，从底向上计算过程中如果发现一处不平衡，直接返回-1，也不会再递归总根节点的右子树
        if (leftDepth == -1) return -1;
        int rightDepth = getDepth(root.getRight());  //在当前根节点左子树平衡的前提下再递归计算右子树的深度，如果右子树有一处不平衡也直接返回-1，递归结束
        if (rightDepth == -1) return -1;
        return Math.abs(rightDepth - leftDepth) > 1 ? -1 : Math.max(leftDepth, rightDepth) + 1;  //核心的计算树深度的代码，如果当前根节点的左右子树深度相差超过1则不平衡，否则返回当前根节点的深度
    }

    /**
     * 12.二叉树的前序遍历
     * 迭代解法
     * abcdef
     * 前：ABDECF
     * 中：DBEAFC
     * 后：DEBFCA
     */
    ArrayList<Integer> preOrder(BinaryTreeUtil.TreeNode root) {
        Stack<BinaryTreeUtil.TreeNode> stack = new Stack<BinaryTreeUtil.TreeNode>();
        ArrayList<Integer> list = new ArrayList<Integer>();
        if (root == null) {
            return list;
        }
        stack.push(root);
        while (!stack.empty()) {
            BinaryTreeUtil.TreeNode node = stack.pop();
            list.add(node.val);
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        return list;
    }


    /**
     * 13.二叉树的中序遍历
     * abcdef
     * 中：DBEAFC
     */
    ArrayList<Integer> inOrder(BinaryTreeUtil.TreeNode root) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        Stack<BinaryTreeUtil.TreeNode> stack = new Stack<BinaryTreeUtil.TreeNode>();
        BinaryTreeUtil.TreeNode current = root;
        while (current != null || !stack.empty()) {
            while (current != null) {
                stack.add(current);
                current = current.left;
            }
            //不同点：peek 不改变栈的值(不删除栈顶的值)，pop会把栈顶的值删除。
            current = stack.peek();
            stack.pop();
            list.add(current.val);
            current = current.right;
        }
        return list;
    }


    public static void main(String[] args) {
//        TreeCreate creator = new TreeCreate();
//        creator.createBinaryTreePre()
//        TreeOther treeOther = new TreeOther();
//        treeOther.preOrder(creator);
    }
}

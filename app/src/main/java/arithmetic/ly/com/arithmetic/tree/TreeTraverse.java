package arithmetic.ly.com.arithmetic.tree;

import java.util.Stack;

/**
 * Created by liuyu1 on 2018/1/30.
 */

public class TreeTraverse {

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
     *
     * @param root
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
     *
     * @param root
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
     * 前序遍历——非迭代
     */
    public void nonRecOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(node);
        while (!stack.isEmpty()) {
            //出栈和进栈
            TreeNode n = stack.pop();//弹出根结点
            //压入子结点
            System.out.println("nonRecOrder data" + n.getValue());
            if (n.getRight() != null) {
                stack.push(n.getRight());

            }
            if (n.getLeft() != null) {
                stack.push(n.getLeft());
            }
        }
    }

    /**
     * 根据前序和中序不建立二叉树，输出后序遍历
     *
     * @param preOrder "ABDEGCF"
     * @param inOrder  "DBGEACF"
     * @return DGEBFCA
     */
    public String postOrder(String preOrder, String inOrder) {
        if (preOrder.isEmpty()) {
            return "";
        }

        char rootValue = preOrder.charAt(0);
        int rootIndex = inOrder.indexOf(rootValue);

        return
                postOrder(
                        preOrder.substring(1, 1 + rootIndex),
                        inOrder.substring(0, rootIndex)) +
                        postOrder(
                                preOrder.substring(1 + rootIndex),
                                inOrder.substring(1 + rootIndex)) +
                        rootValue;
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


}

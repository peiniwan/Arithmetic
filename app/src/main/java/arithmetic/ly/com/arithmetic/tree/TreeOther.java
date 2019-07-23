package arithmetic.ly.com.arithmetic.tree;

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
            int i = getHeight(node.getLeft());
            int j = getHeight(node.getRight());
            return (i < j) ? j + 1 : i + 1;
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


    public static void main(String[] args) {
        TreeCreate creator = new TreeCreate();
        TreeOther treeOther = new TreeOther();

    }
}

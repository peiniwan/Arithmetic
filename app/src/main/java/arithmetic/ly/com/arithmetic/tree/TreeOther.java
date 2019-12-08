package arithmetic.ly.com.arithmetic.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

public class TreeOther {

    public TreeNode root = null;

    /**
     * 通过前序遍历的数据反向生成二叉树
     *          A
     *       B       C
     *   D     E   #     F
     * #   #  # #     #    #
     * ABD##E##C#F##
     */
    public TreeNode createBinaryTreePre(ArrayList<String> data) {
        if (data.size() == 0) {
            return null;
        }
        String d = data.get(0);
        TreeNode node;
        if (d.equals("#")) {
            node = null;
            data.remove(0);
            return node;
        }
        node = new TreeNode(d);
        data.remove(0);
        //第一次返回是D，b.setleft(d)
        node.setLeft(createBinaryTreePre(data));
        node.setRight(createBinaryTreePre(data));
        return node;
    }


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
    public static void preorderTraversal(TreeNode root) {
        // 用来暂存节点的栈
        Stack<TreeNode> treeNodeStack = new Stack<TreeNode>();
        // 新建一个游标节点为根节点
        TreeNode node = root;
// 当遍历到最后一个节点的时候，无论它的左右子树都为空，并且栈也为空
// 所以，只要不同时满足这两点，都需要进入循环
        while (node != null || !treeNodeStack.isEmpty()) {
// 若当前考查节点非空，则输出该节点的值
// 由考查顺序得知，需要一直往左走
            while (node != null) {
                System.out.print(node.getValue() + " ");
// 为了之后能找到该节点的右子树，暂存该节点
                treeNodeStack.push(node);
                node = node.getLeft();
            }
// 一直到左子树为空，则开始考虑右子树
// 如果栈已空，就不需要再考虑
// 弹出栈顶元素，将游标等于该节点的右子树
            if (!treeNodeStack.isEmpty()) {
                node = treeNodeStack.pop();
                node = node.getRight();
            }
        }
    }


    /**
     * 13.二叉树的中序遍历
     * abcdef
     * 中：DBEAFC
     */
    public static void middleorderTraversal(TreeNode root) {
        Stack<TreeNode> treeNodeStack = new Stack<TreeNode>();
        TreeNode node = root;
        while (node != null || !treeNodeStack.isEmpty()) {
            while (node != null) {
                treeNodeStack.push(node);
                node = node.getLeft();
            }
            if (!treeNodeStack.isEmpty()) {
                node = treeNodeStack.pop();
                System.out.print(node.getValue() + " ");
                node = node.getRight();
            }
        }
    }

    public static void postorderTraversal(TreeNode root) {
        Stack<TreeNode> treeNodeStack = new Stack<TreeNode>();
        TreeNode node = root;
        TreeNode lastVisit = root;
        while (node != null || !treeNodeStack.isEmpty()) {
            while (node != null) {
                treeNodeStack.push(node);
                node = node.getLeft();
            }
//查看当前栈顶元素
            node = treeNodeStack.peek();
//如果其右子树也为空，或者右子树已经访问
//则可以直接输出当前节点的值
            if (node.getRight() == null || node.getRight() == lastVisit) {
                System.out.print(node.getValue() + " ");
                treeNodeStack.pop();
                lastVisit = node;
                node = null;
            } else {
//否则，继续遍历右子树
                node = node.getRight();
            }
        }
    }


    /**
     * 按层遍历
     * 从上往下打印出二叉树的每个节点，同层节点从左至右打印。
     */
    public static void levelOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        while (!queue.isEmpty()) { //当“队列中”有二叉树节点时不断循环
            TreeNode currentNode = queue.poll();//poll获取并删除列表的第一个元素
            System.out.print(currentNode.getValue() + " "); //将二叉树当前根节点的值添加到列表中
            if (currentNode.getLeft() != null) {//如果有左子树就将左节点添加到“队列”中
                queue.add(currentNode.getLeft());
            }
            if (currentNode.getRight() != null) {//如果有右子树就将右节点添加到“队列”中
                queue.add(currentNode.getRight());
            }
        }
    }

    /**
     * 二叉查找树的查找操作
     */
    public TreeNode find(TreeNode<Integer> root, int data) {
        while (root != null) {
            if (data < root.getValue()) root = root.getLeft();
            else if (data > root.getValue()) root = root.getRight();
            else return root;
        }
        return null;
    }

    public void insert(TreeNode root, int data) {
        TreeNode<Integer> node = new TreeNode(data);
        while (root != null) {
            if (data < (Integer) root.getValue()) {
                if (root.getLeft() == null) {
                    root.setLeft(node);
                    return;
                }
                root = root.getLeft();
            } else {
                if (root.getRight() == null) {
                    root.setRight(node);
                    return;
                }
                root = root.getRight();
            }
        }
    }


    /**
     * 删除比较复杂，看文章
     * https://time.geekbang.org/column/article/68334
     */
    public void delete(int data) {

    }


    public static void main(String[] args) {
        TreeCreate creator = new TreeCreate();
        ArrayList<String> data = new ArrayList<>();
        String[] dataArray = new String[]{"A", "B", "D", "#", "#", "E", "#", "#", "C", "#", "F", "#", "#"};
        for (String s : dataArray) {
            data.add(s);
        }
        TreeNode binaryTreePre = creator.createBinaryTreePre(data);
        preorderTraversal(binaryTreePre);
//        treeOther.preOrder(creator);
    }
}

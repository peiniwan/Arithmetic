package arithmetic.ly.com.arithmetic.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import arithmetic.ly.com.arithmetic.other.LeetCode;


public class BinaryTreeUtil {

    public class TreeNode {
        int val;
        //左孩子
        TreeNode left;
        //右孩子
        TreeNode right;
    }


    public static void main(String[] args) {
        BinaryTreeUtil binaryTreeUtil = new BinaryTreeUtil();
        int[] perInt = {1, 2, 3, 4};
        int[] postInt = {1, 2, 3, 4};
        TreeNode treeNode = binaryTreeUtil.buildTreeNode(perInt, postInt);
        ArrayList<Integer> integers = binaryTreeUtil.preOrder(treeNode);
        for (Integer integer : integers) {
            System.out.print(integer + " ");
        }
    }


    /**
     * 1.二叉树的最大深度
     * 时间复杂度：O(n)，其中 n 为二叉树节点的个数。每个节点在递归中只被遍历一次。
     * 空间复杂度：O(height) height 表示二叉树的高度。
     */
    int maxDeath(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int left = maxDeath(node.left);
        int right = maxDeath(node.right);
        return Math.max(left, right) + 1;
    }

    /**
     * 2.求二叉树的最小深度
     *
     * @param root
     * @return
     */
    int getMinDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return getMin(root);
    }

    int getMin(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        return Math.min(getMin(root.left), getMin(root.right)) + 1; //
    }

    /**
     * 3.求二叉树中节点的个数
     *
     * @param root
     * @return
     */
    int numOfTreeNode(TreeNode root) {
        if (root == null) {
            return 0;

        }
        int left = numOfTreeNode(root.left);
        int right = numOfTreeNode(root.right);
        return left + right + 1;
    }

    /**
     * 4.求二叉树中叶子节点的个数
     *
     * @param root
     * @return
     */
    int numsOfNoChildNode(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        return numsOfNoChildNode(root.left) + numsOfNoChildNode(root.right);
    }

    /**
     * 5.求二叉树中第k层节点的个数
     * 通过递归不断减少 k，到达目标层时计数，最终返回第 k 层的结点数。
     */
    int numsOfkLevelTreeNode(TreeNode root, int k) {
        if (root == null || k < 1) {
            return 0;
        }
        if (k == 1) {
            return 1;
        }
        int numsLeft = numsOfkLevelTreeNode(root.left, k - 1);
        int numsRight = numsOfkLevelTreeNode(root.right, k - 1);
        return numsLeft + numsRight;
    }


    /**
     * 39.输入一棵二叉树，判断该二叉树是否是平衡二叉树。
     * 平衡二叉树,具有以下性质：
     * 它是一棵空树或它的左右两个子树的高度差的绝对值不超过1，并且左右两个子树都是一棵平衡二叉树。
     */
    public boolean isBalanced(TreeOther.TreeNode root) {
        return getDepth(root) != -1;  //在下面的获取树深度的方法中，如果返回值为-1说明不平衡，为0或者正整数就是平衡
    }

    private int getDepth(TreeOther.TreeNode root) {  //这种方法运用了“剪枝”的思想，即二叉树从底部到顶部递归返回时，每个节点最多只遍历到一次，一旦条件不满足立刻取消遍历，而不是从顶向下每判断一个当前根节点就每次都把下面所有子树节点遍历一遍
        if (root == null) return 0;  //如果当前根节点为空，深度返回0
        int leftDepth = getDepth(root.left);  //递归到树底部计算当前根节点左子树的深度，从底向上计算过程中如果发现一处不平衡，直接返回-1，也不会再递归总根节点的右子树
        if (leftDepth == -1) return -1;
        int rightDepth = getDepth(root.right);  //在当前根节点左子树平衡的前提下再递归计算右子树的深度，如果右子树有一处不平衡也直接返回-1，递归结束
        if (rightDepth == -1) return -1;
        return Math.abs(rightDepth - leftDepth) > 1 ? -1 : Math.max(leftDepth, rightDepth) + 1;  //核心的计算树深度的代码，如果当前根节点的左右子树深度相差超过1则不平衡，否则返回当前根节点的深度
    }

    /**
     * 543. 二叉树的直径
     * 给你一棵二叉树的根节点，返回该树的 直径 。
     * 二叉树的 直径 是指树中任意两个节点之间最长路径的 长度 。这条路径可能经过也可能不经过根节点 root 。
     * 两节点之间路径的 长度 由它们之间边数表示。
     * https://leetcode.cn/problems/diameter-of-binary-tree/description/?envType=study-plan-v2&envId=top-100-liked
     */
    int ans;

    public int diameterOfBinaryTree(TreeNode root) {
        ans = 1;
        depth(root);
        //从每个节点的路径节点讨论的，然后最后的直径是节点总数减一
        return ans - 1;
    }

    public int depth(TreeNode node) {
        if (node == null) {
            return 0; // 访问到空节点了，返回0
        }
        int L = depth(node.left); // 左儿子为根的子树的深度
        int R = depth(node.right); // 右儿子为根的子树的深度
        ans = Math.max(ans, L + R + 1); // 计算d_node即L+R+1 并更新ans
        return Math.max(L, R) + 1; // 返回该节点为根的子树的深度
    }


    //========================================================= 遍历 =============================================================

    /**
     * 前序遍历
     * 根左右
     */
    public void preOrder(TreeOther.TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.print(root.val);
        preOrder(root.left);
        preOrder(root.right);
    }


    /**
     * 中序遍历
     * 左根右
     */
    public void inOrder(TreeOther.TreeNode root) {
        if (root == null) {
            return;
        }
        inOrder(root.left);
        System.out.print(root.val + " ");
        inOrder(root.right);
    }


    /**
     * 后序遍历
     * 左右根
     */
    public void postOrder(TreeOther.TreeNode root) {
        if (root == null) {
            return;
        }
        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.val);
    }


    /**
     * 19.二叉树的层次遍历
     */
    ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
//            每次从队列中取出当前层的所有节点，将其值存入level列表。
            ArrayList<Integer> level = new ArrayList<Integer>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            result.add(level);
        }
        return result;
    }


    /**
     * 12.二叉树的前序遍历
     * 迭代解法
     *
     * @param root
     * @return
     */
    ArrayList<Integer> preOrder(TreeNode root) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        ArrayList<Integer> list = new ArrayList<Integer>();
        if (root == null) {
            return list;
        }
        stack.push(root);
        while (!stack.empty()) {
            TreeNode node = stack.pop();
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
     *
     * @param root
     * @return
     */
    ArrayList<Integer> inOrder(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode current = root;
        while (current != null || !stack.empty()) {
            while (current != null) {
                stack.add(current);
                current = current.left;
            }
            current = stack.peek();
            stack.pop();
            list.add(current.val);
            current = current.right;
        }
        return list;
    }


    /**
     * 15.前序遍历和后序遍历构造二叉树
     *
     * @param preorder
     * @param inorder
     * @return
     */
    TreeNode buildTreeNode(int[] preorder, int[] inorder) {
        if (preorder.length != inorder.length) {
            return null;
        }
        return myBuildTree(inorder, 0, inorder.length - 1, preorder, 0, preorder.length - 1);
    }

    TreeNode myBuildTree(int[] inorder, int instart, int inend, int[] preorder, int prestart, int preend) {
        if (instart > inend) {
            return null;
        }
//        TreeNode root = new TreeNode(preorder[prestart]);
        TreeNode root = new TreeNode();
        root.val = preorder[prestart];
        int position = findPosition(inorder, instart, inend, preorder[prestart]);
        root.left = myBuildTree(inorder, instart, position - 1, preorder, prestart + 1, prestart + position - instart);
        root.right = myBuildTree(inorder, position + 1, inend, preorder, position - inend + preend + 1, preend);
        return root;
    }

    int findPosition(int[] arr, int start, int end, int key) {
        int i;
        for (i = start; i <= end; i++) {
            if (arr[i] == key) {
                return i;
            }
        }
        return -1;
    }


    //===================================== 遍历end ========================================

    /**
     * 8.两个二叉树是否完全相同
     *
     * @param t1
     * @param t2
     * @return
     */
    boolean isSameTreeNode(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return true;
        } else if (t1 == null || t2 == null) {
            return false;
        }
        if (t1.val != t2.val) {
            return false;
        }
        boolean left = isSameTreeNode(t1.left, t2.left);
        boolean right = isSameTreeNode(t1.right, t2.right);
        return left && right;
    }

    /**
     * 101. 对称二叉树
     * 如果一个树的左子树与右子树镜像对称，那么这个树是对称的。
     * 因此，该问题可以转化为：两个树在什么情况下互为镜像？
     */
    public boolean isSymmetric(TreeNode root) {
        //进行递归调用
        return isMirror(root, root);
    }

    /**
     * 9.两个二叉树是否互为镜像
     *
     * @param t1
     * @param t2
     * @return
     */
    boolean isMirror(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return true;
        }
        if (t1 == null || t2 == null) {
            return false;
        }
        if (t1.val != t2.val) {
            return false;
        }
        return isMirror(t1.left, t2.right) && isMirror(t1.right, t2.left);
    }

    /**
     * 10.翻转二叉树or镜像二叉树
     *
     * @param root
     * @return
     */
    TreeNode mirrorTreeNode(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode left = mirrorTreeNode(root.left);
        TreeNode right = mirrorTreeNode(root.right);
        root.left = right;
        root.right = left;
        return root;
    }


    // 查找节点node是否在当前 二叉树中
    boolean findNode(TreeNode root, TreeNode node) {
        if (root == null || node == null) {
            return false;
        }
        if (root == node) {
            return true;
        }
        boolean found = findNode(root.left, node);
        if (!found) {
            found = findNode(root.right, node);
        }
        return found;
    }


    /**
     * 16.在二叉树中插入节点
     *
     * @param root
     * @param node
     * @return
     */
    TreeNode insertNode(TreeNode root, TreeNode node) {
        if (root == node) {
            return node;
        }
        TreeNode tmp = new TreeNode();
        tmp = root;
        TreeNode last = null;
        while (tmp != null) {
            last = tmp;
            if (tmp.val > node.val) {
                tmp = tmp.left;
            } else {
                tmp = tmp.right;
            }
        }
        if (last != null) {
            if (last.val > node.val) {
                last.left = node;
            } else {
                last.right = node;
            }
        }
        return root;
    }


    /**
     * 17.输入一个二叉树和一个整数，打印出二叉树中节点值的和
     * 等于输入整数所有的路径
     *
     */
    void findPath(TreeNode root, int i) {
        if (root == null) {
            return;
        }
        Stack<Integer> stack = new Stack<Integer>();
        int currentSum = 0;
        findPath(root, i, stack, currentSum);
    }

    void findPath(TreeNode r, int i, Stack<Integer> stack, int currentSum) {
        currentSum += r.val;
        stack.push(r.val);
        if (r.left == null && r.right == null) {
            if (currentSum == i) {
                for (int path : stack) {
                    System.out.println(path);
                }
            }
        }
        if (r.left != null) {
            findPath(r.left, i, stack, currentSum);
        }
        if (r.right != null) {
            findPath(r.right, i, stack, currentSum);
        }
        stack.pop();
    }


    /**
     * 18.二叉树的搜索区间
     * 给定两个值 k1 和 k2（k1 < k2）和一个二叉查找树的根节点。找到树中所有值在 k1 到 k2 范围内的节点。
     * 即打印所有x (k1 <= x <= k2) 其中 x 是二叉查找树的中的节点值。返回所有升序的节点值。
     */
    ArrayList<Integer> result;

    ArrayList<Integer> searchRange(TreeNode root, int k1, int k2) {
        result = new ArrayList<Integer>();
        searchHelper(root, k1, k2);
        return result;
    }

    void searchHelper(TreeNode root, int k1, int k2) {
        if (root == null) {
            return;
        }
        if (root.val > k1) {
            searchHelper(root.left, k1, k2);
        }
        if (root.val >= k1 && root.val <= k2) {
            result.add(root.val);
        }
        if (root.val < k2) {
            searchHelper(root.right, k1, k2);
        }
    }


    /**
     * 20.二叉树内两个节点的最长距离
     * 二叉树中两个节点的最长距离可能有三种情况：
     * 1.左子树的最大深度+右子树的最大深度为二叉树的最长距离
     * 2.左子树中的最长距离即为二叉树的最长距离
     * 3.右子树种的最长距离即为二叉树的最长距离
     * 因此，递归求解即可
     */
    private static class Result {
        int maxDistance;// 子树中的最大距离（即二叉树直径）
        int maxDepth;  // 子树的最大深度（从该子树根到最远叶子的边数）

        public Result() {
        }

        public Result(int maxDistance, int maxDepth) {
            this.maxDistance = maxDistance;
            this.maxDepth = maxDepth;
        }
    }

    int getMaxDistance(TreeNode root) {
        return getMaxDistanceResult(root).maxDistance;
    }

    Result getMaxDistanceResult(TreeNode root) {
        if (root == null) {
            Result empty = new Result(0, -1);
            return empty;
        }
        Result lmd = getMaxDistanceResult(root.left);
        Result rmd = getMaxDistanceResult(root.right);
        Result result = new Result();
        // 当前子树的最大深度 = 左右子树最大深度中的较大者 + 1
        result.maxDepth = Math.max(lmd.maxDepth, rmd.maxDepth) + 1;
        // 当前子树的最大距离可能有三种情况：
        // 1. 路径经过当前 root：等于左子树深度 + 右子树深度 （两边拼起来）
        // 2. 在左子树内部：lmd.maxDistance
        // 3. 在右子树内部：rmd.maxDistanc
        result.maxDistance = Math.max(lmd.maxDepth + rmd.maxDepth, Math.max(lmd.maxDistance, rmd.maxDistance));
        return result;
    }

    /**
     * 21.不同的二叉树
     * 给出 n，问由 1...n 为节点组成的不同的二叉查找树有多少种？
     *
     * @param n
     * @return
     */
    int numTrees(int n) {
        int[] counts = new int[n + 2];
        counts[0] = 1;
        counts[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                counts[i] += counts[j] * counts[i - j - 1];
            }
        }
        return counts[n];
    }


}

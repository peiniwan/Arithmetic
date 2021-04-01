package arithmetic.ly.com.arithmetic.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;


public class TreeOther {


    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }

        public TreeNode(String d) {
        }

//        private void setParent(TreeNode parent) {
//            this.parent = parent;
//        }

//        public void setLeft(TreeNode left) {
//            this.left = left;
//            if (this.left != null) {
//                this.left.setParent(this);
//            }
//        }
    }


    /**
     * 通过前序遍历的数据反向生成二叉树
     * A
     * B       C
     * D   E   #     F
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
        node.left = (createBinaryTreePre(data));
        node.right = (createBinaryTreePre(data));
        return node;
    }


    /**
     * 获取二叉树的结点数
     * 返回左子树和右子树个数的和，然后加上一个根节点view
     */
    private int getSize(TreeNode node) {
        if (node == null) {
            return 0;
        } else {
            return 1 + getSize(node.left) + getSize(node.right);
        }
    }


    /**
     * 求二叉树的高度
     * 1.判断根节点是否为空
     * 2.递归获取左子树的深度
     * 3.递归获取右子树的深度
     */
    private int getHeight(TreeNode node) {
        if (node == null) {
            return 0;
        } else {
            int i = getHeight(node.left); //递归求出当前根节点左子树和右子树的深度
            int j = getHeight(node.right);
            return (i < j) ? j + 1 : i + 1;//从底向上递归返回左右子树深度的较大值，加1即当前根节点的深度
        }
    }

    /**
     * 前序遍历
     * 根左右
     */
    public void preOrder(TreeNode root) {
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
    public void inOrder(TreeNode root) {
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
    public void postOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.val);
    }


    /**
     * 翻转二叉树or镜像二叉树
     * 使用递归遍历树，所有left换成right，所有right换成left
     */
    public TreeNode mirrorTreeNode(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode left = mirrorTreeNode(root.left);
        TreeNode right = mirrorTreeNode(root.right);
        root.left = (right);
        root.right = (left);
        return root;
    }


    /**
     * 39.输入一棵二叉树，判断该二叉树是否是平衡二叉树。
     * 平衡二叉树,具有以下性质：
     * 它是一棵空树或它的左右两个子树的高度差的绝对值不超过1，并且左右两个子树都是一棵平衡二叉树。
     */
    public boolean isBalanced(TreeNode root) {
        return getDepth(root) != -1;  //在下面的获取树深度的方法中，如果返回值为-1说明不平衡，为0或者正整数就是平衡
    }

    private int getDepth(TreeNode root) {  //这种方法运用了“剪枝”的思想，即二叉树从底部到顶部递归返回时，每个节点最多只遍历到一次，一旦条件不满足立刻取消遍历，而不是从顶向下每判断一个当前根节点就每次都把下面所有子树节点遍历一遍
        if (root == null) return 0;  //如果当前根节点为空，深度返回0
        int leftDepth = getDepth(root.left);  //递归到树底部计算当前根节点左子树的深度，从底向上计算过程中如果发现一处不平衡，直接返回-1，也不会再递归总根节点的右子树
        if (leftDepth == -1) return -1;
        int rightDepth = getDepth(root.right);  //在当前根节点左子树平衡的前提下再递归计算右子树的深度，如果右子树有一处不平衡也直接返回-1，递归结束
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
     * 因为要在遍历完节点的左子树后接着遍历节点的右子树，为了能找到该节点，需要使用栈来进行暂存。
     * 中序和后序也都涉及到回溯，所以都需要用到栈。
     * https://www.jianshu.com/p/456af5480cee
     */
    public static void preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<Integer>();
        // 用来暂存节点的栈
        Stack<TreeNode> treeNodeStack = new Stack<TreeNode>();
        // 新建一个游标节点为根节点
        TreeNode node = root;
// 当遍历到最后一个节点的时候，无论它的左右子树都为空，并且栈也为空
        while (node != null || !treeNodeStack.isEmpty()) {
// 若当前考查节点非空，则输出该节点的值
// 由考查顺序得知，需要一直往左走
            while (node != null) {
                System.out.print(node.val + " ");
// 为了之后能找到该节点的右子树，暂存该节点
                treeNodeStack.push(node);
                node = node.left;
            }
// 一直到左子树为空，则开始考虑右子树
// 如果栈已空，就不需要再考虑
// 弹出栈顶元素，将游标等于该节点的右子树
            if (!treeNodeStack.isEmpty()) {
                node = treeNodeStack.pop();
                node = node.right;
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
                node = node.left;
            }
            if (!treeNodeStack.isEmpty()) {
                node = treeNodeStack.pop();
                System.out.print(node.val + " ");
                node = node.right;
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
            System.out.print(currentNode.val + " "); //将二叉树当前根节点的值添加到列表中
            if (currentNode.left != null) {//如果有左子树就将左节点添加到“队列”中
                queue.add(currentNode.left);
            }
            if (currentNode.right != null) {//如果有右子树就将右节点添加到“队列”中
                queue.add(currentNode.right);
            }
        }
    }

    /**
     * 二叉查找树的查找操作
     */
    public TreeNode find(TreeNode root, int data) {
        while (root != null) {
            if (data < root.val) root = root.left;
            else if (data > root.val) root = root.right;
            else return root;
        }
        return null;
    }

    /**
     * 创建bst
     * 二叉查找树的插入操作
     */
    public TreeNode insert(TreeNode root, int data) {
        TreeNode node = new TreeNode(data);
        while (root != null) {
            if (data < (Integer) root.val) {
                if (root.left == null) {
                    root.left = (node);
                    return root;
                }
                root = root.left;
            } else {
                if (root.right == null) {
                    root.right = (node);
                    return root;
                }
                root = root.right;
            }
        }
        return root;
    }

    /**
     * 是否是二叉查找树
     * 节点的左子树只包含小于当前节点的数。
     * 节点的右子树只包含大于当前节点的数。
     * 所有左子树和右子树自身必须也是二叉搜索树。
     * 中序遍历 左子树 -> 结点 -> 右子树
     * 12645  6<4  return false
     * 时间复杂度 : 最坏情况下（树为二叉搜索树或破坏条件的元素是最右叶结点）为(N)。
     * 空间复杂度 : O(N)
     */
    public boolean isValidBST(TreeNode root) {
        Stack<TreeNode> stack = new Stack();
        int inorder = -Integer.MAX_VALUE;

        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            //计算中序遍历列表 inorder.
            //检查 inorder中的每个元素是否小于下一个。是就return false，不是就继续
            if ((Integer) root.val <= inorder) return false;
            inorder = (Integer) root.val;
            root = root.right;
        }
        return true;
    }


    /**
     * 给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。
     * 说明: 叶子节点是指没有子节点的节点。
     * 时间复杂度： O(N)
     * 空间复杂度:例如每个节点都只有一个孩子，递归会调用 N 次（树的高度），因此栈的空间开销是 O(N)
     */
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null)
            return false;

        sum -= (Integer) root.val;
        if ((root.left == null) && (root.right == null))
            return (sum == 0);
        return hasPathSum(root.left, sum) || hasPathSum(root.right, sum);
    }


    /*
     * 17.输入两棵二叉树A，B，判断B是不是A的子结构。（ps：我们约定空树不是任意一个树的子结构）
     * */
    public boolean hasSubtree(TreeNode root1, TreeNode root2) {
        boolean result = false;  //设置匹配标志位，匹配成功设为true
        if (root1 != null && root2 != null) {  //仅当root1和root2不为空树时比较，否则直接返回false
            if (root1.val == root2.val) {  //当二叉树root1和root2的根节点值相同时，比较它们的子树是否完全一致
                result = doesTree1HasTree2(root1, root2);
            }
            if (!result) {  //如果在root1的根节点处没有找到和root2根节点的值相同的节点，递归查找root1的左子树里是否有和root2根节点的值相同的节点
                result = hasSubtree(root1.left, root2);
            }
            if (!result) {  //如果在root1的左子树中没有找到和root2根节点的值相同的节点，递归查找root1的右子树里是否有和root2根节点的值相同的节点
                result = hasSubtree(root1.right, root2);
            }
        }
        return result;
    }

    public boolean doesTree1HasTree2(TreeNode root1, TreeNode root2) {
        if (root2 == null) {  //如果二叉树root2已经被遍历完，此时root1不管有没有遍历完，都说明匹配，二叉树root2是root1的子树
            return true;
        }
        if (root1 == null && root2 != null) {  //如果二叉树root1已经被遍历完，而root2没有被遍历完，说明root1里不包含root2
            return false;
        }
        if (root1.val != root2.val) {  //在递归遍历的比较过程中，也许root1中某个子树前面一些节点与root2的前面一些节点一致，但只要有中间一个节点的值不一致，说明root2不是root1的子树
            return false;
        }
        return doesTree1HasTree2(root1.left, root2.left) && doesTree1HasTree2(root1.right, root2.right);  //在root1当前子树根节点与root2根节点值相同时，递归比较它们的左子树和右子树是否都一致
    }


    /**
     * 删除比较复杂，看文章
     * https://time.geekbang.org/column/article/68334
     */
    public void delete(int data) {

    }


    public static void main(String[] args) {
        ArrayList<String> data = new ArrayList<>();
        String[] dataArray = new String[]{"A", "B", "D", "#", "#", "E", "#", "#", "C", "#", "F", "#", "#"};
        for (String s : dataArray) {
            data.add(s);
        }
        TreeOther treeOther = new TreeOther();

        TreeNode node = new TreeNode(3);
        treeOther.insert(node, 2);
        treeOther.insert(node, 5);
        treeOther.insert(node, 1);
        TreeNode insert = treeOther.insert(node, 5);
        boolean validBST = treeOther.isValidBST(insert);
        System.out.println(validBST);

    }


    /**
     * 145.(困难)后序遍历在决定是否可以输出当前节点的值的时候，需要考虑其左右子树是否都已经遍历完成。
     * 所以需要设置一个lastVisit游标。
     */
    public static void postorderTraversal(TreeNode root) {
        Stack<TreeNode> treeNodeStack = new Stack<TreeNode>();
        TreeNode node = root;
        TreeNode lastVisit = root;
        while (node != null || !treeNodeStack.isEmpty()) {
            while (node != null) {
                treeNodeStack.push(node);
                node = node.left;
            }
//查看当前栈顶元素
            node = treeNodeStack.peek();
//如果其右子树也为空(说明已经遍历完了)，若lastVisit等于当前考查节点的右子树，
// 表示该节点的左右子树都已经遍历完成，则可以输出当前节点。
//则可以直接输出当前节点的值
            if (node.right == null || node.right == lastVisit) {
                System.out.print(node.val + " ");
                treeNodeStack.pop();
                lastVisit = node;
                node = null;
            } else {
//否则，继续遍历右子树
                node = node.right;
            }
        }
    }


    //这是力扣第 116 题，看下题目：把二叉树的每一层节点都用 next 指针连接起来：
    // 主函数
    TreeNode connect(TreeNode root) {
        if (root == null) return null;
        connectTwoNode(root.left, root.right);
        return root;
    }

    // 辅助函数
    void connectTwoNode(TreeNode node1, TreeNode node2) {
        if (node1 == null || node2 == null) {
            return;
        }
        /**** 前序遍历位置 ****/
        // 将传入的两个节点连接
//        node1.next = node2;

        // 连接相同父节点的两个子节点
        connectTwoNode(node1.left, node1.right);
        connectTwoNode(node2.left, node2.right);
        // 连接跨越父节点的两个子节点
        connectTwoNode(node1.right, node2.left);
    }


    // 114：给你二叉树的根结点 root ，请你将它展开为一个单链表：
//    展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
//    展开后的单链表应该与二叉树 先序遍历 顺序相同。
    // 定义：将以 root 为根的树拉平为链表
    void flatten(TreeNode root) {
        // base case
        if (root == null) return;

        flatten(root.left);
        flatten(root.right);

        /**** 后序遍历位置 ****/
        // 1、左右子树已经被拉平成一条链表
        TreeNode left = root.left;
        TreeNode right = root.right;

        // 2、将左子树作为右子树
        root.left = null;
        root.right = left;

        // 3、将原先的右子树接到当前右子树的末端
        TreeNode p = root;
        while (p.right != null) {
            p = p.right;
        }
        p.right = right;
    }


    //654:构造最大二叉树
    /* 主函数 */
    TreeNode constructMaximumBinaryTree(int[] nums) {
        return build(nums, 0, nums.length - 1);
    }

    /* 将 nums[lo..hi] 构造成符合条件的树，返回根节点 */
    TreeNode build(int[] nums, int lo, int hi) {
        // base case
        if (lo > hi) {
            return null;
        }

        // 找到数组中的最大值和对应的索引
        int index = -1, maxVal = Integer.MIN_VALUE;
        for (int i = lo; i <= hi; i++) {
            if (maxVal < nums[i]) {
                index = i;
                maxVal = nums[i];
            }
        }

        TreeNode root = new TreeNode(maxVal);
        // 递归调用构造左右子树
        root.left = build(nums, lo, index - 1);
        root.right = build(nums, index + 1, hi);

        return root;
    }


    //105:通过前序和中序遍历结果构造二叉树
    /* 主函数 */
    TreeNode buildTree(int[] preorder, int[] inorder) {
        return build(preorder, 0, preorder.length - 1,
                inorder, 0, inorder.length - 1);
    }


    TreeNode build(int[] preorder, int preStart, int preEnd,
                   int[] inorder, int inStart, int inEnd) {

        if (preStart > preEnd) {
            return null;
        }

        // root 节点对应的值就是前序遍历数组的第一个元素
        int rootVal = preorder[preStart];
        // rootVal 在中序遍历数组中的索引
        int index = 0;
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == rootVal) {
                index = i;
                break;
            }
        }

        int leftSize = index - inStart;

        // 先构造出当前根节点
        TreeNode root = new TreeNode(rootVal);
        // 递归构造左右子树
        root.left = build(preorder, preStart + 1, preStart + leftSize,
                inorder, inStart, index - 1);

        root.right = build(preorder, preStart + leftSize + 1, preEnd,
                inorder, index + 1, inEnd);
        return root;
    }

    //106:通过后序和中序遍历结果构造二叉树
    TreeNode buildTree2(int[] inorder, int[] postorder) {
        return build2(inorder, 0, inorder.length - 1,
                postorder, 0, postorder.length - 1);
    }

    TreeNode build2(int[] inorder, int inStart, int inEnd,
                   int[] postorder, int postStart, int postEnd) {

        if (inStart > inEnd) {
            return null;
        }
        // root 节点对应的值就是后序遍历数组的最后一个元素
        int rootVal = postorder[postEnd];
        // rootVal 在中序遍历数组中的索引
        int index = 0;
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == rootVal) {
                index = i;
                break;
            }
        }
        // 左子树的节点个数
        int leftSize = index - inStart;
        TreeNode root = new TreeNode(rootVal);
//        // 递归构造左右子树
//        root.left = build(inorder, inStart, index - 1,
//                postorder, postStart, postStart + leftSize - 1);
//
//        root.right = build(inorder, index + 1, inEnd,
//                postorder, postStart + leftSize, postEnd - 1);
        return root;
    }


    //652:寻找重复子树
    // 记录所有子树以及出现的次数
    HashMap<String, Integer> memo = new HashMap<>();
    // 记录重复的子树根节点
    LinkedList<TreeNode> res = new LinkedList<>();

    /* 主函数 */
    List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        traverse(root);
        return res;
    }

    /* 辅助函数 */
    String traverse(TreeNode root) {
        if (root == null) {
            return "#";
        }

        String left = traverse(root.left);
        String right = traverse(root.right);

        String subTree = left + "," + right+ "," + root.val;

        int freq = memo.getOrDefault(subTree, 0);
        // 多次重复也只会被加入结果集一次
        if (freq == 1) {
            res.add(root);
        }
        // 给子树对应的出现次数加一
        memo.put(subTree, freq + 1);
        return subTree;
    }


}

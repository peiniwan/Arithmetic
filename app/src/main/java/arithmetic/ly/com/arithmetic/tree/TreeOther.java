package arithmetic.ly.com.arithmetic.tree;

public class TreeOther {
    public TreeNode root = null;


    /**
     * 获取二叉树的结点数
     * 返回左子树和右子树个数的和，然后加上一个根节点view
     *
     * @author Administrator
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
     *
     * @author Administrator
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
     * 寻找中序遍历时的下一个节点(不用迭代中序遍历)
     *
     * @param root
     * @return
     */
    public void traverse(TreeNode root) {
        for (TreeNode node = first(root);
             node != null;
             node = next(node)) {
            System.out.print(node.getValue());
        }
        System.out.println();
    }


    public TreeNode next(TreeNode node) {
        if (node == null) {
            return null;
        }

        if (node.getRight() != null) {
            return first(node.getRight());
        } else {
            while (node.getParent() != null
                    && node.getParent().getRight() == node) {
                node = node.getParent();
            }
            // now we have:
            // node.getParent() == null
            // || node is left child of its parent
            return node.getParent();
        }
    }

    public TreeNode first(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode curNode = root;
        while (curNode.getLeft() != null) {
            curNode = curNode.getLeft();
        }
        return curNode;
    }




    public static void main(String[] args) {
        TreeCreate creator = new TreeCreate();
        TreeOther treeOther = new TreeOther();

        TreeNode sampleTree = creator.createSampleTree();
        treeOther.traverse(sampleTree);

        treeOther.traverse(creator.createTree("", ""));
        treeOther.traverse(creator.createTree("A", "A"));
        treeOther.traverse(creator.createTree("AB", "BA"));
        treeOther.traverse(creator.createTree("ABCD", "DCBA"));
        treeOther.traverse(creator.createTree("ABCD", "ABCD"));

        System.out.println(treeOther.getSize(sampleTree) + "  ");
        System.out.println(treeOther.getHeight(sampleTree) + "  ");

    }
}

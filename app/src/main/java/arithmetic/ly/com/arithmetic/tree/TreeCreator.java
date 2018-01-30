package arithmetic.ly.com.arithmetic.tree;

public class TreeCreator {

    /**
     *           A
     *    B            C
     * D     E       #   F
     *      G  #
     * @return
     */
    public TreeNode createSampleTree() {
        TreeNode root = new TreeNode('A');
        root.setLeft(new TreeNode('B'));
        root.getLeft().setLeft(new TreeNode('D'));
        root.getLeft().setRight(new TreeNode('E'));
        root.getLeft().getRight().setLeft(new TreeNode('G'));
        root.setRight(new TreeNode('C'));
        root.getRight().setRight(new TreeNode('F'));
        return root;
    }


    /**
     * 根据前序和中序构造二叉树
     *
     * @param preOrder   "ABDEGCF"
     * @param inOrder    "DBGEACF"
     * @return
     */
    public TreeNode createTree(String preOrder, String inOrder) {
        if (preOrder.isEmpty()) {//BDEG  DBGE
            return null;
        }

        char rootValue = preOrder.charAt(0);
        int rootIndex = inOrder.indexOf(rootValue);

        TreeNode root = new TreeNode(rootValue);
        //BDEG的setLeft迭代执行完，会执行setRight
        root.setLeft(
                createTree(
                        preOrder.substring(1, 1 + rootIndex),
                        inOrder.substring(0, rootIndex)));
        root.setRight(//EG  GE
                createTree(
                preOrder.substring(1 + rootIndex),
                inOrder.substring(1 + rootIndex)));

        return root;
    }
}

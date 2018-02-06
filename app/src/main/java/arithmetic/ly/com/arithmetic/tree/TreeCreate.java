package arithmetic.ly.com.arithmetic.tree;

import java.util.ArrayList;

/**
 * Created by liuyu1 on 2018/1/30.
 */

public class TreeCreate {
    private TreeNode root;

    /**
     *           A
     *    B            C
     * D     E       #   F
     *     G  #
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
     * 查找二叉树建立
     * @param data
     * @return
     */
    private TreeNode put(int data) {
        TreeNode node = null;
        TreeNode parent = null;

        if (root == null) {
            node = new TreeNode(data);
            root = node;
            return node;
        }
        node = root;
        while (node != null) {
            //为了获取parent，30 50 60，data60的话parent会是50，然后60添加到右边
            parent = node;
            if (data > (int)node.getValue()) {
                node = node.getRight();
            } else if (data < (int)node.getValue()) {
                node = node.getLeft();
            } else {
                return node;
            }
        }
        //表示将此节点添加到相应的位置
        node = new TreeNode(data);
        if (data < (int)parent.getValue()) {
            parent.setLeft(node);
        } else {
            parent.setRight(node);
        }
        return node;
    }



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




    public static void main(String[] args) {

        TreeCreate treeCreate = new TreeCreate();
        TreeTraverse treeTraverse = new TreeTraverse();

//        ArrayList<String> data = new ArrayList<>();
//        String[] dataArray = new String[]{"A", "B", "D", "#", "#", "E", "#", "#", "C", "#", "F", "#", "#"};
//        for (String s : dataArray) {
//            data.add(s);
//        }
//        TreeNode binaryTreePre = treeCreate.createBinaryTreePre(data);
//        treeTraverse.preOrder(binaryTreePre);
//        System.out.println();
//        treeTraverse.inOrder(binaryTreePre);

        int[] intarry = new int[]{30, 50, 60, 40, 45, 25, 75, 15};
        for (int i : intarry) {
            treeCreate.put(i);
        }
        treeTraverse.inOrder(treeCreate.root);

//        System.out.println("=====");
//        System.out.println("Creating tree from preOrder and inOrder");
//        System.out.println("=====");
//        TreeNode tree = treeCreate.createTree("ABDEGCF", "DBGEACF");
//        treeTraverse.postOrder(tree);
//        System.out.println();
//        treeTraverse.postOrder(treeCreate.createTree("", ""));
//        System.out.println();
//        treeTraverse.postOrder(treeCreate.createTree("A", "A"));
//        System.out.println();
//        treeTraverse.postOrder(treeCreate.createTree("AB", "BA"));
//        System.out.println();

//        System.out.println("=====");
//        System.out.println("Generating postOrder directly");
//        System.out.println("=====");
//        System.out.println(
//                treeTraverse.postOrder("ABDEGCF", "DBGEACF"));
//        System.out.println(
//                treeTraverse.postOrder("", ""));
//        System.out.println(
//                treeTraverse.postOrder("A", "A"));
//        System.out.println(
//                treeTraverse.postOrder("AB", "BA"));

    }


}

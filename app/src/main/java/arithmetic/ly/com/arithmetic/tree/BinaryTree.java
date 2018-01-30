package arithmetic.ly.com.arithmetic.tree;

import java.util.ArrayList;
import java.util.Stack;

/**
 * @author
 */
public class BinaryTree {


    public class TreeNode<T> {
        private int index;
        //        private T data;
        private String data;
        private TreeNode leftChild;
        private TreeNode rightChild;


        public int getIndex() {
            return index;
        }


        public void setIndex(int index) {
            this.index = index;
        }


        public String getData() {
            return data;
        }


        public void setData(String data) {
            this.data = data;
        }


        public TreeNode(int index, String data) {
            this.index = index;
            this.data = data;
            this.leftChild = null;
            this.rightChild = null;
        }
    }

    public TreeNode root = null;

    public BinaryTree() {
        root = new TreeNode(1, "A");
    }

    /**
     * 构建二叉树
     * A
     * B       C
     * D E        F
     */
    public void createBinaryTree() {
        TreeNode nodeB = new TreeNode(2, "B");
        TreeNode nodeC = new TreeNode(3, "C");
        TreeNode nodeD = new TreeNode(4, "D");
        TreeNode nodeE = new TreeNode(5, "E");
        TreeNode nodeF = new TreeNode(6, "F");
        root.leftChild = nodeB;
        root.rightChild = nodeC;
        nodeB.leftChild = nodeD;
        nodeB.rightChild = nodeE;
        nodeC.rightChild = nodeF;
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
//            System.out.println("treeHeihgt:" + "----" + "0");
            return 0;
        } else {
//            System.out.println("treeHeihgt:" + node.data + "----" + "1");
            int i = getHeight(node.leftChild);
//            System.out.println("treeHeihgt:" + node.data + "----" + "2");
            int j = getHeight(node.rightChild);
//            System.out.println("treeHeihgt:" + node.data + "----" + i + "---" + j + "---" + (i + 1) + "---" + (j + 1));
            return (i < j) ? j + 1 : i + 1;
        }

//        treeHeihgt:A----1
//        treeHeihgt:B----1
//        treeHeihgt:D----1
//        treeHeihgt:----0
//        treeHeihgt:D----2
//        treeHeihgt:----0
//        treeHeihgt:D----0---0---1---1
//        treeHeihgt:B----2
//        treeHeihgt:E----1
//        treeHeihgt:----0
//        treeHeihgt:E----2
//        treeHeihgt:----0
//        treeHeihgt:E----0---0---1---1
//        treeHeihgt:B----1---1---2---2
//        treeHeihgt:A----2
//        treeHeihgt:C----1
//        treeHeihgt:----0
//        treeHeihgt:C----2
//        treeHeihgt:F----1
//        treeHeihgt:----0
//        treeHeihgt:F----2
//        treeHeihgt:----0
//        treeHeihgt:F----0---0---1---1
//        treeHeihgt:C----0---1---1---2
//        treeHeihgt:A----2---2---3---3
    }

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
            return 1 + getSize(node.leftChild) + getSize(node.rightChild);
        }
    }

    /**
     * 前序遍历——迭代
     *
     * @author Administrator
     * 根左右
     * A B D E C F
     */
    public void preOrder(TreeNode node) {
        if (node == null) {
            return;
        } else {
            System.out.println("preOrder data:" + node.getData());
            preOrder(node.leftChild);
            preOrder(node.rightChild);
        }
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
            System.out.println("nonRecOrder data" + n.getData());
            if (n.rightChild != null) {
                stack.push(n.rightChild);

            }
            if (n.leftChild != null) {
                stack.push(n.leftChild);
            }
        }
    }

    /**
     * 通过前序遍历的数据反向生成二叉树
     *           A
     *       B       C
     *   D     E   #     F
     * #   #  # #     #    #
     * ABD##E##C#F##
     */
    public void createBinaryTreePre(ArrayList<String > data){
        createBinaryTree(data.size(),data);
    }

    private TreeNode createBinaryTree(int size, ArrayList<String> data) {
        if(data.size()==0){
            return null;
        }
        String d=data.get(0);
        TreeNode node;
        int index=size-data.size();
        if(d.equals("#")){
            node=null;
            data.remove(0);
            return  node;
        }
        node=new TreeNode(index,d);
        if(index==0){
            //创建根节点
            root=node;
        }
        data.remove(0);
        node.leftChild=createBinaryTree(size,data);
        node.rightChild =createBinaryTree(size,data);

        return node;
    }

    /**
     * 中序遍历——迭代
     *
     * @author Administrator
     * 左根右
     * D B E A C F
     */
    public void midOrder(TreeNode node) {
        if (node == null) {
            return;
        } else {
            midOrder(node.leftChild);
            System.out.println("midOrder data:" + node.getData());
            midOrder(node.rightChild);
        }
    }

    /**
     * 后序遍历——迭代
     *
     * @author Administrator
     */
    public void postOrder(TreeNode node) {
        if (node == null) {
            return;
        } else {
            postOrder(node.leftChild);
            postOrder(node.rightChild);
            System.out.println("postOrder data:" + node.getData());
        }
    }


    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
//        binaryTree.createBinaryTree();
//        int height = binaryTree.getHeight();
//        System.out.println("treeHeihgt:" + height);
//        int size = binaryTree.getSize();
//        System.out.println("treeSize:" + size);
////		binaryTree.preOrder(binaryTree.root);
//        binaryTree.midOrder(binaryTree.root);
////		binaryTree.postOrder(binaryTree.root);
////        binaryTree.nonRecOrder(binaryTree.root);

        ArrayList<String> data = new ArrayList<>();
        String[] dataArray=new String[]{"A","B","D","#","#","E","#","#","C","#","F","#","#"};
        for (String s : dataArray) {
            data.add(s);
        }
        binaryTree.createBinaryTreePre(data);
        binaryTree.postOrder(binaryTree.root);

    }
}

package arithmetic.ly.com.arithmetic.tree;

/**
 * Created by 拯救者 on 2018/1/26.
 * 查找二叉树的建立
 */

public class SearchBinaryTree {

    public static void main(String[] args) {
        SearchBinaryTree searchBinaryTree = new SearchBinaryTree();
        int[] intarry = new int[]{50, 30, 20, 70, 90, 77, 85, 44};
        for (int i : intarry) {
            searchBinaryTree.put(i);
        }
        searchBinaryTree.midOrder(searchBinaryTree.root);
    }

    private TreeNode root;

    public SearchBinaryTree() {

    }

    //从小到大用中序遍历
    public void midOrder(TreeNode node) {
        if (node == null) {
            return;
        } else {
            midOrder(node.leftChild);
            System.out.print(node.data + " ");
            midOrder(node.rightChild);
        }
    }

    public TreeNode put(int data) {
        TreeNode node = null;
        TreeNode parent = null;
        if (root == null) {
            node = new TreeNode(0, data);
            root = node;
            return node;
        }
        node = root;
        while (node != null) {
            parent = node;
            if (data > node.data) {
                node = node.rightChild;
            } else if (data < node.data) {
                node = node.leftChild;
            } else {
                return node;
            }
        }
        //表示将此节点添加到相应的位置
        node = new TreeNode(0, data);
        if (data < parent.data) {
            parent.leftChild = node;
            node.parent = parent;
        } else {
            parent.rightChild = node;
        }
        node.parent = parent;
        return node;
    }

    class TreeNode {
        private int key;
        private int data;
        private TreeNode leftChild;
        private TreeNode rightChild;
        private TreeNode parent;

        public TreeNode(int key, int data) {
            this.key = key;
            this.data = data;
            this.leftChild = null;
            this.rightChild = null;
            this.parent = null;
        }

        public int getKey() {
            return key;
        }

        public void setKey(int key) {
            this.key = key;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }


    }
}

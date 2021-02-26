package com.example;

/**
 * Created by 拯救者 on 2018/2/17.
 */

public class TreeNode<T> {
    public TreeNode(T value) {
        this.value = value;
        this.left = null;
        this.right = null;
        this.parent = null;
    }


    public T getValue() {
        return value;
    }

    private final T value;

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
        if (this.left != null) {
            this.left.setParent(this);
        }
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
        if (this.right != null) {
            this.right.setParent(this);
        }
    }

    public TreeNode getParent() {
        return parent;
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    private TreeNode left;
    private TreeNode right;
    private TreeNode parent;

}

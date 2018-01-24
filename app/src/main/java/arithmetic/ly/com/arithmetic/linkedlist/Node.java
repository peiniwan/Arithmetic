package arithmetic.ly.com.arithmetic.linkedlist;

import android.util.Log;

/**
 * Created by liuyu1 on 2018/1/17.
 */

public class Node {
    public Node next; //指针域
    public int data;//数据域

    public Node(int data) {
        this.data = data;
    }

    //显示此节点
    public void display() {
//        System.out.print(data + " ");
        Log.d("data", data + " ");
    }
}

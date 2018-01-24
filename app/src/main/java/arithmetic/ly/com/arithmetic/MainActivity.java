package arithmetic.ly.com.arithmetic;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;
import java.util.LinkedList;

import arithmetic.ly.com.arithmetic.linkedlist.LinkList;
import arithmetic.ly.com.arithmetic.linkedlist.Node;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinkedList<Object> objects = new LinkedList<>();
        objects.add(1);

        LinkList linkList = new LinkList();
        linkList.addFirstNode(1);
        linkList.addFirstNode(2);
        linkList.addFirstNode(3);
        linkList.addFirstNode(4);

        linkList.deleteFirstNode();//321
        linkList.add(2, 5);//3251
        linkList.deleteByPos(2);//321   0是删的第一个
        linkList.deleteByData(3);//21

        linkList.addFirstNode(3);//321
        linkList.addFirstNode(4);//4321

        Node byPos = linkList.findByPos(1);
        Log.d("data","byPos:"+byPos.data);
        Node byData = linkList.findByData(1);
        Log.d("data","byData:"+byData.data);


        linkList.displayAllNodes();

        BinaryTree binaryTree = new BinaryTree();
        ArrayList<String> data = new ArrayList<>();
        String[] dataArray=new String[]{"A","B","D","#","#","E","#","#","C","#","F","#","#"};
        for (String s : dataArray) {
            data.add(s);
        }
        binaryTree.createBinaryTreePre(data);
        binaryTree.preOrder(binaryTree.root);
//        Stack stack = new Stack();
//        stack.peek();

    }
}

package arithmetic.ly.com.arithmetic.linkedlist;

/**
 * Created by liuyu1 on 2018/1/17.
 */

public class LinkList {
    public Node first; // 定义一个头结点
    private int pos = 0;// 节点的位置

    public LinkList() {
        this.first = null;
    }

    // 插入一个头节点
    public void addFirstNode(int data) { // data 1  next null data 2 next 1  data 3 next 2
        Node node = new Node(data);
        node.next = first;
        first = node;
    }

    // 删除一个头结点,并返回头结点
    public Node deleteFirstNode() {
        Node tempNode = first;
        first = tempNode.next;
        return tempNode;
    }

    // 在任意位置插入节点 在index的后面插入
    public void add(int index, int data) {
        Node node = new Node(data);
        Node current = first;
        Node previous = first;
        while (pos != index) {
            previous = current;
            current = current.next;
            pos++;
        }
        node.next = current;//最后current 1 null  previous 2  1
        previous.next = node;// node.next设为index后面的节点，index.next设为node
        pos = 0;
    }

    // 删除任意位置的节点
    public Node deleteByPos(int index) {
        Node current = first;
        Node previous = first;
        while (pos != index) {
            pos++;
            previous = current;
            current = current.next;
        }
        if (current == first) {
            first = first.next;
        } else {
            pos = 0;
            previous.next = current.next;//251   51
        }
        return current;
    }

    // 根据节点的data删除节点(仅仅删除第一个)
    public Node deleteByData(int data) {
        Node current = first;
        Node previous = first; //记住上一个节点
        while (current.data != data) {
            if (current.next == null) {
                return null;
            }
            previous = current;
            current = current.next;
        }
        if (current == first) {
            first = first.next;
        } else {
            previous.next = current.next;
        }
        return current;
    }

    // 根据位置查找节点信息
    public Node findByPos(int index) {
        Node current = first;
        if (pos != index) {
            current = current.next;
            pos++;
        }
        return current;
    }

    // 根据数据查找节点信息
    public Node findByData(int data) {
        Node current = first;
        while (current.data != data) {
            if (current.next == null) {
                return null;
            }
            current = current.next;
        }
        return current;
    }

    // 显示出所有的节点信息
    public void displayAllNodes() {
        Node current = first;
        while (current != null) {
            current.display();
            current = current.next;
        }
    }


    public class Node {
        public Node next; //指针域
        public int data;//数据域

        public Node(int data) {
            this.data = data;
        }

        //显示此节点
        public void display() {
            System.out.print(data + " ");
        }
    }

    public static void main(String[] args) {
        LinkList linkList = new LinkList();
        linkList.addFirstNode(1);
        linkList.addFirstNode(2);
        linkList.addFirstNode(3);
        linkList.addFirstNode(4);

        linkList.deleteFirstNode();//321
        linkList.add(2, 5);//3251
        linkList.deleteByPos(2);//321   0是删的第一个
//        linkList.deleteByData(3);//21
//
//        linkList.addFirstNode(3);//321
//        linkList.addFirstNode(4);//4321
//        LinkList.Node byPos = linkList.findByPos(1);
//        System.out.println("byPos:" + byPos.data);
//        LinkList.Node byData = linkList.findByData(1);
//        System.out.println("byPos:" + byData.data);

        linkList.displayAllNodes();
    }
}

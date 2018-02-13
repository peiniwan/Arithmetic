package arithmetic.ly.com.arithmetic.linkedlist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import arithmetic.ly.com.arithmetic.Node;

/**
 * Created by 拯救者 on 2018/1/27.
 */
public class LinkedListTest {

    /**
     * Creates a linked list.
     *
     * @param data the data to create the list
     * @return head of the linked list. The returned linked list
     * ends with last node with getNext() == null.
     */
    public <T> Node<T> createLinkedList(List<T> data) {
        if (data.isEmpty()) {
            return null;
        }

        Node<T> firstNode = new Node<>(data.get(0));
        firstNode.setNext(
                createLinkedList(data.subList(1, data.size())));
        return firstNode;
    }


    public Node<Integer> createLargeLinkedList(int size) {
        Node<Integer> prev = null;
        Node<Integer> head = null;
        for (int i = 1; i <= size; i++) {
            Node<Integer> node = new Node<>(i);
            if (prev != null) {
                prev.setNext(node);
            } else {
                head = node;
            }
            prev = node;
        }
        return head;
    }

    /**
     * Reverses a linked list.
     *
     * @param head the linked list to reverse
     * @return head of the reversed linked list
     */
    public <T> Node<T> reverseLinkedList(Node<T> head) {
        // size == 0 or size == 1
        if (head == null || head.getNext() == null) {
            //第一次出来的是5,他的nextnull
            return head;
        }
        //先一般再特殊，先假设可以反转，缩小问题规模程度1
        //写完一般后再一行一行看可能会出现什么问题
        //递归执行完后，递归上面的方法就不回执行了
        //2-1-null
        Node<T> newHead = reverseLinkedList(head.getNext());
        head.getNext().setNext(head);//2->1
        head.setNext(null);//1->null
//      head是4，将4.next（5）的next设为4，第一次出来5的next是4，4的next是null
        return newHead;
    }


    /**
     * 列出所有的组合
     * Generates all combinations and output them,
     * selecting n elements from data.
     */
    public void combinations(List<Integer> selected, List<Integer> data, int n) {
        if (n == 0) {
            // output all selected elements
            for (Integer i : selected) {
                System.out.print(i);
                System.out.print(" ");
            }
            System.out.println();
            return;
        }

        if (data.isEmpty()) {
            return;
        }

        // select element 0
        selected.add(data.get(0));
        combinations(selected, data.subList(1, data.size()), n - 1);

        // un-select element 0
        selected.remove(selected.size() - 1);
        combinations(selected, data.subList(1, data.size()), n);
    }


    // 插入一个头节点
    public Node addFirstNode(Node head, int data) { // data 1  next null data 2 next 1  data 3 next 2
        Node node = new Node(data);
        node.setNext(head);
        head = node;
        return head;
    }

    // 删除一个头结点
    public Node deleteFirstNode(Node head) {
        Node tempNode = head;
        head = tempNode.getNext();
        return head;
    }

    // 在任意位置插入节点 在index的后面插入
    public Node add(Node head, int index, int data) {
        int pos = 0;
        Node node = new Node(data);
        Node current = head;
        Node previous = head;
        while (pos != index) {
            previous = current;
            current = current.getNext();
            pos++;
        }
        node.setNext(current);//最后current 1 null  previous 2  1
        previous.setNext(node);// node.next设为index后面的节点，index.next设为node
        pos = 0;
        return head;
    }


    /**
     * 删除指定位置的结点(错误)
     *
     * @param index 索引
     */
    public Node deleteByPos(Node head, int index) {
        Node node = head;
        int j = 0;
        while (node != null && j < index - 2) {
            //查找到第i-1个元素
            node = node.getNext();
            j++;
        }
        node.setNext(node.getNext().getNext());//删除第index个元素
        return head;
    }

    /**
     * 删除指定的数据
     * @param head
     * @param data
     * @return
     */
    public Node deleteByData(Node head, int data) {
        if ((int) head.getValue() == data) {
            head = head.getNext();
        } else {//此时根元素已经判断过了，从第二个元素开始，head就是上一个node，传进去
            head.getNext().removeNode(head, data);
        }
        return head;
    }


    // 根据位置查找节点信息
    public Object findByPos(Node head, int index) {
        int pos = 0;
        Node current = head;
        while (pos != index) {
            if (current.getNext() != null) {
                return -1;
            }
            current = current.getNext();
            pos++;
        }
        return current.getValue();
    }


    // 根据数据查找节点信息
    public int findByData(Node head, Object data) {
        int pos = 0;
        Node current = head;
        while (current.getValue() != data) {
            if (current.getNext() == null) {
                return -1;
            }
            current = current.getNext();
            pos++;
        }
        return pos;
    }


    public static void main(String[] args) {
        LinkedListTest creator = new LinkedListTest();

        Node.printLinkedList(
                creator.createLinkedList(new ArrayList<>()));
        Node.printLinkedList(
                creator.createLinkedList(Arrays.asList(1)));
        Node.printLinkedList(
                creator.createLinkedList(Arrays.asList(1, 2, 3, 4, 5)));

//        Node.printLinkedList(creator.reverseLinkedList(
//                creator.createLinkedList(new ArrayList<>())));
//
//        Node.printLinkedList(creator.reverseLinkedList(
//                creator.createLinkedList(Arrays.asList(1))));

//        Node.printLinkedList(creator.reverseLinkedList(
//                creator.createLinkedList(Arrays.asList(1, 2, 3, 4, 5))));

//        System.out.println("Testing large data. Expect exceptions.");
//        creator.reverseLinkedList(
//                creator.createLargeLinkedList(1000000));
//        System.out.println("done");


//        System.out.println("Testing normal data.");
//        creator.combinations(
//                new ArrayList<Integer>(), Arrays.asList(1, 2, 3), 2);
//        System.out.println("==========");
//
//        System.out.println("Testing empty source data.");
//        creator.combinations(new ArrayList<Integer>(), new ArrayList<Integer>(), 2);
//        System.out.println("==========");
//        creator.combinations(
//                new ArrayList<Integer>(), new ArrayList<Integer>(), 0);
//        System.out.println("==========");
//
//        System.out.println("Selecting 1 and 0 elements.");
//        comb.combinations(
//                new ArrayList<Integer>(), Arrays.asList(1, 2, 3, 4), 1);
//        System.out.println("==========");
//        creator.combinations(
//                new ArrayList<Integer>(), Arrays.asList(1, 2, 3, 4), 0);
//        System.out.println("==========");
//
//        System.out.println("Testing large data");
//        creator.combinations(
//                new ArrayList<Integer>(),
//                Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), 4);

        Node.printLinkedList(creator.addFirstNode(
                creator.createLinkedList(Arrays.asList(1, 2, 3, 4, 5)), 3));

        Node.printLinkedList(creator.deleteFirstNode(
                creator.createLinkedList(Arrays.asList(1, 2, 3, 4, 5))));

        Node.printLinkedList(creator.add(
                creator.createLinkedList(Arrays.asList(1, 2, 3, 4, 5)), 4, 8));

        System.out.println(creator.findByPos(
                creator.createLinkedList(Arrays.asList(3, 2, 5, 1)), 4));

        System.out.println(creator.findByData(
                creator.createLinkedList(Arrays.asList(3, 2, 5, 1)), 1));

//        Node.printLinkedList(creator.deleteByPos(
//                creator.createLinkedList(Arrays.asList(1, 2, 3, 4, 5, 6)), 3));

        Node.printLinkedList(creator.deleteByData(
                creator.createLinkedList(Arrays.asList(1, 2, 3, 4, 5, 6)), 5));


    }
}

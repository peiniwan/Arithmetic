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
            return head;
        }
        //先一般再特殊，先假设可以反转，缩小问题规模程度1
        //写完一般后再一行一行看可能会出现什么问题
        //递归执行完后，递归上面的方法就不回执行了
        //2-1-null
        Node<T> newHead = reverseLinkedList(head.getNext());
        head.getNext().setNext(head);//2->1
        head.setNext(null);//1->null
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

    public static void main(String[] args) {
        LinkedListTest creator = new LinkedListTest();

        Node.printLinkedList(
                creator.createLinkedList(new ArrayList<>()));
        Node.printLinkedList(
                creator.createLinkedList(Arrays.asList(1)));
        Node.printLinkedList(
                creator.createLinkedList(Arrays.asList(1, 2, 3, 4, 5)));

        Node.printLinkedList(creator.reverseLinkedList(
                creator.createLinkedList(new ArrayList<>())));

        Node.printLinkedList(creator.reverseLinkedList(
                creator.createLinkedList(Arrays.asList(1))));

        Node.printLinkedList(creator.reverseLinkedList(
                creator.createLinkedList(Arrays.asList(1, 2, 3, 4, 5))));

        System.out.println("Testing large data. Expect exceptions.");
        creator.reverseLinkedList(
                creator.createLargeLinkedList(1000000));
        System.out.println("done");


        System.out.println("Testing normal data.");
        creator.combinations(
                new ArrayList<Integer>(), Arrays.asList(1, 2, 3), 2);
        System.out.println("==========");
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

    }
}

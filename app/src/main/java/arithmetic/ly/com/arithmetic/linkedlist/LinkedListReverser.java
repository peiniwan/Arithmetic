package arithmetic.ly.com.arithmetic.linkedlist;

import java.util.ArrayList;
import java.util.Arrays;

import arithmetic.ly.com.arithmetic.Node;

/**
 * Created by 拯救者 on 2018/1/27.
 * 反转链表
 */

public class LinkedListReverser {

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

    public static void main(String[] args) {
        LinkedListCreator creator = new LinkedListCreator();
        LinkedListReverser reverser = new LinkedListReverser();

        Node.printLinkedList(reverser.reverseLinkedList(
                creator.createLinkedList(new ArrayList<>())));

        Node.printLinkedList(reverser.reverseLinkedList(
                creator.createLinkedList(Arrays.asList(1))));

        Node.printLinkedList(reverser.reverseLinkedList(
                creator.createLinkedList(Arrays.asList(1, 2, 3, 4, 5))));

        System.out.println("Testing large data. Expect exceptions.");
        reverser.reverseLinkedList(
                creator.createLargeLinkedList(1000000));
        System.out.println("done");
    }
}


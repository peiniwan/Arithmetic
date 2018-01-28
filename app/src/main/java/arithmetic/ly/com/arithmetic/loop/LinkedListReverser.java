package arithmetic.ly.com.arithmetic.loop;

import java.util.Arrays;

import arithmetic.ly.com.arithmetic.Node;
import arithmetic.ly.com.arithmetic.linkedlist.LinkedListCreator;

/**
 * Created by 拯救者 on 2018/1/28.
 *
 */

public class LinkedListReverser {
    public <T> Node<T> reverseLinkedList(Node<T> head) {
        Node<T> newHead = null;//5
        Node<T> curHead = head;//null
        // Loop invariant:
        // newHead points to the linked list already reversed.
        // curHead points to the linked list not yet reversed.

        // Loop invariant holds.
        while(curHead != null) {
            // Loop invariant holds.
            Node<T> next = curHead.getNext();//2
            curHead.setNext(newHead);//1的next的null
            newHead = curHead;//=1 next:null
            curHead = next;//=2 next:3
            // Loop invariant holds.
        }
        // Loop invariant holds.
        return newHead;
    }

    public static void main(String[] args) {
        LinkedListCreator creator = new LinkedListCreator();
        LinkedListReverser reverser = new LinkedListReverser();

//        Node.printLinkedList(reverser.reverseLinkedList(
//                creator.createLinkedList(new ArrayList<>())));
//
//        Node.printLinkedList(reverser.reverseLinkedList(
//                creator.createLinkedList(Arrays.asList(1))));
//
        Node.printLinkedList(reverser.reverseLinkedList(
                creator.createLinkedList(Arrays.asList(1, 2, 3, 4, 5))));
//
//        reverser.reverseLinkedList(
//                creator.createLargeLinkedList(1000000));
//        System.out.println("done");
    }
}

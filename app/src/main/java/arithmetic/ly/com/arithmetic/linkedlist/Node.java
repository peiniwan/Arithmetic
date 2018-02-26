package arithmetic.ly.com.arithmetic.linkedlist;

/**
 * Created by 拯救者 on 2018/1/27.
 */
public class Node<T> {
    private final T value;
    private Node<T> next;

    public Node(T value) {
        this.value = value;
        this.next = null;
    }

    public T getValue() {
        return value;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

    public static <T> void printLinkedList(Node<T> head) {
        while (head != null) {
            System.out.print(head.getValue());
            System.out.print(" ");
            head = head.getNext();
        }
        System.out.println();
    }

    public void removeNode(Node previous, T data) {
        //第一次previous=node、this=node.next
        //第二次previous=node.next、this=node.next.next
        if (data.equals(this.value)) {
            previous.next = this.next;//空出当前节点
        } else {//向后继续查询
            if (this.next == null) {
                return;
            }
            this.next.removeNode(this, data);
        }
    }
}

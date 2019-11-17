package arithmetic.ly.com.arithmetic.stackqueue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 俩个队列实现栈
 */
public class StackWithQueue {

    public static void main(String[] args) {
        push(1);
        push(2);
        push(3);
        pop();
        pop();
        pop();

    }

    private static Queue<Object> queue1 = new LinkedList<Object>();
    private static Queue<Object> queue2 = new LinkedList<Object>();

    /*
     * 向队列中执行入栈操作时，把元素添加到非空的队列中
     */
    public static void push(Object item) {
        if (!queue1.isEmpty())
            queue1.offer(item);
        else
            queue2.offer(item);
        System.out.println("入栈元素为：" + item);
    }

    public static int  pop() {
        if (!isEmpty()) {
            if (queue1.isEmpty()) {
                while (queue2.size() > 1) {
                    queue1.offer(queue2.poll());
                }
                //移到1队列里面，剩下一个就是要出栈的元素
                System.out.println("出栈元素为：" + queue2.poll());
                return (int) queue2.poll();
            } else {
                while (queue1.size() > 1) {
                    queue2.offer(queue1.poll());
                }
                System.out.println("出栈元素为：" + queue1.poll());
                return (int) queue1.poll();
            }
        } else
//            throw new RuntimeException("栈为空，无法执行出栈操作");
        return -1;
    }

    /*
     * 检查栈是否为空
     */
    private static boolean isEmpty() {
        return queue1.isEmpty() && queue2.isEmpty();
    }
}

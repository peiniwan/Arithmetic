package arithmetic.ly.com.arithmetic.stackqueue;

import java.util.LinkedList;
import java.util.Queue;

public class QueueTest {
    /**
     * 基于数组实现的顺序栈
     * 时间复杂度和空间复杂度都是O(1)
     * 注意，这里存储数据需要一个大小为 n 的数组，并不是说空间复杂度就是 O(n)。
     * 因为，这 n 个空间是必须的，无法省掉。
     */
    public class ArrayStack {

        private String[] items;  // 数组
        private int count;       // 栈中元素个数
        private int n;           //栈的大小

        // 初始化数组，申请一个大小为n的数组空间
        public ArrayStack(int n) {
            this.items = new String[n];
            this.n = n;
            this.count = 0;
        }

        // 入栈操作
        public boolean push(String item) {
            // 数组空间不够了，直接返回false，入栈失败。
            if (count == n) return false;
            // 将item放到下标为count的位置，并且count加一
            items[count] = item;
            ++count;//在前在后都无所谓
            return true;
        }

        // 出栈操作
        public String pop() {
            // 栈为空，则直接返回null
            if (count == 0) return null;
            // 返回下标为count-1的数组元素，并且栈中元素个数count减一
            String tmp = items[count - 1];
            --count;
            return tmp;
        }
    }

    /**
     * 用数组实现的队列
     */
    public class ArrayQueue {
        // 数组：items，数组大小：n
        private String[] items;
        private int n = 0;
        // head表示队头下标，tail表示队尾下标
        private int head = 0;
        private int tail = 0;

        // 申请一个大小为capacity的数组
        public ArrayQueue(int capacity) {
            items = new String[capacity];
            n = capacity;
        }

        // 入队
        public boolean enqueue(String item) {
            // 如果tail == n 表示队列已经满了
            if (tail == n) return false;
            items[tail] = item;
            ++tail;
            return true;
        }

        // 出队
        public String dequeue() {
            // 如果head == tail 表示队列为空
            if (head == tail) return null;
            // 为了让其他语言的同学看的更加明确，把--操作放到单独一行来写了
            String ret = items[head];
            ++head;
            return ret;
        }

        // 入队操作，将item放入队尾
        public boolean enqueue2(String item) {
            // tail == n表示队列末尾没有空间了
            if (tail == n) {
                // tail ==n && head==0，表示整个队列都占满了
                if (head == 0) return false;
                // 数据搬移
                for (int i = head; i < tail; ++i) {
                    items[i - head] = items[i];
                }
                // 搬移完之后重新更新head和tail
                tail -= head;
                head = 0;
            }

            items[tail] = item;
            ++tail;
            return true;
        }

    }


    /**
     * 俩个队列实现栈
     */
    public static class StackWithQueue {

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

        public static void pop() {
            if (!isEmpty()) {
                if (queue1.isEmpty()) {
                    while (queue2.size() > 1) {
                        queue1.offer(queue2.poll());
                    }
                    System.out.println("出栈元素为：" + queue2.poll());
                } else {
                    while (queue1.size() > 1) {
                        queue2.offer(queue1.poll());
                    }
                    System.out.println("出栈元素为：" + queue1.poll());
                }
            } else
                throw new RuntimeException("栈为空，无法执行出栈操作");
        }

        /*
         * 检查栈是否为空
         */
        private static boolean isEmpty() {
            return queue1.isEmpty() && queue2.isEmpty();
        }
    }


    /**
     * 循环队列
     */
    public class CircularQueue {
        // 数组：items，数组大小：n
        private String[] items;
        private int n = 0;
        // head表示队头下标，tail表示队尾下标
        private int head = 0;
        private int tail = 0;

        // 申请一个大小为capacity的数组
        public CircularQueue(int capacity) {
            items = new String[capacity];
            n = capacity;
        }

        // 入队
        public boolean enqueue(String item) {
            // 队列满了
            if ((tail + 1) % n == head) return false;
            items[tail] = item;
            tail = (tail + 1) % n;
            return true;
        }

        // 出队
        public String dequeue() {
            // 如果head == tail 表示队列为空
            if (head == tail) return null;
            String ret = items[head];
            head = (head + 1) % n;
            return ret;
        }

    }


}

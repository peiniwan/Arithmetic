package arithmetic.ly.com.arithmetic.stackqueue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class QueueTest {


    /**
     * 用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型。
     */
    class TwoStacksAsQueue {
        Stack<Integer> stack1 = new Stack<Integer>();
        Stack<Integer> stack2 = new Stack<Integer>();

        public void push(int node) {
            stack1.push(node);
        }

        public int pop() {
            if (stack1.isEmpty() && stack2.isEmpty()) {
                throw new RuntimeException("The queue is empty.");
            }
            if (stack2.isEmpty()) {
                while (!stack1.isEmpty()) {
                    stack2.push(stack1.pop());
                }
            }
            return stack2.pop();
        }
    }


    /**
     * 两个队列实现一个栈
     * 创建两个队列，比如 Queue<Integer> queue1 和 Queue<Integer> queue2。
     * 入栈操作时，将元素添加到非空的队列中。如果两个队列都为空，可以选择将元素添加到 queue1 中。
     * 出栈操作时，将一个队列中的元素依次转移到另一个空队列中，直到只剩下一个元素。这个剩下的元素就是要出栈的元素，将其移除并返回。
     * 为了实现下一次的出栈操作，需要交换两个队列的引用。即将 queue2 的引用指向当前非空队列，将 queue1 的引用指向空队列。
     */
    public class StackWithTwoQueues {
        private Queue<Integer> queue1;
        private Queue<Integer> queue2;

        public StackWithTwoQueues() {
            queue1 = new LinkedList<>();
            queue2 = new LinkedList<>();
        }

        public void push(int element) {
            if (queue1.isEmpty()) {
                queue2.offer(element);
            } else {
                queue1.offer(element);
            }
        }

        public int pop() {
            if (isEmpty()) {
                throw new RuntimeException("Stack is empty.");
            }

            Queue<Integer> nonEmptyQueue = queue1.isEmpty() ? queue2 : queue1;
            Queue<Integer> emptyQueue = queue1.isEmpty() ? queue1 : queue2;

            while (nonEmptyQueue.size() > 1) {
                emptyQueue.offer(nonEmptyQueue.poll());
            }

            return nonEmptyQueue.poll();
        }

        public boolean isEmpty() {
            return queue1.isEmpty() && queue2.isEmpty();
        }
    }

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
            String tmp = items[count - 1];//因为前面已经+1了
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

    /**
     * 20. 有效的括号
     * 输入: "()[]{}"
     * 输出: true
     * 输入: "([)]"
     * 输出: false
     * 输入: "{[]}"
     * 输出: true
     * 栈先入后出特点恰好与本题括号排序特点一致，即若遇到左括号入栈，
     * 遇到右括号时将对应栈顶左括号出栈，则遍历完所有括号后 stack 仍然为空；
     */
    public boolean isValid(String s) {
        if (s.isEmpty())
            return true;
        Stack<Character> stack = new Stack<Character>();
        for (char c : s.toCharArray()) {
            if (c == '(')
                stack.push(')');
            else if (c == '{')
                stack.push('}');
            else if (c == '[')
                stack.push(']');
            else if (stack.empty() || c != stack.pop())//([)]不相等，直接返回false
                return false;
        }
        if (stack.empty())
            return true;
        return false;
    }


    /**
     * 实现一个大小固定的有序数组，支持动态增删改操作
     */
    public class Array {
        private String[] data;
        private int len;
        private int cap;

        // 构造器
        public Array(int capacity) {
            data = new String[capacity];
            len = 0;
            cap = capacity;
        }

        public boolean append(String value) {
            if (len == cap) {
                return false;
            }
            data[len++] = value;
            return true;
        }

        public boolean insert(int index, String value) {
            if (len == cap) {
                return false;
            }
            if (index < 0 || index > len) {
                return false;
            }
            for (int i = len; i > index; i--) {
                data[i] = data[i - 1];
            }
            data[index] = value;
            len++;
            return true;
        }

        public boolean delete(int index, String value) {
            if (len == 0) {
                return false;
            }
            if (index < 0 || index > len) {
                return false;
            }
            for (int i = index; i <= len - 1; i++) {
                data[i - 1] = data[i];
            }
            len--;
            return true;
        }
    }


    /**
     * 20.定义栈的数据结构，请在该类型中实现一个能够得到栈中所含最小元素的min函数（时间复杂度应为O（1））。
     */
    Stack<Integer> data = new Stack<Integer>();  //初始化一个数据栈来存放所有元素
    Stack<Integer> min = new Stack<Integer>();  //初始化一个辅助栈存放依次存放最小元素
    Integer temp = null;  //不能用int，只有Integer对象才能为空

    public void push(int node) {
        if (temp != null) {
            if (node <= temp) {  //当有比辅助栈min中的栈顶元素更小或等于的元素时，将更小元素放入辅助栈以及数据栈，否则只放入数据栈
                temp = node;
                min.push(node);
            }
        } else {  //当一开始temp值为空时，第一个元素可以放入最小栈和数据栈中
            temp = node;
            min.push(node);
        }
        data.push(node);

    }

    public void pop() {
        int dataNumber = data.pop();
        int minNumber = min.pop();
        if (dataNumber != minNumber) min.push(minNumber);  //如果数据栈中出栈的元素并非最小元素，再把最小元素放回辅助栈
    }

    public int top() {
        return data.peek();  //检查数据栈中的栈顶元素但不出栈
    }

    public int min() {
        return min.peek();  //检查辅助栈中的栈顶元素但不出栈，即最小元素
    }





}

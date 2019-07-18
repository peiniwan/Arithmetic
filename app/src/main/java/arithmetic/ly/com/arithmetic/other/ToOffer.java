package arithmetic.ly.com.arithmetic.other;

import java.util.Stack;

public class ToOffer {

    public static void main(String[] args) {
        ToOffer toOffer = new ToOffer();
        toOffer.replaceSpace("We Are Happy");
    }

    /**
     * 替换空格
     * 从前往后就把后面的覆盖了
     */
    public String replaceSpace(String str) {
        StringBuffer res = new StringBuffer();
        int len = str.length() - 1;
        for (int i = len; i >= 0; i--) {
            if (str.charAt(i) == ' ')
                res.append("02%");
            else
                res.append(str.charAt(i));
        }
        System.out.println(res.reverse().toString());
        return res.reverse().toString();
    }

    /*
     * 用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型。
     * */
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



}

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

    //输入n,求斐波那契数列的第n项

    /**
     * 递归
     */
    public int fibonacci(int n) {
        if (n < 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);

    }

    /**
     * 递归效率低，重复数据，栈溢出
     * 爬楼梯、青蛙跳就是斐波那契
     */
    public int fibonacci_2(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        int f = 0, g = 1;
        for (int i = 0; i < n; i++) {
            f = f + g;  //前一项加后一项
            g = f - g;  //已经求和过的f减去g，会得到求和前的f，赋值给g
        }
        return f;
    }

    /**
     * 数值的整数次方
     */
    public double Power(double base, int exponent) {
        int n = Math.abs(exponent);
        if (n == 0) return 1;
        if (n == 1) return base;
        //递归：若n为偶数，则a^n=a^(n/2)*a^(n/2)；若n为奇数，则a^n=(a^(n-1)/2)*(a^((n-1)/2))*a，时间复杂度为O(log(n))
        double result = Power(base, n >> 1);  //将n的二进制右移一位，意味着除以2，求得a^(n/2)或a^((n-1)/2)
        result *= result;  //将a^(n/2)或a^((n-1)/2)进行平方，变为a^n或a^(n-1)
        if ((n & 1) == 1) result *= base;  //在n的二进制右移递归后，若n为奇数，二进制最低位一定是1，这样就再乘一次a。偶数二进制的最低位为0
        if (exponent < 0) result = 1 / result;  //若指数为负数，则求倒数
        return result;
    }


    /*
     * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有的奇数位于数组的前半部分，
     * 所有的偶数位于数组的后半部分，并保证奇数和奇数，偶数和偶数之间的相对位置不变。
     *
     * */
    private static void mysort(int[] array) {
        if (array == null) {
            return;
        }
        int left = 0;
        int right = array.length - 1;
        while (left < right) {
            while (left < right && !isEven(array[left])) {
                left++;
            }
            while (left < right && isEven(array[right])) {
                right--;
            }
            if (left < right) {
                int temp = array[right];
                array[right] = array[left];
                array[left] = temp;
            }
            if (left >= right) {//如果第二个指针已到了第一个指针前面，表明所有的奇数都位于偶数的前面
                break;
            }
        }
    }

    //方便扩展
    private static boolean isEven(int i) {
        return (i & 0x1) == 0;
    }


}

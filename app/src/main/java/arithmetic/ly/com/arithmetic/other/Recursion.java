package arithmetic.ly.com.arithmetic.other;

/**
 * Created by liuyu1 on 2018/2/6.
 * 递归
 */
public class Recursion {
    /**
     * 求和
     * fn=fn+fn-1
     */
    public static int summation(int num) {
        if (num == 1) {
            return 1;
        }
        return num + summation(num - 1);
    }


    /**
     * 求二进制
     */
    public static int binary(int num) {
        StringBuilder sb = new StringBuilder();
        if (num > 0) {
            binary(num / 2);
            int i = num % 2;
            sb.append(i);
        }
        System.out.print(sb.toString());
        return -1;
    }

    /**
     * 求n的阶乘
     */
    public int f(int n) {
        if (n == 1) {
            return n;
        } else {
            return n * f(n - 1);
        }
    }


    /**
     * 欧几里德
     * 定理：两个整数的最大公约数等于其中较小的那个数和两数相除余数的最大公约数
     * (m>n)m和n的最大公约数 = n 和m%n的最大公约数
     * 36 24  12 = 24和12 = 12和 0
     * int x = gcd.gcd(99,55);
     *
     * @param m
     * @param n
     * @return
     */
    public int gcd(int m, int n) {
        if (n == 0) {
            return m;
        } else {
            return gcd(n, m % n);
        }
    }

    public static void main(String[] args) {
        Recursion recursion = new Recursion();
        int i = recursion.binary(11);
    }

}

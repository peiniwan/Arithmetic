package arithmetic.ly.com.arithmetic.other;

/**
 * Created by liuyu1 on 2018/2/6.
 * 递归
 */
public class Recursion {
    /**
     * 求和
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
            summation(num / 2);
            int i = num % 2;
            sb.append(i);
        }
        System.out.println(sb.toString());
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
}

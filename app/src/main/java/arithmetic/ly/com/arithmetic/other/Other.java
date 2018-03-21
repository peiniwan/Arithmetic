package arithmetic.ly.com.arithmetic.other;

/**
 * Created by liuyu1 on 2018/3/19.
 */

public class Other {


    public static void main(String[] args) {
        int a = 10;
        int b = 5;
        //怎么在不引入其他变量的情况下,让a和b互换？
        a = a + b;
        b = a - b;
        a = a - b;
        System.out.println("b=" + b);
        System.out.println("a=" + a);

    }
}

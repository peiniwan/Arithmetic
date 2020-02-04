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
     */
    public int gcd(int m, int n) {
        if (n == 0) {
            return m;
        } else {
            return gcd(n, m % n);
        }
    }


    /**
     * 递归实现汉诺塔
     * 从一个柱子挪到另一个柱子，必须从小到大
     */
    private int i = 1;

    public void hanNota(int n, char from, char dependOn, char to) {
        if (n == 1) {
            move(1, from, to);
        } else {
            hanNota(n - 1, from, to, dependOn);//第一步，先将n-1个盘子从A利用C挪到B
            move(n, from, to);//将n这个盘子（底盘）从A挪到C
            hanNota(n - 1, dependOn, from, to);//讲n-1个盘子从B利用A挪到C
        }
    }


    private void move(int n, char from, char to) {
        System.out.println("第" + i++ + "步从" + from + "------>" + to);
    }


    /**
     * 穷举
     * 倒酒
     */
    private int b1 = 12;
    private int b2 = 8;
    private int b3 = 5;
    private int m = 6;//目标酒量

    //假设一开始12,0,0
    private void backBottle(int bb1, int bb2, int bb3) {
        System.out.println("bb1:" + bb1 + "~~bb2:" + bb2 + "~~bb3:" + bb3);
        if (bb1 == m || bb2 == m || bb3 == m) {
            System.out.println("find the bottle");
            return;
        }
        if (bb2 != 0 && bb3 != b3) {
            if (bb2 + bb3 <= b3) {
                //倒不满
                backBottle(bb1, 0, bb2 + bb3);
            } else {
                backBottle(bb1, bb2 - (b3 - bb3), b3);
            }
        } else if (bb3 == b3) {
            //瓶子3满了，往瓶子1倒
            if (bb3 + bb1 <= b1) {
                //说明倒完后瓶子1没满
                backBottle(bb1 + bb3, bb2, 0);
            } else {
                backBottle(b1, bb2, bb3 - (b1 - bb1));
            }
        } else if (bb2 == 0) {
            //从瓶子1往瓶子2里倒酒
            if (bb1 >= b2) {
                backBottle(bb1 - b2, b2, bb3);
            } else {
                backBottle(0, bb1, bb3);
            }
        }

    }

    /**
     * 鸡翁一值钱5,鸡母一值钱3,鸡雏三值钱1。百钱买百鸡,问鸡翁、母、雏各几何?
     */
    int Cock, Hen, Chick; /* 定义公鸡，母鸡，鸡雏三个变量 */

    public void calculateCock() {
        int Cock = 0;
        /* 公鸡最多不可能大于19 */
        while (Cock <= 19) {
            Hen = 0;
            while (Hen <= 33) /* 母鸡最多不可能大于33 */ {
                Chick = 100 - Cock - Hen;
                /* 将数量放大三倍比较  这里随着倍数放大答案也会变多*/
                if (Cock * 15 + Hen * 9 + Chick == 300) {
                    System.out.println("\n公鸡=" + Cock + "母鸡=" + Hen + "雏鸡=" + Chick);
                }
                Hen = Hen + 1;
            }
            Cock = Cock + 1;
        }
    }


    public static void main(String[] args) {
        Recursion recursion = new Recursion();
        int i = recursion.binary(11);
        recursion.hanNota(3, 'A', 'B', 'C');
        recursion.backBottle(12, 0, 0);
        recursion.calculateCock();
    }

}

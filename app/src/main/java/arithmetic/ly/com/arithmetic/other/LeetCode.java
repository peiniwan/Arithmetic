package arithmetic.ly.com.arithmetic.other;


import java.util.HashMap;

public class LeetCode {

    public static void main(String[] args) {
        LeetCode leetCode = new LeetCode();
        ArrayTest arrayTest = new ArrayTest();
        arrayTest.twoSum2(new int[]{2, 7, 11, 15},9);
    }

    //============================动态规划============================

    /**
     * 爬楼梯
     * 动态规划算法通常基于一个递推公式及一个或多个初始状态。
     * 当前子问题的解将由上一次子问题的解推出。
     * https://segmentfault.com/a/1190000015944750   这个文章好
     * f(n) = f(n-1) + f(n-2);
     * 还有两个初始状态：
     * f(1) = 1;
     * f(2) = 2;
     * 其实就是二叉树
     * 时间复杂度为O(2^n)
     */
    public int getWays(int n) {

        if (n < 1) return 0;
        if (n == 1) return 1;
        if (n == 2) return 2;

        return getWays(n - 1) + getWays(n - 2);
    }


    /**
     * 上面重复计算了,备忘录
     * 空间复杂度为O(n),时间复杂度也为O(n)
     */
    HashMap map = new HashMap();

    public int getWays2(int n) {

        if (n < 1) return 0;
        if (n == 1) return 1;
        if (n == 2) return 2;

        if (map.containsKey(n)) {
            return (int) map.get(n);
        }
        int value = getWays2(n - 1) + getWays2(n - 2);
        map.put(n, value);
        return value;
    }


    /**
     * 时间复杂度仍为O(n)，但空间复杂度降为O(1)
     */
    public int getWays3(int n) {

        if (n < 1) return 0;
        if (n == 1) return 1;
        if (n == 2) return 2;

        // a保存倒数第二个子状态数据，b保存倒数第一个子状态数据， temp 保存当前状态的数据
        int a = 1, b = 2;
        int temp = a + b;
        for (int i = 3; i <= n; i++) {
            temp = a + b;
            a = b;
            b = temp;
        }
        return temp;
    }


    /**
     * 输入n,求斐波那契数列的第n项
     * 递归
     * 1、1、2、3、5、8、13、21
     */
    public int fibonacci(int n) {
        if (n == 0) {
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
//        int f = 0, g = 1;
//        for (int i = 0; i < n; i++) {
//            f = f + g;  //前一项加后一项
//            g = f - g;  //已经求和过的f减去g，会得到求和前的f，赋值给g
//        }
        int a = 0, b = 1;
        int temp = a + b;
        for (int i = 2; i <= n; i++) {
            temp = a + b;
            a = b;
            b = temp;
        }
        return temp;
    }


    /**
     * 题目：一个人迈步上台阶，一次可以迈1阶、2阶、3阶，这样，到1阶的走法有1中，
     * 到2阶的走法有2中，到3阶的走法有4种，那么到第n级的走法有多少种？
     * 【分析】走到n阶前，可能位于n-1阶，再迈1步1阶即可；可能为n-2阶，再迈1步2阶即可；可能位于n-3阶，
     * 再迈1步3阶即可。递归公式：f(n)= f(n-1)+ f(n-1)+ f(n-2)。
     */
    public static int stepCounter(int n) {
        if (n <= 0) throw new IllegalArgumentException("参数错误，n必须大于0！");
        if (n == 1) return 1;
        if (n == 2) return 2;
        if (n == 3) return 4;
        return stepCounter(n - 1) + stepCounter(n - 2) + stepCounter(n - 3);
    }


    public void onther() {
        int a = 10;
        int b = 5;
        //怎么在不引入其他变量的情况下,让a和b互换？
        a = a + b;
        b = a - b;
        a = a - b;
        System.out.println("b=" + b);
        System.out.println("a=" + a);

    }


    //============================回溯（穷举）============================

    /**
     * 回溯算法：8皇后
     */
    public int[] result = new int[8];//全局或成员变量,下标表示行,值表示queen存储在哪一列

    public void cal8queens(int row) { // 调用方式：cal8queens(0);
        if (row == 8) { // 8个棋子都放置好了，打印结果
            printQueens(result);
            return; // 8行棋子都放好了，已经没法再往下递归了，所以就return
        }
        for (int column = 0; column < 8; ++column) { // 每一行都有8中放法
            if (isOk(row, column)) { // 有些放法不满足要求
                result[row] = column; // 第row行的棋子放到了column列
                cal8queens(row + 1); // 考察下一行
            }
        }
    }

    private boolean isOk(int row, int column) {//判断row行column列放置是否合适
        int leftup = column - 1, rightup = column + 1;
        for (int i = row - 1; i >= 0; --i) { // 逐行往上考察每一行
            if (result[i] == column) return false; // 第i行的column列有棋子吗？
            if (leftup >= 0) { // 考察左上对角线：第i行leftup列有棋子吗？
                if (result[i] == leftup) return false;
            }
            if (rightup < 8) { // 考察右上对角线：第i行rightup列有棋子吗？
                if (result[i] == rightup) return false;
            }
            --leftup;
            ++rightup;
        }
        return true;
    }

    private void printQueens(int[] result) { // 打印出一个二维矩阵
        for (int row = 0; row < 8; ++row) {
            for (int column = 0; column < 8; ++column) {
                if (result[row] == column) System.out.print("Q ");
                else System.out.print("* ");
            }
            System.out.println();
        }
        System.out.println();
    }


    public int maxW = Integer.MIN_VALUE; //存储背包中物品总重量的最大值

    /**
     * 假设背包可承受重量100，物品个数10，物品重量存储在数组a中，那可以这样调用函数：
     * f(0, 0, a, 10, 100)
     *
     * @param i     表示考察到哪个物品了
     * @param cw    表示当前已经装进去的物品的重量和
     * @param items 表示每个物品的重量
     * @param n     表示物品个数
     * @param w     背包重量
     */
    public void f(int i, int cw, int[] items, int n, int w) {
        if (cw == w || i == n) { // cw==w表示装满了;i==n表示已经考察完所有的物品
            if (cw > maxW) maxW = cw;
            return;
        }
        //当前物品不装进背包，第 11 行的递归调用表示不选择当前物品，直接考虑下一个（第 i+1 个），故 cw 不更新
        f(i + 1, cw, items, n, w);
        if (cw + items[i] <= w) {// 已经超过可以背包承受的重量的时候，就不要再装了
            f(i + 1, cw + items[i], items, n, w);//当前物品装进背包
        }
    }


    /**
     * 动态规划优化背包
     * （i, cw）来表示。其中，i 表示将要决策第几个物品是否装入背包，cw 表示当前背包中物品的总重量
     */
    private int[] weight = {2, 2, 4, 6, 3};  // 物品重量
    private int n = 5; // 物品个数
    private int w = 9; // 背包承受的最大重量
    private boolean[][] mem = new boolean[5][10]; // 备忘录，默认值false

    public void f(int i, int cw) { // 调用f(0, 0)
        if (cw == w || i == n) { // cw==w表示装满了，i==n表示物品都考察完了
            if (cw > maxW) maxW = cw;
            return;
        }
        if (mem[i][cw]) return; // 重复状态
        mem[i][cw] = true; // 记录(i, cw)这个状态
        f(i + 1, cw); // 选择不装第i个物品
        if (cw + weight[i] <= w) {
            f(i + 1, cw + weight[i]); // 选择装第i个物品
        }
    }


    /**
     * 如何把 n 个数据的所有排列都找出来
     * int[]a = a={1, 2, 3, 4}; printPermutations(a, 4, 4);
     * n 个“n−1 个数据的排列”
     *
     * @param k 表示要处理的子数组的数据个数
     */
    public void printPermutations(int[] data, int n, int k) {
        if (k == 1) {
            for (int i = 0; i < n; ++i) {
                System.out.print(data[i] + " ");
            }
            System.out.println();
        }

        for (int i = 0; i < k; ++i) {
            int tmp = data[i];
            data[i] = data[k - 1];
            data[k - 1] = tmp;

            printPermutations(data, n, k - 1);

            tmp = data[i];
            data[i] = data[k - 1];
            data[k - 1] = tmp;
        }
    }


}


package arithmetic.ly.com.arithmetic.other;


import android.graphics.Paint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class LeetCode {

    public static void main(String[] args) {
        LeetCode leetCode = new LeetCode();
//        leetCode.twoSum(new int[]{2, 11, 7, 15}, 9);
//        leetCode.printPermutations(new int[]{1, 2, 3, 4}, 4, 4);
//        leetCode.removeDuplicates(new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4});
//        leetCode.rotate(new int[]{1, 2, 3, 4, 5, 6, 7}, 3);//5 6 7 1 2 3 4
//        leetCode.rotate2(new int[]{1, 2, 3, 4, 5, 6, 7}, 3);//5 6 7 1 2 3 4

//        leetCode.singleNumber(new int[]{2, 2, 1});
//        leetCode.moveZeroes(new int[]{0, 1, 0, 3, 12});
//        leetCode.reverseInt(123);
//        leetCode.firstUniqChar2("loveleetcode");
//        leetCode.isPalindrome("A man, a plan, a canal: Panama");
//        leetCode.strStr("hello", "ll");
//        leetCode.longestCommonPrefix(new String[]{"leets", "leetcode", "leet", "leets"});
//        leetCode.climbStairs(5);
//        leetCode.climbStairs2(5);
//        leetCode.getRandomArray(new String[]{"a","b","c","d","e","f","g","h","t"},5);
//        leetCode.getRandomArray2(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 0}, 5);


//        int kthLargest = leetCode.findKthLargest(new int[]{4, 2, 5, 12, 3}, 3);
//        leetCode.findNumsAppearOnce(new int[]{4, 4, 5, 5, 2, 3}, new int[1], new int[1]);
//
//        int fibonacci = leetCode.fibonacci_2(8);
//        System.out.println("fibonacci"+fibonacci);

//        String a = "abbbbaaaccssdd";
//        String b = "acc";
//        System.out.println(bfFind(a, b, 3));

////        leetCode.cal8queens(0);
//        int[] a = new int[]{4, 1, 2};
//        leetCode.f(0, 0, a, 3, 9);
//        System.out.println("args = [" + leetCode.maxW + "]");

//        LinkedList<Integer> intersect = leetCode.intersect(new int[]{1, 2, 8, 9}, new int[]{1, 4, 6, 7});
//        for (Integer integer : intersect) {
//            System.out.println("args = [" + integer + "]");
//        }


        int a[][] = new int[2][2];
        int b[][] = {{1, 2}, {3, 4}};
        leetCode.rotate(b);

    }

    //============================数组============================\

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
     * BF算法（交集）
     * 检查起始位置分别是 0、1、2…n-m 且长度为 m 的 n-m+1 个子串，看有没有跟模式串匹配的
     * indexOf(xxx)方法直接就返回了
     */
    public static int bfFind(String S, String T, int pos) {
        char[] arr1 = S.toCharArray();
        char[] arr2 = T.toCharArray();
        int i = 0, j = 0;
        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] == arr2[j]) {
                i++;
                j++;
            } else {
                i = i - j + 1;
                j = 0;
            }
        }
        if (j == arr2.length) return i - j;
        else return -1;
    }


    /**
     * 奇数在前，偶数在后
     * 先计算出奇数的个数count，然后用双指针来遍历，一个从头遍历到count，一个从数组尾部遍历到count。
     * 从前向后找到一个偶数的下标，从后向前找到一个奇数的下标，然后交换对应的值。直到遍历完整个数组。
     * 时间复杂度为O（n），空间复杂度为O（1）。
     * https://blog.csdn.net/ucsasuke/article/details/96192857
     */
    private static void fun(int[] arr) {
        int front = 0, end = arr.length - 1;//设置两个指针，一个指向头部，一个指向尾部
        if (arr.length == 0) {//数组长度为0则返回
            return;
        }

        while (front < end) {
            while (front < arr.length && arr[front] % 2 == 1) {//从前往后找偶数
                front++;
            }
            while (end >= 0 && arr[end] % 2 == 0) {//从后往前找奇数
                end--;
            }
            if (front < end) {//将前面的偶数与后面奇数交换位置
                int temp = arr[front];
                arr[front] = arr[end];
                arr[end] = temp;
            }
        }
    }


    /**
     * 求数组的俩数之和等于第三个数
     */
    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] + nums[i] == target) {
                    int[] xx = new int[]{i, j};
                    System.out.println(xx[0] + "----" + xx[1]);
                    return xx;
                }
            }
        }
        return null;
    }


    /**
     * 删除排序数组中的重复项
     * 双指针法：其中 i 是慢指针，而 j 是快指针。
     */

    public void removeDuplicates(int[] nums) {
        if (nums.length == 0) return;
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[i]) {
                i++;
                nums[i] = nums[j];
            }
        }
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }


    /**
     * 判断是否有重复
     */
    public boolean containsDuplicate(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                return true;
            }
        }
        return false;
    }

    public boolean containsDuplicate2(int[] nums) {
        Set save = new HashSet();
        for (int a : nums) {
            if (!save.add(a)) return true;
        }
        return false;
    }


    /**
     * 只出现一次的数字
     * 除了某个元素只出现一次以外，其余每个元素均出现两次
     */
    public int singleNumber(int[] nums) {
        Arrays.sort(nums);  // 排序数组
        for (int i = 0; i < nums.length - 1; i += 2) {
            // 找到不相等的一组，直接返回
            if (nums[i] != nums[i + 1]) {
                return nums[i];
            }
        }
        return nums[nums.length - 1];

    }

    //去重法：最后剩下一个单独的元素，返回即可
    public static int singleNumber2(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (!set.add(nums[i])) { // add成功返回true，如果set中已有相同数字，则add方法会返回false
                set.remove(nums[i]); // 删除重复出现的数字
            }
        }
        return set.iterator().next();
    }


    /**
     * 40.一个整型数组里除了两个数字之外，其他的数字都出现了偶数次。请写程序找出这两个只出现一次的数字。
     * num1,num2分别为长度为1的数组。传出参数
     * 将num1[0],num2[0]设置为返回结果
     * 和下面数组类似
     * 使用foreach比较简单
     */
    public void findNumsAppearOnce(int[] array, int num1[], int num2[]) {
        HashMap<Integer, Integer> hashMap = new HashMap<Integer, Integer>();
        for (int i : array) {
            int val = 1;
            if (hashMap.get(i) != null) {
                hashMap.put(i, val + 1);
            } else {
                hashMap.put(i, val);
            }
        }
        for (int i : array) {
            if (hashMap.get(i) == 1) {
                if (num1[0] == 0) num1[0] = i;
                else num2[0] = i;
            }
        }
    }


    /**
     * 俩个数组交集
     * 算法复杂度为O(N + M)
     */
    public LinkedList<Integer> intersect(int[] A, int[] B) {
        if (A == null || B == null || A.length == 0 || B.length == 0) return null;
        LinkedList<Integer> list = new LinkedList<>();
        int pointerA = 0;
        int pointerB = 0;
        while (pointerA < A.length && pointerB < B.length) {
            if (A[pointerA] < B[pointerB]) pointerA++;
            else if (A[pointerA] > B[pointerB]) pointerB++;
            else {
                list.add(A[pointerA]);
                pointerA++;
                pointerB++;
            }
        }
        return list;
    }


    public int[] intersect2(int[] nums1, int[] nums2) {
        List<Integer> list1 = new ArrayList<>();
        for (int num : nums1) {
            list1.add(num);
        }
        List<Integer> list2 = new ArrayList<>();
        for (int num : nums2) {
            if (list1.contains(num)) {
                list2.add(num);
                // 从 list1 除去已匹配的数值
                list1.remove(Integer.valueOf(num));
            }
        }
        int[] res = new int[list2.size()];
//        for (int i = 0; i < list2.size(); i++) {
//            res[i] = list2.get(i);
//        }
        int i = 0;
        for (int num : list2) {
            res[i++] = num;
        }
        return res;
    }


    /**
     * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
     * [0,1,0,3,12]
     * 快指针，慢指针，最后我们再遍历剩下的元素，并给他们赋值为0即可。
     */
    public void moveZeroes(int[] nums) {
        int fast = 0, slow = 0;
        int n = nums.length;

        while (fast < n) {
            if (nums[fast] != 0) {
                nums[slow] = nums[fast];
                ++slow;
            }
            ++fast;
        }
        for (int i = slow; i < n; i++)
            nums[i] = 0;
    }

    /**
     * 整数反转
     */
    public int reverseInt(int x) {
        if (x >= Integer.MAX_VALUE || x <= Integer.MIN_VALUE) {
            return 0;
        }
        int r = 0;//存反转的数字
        while (x != 0) {
            int n = x % 10;//取出最低位上的数字
            r = r * 10 + n;//依次的反转存储得到反转的数字
            x = x / 10;//降位 123/10=12
            if (r > Integer.MAX_VALUE / 10 && x > 0 || r == Integer.MAX_VALUE / 10 && x > 7 || r < Integer.MIN_VALUE / 10 && x < 0 || r == Integer.MIN_VALUE / 10 && x < -8) {
                return 0;
            }
        }
        return r;
    }

    /**
     * 数组倒序
     */
    public static int[] rerves(int[] array) {
        for (int i = 0; i < array.length / 2; i++) {
            int tmp = array[i];
            array[i] = array[array.length - 1 - i];
            array[array.length - 1 - i] = tmp;
        }
        return array;
    }


    //============================字符串============================

    /**
     * 用递归实现字符串反转
     */
    public static String reverseString(String originStr) {
        if (originStr == null || originStr.length() <= 1)
            return originStr;
        return reverseString(originStr.substring(1)) + originStr.charAt(0);
    }


    private static String reverseString2(String str) {
        char[] chs = str.toCharArray();
        reverseArray(chs);
        return new String(chs);
    }


    private static void reverseArray(char[] chs) {
        char temp;
        for (int start = 0, end = chs.length - 1; start < end; start++, end--) {
            temp = chs[start];
            chs[start] = chs[end];
            chs[end] = temp;
        }
    }

    /**
     * 42.输入一个英文句子，翻转句子中单词的顺序，但单词内字符的顺序不变。为简单起见，标点符号和普通字母一样处理。
     * 例如输入字符串"I am student."，则输出"student. a am I"
     */
    private static void reverseSentence(String str) {
        if (str == null)
            return;
        char[] arr = str.toCharArray();

        reverse(arr, 0, arr.length - 1);
        int start = 0;
        int end = 0;
        for (char i = 0; i < arr.length; i++) {
            if (arr[i] == ' ') {
                reverse(arr, start, end);
                end++;
                start = end;
            } else if (i == arr.length) {
                end++;
                reverse(arr, start, end);
            } else {
                end++;
            }
        }

        for (char c : arr) {
            System.out.print(c);
        }
    }

    private static void reverse(char[] arr, int start, int end) {
        for (int i = start, j = end; i <= j; i++, j--) {
            char temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }


    /**
     * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
     * s = "leetcode"
     * 返回 0.
     * s = "loveleetcode",
     * 返回 2.
     */
    public int firstUniqChar(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            int value;
            if (map.get(c) == null) {
                value = 1;
            } else {
                value = map.get(c) + 1;
            }
            map.put(c, value);
        }
        for (int i = 0; i < s.length(); i++) {
            if (map.get(s.charAt(i)) == 1) {
                System.out.println(i);
                return i;
            }
        }
        return -1;
    }


    /**
     * 验证回文字符串
     */
    public boolean isPalindrome(String s) {
        int size = s.length(), i = 0, j = size - 1;
        s = s.toLowerCase();

        while (i < j) {//非数字/字符则跳过
            if (!(s.charAt(i) >= 'a' && s.charAt(i) <= 'z') && !(s.charAt(i) >= '0' && s.charAt(i) <= '9')) {
                i++;
            } else if (!(s.charAt(j) >= 'a' && s.charAt(j) <= 'z') && !(s.charAt(j) >= '0' && s.charAt(j) <= '9')) {
                j--;
            } else {
                if (s.charAt(i) != s.charAt(j)) return false;
                i++;
                j--;
            }
        }
        return true;
    }

    /**
     * 12.数值的整数次方
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


    public double Power2(double base, int exponent) {
        double result = 1.000;
        int ex = Math.abs(exponent);
        boolean flag = false;
        if (exponent < 0) {
            flag = true;
        }
        while (ex > 0) {
            result *= base;
            ex--;
        }
        if (flag) {
            result = 1 / result;
        }
        return result;
    }

    /**
     * 从数组和List中随机抽取若干不重复的元素.
     * 这里的重复是指索引位置重复
     * 从长度为m的int数组中随机取出n个元素，每次取的元素都是之前未取过的，
     * 改成int【】就行
     */
    public String[] getRandomArray(String[] paramArray, int count) {
        if (paramArray.length < count) {
            return paramArray;
        }
        String[] newArray = new String[count];
        Random random = new Random();
        int temp = 0;//接收产生的随机数
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 1; i <= count; i++) {
            temp = random.nextInt(paramArray.length);//将产生的随机数作为被抽数组的索引
            if (!(list.contains(temp))) {
                newArray[i - 1] = paramArray[temp];
                list.add(temp);
            } else {
                i--;//重新设置
            }
        }
        for (String s : newArray) {
            System.out.println("String:" + s);
        }
        return newArray;
    }

    /**
     * 48. 旋转图像
     * nXn整型二维数组顺时针旋转90度
     * 最直接的想法是先转置矩阵，然后翻转每一行。
     * 时间复杂度O(N^2)。空间复杂度：O(1) 由于旋转操作是 就地 完成的
     * 1,2
     * 3,4
     * 1,3
     * 2,4
     */
    public void rotate(int[][] matrix) {
        int n = matrix.length;

        // transpose matrix
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int tmp = matrix[j][i];
                matrix[j][i] = matrix[i][j];
                matrix[i][j] = tmp;
            }
        }
        // reverse each row
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[i][n - j - 1];
                matrix[i][n - j - 1] = tmp;
            }
        }
        for (int[] ints : matrix) {
            for (int anInt : ints) {
                System.out.println("anInt:" + anInt);
            }
        }

    }

    /**
     * 旋转数组(看规律)  O(n)
     * 输入: [-1,-100,3,99] 和 k = 2
     * 输出: [3,99,-1,-100]
     * 解释:
     * 向右旋转 1 步: [99,-1,-100,3]
     * 向右旋转 2 步: [3,99,-1,-100]
     * 翻转 O(1)
     */
    public void rotate(int[] nums, int k) {
        //-100 -1  99 3
        // 旋转即是元素顺序轮转的意思
        if (nums.length < 2 || k < 1 || k % nums.length == 0) {
            return;
        }
        // 处理 k 大于 数组长度的情况
        if (k > nums.length) {
            k = k % nums.length;
        }
        // 对前 n - k(7-3) 个元素 [1,2,3,4] 进行逆转后得到 [4,3,2,1]
        reverse(nums, 0, nums.length - 1 - k);
        // 对后k个元素 [5,6,7] 进行逆转后得到 [7,6,5]
        reverse(nums, nums.length - k, nums.length - 1);
        // 将前后元素 [4,3,2,1,7,6,5] 逆转得到：[5,6,7,1,2,3,4]
        reverse(nums, 0, nums.length - 1);
    }

    // 逆转数组指定区间内的元素，比如 [1,2,3,4] 逆转后变成  [4,3,2,1]
    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start++] = nums[end];
            nums[end--] = temp;
        }
    }


    public void rotate2(int[] nums, int k) {
        if (nums.length < 2 || k < 1 || k % nums.length == 0) {
            return;
        }
        // O(n) 的空间复杂度
        int[] result = new int[nums.length];

        // 用另外一个数组记录元素旋转后的位置
        for (int i = 0; i < nums.length; i++) {
            int fIndex = (i + k) % nums.length;//k后面就是原来的顺序
            result[fIndex] = nums[i];
        }
        // 赋值给原数组
        for (int i = 0; i < nums.length; i++) {
            nums[i] = result[i];
        }

        for (int num : nums) {
            System.out.print(num + " ");
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
     * <p>
     * 时间复杂度为O(2^n)
     */
    public int getWays(int n) {

        if (n < 1) return 0;
        if (n == 1) return 1;
        if (n == 2) return 2;

        return getWays(n - 1) + getWays(n - 2);
    }


    /**
     * 上面重复计算了
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


}


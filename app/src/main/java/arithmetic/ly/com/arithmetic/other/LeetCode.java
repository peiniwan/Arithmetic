package arithmetic.ly.com.arithmetic.other;


import android.annotation.TargetApi;
import android.os.Build;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class LeetCode {

    public static void main(String[] args) {
        LeetCode leetCode = new LeetCode();
//        leetCode.twoSum(new int[]{2, 11, 7, 15}, 9);
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


        int kthLargest = leetCode.findKthLargest(new int[]{4, 2, 5, 12, 3}, 3);

    }

    //============================数组============================


    /**
     * 求数组的俩数之和等于第三个数
     */
    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] == target - nums[i]) {
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

    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[i]) {
                i++;
                nums[i] = nums[j];
            }
        }

        System.out.println("nums = [" + (i + 1) + "]");
        for (int num : nums) {
            System.out.print(num + " ");
        }
        return i + 1;
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
     */
    public void findNumsAppearOnce(int[] array, int num1[], int num2[]) {
        HashMap<Integer, Integer> hashMap = new HashMap<Integer, Integer>();
        for (int i = 0; i < array.length; i++) {
            if (hashMap.containsKey(array[i])) {
                int count = hashMap.get(array[i]);
                hashMap.put(array[i], ++count);
            } else {
                hashMap.put(array[i], 1);
            }
        }
        for (int i = 0; i < array.length; i++) {
            if (hashMap.get(array[i]) == 1) {
                if (num1[0] == 0) num1[0] = array[i];
                else num2[0] = array[i];
            }
        }
    }


    /**
     * 俩个数组交集
     */
    public int[] intersect1(int[] nums1, int[] nums2) {
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
        int i = 0;
        for (int num : list2) {
            res[i++] = num;
        }
        return res;
    }


    public int[] intersect2(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        boolean[] bl = new boolean[len2];
        ArrayList<Integer> al = new ArrayList<Integer>();
        for (int i = 0; i < len1; i++) {
            for (int j = 0; j < len2; j++) {
                if (nums1[i] == nums2[j] && bl[j] == false) {
                    al.add(nums1[i]);
                    bl[j] = true;
                    break;
                }
            }
        }
        int[] in = new int[al.size()];
        int e = 0;
        for (int i : al)
            in[e++] = i;

        return in;
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
     * 数组中的第K个最大元素
     *
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] nums, int k) {
        int len = nums.length;
        int left = 0, right = len - 1;
        int pivot = 0;
        while (len - k != (pivot = partition(nums, left, right))) {
            //4所在的问题就是2，那就找到了
            //第k大应该在第K位，找每个数字应该在的位置，正好第0个4就是第K位，就找到了
            if (pivot < len - k) {
                left = pivot + 1;
                right = len - 1;
            } else {
                left = 0;
                right = pivot - 1;
            }
        }
        return nums[pivot];
    }

    private int partition(int[] nums, int left, int right) {
        int pivot = nums[left];
        while (left < right) {
            while (left < right && nums[right] >= pivot) right--;
            nums[left] = nums[right];
            while (left < right && nums[left] <= pivot) left++;
            nums[right] = nums[left];
        }
        nums[left] = pivot;
        return left;
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
    @TargetApi(Build.VERSION_CODES.N)
    public int firstUniqChar(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        int n = s.length();
        for (int i = 0; i < n; i++) {
            if (map.get(s.charAt(i)) == 1) {
                return i;
            }
        }
        return -1;
    }

    public int firstUniqChar2(String s) {
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
     * 实现strStr()
     * 输入: haystack = "hello", needle = "ll"
     * 输出: 2
     */
    public int strStr(String haystack, String needle) {
        int start = 0;
        // 如果剩下的字母不够needle长度就停止遍历
        while (start <= haystack.length() - needle.length()) {
            int i1 = start, i2 = 0;
            while (i2 < needle.length() && haystack.charAt(i1) == needle.charAt(i2)) {
                i1++;
                i2++;
            }
            if (i2 == needle.length()) return start;
            start++;
        }
        return -1;
    }

    public int strStr2(String haystack, String needle) {
        if (needle.equals(""))
            return 0;
        else if (!haystack.contains(needle))
            return -1;
        else {
            return haystack.indexOf(needle);
        }
    }

    /**
     * 最长公共前缀
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            // 找出S1与Si间的最长公共字符串
            // indexOf：当存在串时返回>0；不存串时返回-1
            // 只要不存在串，就缩减串的规模，再进行查找
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) return "";
            }
        }
        System.out.println("prefix:" + prefix);
        return prefix;
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

    public int[] getRandomArray2(int[] paramArray, int count) {
        if (paramArray.length < count) {
            return paramArray;
        }
        int[] newArray = new int[count];
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
        for (int s : newArray) {
            System.out.println("String:" + s);
        }
        return newArray;
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


}


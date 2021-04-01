package arithmetic.ly.com.arithmetic.other;

import android.os.RemoteException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;


public class StringUtils {

    public static void main(String[] args) {
        String a = "abbbbaaaccssdd";
        String b = "acc";
        System.out.println(bfFind(a, b, 3));
    }


    /**
     * 给定一个字符串数组。按照字典顺序进行从小到大的排序。
     */
    public static void sortString(String[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j].compareTo(arr[j + 1]) > 0)// 字符串比较用compareTo方法
                    swap(arr, j, j + 1);
            }
        }
    }

    private static void swap(String[] arr, int i, int j) {
        String temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 用递归实现字符串反转
     *
     * @param originStr
     * @return
     */
    public static String reverse(String originStr) {
        if (originStr == null || originStr.length() <= 1)
            return originStr;
        return reverse(originStr.substring(1)) + originStr.charAt(0);
    }


    /**
     * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
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


    // 下面都是双指针

    /**
     * 是否是字串，BF算法（交集）
     * 检查起始位置分别是 0、1、2…n-m 且长度为 m 的 n-m+1 个子串，看有没有跟模式串匹配的
     * indexOf(xxx)方法直接就返回了
     * 时间复杂度：O(n* m)
     */
    public static int bfFind(String S, String T) {
        char[] arr1 = S.toCharArray();
        char[] arr2 = T.toCharArray();
        int i = 0, j = 0;
        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] == arr2[j]) {
                i++;
                j++;
            } else {
                //++操作，为什么-j，因为相同时ij都+过，其他情况j是0
                i = i - j + 1;
                j = 0;
            }
        }
        if (j == arr2.length) return i - j;
        else return -1;
    }


    /**
     * 151. 翻转字符串里的单词
     * 输入: "  hello world!  "
     * 输出: "world! hello"//去掉多余的空格
     */
    public String reverseWords(String s) {
        if (s == null) return null;
        char[] s_arr = s.toCharArray();
        int n = s_arr.length;
        // 翻转这个数组
        reverse(s_arr, 0, n - 1);
        // 翻转每个单词
        word_reverse(s_arr, n);
        // 去除多余空格
        String s1 = clean_space(s_arr, n);
        System.out.println(s1);
        return s1;
    }


    private void word_reverse(char[] s_arr, int n) {
        int i = 0;
        int j = 0;
        while (j < n) {
            // 找到第一个首字母,画图看
            while (i < n && s_arr[i] == ' ') i++;
            j = i;
            // 末位置
            while (j < n && s_arr[j] != ' ') j++;
            reverse(s_arr, i, j - 1);
            i = j;//继续找
        }
    }

    /**
     * 替换空格
     */
    private String clean_space(char[] s_arr, int n) {
        int i = 0;
        int j = 0;
        while (j < n) {
            while (j < n && s_arr[j] == ' ') j++;
            while (j < n && s_arr[j] != ' ') s_arr[i++] = s_arr[j++];
            while (j < n && s_arr[j] == ' ') j++;
            if (j < n) s_arr[i++] = ' ';//自己加了个空格
        }
        return new String(s_arr).substring(0, i);
    }

    private void reverse(char[] s_arr, int i, int j) {
        while (i < j) {
            char t = s_arr[i];
            s_arr[i++] = s_arr[j];
            s_arr[j--] = t;
        }
    }

    private static void reverse2(char[] arr, int start, int end) {
        for (int i = start, j = end; i <= j; i++, j--) {
            char temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }


    /**
     * 反转字符串中的元音字符
     * 英文字母一共26个，其中由5个元音字母和21个辅音字母组成。
     * 使用双指针，一个指针从头向尾遍历，一个指针从尾到头遍历，
     * 当两个指针都遍历到元音字符时，交换这两个元音字符。
     * 时间复杂度为 O(N)：只需要遍历所有元素一次
     * 空间复杂度 O(1)：只需要使用两个额外变量
     */
    private final static HashSet<Character> vowels = new HashSet<>(
            Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));

    public String reverseVowels(String s) {
        if (s == null) return null;
        int i = 0, j = s.length() - 1;
        char[] result = new char[s.length()];
        while (i <= j) {
            char ci = s.charAt(i);
            char cj = s.charAt(j);
            if (!vowels.contains(ci)) {
                result[i++] = ci;
            } else if (!vowels.contains(cj)) {
                result[j--] = cj;
            } else {
                result[i++] = cj;
                result[j--] = ci;
            }
        }
        return new String(result);
    }

    /**
     * 验证回文字符串
     * 每次都判断两个指针指向的字符是否相同
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
                if (s.charAt(i++) != s.charAt(j--)) return false;
//                i++;
//                j--;
            }
        }
        return true;
    }

    /**
     * 给定一个非空字符串 s，最多删除一个字符。判断是否能成为回文字符串。
     * 输入: "aba"
     * 输出: True
     * 输入: "abca"
     * 输出: True
     * 解释: 你可以删除c字符。
     */
    public boolean validPalindrome(String s) {
        for (int i = 0, j = s.length() - 1; i < j; i++, j--) {
            if (s.charAt(i) != s.charAt(j)) {
                // 子串是回文
                return isPalindrome(s, i, j - 1) || isPalindrome(s, i + 1, j);
            }
        }
        return true;
    }

    private boolean isPalindrome(String s, int i, int j) {
        while (i < j) {
            if (s.charAt(i++) != s.charAt(j--)) {
                return false;
            }
        }
        return true;
    }

    /**
     * string转int
     * 0-9   48-59
     */
    public int stringToInt(String mun) {
        char[] nums = mun.toCharArray();
        int length = nums.length;
        int result = 0;
        for (int i = 0; i < length; i++) {
            int temp = nums[i];
            if (temp > 47 && temp < 58) {
                result += (temp - 48) * Math.pow(10, length - 1 - i);
            }
        }
        return result;
    }


}

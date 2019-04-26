package arithmetic.ly.com.arithmetic.other;

import android.annotation.TargetApi;
import android.os.Build;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Created by liuyu1 on 2018/2/9.
 */

public class StringUtils {

    public static void main(String[] args) {
        /**
         * 给定一个字符串数组。按照字典顺序进行从小到大的排序。
         *
         *思路：
         * 1,对数组排序。可以用选择，冒泡都行。
         * 2,问题：以前排的是整数，比较用的比较运算符，可是现在是字符串对象。
         *   字符串对象怎么比较呢？对象中提供了用于字符串对象比较的功能。
         *
         *   将字符串变成数组。对数组反转。将数组变成字符串。
         */
        String[] arr = {"nba", "abc", "cba", "zz", "qq", "haha"};
        printArray(arr); // 原来的
        sortString(arr);
        printArray(arr); // 比较后的

        String str = "shag klh";
        String out = reverseString(str);
//        String out = reverse(str);
        System.out.println(out);

        countWordInString("ddfdffwgfddd berterg   sdfwersdf");
    }


    public static void sortString(String[] arr) {
        for (int i = 0; i < arr.length ; i++) {
            for (int j = 0; j < arr.length-i-1; j++) {
                if (arr[j].compareTo(arr[j+1]) > 0)// 字符串比较用compareTo方法
                    swap(arr, j, j+1);
            }
        }
    }

    private static void swap(String[] arr, int i, int j) {
        String temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void printArray(String[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            if (i != arr.length - 1)
                System.out.print(arr[i] + ", ");
            else
                System.out.println(arr[i] + "]");
        }
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
     * 将字符串反转
     *
     * @param str
     * @return
     */
    private static String reverseString(String str) {
        // 将字符串变成数组
        char[] chs = str.toCharArray();

        // 将数组反正，也就是收尾调换
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
     * 统计给定文件中给定字符串的出现次数
     *
     * @param filename 文件名
     * @param word     字符串
     * @return 字符串在文件中出现的次数
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static int countWordInFile(String filename, String word) {
        int counter = 0;
        try (FileReader fr = new FileReader(filename)) {
            try (BufferedReader br = new BufferedReader(fr)) {
                String line = null;
                while ((line = br.readLine()) != null) {
                    int index = -1;
                    while (line.length() >= word.length() && (index = line.indexOf(word)) >= 0) {
                        counter++;
                        line = line.substring(index + word.length());
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return counter;
    }

    /**
     * 统计给定字符串中字符出现的次数
     */
    public static int countWordInString(String s) {
        int len1 = s.length();//获取原来的字符串长度
        String[] strings = s.split("");
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < strings.length; i++) {
            if (stringBuilder.toString().contains(strings[i])) {
                continue;
            }
            stringBuilder.append(strings[i]);
            String s1 = s.replaceAll(strings[i], "");
            int len2 = s1.length();
            int lenTimes = len1 - len2;//出现的次数
            System.out.println(strings[i] + "的个数：" + lenTimes);
        }


        int count = 0;
//        for (int i = 0; i < s.length(); i++) {
//            if (s.charAt(i) == c) {
//                count++;
//            }
//        }
        return count;
    }
}

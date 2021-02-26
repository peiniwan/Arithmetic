package arithmetic.ly.com.arithmetic.other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class ArrayTest {

    /**
     * 归并两个有序数组
     * 把归并结果存到第一个数组上。
     * 需要从尾开始遍历，否则在 nums1 上归并得到的值会覆盖还未进行归并比较的值。
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int index1 = m - 1, index2 = n - 1;
        int indexMerge = m + n - 1;
        while (index1 >= 0 || index2 >= 0) {
            if (index1 < 0) {
                nums1[indexMerge--] = nums2[index2--];
            } else if (index2 < 0) {//一个短一个长，index2<0了，设置nums1的数字
                nums1[indexMerge--] = nums1[index1--];
            } else if (nums1[index1] > nums2[index2]) {
                nums1[indexMerge--] = nums1[index1--];
            } else {
                nums1[indexMerge--] = nums2[index2--];
            }
        }
    }


    /**
     * 169. 多数元素
     * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
     * 另一种方法：先排序，那么众数的下标为（n/2）
     */
    public int majorityElement(int[] nums) {
        Map<Integer, Integer> counts = countNums(nums);

        Map.Entry<Integer, Integer> majorityEntry = null;
        for (Map.Entry<Integer, Integer> Entry : counts.entrySet()) {
            if (majorityEntry == null || Entry.getValue() > majorityEntry.getValue()) {
                majorityEntry = Entry;
            }
        }

        return majorityEntry.getKey();
    }


    private Map<Integer, Integer> countNums(int[] nums) {
        Map<Integer, Integer> counts = new HashMap<Integer, Integer>();
        for (int num : nums) {
            if (!counts.containsKey(num)) {
                counts.put(num, 1);
            } else {
                counts.put(num, counts.get(num) + 1);
            }
        }
        return counts;
    }

    /**
     * 俩数之和等于第三个数
     * n^2
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
     * (有序数组)的Two Sum 双指针
     * Input: numbers={2, 7, 11, 15}, target=9
     * Output: index1=1, index2=2
     * 时间复杂度为 O(N)。只使用了两个额外变量，空间复杂度为 O(1)。
     */
    public int[] twoSum2(int[] numbers, int target) {
        if (numbers == null) return null;
        int i = 0, j = numbers.length - 1;
        while (i < j) {
            int sum = numbers[i] + numbers[j];
            if (sum == target) {
                return new int[]{i + 1, j + 1};
            } else if (sum < target) {
                i++;
            } else {
                j--;
            }
        }
        return null;
    }

    /**
     * 判断一个非负整数是否为两个整数的平方和。
     * Input: 5
     * Output: True
     * Explanation: 1 * 1 + 2 * 2 = 5
     * 和上面类似，双指针，关键是右指针的初始化，实现剪枝，从而降低时间复杂度
     * 设右指针为 x，左指针固定为 0，为了使 02 + x2 的值尽可能接近 target，
     * 我们可以将 x 取为 sqrt(target),肯定小于他的平方根，9
     * 因为最多只需要遍历一次 0~sqrt(target)，所以时间复杂度为 O(sqrt(target))。
     * 又因为只使用了两个额外的变量，因此空间复杂度为 O(1)。
     */
    public boolean judgeSquareSum(int target) {
        if (target < 0) return false;
        int i = 0, j = (int) Math.sqrt(target);
        while (i <= j) {
            int powSum = i * i + j * j;
            if (powSum == target) {
                return true;
            } else if (powSum < target) {
                i++;
            } else {
                j--;
            }
        }
        return false;
    }

    /**
     * x 的平方根
     * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
     * 输入: 8   输出: 2, 8 的平方根是 2.82842...,
     * 因为aa可能超过Integer.MAX_VALUE。所以可以比较a和b/a的大小。
     * 夹挤定理：从两边向中间以二分的方式进行夹击。
     */
    public int mySqrt(int x) {
        if (x == 1 || x == 0) {
            return x;
        }
        int start = 1;
        //不可能大于x/2，剪枝
        int end = x / 2 + 1;
        int mid = 0;
        while (start <= end) {
            mid = start + (end - start) / 2;
            //防止越界
            if (mid <= x / mid && (mid + 1) > x / (mid + 1)) {//并且mid+1的平方大于x
                return mid;
            }
            if (mid > x / mid) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return mid;
    }


    /**
     * 15. 三数之和=0
     * 首先对数组进行排序，排序后固定一个数 nums[i]，
     * 再使用左右指针指向 nums[i]后面的两端，数字分别为 nums[L] 和 nums[R]，
     * 计算三个数的和 sum 判断是否满足为 0，满足则添加进结果集
     * 如果 nums[i]大于 0，则三数之和必然无法等于 0，结束循环，因为是排过序的
     * 如果 nums[i] == nums[i−1]，则说明该数字重复，会导致结果重复，所以应该跳过
     * 当 sum == 0 时，nums[L] == nums[L+1] 则会导致结果重复，应该跳过，L++
     * 当 sum == 0 时，nums[R] == nums[R−1] 则会导致结果重复，应该跳过，R--
     * 时间复杂度：O(n^2),n 为数组长度
     * 1 -6 5 4
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList();
        int len = nums.length;
        if (nums == null || len < 3) return ans;
        Arrays.sort(nums); // 排序
        for (int i = 0; i < len; i++) {
            if (nums[i] > 0) break; // 如果当前数字大于0，则三数之和一定大于0，所以结束循环
            if (i > 0 && nums[i] == nums[i - 1]) continue; // 去重
            int L = i + 1;
            int R = len - 1;
            while (L < R) {//和后面的都比一遍
                int sum = nums[i] + nums[L] + nums[R];
                if (sum == 0) {
                    ans.add(Arrays.asList(nums[i], nums[L], nums[R]));
                    while (L < R && nums[L] == nums[L + 1]) L++; // 去重
                    while (L < R && nums[R] == nums[R - 1]) R--; // 去重
                    L++;
                    R--;
                } else if (sum < 0) L++;
                else if (sum > 0) R--;
            }
        }
        return ans;
    }


    /**
     * 奇数在前，偶数在后
     * 先计算出奇数的个数count，然后用双指针来遍历，一个从头遍历到count，一个从数组尾部遍历到count。
     * 从前向后找到一个偶数的下标，从后向前找到一个奇数的下标，然后交换对应的值。直到遍历完整个数组。
     * 时间复杂度为O（n），空间复杂度为O（1）。
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

    /*
     * 34.丑数
     * 包含的质数因子只有2、3和5的数被称作丑数（Ugly Number）。
     * 例如6、8都是丑数，但14不是，因为它包含质因子7。
     * 习惯上我们把1当做是第一个丑数。求按从小到大的顺序的第N个丑数。
     * */
    public int getUglyNumber(int num) {
        if (num <= 0) return 0;
        ArrayList<Integer> list = new ArrayList<Integer>();  //用一个列表存放丑数
        int i2 = 0, i3 = 0, i5 = 0;  //一开始丑数为1，因此后面产生的丑数为1*2，1*3，1*5，这里分别存放乘以2的最小的数、乘以3的最小的数、乘以5的最小的数的索引
        list.add(1);
        while (list.size() < num) {
            int m2 = list.get(i2) * 2;
            int m3 = list.get(i3) * 3;
            int m5 = list.get(i5) * 5;
            //比较三个丑数中的最小值，例如第一轮中1*2，1*3和1*5中2最小，则接下来比较2*2，1*3和1*5最小为3，第三轮比较2*2，3*2和1*5，所以先比较后面再比较前面
            int min = Math.min(m2, Math.min(m3, m5));
            list.add(min);  //将比较出的最小丑数放入列表
            if (min == m2)
                i2++;  //如果上一轮比较中的最小丑数来源于三个比较索引中的某一个，则对应索引加一，对应下一个索引的丑数乘2，乘3或乘5后加入下一轮比较
            if (min == m3) i3++;
            if (min == m5) i5++;
        }
        return list.get(list.size() - 1);  //返回列表中最后一个丑数即为从小到大的第N个丑数
    }

    public boolean isUgly(int num) {
        if (num == 0) {
            return false;
        }
        while (num != 1) {
            if (num % 2 == 0) {
                num /= 2;
                continue;
            }
            if (num % 3 == 0) {
                num /= 3;
                continue;
            }
            if (num % 5 == 0) {
                num /= 5;
                continue;
            }
            return false;
        }
        return true;
    }


    /**
     * 删除排序数组中的重复项
     * 双指针法：其中 i 是慢指针，而 j 是快指针。
     */
    public static void removeDuplicates(int[] nums) {
        if (nums.length == 0) return;
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[i]) {
                i++;
                nums[i] = nums[j];
            }
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
     * 两个有序数组交集
     * 算法复杂度为O(N + M)
     */
    public static LinkedList<Integer> intersect(int[] A, int[] B) {
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
        boolean flag = false;
        if (exponent < 0) {
            flag = true;
        }
        int ex = Math.abs(exponent);
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

    public static void main(String[] args) {
        removeDuplicates(new int[]{1, 1, 2, 3, 4, 5, 5, 6});
        intersect(new int[]{1, 3, 2, 5, 6, 4}, new int[]{3, 4, 5, 6, 7, 8});
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("a",123);
        hashMap.put("a",233);
        System.out.print(hashMap.get("a")+"----");

    }
}

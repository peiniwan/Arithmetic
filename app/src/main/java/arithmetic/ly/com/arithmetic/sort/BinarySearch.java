package arithmetic.ly.com.arithmetic.sort;

import android.os.Bundle;

public class BinarySearch {

    /**
     * 普通二分查找
     */
    public int binarySearch(int[] arr, int k) {
        if (arr.length == 0) {
            return -1;
        }
        if (arr[0] == k) {
            return 0;
        }
        int a = 0;
        int b = arr.length - 1;
        while (a <= b) {
            int m = a + (b - a) / 2;
            if (k < arr[m]) {
                b = m - 1;
            } else if (k > arr[m]) {
                a = m + 1;
            } else {
                return m;
            }
        }
        return -1;
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
     * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
     * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
     * 思路：如果中间的数小于最右边的数，则右半段是有序的，
     * 若中间数大于最右边数，则左半段是有序的，(有序无序相对于middle来说的)
     * 我们只要在有序的半段里用首尾两个数组来判断目标值是否在这一区域内，
     * 这样就可以确定保留哪半边
     */
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < nums[right]) {//如果中间的数小于最右边的数，则右半段是有序的
                //有序的半段里用首尾两个数组来判断目标值是否在这一区域内
                if (nums[mid] < target && target <= nums[right]) {//去middle的右边找
                    left = mid + 1;
                } else {//去middle的左边找
                    right = mid - 1;
                }
            } else {//若中间数大于最右边数，则左半段是有序的
                //有序的半段里用首尾两个数组来判断目标值是否在这一区域内
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }

            }
        }
        return -1;
    }

    /**
     * 二分查找的递归实现
     */
    public int bsearch(int[] a, int n, int val) {
        return bsearchInternally(a, 0, n - 1, val);
    }

    private int bsearchInternally(int[] a, int low, int high, int value) {
        if (low > high) return -1;

        int mid = low + ((high - low) >> 1);
        if (a[mid] == value) {
            return mid;
        } else if (a[mid] < value) {
            return bsearchInternally(a, mid + 1, high, value);
        } else {
            return bsearchInternally(a, low, mid - 1, value);
        }
    }

    /**
     * 变体一：查找第一个值等于给定值的元素
     * 如下面这样一个有序数组，其中，a[5]，a[6]，a[7] 的值都等于 8，是重复的数据。
     * 我们希望查找第一个等于 8 的数据，也就是下标是 5 的元素。
     */
    public int bsearch1(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] > value) {
                high = mid - 1;
            } else if (a[mid] < value) {
                low = mid + 1;
            } else {
                if ((mid == 0) || (a[mid - 1] != value)) return mid;
                else high = mid - 1;
            }
        }
        return -1;
    }

    /**
     * 变体二：查找最后一个值等于给定值的元素
     */
    public int bsearch2(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] > value) {
                high = mid - 1;
            } else if (a[mid] < value) {
                low = mid + 1;
            } else {
                if ((mid == n - 1) || (a[mid + 1] != value)) return mid;
                else low = mid + 1;
            }
        }
        return -1;
    }


    /**
     * 变体三：查找第一个大于等于给定值的元素
     * 在有序数组中，查找第一个大于等于给定值的元素。比如，数组中存储的这样一个序列：
     * 3，4，6，7，10。如果查找第一个大于等于 5 的元素，那就是 6。
     */
    public int bsearch3(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] >= value) {
                if ((mid == 0) || (a[mid - 1] < value)) return mid;
                else high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 变体四：查找最后一个小于等于给定值的元素
     * 比如，数组中存储了这样一组数据：3，5，6，8，9，10。
     * 最后一个小于等于 7 的元素就是 6。
     */
    public int bsearch4(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] > value) {
                high = mid - 1;
            } else {
                if ((mid == n - 1) || (a[mid + 1] > value)) return mid;
                else low = mid + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        BinarySearch bs = new BinarySearch();
//        System.out.println("Testing normal data");
//        System.out.println(
//                bs.binarySearch(new int[]{1, 2, 10, 15, 100}, 15));

        System.out.println(
                bs.search(new int[]{4, 5, 6, 7, 0, 1, 2}, 0));

    }
}

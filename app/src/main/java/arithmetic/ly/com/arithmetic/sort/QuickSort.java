package arithmetic.ly.com.arithmetic.sort;

/**
 * Created by 拯救者 on 2018/2/10.
 */

public class QuickSort {

    public static void main(String[] args) {
        QuickSort quickSort = new QuickSort();
        int[] a = {19, 2, 3, 90, 33, -7, 24, 3, 34, 5};
        quickSort.quick(a);
        for (int num : a) {
            System.out.println(" " + num);
        }
    }

    public void quick(int[] a) {
        if (a.length > 0) {
            quickSort(a, 0, a.length - 1);
        }
    }

    /**
     * 快速排序
     * @param a
     * @param low  低位
     * @param high 高位
     */
    private void quickSort(int[] a, int low, int high) {
        if (low < high) {
            int middle = getMiddle(a, low, high);
            //递归排比第一个基数小的数和大的数
            quickSort(a, 0, middle - 1);
            quickSort(a, middle + 1, high);
        }
    }

    /**
     * 获取中间下标
     *
     * @param a
     * @param low
     * @param high
     * @return
     */
    private int getMiddle(int[] a, int low, int high) {
        int temp = a[low];//基准元素
        while (low < high) {//第二次3，9
            while (low < high && a[high] >= temp) {
                high--;
            }
            a[low] = a[high];//将比基数小的数放到基数前面
            while (low < high && a[low] <= temp) {
                low++;
            }
            a[high] = a[low];//将比基数大的数放到基数后面
        }
        a[low] = temp;//插入到排序后正确的位置，low就是基数应该在的位置
        return low;
    }


}

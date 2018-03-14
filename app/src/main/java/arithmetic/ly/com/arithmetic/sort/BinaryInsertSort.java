package arithmetic.ly.com.arithmetic.sort;

/**
 * Created by liuyu1 on 2018/2/7.
 * 二分法插入排序
 * 先找到正确的位置，然后用插入排序排序
 */
public class BinaryInsertSort {
    public static void main(String[] args) {
        int[] a = {4, 8, 6, 7, 3, 5, 9, 1};
        System.out.println("排序之前：");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        //二分插入排序
        sort(a);
        System.out.println();
        System.out.println("排序之后：");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
    }


    private static void sort(int[] a) {
        // {4, 6, 8, 7, 3, 5, 9, 1}
        // {4, 6, 7, 8, 3, 5, 9, 1}
        for (int i = 1; i < a.length; i++) {
            int temp = a[i];//7
            int left = 0;
            int right = i - 1;//2
            int mid = 0;
            //确定(找到)要插入的位置
            while (left <= right) {
                //先获取中间位置
                mid = (left + right) / 2;
                if (temp < a[mid]) {
                    //如果值比中间值小，让right左移到中间下标-1，舍弃右边
                    right = mid - 1;
                } else {//7  6
                    //如果值比中间值大,让left右移到中间下标+1，舍弃左边
                    left = mid + 1;//2
                }
            }
            //移动必须从最后一个记录开始，向后移动一位，再移动倒数第2位，直到要插入的位置的记录移后一位。
            for (int j = i - 1; j >= left; j--) {
                //以左下标为标准，左及左后边全部后移，然后左位置前插入该数据
                a[j + 1] = a[j];
            }
            if (left != i) {//如果相等，不需要移动
                //左位置插入该数据
                a[left] = temp;
            }
        }
    }
}

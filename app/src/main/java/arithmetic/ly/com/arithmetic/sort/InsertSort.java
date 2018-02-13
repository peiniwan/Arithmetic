package arithmetic.ly.com.arithmetic.sort;

/**
 * Created by liuyu1 on 2018/2/7.
 * 直接插入排序
 */
public class InsertSort {

    public static void main(String[] args) {
        int[] a = {9, 8, 6, 7, 5, 1, 2, 4, 10};
        System.out.println("排序之前：");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        for (int i = 1; i < a.length; i++) {
            //待插入元素
            int temp = a[i];//8
            int j;
            for (j = i - 1; j >= 0; j--) {
                //将大于temp的往后移动一位
                if (a[j] > temp) {//9>8
                    a[j + 1] = a[j];//将8的位置的值改成9
                } else {
                    break;
                }
            }
            //插入进来。将小于temp的后一位设为temple，将9的位置改成8,a[j+1](9)=8
            a[j + 1] = temp;
        }
        System.out.println();
        System.out.println("排序之后：");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
    }
}


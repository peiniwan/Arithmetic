package arithmetic.ly.com.arithmetic.sort;

/**
 * Created by liuyu1 on 2018/2/7.
 * 直接插入排序
 */
public class InsertSort {

    public static void main(String[] args) {
        int[] a = {5,4,6,1,3,2};
        System.out.println("排序之前：");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        InsertSort insertSort = new InsertSort();
        insertSort.insert(a);
        System.out.println();
        System.out.println("排序之后：");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
    }

    public void insert(int[] array) {
        for (int i = 1; i < array.length; i++) {
            //待插入元素
            int temp = array[i];//4
            int j;
            //确定(找到)要插入的位置
            for (j = i - 1; j >= 0; j--) {
                //将大于temp的往后移动一位
                if (array[j] > temp) {// 5>4
                    array[j + 1] = array[j];//4的位置改成5
                } else {
                    break;
                }
            }
            //插入进来。
            array[j + 1] = temp;//-1+1 改成4
        }
    }
}


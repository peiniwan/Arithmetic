package arithmetic.ly.com.arithmetic.sort;

/**
 * Created by liuyu1 on 2018/2/7.
 * 简单选择排序
 */
public class SelertSort {

    public void selectSort(int[] array) {
        int min;
        int tmp;
        for (int i = 0; i < array.length; i++) {
            min = array[i];
            //里面for第一次出来0，并且排在最前面，然后从i=1开始遍历
            for (int j = i; j < array.length; j++) {
                if (array[j] < min) {
                    min = array[j];//记录最小值  3
                    tmp = array[i];//9
                    array[i] = min;//3
                    array[j] = tmp;//9
                }
            }
        }
        for (int num : array) {
            System.out.println(num);
        }
    }



    public static void main(String[] args) {
        SelertSort selertSort = new SelertSort();
        selertSort.selectSort(new int[]{9, 4, 2, 6, 7, 3, 10, 33, 88, 1, 17});
    }
}
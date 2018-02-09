package arithmetic.ly.com.arithmetic.sort;

/**
 * Created by liuyu1 on 2018/2/7.
 */

public class SortTest {

    public void selertSort(int[] arr) {
        int min;
        int temp;
        for (int i = 0; i < arr.length; i++) {
            min = arr[i];
            for (int j = i; j < arr.length; j++) {
                if (arr[j] < min) {
                    min = arr[j];
                    temp = arr[i];
                    arr[i] = min;
                    arr[j] = temp;
                }
            }
        }
    }

    public void insertSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int temp = arr[i];
            int j;
            for (j = i - 1; j >= 0; j--) {
                if (arr[j] > temp) {
                    arr[j + 1] = arr[j];
                } else {
                    break;
                }
            }
            arr[j + 1] = temp;
        }
    }

    public void binaryInsertSort(int[] arr) {

    }

    public void printArr(int[] arr) {
        for (int num : arr) {
            System.out.print(" " + num);
        }
    }


    public static void main(String[] args) {
        SortTest sortTest = new SortTest();
        int[] arr = {9, 4, 2, 6, 7, 3, 10, 33, 88, 1, 17};
//        sortTest.selertSort(arr);
        sortTest.insertSort(arr);
        sortTest.printArr(arr);
    }
}

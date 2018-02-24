package arithmetic.ly.com.arithmetic.sort;

/**
 * Created by liuyu1 on 2018/2/7.
 */

public class SortTest {

    public void insertSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int temp = array[i];
            int j;
            for (j = i - 1; j >= 0; j--) {
                if (array[j] > temp) {
                    array[j + 1] = array[j];
                } else {
                    break;
                }
            }
            array[j + 1] = temp;
        }
    }

    public void bubbleSort(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = i; i < a.length - i - 1; i++) {
                if (a[j] > a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }
    }


    public void selectSor(int[] a) {
        int temp;
        int min;
        for (int i = 0; i < a.length; i++) {
            min = a[i];
            for (int j = i; j < a.length; j++) {
                if (a[j] < min) {
                    min = a[j];
                    temp = a[i];
                    a[i] = min;
                    a[j] = temp;
                }
            }
        }
    }


    public void quickSort(int[] a) {
        if (a.length > 0) {
            quick(a, 0, a.length - 1);
        }
    }

    private void quick(int[] a, int low, int high) {
        if (low < high) {
            int middle = getMiddle(a, low, high);
            quick(a, 0, middle - 1);
            quick(a, middle + 1, high);
        }
    }

    private int getMiddle(int[] a, int low, int high) {
        int temp = a[low];
        while (low < high) {
            while (low < high && a[high] >= temp) {
                high--;
            }
            a[low] = a[high];
            while (low < high && a[low] <= temp) {
                low++;
            }
            a[high] = a[low];
        }
        a[low] = temp;
        return low;
    }


    public void binaryInsertSort(int[] a) {

        for (int i = 0; i < a.length; i++) {
            int temp = a[i];
            int left = 0;
            int right = i - 1;
            int middle = 0;
            while (left <= right) {
                middle = (left + right) / 2;
                if (temp < a[middle]) {
                    right = middle - 1;
                } else {
                    left = middle + 1;
                }
            }
            for (int j = i - 1; j >= left; j--) {
                a[j + 1] = a[j];
            }
            if (left != i) {
                a[left] = temp;
            }
        }
    }

    public void heer(int[] a) {
        int d = a.length / 2;
        while (true) {
            for (int i = 0; i < d; i++) {
                for (int j = i; j + d < a.length; j += d) {
                    int temp;
                    if (a[j] > a[j + d]) {
                        temp = a[j];
                        a[j] = a[j + d];
                        a[j + d] = temp;
                    }
                }
            }
            if (d == 1) {
                break;
            }
            d--;
        }
    }


    public void heapSort(int[] a) {
        buildMaxHead(a);
        for (int i = a.length - 1; i >= 1; i--) {
            exchange(a, 0, i);
            maxHeap(a, i, 0);
        }
    }

    private void buildMaxHead(int[] a) {
        if (a == null || a.length <= 1) {
            return;
        }
        int half = (a.length - 1) / 2;
        for (int i = half; i >= 0; i--) {
            maxHeap(a, a.length, i);
        }
    }

    private void maxHeap(int[] a, int headSize, int index) {
        int left = 2 * index + 1;
        int right = 2 * index + 2;
        int largest = index;
        if (left < headSize && a[left] > a[index]) {
            largest = left;
        }
        if (right > headSize && a[right] > a[index]) {
            largest = right;
        }
        if (index != largest) {
            exchange(a, index, largest);
            maxHeap(a, headSize, largest);
        }
    }

    private void exchange(int[] a, int index, int index2) {
        int temp = a[index];
        a[index] = a[index2];
        a[index2] = temp;
    }



    public void mergeSort(int[] a, int left, int right) {
        if (left < right) {
            int middle = (left + right) / 2;
            mergeSort(a, left, middle);
            mergeSort(a, middle + 1, right);
            merge(a, left, middle, right);
        }
    }

    private void merge(int[] a, int left, int middle, int right) {
        int[] temArray = new int[a.length];
        int rightStart = middle + 1;
        int leftStart = left;
        int temp = left;
        while (left <= middle && rightStart <= right) {
            if (a[left] <= a[rightStart]) {
                temArray[temp++] = a[left++];
            } else {
                temArray[temp++] = a[rightStart++];
            }
        }
        while (left <= middle) {
            temArray[temp++] = a[left++];
        }
        while (rightStart <= right) {
            temArray[temp++] = a[rightStart++];
        }
        while (leftStart <= right) {
            a[leftStart] = temArray[temp++];
        }
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

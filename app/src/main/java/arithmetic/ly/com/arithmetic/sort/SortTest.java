package arithmetic.ly.com.arithmetic.sort;

/**
 * Created by liuyu1 on 2018/2/7.
 */

public class SortTest {


    /**
     * 插入排序，a表示数组，n表示数组大小
     * 每一步将一个待排序的记录，插入到前面已经排好序的有序序列中去，直到插完所有元素为止。
     * int[] a = {5,4,6,1,3,2};
     */
    public void insertionSort(int[] a, int n) {
        if (n <= 1) return;

        for (int i = 1; i < n; ++i) {
            //待插入元素
            int value = a[i];
            int j = i - 1;
            // 查找插入的位置
            for (; j >= 0; --j) {
                if (a[j] > value) {//5>4
                    // 4的位置改成5,数据移动，将大于temp的往后移动一位
                    a[j + 1] = a[j];
                } else {
                    break;
                }
            }
            a[j + 1] = value; // 插入数据
        }
    }

    /**
     * 选择排序
     * 每次i排序后最小的数到了最前面
     * 和插入类似，都要先定义 a[i];
     */
    public void selectSort(int[] a) {
        int min;
        int tmp;
        for (int i = 0; i < a.length; i++) {
            min = a[i];
            //里面for第一次出来0，并且排在最前面，然后从i=1开始遍历
            for (int j = i; j < a.length; j++) {
                if (a[j] < min) {
                    min = a[j];//记录最小值  3
                    tmp = a[i];//9
                    a[i] = min;//3
                    a[j] = tmp;//9
                }
            }
        }
    }

    /**
     * 冒泡排序
     * 每次i排序后最大的数到了最后边
     * -i每进行一趟比较，每一趟少比较一次，一定程度上减少了算法的量
     */
    public void bubbleSort(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {//外层循环控制排序趟数
            for (int j = i; j < a.length - 1 - i; j++) {//内层循环控制每一趟排序多少次,选择一个数和i进行比较
                if (a[j] > a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }
    }


    /**
     * 快速排序
     */
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


    /**
     * 归并排序
     */
    public void mergeSort(int[] a, int left, int right) {
        if (left < right) {
            int middle = (left + right) / 2;
            mergeSort(a, left, middle);//左边归并排序，使得左子序列有序
            mergeSort(a, middle + 1, right);//右边归并排序，使得右子序列有序
            merge(a, left, middle, right);//将两个有序子数组合并操作
        }
    }


    private void merge(int[] a, int left, int middle, int right) {//left0,mid0,right1
        //在排序前，先建好一个长度等于原数组长度的临时数组，避免递归中频繁开辟空间
        int[] tmpArray = new int[a.length];
        int rightStart = middle + 1;//右序列指针
        int leftStart = left;//左序列指针
        int temp = left;//临时数组指针
        //比较两个小数组相应下标位置的数组大小，小的先放进新数组
        while (left <= middle && rightStart <= right) {
            if (a[left] <= a[rightStart]) {
                //相当于tmpArray[third]=a[left];third++;left++三步合一步
                tmpArray[temp++] = a[left++];
            } else {
                tmpArray[temp++] = a[rightStart++];
            }
        }
        //如果左边还有数据需要拷贝，把左边数组剩下的拷贝到新数组
        while (left <= middle) {
            tmpArray[temp++] = a[left++];
        }
        //如果右边还有数据......
        while (rightStart <= right) {
            tmpArray[temp++] = a[rightStart++];
        }
        //将temp中的元素全部拷贝到原数组中
        while (leftStart <= right) {
            a[leftStart] = tmpArray[leftStart++];
        }
    }


    /**
     * 堆排序
     */
    //(1)
    public void heapSort(int[] array) {
        buildMaxHeap(array);//建立最大堆
        for (int i = array.length - 1; i >= 1; i--) {
            //最大的在0位置，那么开始沉降，这样每交换一次最大的值就丢到最后了
            exchangeElements(array, 0, i);
            //继续获取0位置最大值，将第一次排序后到了最后面的最大值排除
            //重新调整结构，使其满足堆，然后继续交换堆顶元素与当前末尾元素，
            //反复执行调整+交换步骤，直到整个序列有序。
            maxHeap(array, i, 0);
        }
    }

    //(2)建立最大堆
    private void buildMaxHeap(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }
        int half = (array.length - 1) / 2;//从一半开始，6
        for (int i = half; i >= 0; i--) {
            maxHeap(array, array.length, i);
        }
    }

    private void maxHeap(int[] array, int heapSize, int index) {//index堆头
        int left = index * 2 + 1;
        int right = index * 2 + 2;
        int largest = index;
        //三者找最大值
        if (left < heapSize && array[left] > array[index]) {
            largest = left;
        }
        if (right < heapSize && array[right] > array[largest]) {
            largest = right;
        }
        if (index != largest) {
            exchangeElements(array, index, largest);
            //继续构造下面的大堆
            maxHeap(array, heapSize, largest);
        }
    }

    //（3）换位置
    public void exchangeElements(int[] array, int index1, int index2) {
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }


    public void binaryInsertSort(int[] a) {
        for (int i = 1; i < a.length; i++) {
            int temp = a[i];
            int left = 0;
            int right = i - 1;
            int middle = 0;
            while (left < right) {
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


    public void printArr(int[] arr) {
        for (int num : arr) {
            System.out.print(" " + num);
        }
    }


    public static void main(String[] args) {
        SortTest sortTest = new SortTest();
//        int[] arr = {9, 4, 2, 6, 7, 3, 10, 33, 88, 1, 17};
////        sortTest.bubbleSort(arr);
//        sortTest.selectSort(arr);
////        sortTest.insertSort(arr);
//        sortTest.printArr(arr);
//        System.out.println(sortTest.findKthLargest(new int[]{4, 2, 5, 12, 3}, 3) + "--");
        int[] num1 = {1, 2, 3, 0, 0, 0};
        int[] num2 = {2, 5, 6};
        sortTest.merge2(num1, num2);
    }


    /**
     * 有序的合并俩个数组
     */
    private void merge2(int[] num1, int[] num2) {//left0,mid0,right1
        //在排序前，先建好一个长度等于原数组长度的临时数组，避免递归中频繁开辟空间
        int a = num1.length;
        int b = num2.length;
        int[] tmpArray = new int[a + b];
        int rightStart = 0;//右序列指针
        int leftStart = 0;//左序列指针
        int temp = 0;//临时数组指针
        //比较两个小数组相应下标位置的数组大小，小的先放进新数组
        while (leftStart < a && rightStart < b) {
            if (num1[leftStart] <= num2[rightStart]) {
                //相当于tmpArray[third]=a[left];third++;left++三步合一步
                tmpArray[temp++] = num1[leftStart++];
            } else {
                tmpArray[temp++] = num2[rightStart++];
            }
        }
        //如果左边还有数据需要拷贝，把左边数组剩下的拷贝到新数组
        while (leftStart < a) {
            tmpArray[temp++] = num1[leftStart++];
        }
        //如果右边还有数据......
        while (rightStart < b) {
            tmpArray[temp++] = num2[rightStart++];
        }
        int slow = 0;
        int fast = 0;
        int[] newArray = new int[tmpArray.length];
        while (fast < tmpArray.length) {
            if (tmpArray[fast] != 0) {
                newArray[slow] = tmpArray[fast];
                ++slow;
            }
            ++fast;
        }
        for (int i : newArray) {
            System.out.println(i + "---");
        }

    }

}

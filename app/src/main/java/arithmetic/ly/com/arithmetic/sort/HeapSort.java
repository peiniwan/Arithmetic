package arithmetic.ly.com.arithmetic.sort;


/**
 * 堆排序
 */
public class HeapSort {
    public static void main(String[] args) {
        HeapSort heapSort = new HeapSort();
        int[] array = {19, 8, 27, 6, 35, 14, 3, 12, 1, 0, 9, 10, 7};
        //{35, 8, 27, 6, 19, 14, 3, 12, 1, 0, 9, 10, 7}
        //{7, 8, 27, 6, 19, 14, 3, 12, 1, 0, 9, 10, 35}
        //{27, 8, 7, 6, 19, 14, 3, 12, 1, 0, 9, 10, 35}
        //{10, 8, 7, 6, 19, 14, 3, 12, 1, 0, 9, 27, 35}

        System.out.println("Before heap:");
        heapSort.printArray(array);

        heapSort.heapSort(array);

        System.out.println("After heap sort:");
        heapSort.printArray(array);
    }

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


    public void printArray(int[] array) {
        System.out.print("{");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            if (i < array.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("}");
    }
}
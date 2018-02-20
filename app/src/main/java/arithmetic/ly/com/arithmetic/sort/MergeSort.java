package arithmetic.ly.com.arithmetic.sort;

/**
 * Created by liuyu1 on 2018/2/11.
 * 归并排序
 */
public class MergeSort {
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

    public static void main(String[] args) {
        MergeSort mergeSort = new MergeSort();
        int[] a = new int[]{9, 7, 6, 5, 4, 3, 2, 1};
        mergeSort.mergeSort(a, 0, a.length - 1);
        for (int n : a) {
            System.out.print(" " + n);
        }
    }
}
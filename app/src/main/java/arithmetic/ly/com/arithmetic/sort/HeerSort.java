package arithmetic.ly.com.arithmetic.sort;


//不稳定
public class HeerSort {

    //希尔排序
    public static void main(String[] args) {
        int[] a = {3, 2, 55, 9, 6, 11, 5, 12};
        System.out.println("排序之前：");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        //希尔排序
        System.out.println();
        HeerSort heerSort = new HeerSort();
        heerSort.heer(a);
        System.out.println();
        System.out.println("排序之后：");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
    }

    public void heer(int[] a) {
        int d = a.length / 2;//默认增量
        while (true) {
            for (int i = 0; i < d; i++) {
                for (int j = i; j + d < a.length; j += d) {
                    //i=0  j=0,4
                    //i=1  j=1,5
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


}
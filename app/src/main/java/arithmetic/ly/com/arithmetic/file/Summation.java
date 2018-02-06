package arithmetic.ly.com.arithmetic.file;

/**
 * Created by liuyu1 on 2018/2/6.
 */

public class Summation {
    public static int method_2(int num) {
//      //求和
//      if(num == 1){
//          return 1;
//      }
//      return num + method_2(num - 1);

        //求二进制
        StringBuilder sb = new StringBuilder();
        if (num > 0) {
            method_2(num / 2);
            int i = num % 2;
            sb.append(i);
        }
        System.out.println(sb.toString());
        return -1;
    }

}

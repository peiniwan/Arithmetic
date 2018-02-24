package arithmetic.ly.com.arithmetic.thread;

/**
 * Created by liuyu1 on 2018/2/5.
 */

public class DeadlockTest {

    public static void main(String[] args) {
        String str1 = new String("资源1");
        String str2 = new String("资源2");

        new Thread(new Lock(str1, str2), "线程1").start();
        new Thread(new Lock(str2, str1), "线程2").start();
    }
}

class Lock implements Runnable {

    private String str1;
    private String str2;

    public Lock(String str1, String str2) {
        super();
        this.str1 = str1;
        this.str2 = str2;
    }

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + "运行");
            synchronized (str1) {
                System.out.println(Thread.currentThread().getName() + "锁住"
                        + str1);
                Thread.sleep(1000);
                synchronized (str2) {
                    // 执行不到这里
                    System.out.println(Thread.currentThread().getName()
                            + "锁住了" + str2);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

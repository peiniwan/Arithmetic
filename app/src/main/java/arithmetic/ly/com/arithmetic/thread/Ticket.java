package arithmetic.ly.com.arithmetic.thread;

/**
 * Created by liuyu1 on 2018/2/5.
 */

public class Ticket implements Runnable { // 这里有俩把锁this和obj
    private int num = 100;
    Object obj = new Object();
    boolean flag = true;

    public void run() {
        if (flag) {
            while (true) {
                synchronized (obj) {
                    show();// 拿着obj想进this
                }
            }
        } else {
            while (true)
                this.show();
        }
    }

    public synchronized void show() {// 拿着this想进obj,互相拿着锁不放。和谐的时候是你进来我出去了
        synchronized (obj) {// 同步函数加入同步代码块
            if (num > 0) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                }
                System.out.println(Thread.currentThread().getName()
                        + ".....sale...." + num--);
            }
        }
    }

    public static void main(String[] args) {
        Ticket t = new Ticket();
        Thread t1 = new Thread(t);
        Thread t2 = new Thread(t);

        t1.start();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t.flag = false; // 切换
        t2.start();
    }
}

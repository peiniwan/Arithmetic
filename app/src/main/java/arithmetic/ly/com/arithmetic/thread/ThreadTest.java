package arithmetic.ly.com.arithmetic.thread;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Handler;

public class ThreadTest {

    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            Count count = new Count();

            @Override
            public void run() {
                count.count();
            }
        };
        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(runnable);
            thread.start();
        }
    }


    public static class Count {
        private int num;

        public synchronized void count() {
            for (int i = 1; i <= 10; i++) {
                num += i;
            }
            System.out.println(Thread.currentThread().getName() + "-" + num);
        }

    }
}

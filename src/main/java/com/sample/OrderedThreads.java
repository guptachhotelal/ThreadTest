package com.sample;

public class OrderedThreads {

    private static final int THREAD_COUNT = 4;
    private static final int COUNT_TILL = 100;
    private static final boolean[] THREAD_STATUS = new boolean[THREAD_COUNT];
    private static int counter = 0;

    public static void main(String[] args) {
        OrderedThreads tol = new OrderedThreads();
        for (int i = 0; i < THREAD_COUNT; i++) {
            THREAD_STATUS[i] = i <= 0;
            MyThread mt = tol.new MyThread();
            Thread ts = new Thread(mt, "Thread" + (i + 1));
            ts.start();
        }
    }

    class MyThread implements Runnable {

        MyThread() {
        }

        @Override
        public void run() {
            try {
                while (counter < COUNT_TILL) {
                    String threadName = Thread.currentThread().getName();
                    for (int i = 0; i < THREAD_STATUS.length; i++) {
                        if (THREAD_STATUS[i] && threadName.equalsIgnoreCase("Thread" + (i + 1))) {
                            ++counter;
                            System.out.println(threadName + " - " + counter);
                            THREAD_STATUS[i] = false;
                            if (i + 1 == THREAD_STATUS.length) {
                                THREAD_STATUS[0] = true;
                            } else {
                                THREAD_STATUS[i + 1] = true;
                            }
                        }
                    }
//                    Thread.sleep(10);
                }
            } catch (Exception e) {
                System.out.println("OrderedThreads MyThread " + e.getMessage());
            }
        }
    }
}

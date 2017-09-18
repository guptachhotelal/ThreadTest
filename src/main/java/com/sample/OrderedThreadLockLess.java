package com.sample;

public class OrderedThreadLockLess {

    private static final int THREAD_COUNT = 4;
    private static final int COUNT_TILL = 100;

    public static void main(String args[]) throws InterruptedException {
        OrderedThreadLockLess toll = new OrderedThreadLockLess();
        for (int i = 0; i < THREAD_COUNT; i++) {
            MyThread mt = toll.new MyThread(i);
            Thread ts = new Thread(mt, "Thread" + (i + 1));
            ts.start();
        }
    }
    private final Counter counter = new Counter(0);

    class MyThread implements Runnable {

        private int tPosition = 0;

        MyThread(int tPosition) {
            super();
            this.tPosition = tPosition;
        }

        @Override
        public void run() {
            while (counter.get() < COUNT_TILL) {
                synchronized (counter) {
                    if (counter.get() % THREAD_COUNT == this.tPosition) {
                        if (counter.get() < COUNT_TILL) {
                            System.out.println(Thread.currentThread() + "  " + counter.increment());
                        }
                    }
                }
            }
        }
    }
}

class Counter {

    private int i;

    Counter(int i) {
        this.i = i;
    }

    public synchronized int get() {
        return i;
    }

    public synchronized int increment() {
        ++i;
        return i;
    }
}

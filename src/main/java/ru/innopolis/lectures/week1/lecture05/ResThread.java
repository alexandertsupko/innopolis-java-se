package ru.innopolis.lectures.week1.lecture05;

public class ResThread extends Thread {
    private final Counter counter;
    private static final Object monitor = new Object();

    public ResThread(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            synchronized (monitor) {
                counter.increment();
            }
        }
    }
}

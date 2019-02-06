package ru.innopolis.lectures.week1.lecture05;

public class Counter {
    private int count;

    public synchronized int getCount() {
        return count;
    }

    public void increment() {
        synchronized (this) {
            this.count++;
        }
    }
}

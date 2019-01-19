package ru.inno.lec05;

import java.util.ArrayList;
import java.util.List;

public class ThreadDemo {
    public static void main(String[] args) throws InterruptedException {
        demo2();
    }

    private static void demo2() throws InterruptedException {
        List<Thread> threads = new ArrayList<>();
        Counter counter = new Counter();
        for (int i = 0; i < 10; i++) {
            Thread thread = new ResThread(counter);
            thread.start();
            threads.add(thread);
        }
        for (Thread thread : threads) {
            thread.join();
        }
    }

    private static void demo1() throws InterruptedException {
        Thread myThread = new MyThread(new MyRun());
        myThread.setName("Демо");
        myThread.start();
        myThread.join();
        System.out.println("Выполнение метода main завершено");
    }
}

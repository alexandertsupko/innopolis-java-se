package ru.innopolis.lectures.week1.lecture05;

public class MyThread extends Thread {
    public MyThread(Runnable myRun) {
        super(myRun);
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 10_000; i++) {
                System.out.println(i);
            }
        } finally {
            System.out.println("end");
        }
    }
}

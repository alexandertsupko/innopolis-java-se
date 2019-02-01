package ru.inno.lec05.wait;

public class WaitDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new ChronoThread();
        thread.start();
        Thread s1 = new SecondsThread(3);
        s1.setDaemon(true);
        s1.start();

        Thread s2 = new SecondsThread(5);
        s2.setDaemon(true);
        s2.start();

//        Thread.sleep(3_000);
//        thread.interrupt();
//        thread.join();
        System.out.println("Done!");
    }
}

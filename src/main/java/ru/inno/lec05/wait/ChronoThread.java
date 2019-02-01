package ru.inno.lec05.wait;

public class ChronoThread extends Thread {
    public static final Object MONITOR = new Object();

    @Override
    public void run() {
        int i = 0;
        while (!isInterrupted()) {
            try {
                sleep(1_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }
            i++;
            System.out.println("chrono " + i);
            synchronized (MONITOR) {
                MONITOR.notifyAll();
            }
        }
    }
}

package ru.innopolis.lectures.week1.lecture05.wait;

public class SecondsThread extends Thread {
    private final int seconds;

    public SecondsThread(int seconds) {
        this.seconds = seconds;
    }

    @Override
    public void run() {
        int j = 0;
        while (!isInterrupted()) {
            synchronized (ChronoThread.MONITOR) {
                try {
                    ChronoThread.MONITOR.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (++j % seconds == 0) {
                System.out.println("Seconds " + seconds);
            }
        }
    }
}

/*
happens-before:
1. Освобождение монитора происходит до его захвата другим потоком
2. Запись volatile переменной происходит до чтения
(не записывается в кэш процессора, все потоки увидят актуальную информацию, можем читать и записывать)
3. Старт потока происходит до того, как в этом потоке что-то происходит
4. Инициализация объекта происходит до того, как мы начинаем в нём что-то менять
Для final переменных synchronized блок не гарантирует, что не случится reordering.
 */
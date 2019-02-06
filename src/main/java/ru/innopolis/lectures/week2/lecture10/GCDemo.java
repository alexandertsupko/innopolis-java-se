package ru.innopolis.lectures.week2.lecture10;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GCDemo {

    private static final int LOOP_COUNT = 100_000_000;

    public static void main(String[] args) throws InterruptedException {
        List<String> list = new ArrayList<>();

        Random random = new Random();
        for (int i = 0; i < LOOP_COUNT; i++) {
            String string = "" + random.nextInt();
            list.add(string);
            Thread.sleep(1);
        }

        System.out.println(list.size());
    }
}

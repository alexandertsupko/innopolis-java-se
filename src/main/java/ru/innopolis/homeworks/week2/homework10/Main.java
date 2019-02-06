/*
 * Copyright (c) 2019. Alexander Tsupko (alexander.tsupko@outlook.com). All rights reserved.
 */

package ru.innopolis.homeworks.week2.homework10;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    private static final int LOOP_COUNT = 100_000_000;

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();

        Random random = new Random();
        for (int i = 0; i < LOOP_COUNT; i++) {
            String string = Integer.toString(random.nextInt());
            if (random.nextBoolean()) {
                string = null;
            }
            list.add(string);
        }

        System.out.println(list.size());
    }
}

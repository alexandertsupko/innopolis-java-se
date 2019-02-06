/*
 * Copyright (c) 2019. Alexander Tsupko (alexander.tsupko@outlook.com). All rights reserved.
 */

package ru.innopolis.lectures.week4.lecture17.people;

import ru.innopolis.lectures.week4.lecture17.beverages.Beer;
import ru.innopolis.lectures.week4.lecture17.beverages.IBeverage;

public class Fan implements IMan {
    private Beer beer;

    @Override
    public void drink(IBeverage beer) {
        System.out.println("I am drunk!");
    }
}

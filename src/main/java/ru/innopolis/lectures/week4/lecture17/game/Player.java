/*
 * Copyright (c) 2019. Alexander Tsupko (alexander.tsupko@outlook.com). All rights reserved.
 */

package ru.innopolis.lectures.week4.lecture17.game;

import ru.innopolis.lectures.week4.lecture17.beverages.IBeverage;
import ru.innopolis.lectures.week4.lecture17.people.IMan;

public class Player implements IMan {
    public void drink(IBeverage beer) {
        System.out.println("I don't drink because I play football");
    }
}

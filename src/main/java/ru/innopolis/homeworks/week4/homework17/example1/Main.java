/*
 * Copyright (c) 2019. Alexander Tsupko (alexander.tsupko@outlook.com). All rights reserved.
 */

package ru.innopolis.homeworks.week4.homework17.example1;

import ru.innopolis.homeworks.week4.homework17.example1.factory.Dialog;

public class Main {
    public static void main(String[] args) {
        Dialog dialog = (new DemoFactory()).createDialog("Windows");
        dialog.renderWindow();
    }
}

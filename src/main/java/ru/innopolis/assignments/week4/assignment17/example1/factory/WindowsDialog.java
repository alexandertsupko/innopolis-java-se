/*
 * Copyright (c) 2019. Alexander Tsupko (alexander.tsupko@outlook.com). All rights reserved.
 */

package ru.innopolis.assignments.week4.assignment17.example1.factory;

import ru.innopolis.assignments.week4.assignment17.example1.buttons.Button;
import ru.innopolis.assignments.week4.assignment17.example1.buttons.WindowsButton;

/**
 * Windows-диалог будет создавать Windows-кнопки.
 */
public class WindowsDialog extends Dialog {
    /**
     * Переопределение фабричного метода, создающее конкретный продукт, Windows-кнопку.
     *
     * @return новый объект класса {@code WindowsButton}
     */
    @Override
    protected Button createButton() {
        return new WindowsButton();
    }
}

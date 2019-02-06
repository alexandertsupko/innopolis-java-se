/*
 * Copyright (c) 2019. Alexander Tsupko (alexander.tsupko@outlook.com). All rights reserved.
 */

package ru.innopolis.homeworks.week4.homework17.example1.factory;

import ru.innopolis.homeworks.week4.homework17.example1.buttons.Button;
import ru.innopolis.homeworks.week4.homework17.example1.buttons.HtmlButton;

/**
 * HTML-диалог будет создавать HTML-кнопки.
 */
public class HtmlDialog extends Dialog {
    /**
     * Переопределение фабричного метода, создающее конкретный продукт, HTML-кнопку.
     *
     * @return новый объект класса {@code HtmlButton}
     */
    @Override
    protected Button createButton() {
        return new HtmlButton();
    }
}

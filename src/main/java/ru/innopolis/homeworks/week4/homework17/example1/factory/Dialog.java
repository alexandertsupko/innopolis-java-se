/*
 * Copyright (c) 2019. Alexander Tsupko (alexander.tsupko@outlook.com). All rights reserved.
 */

package ru.innopolis.homeworks.week4.homework17.example1.factory;

import ru.innopolis.homeworks.week4.homework17.example1.buttons.Button;

/**
 * Базовый фабричный класс. "Фабричный" означает просто роль для класса.
 * Он должен иметь некую базовую бизнес-логику, которая нужна разным продуктам для создания.
 */
public abstract class Dialog {
    public void renderWindow() {
        // ... бизнес-логика ...

        Button okButton = createButton();
        okButton.render();
    }

    /**
     * Подклассы будут переопределять этот метод, чтобы создать конкретные объекты кнопок.
     *
     * @return возвращаемый тип фабричного метода должен совпадать с интерфейсом продуктов
     */
    protected abstract Button createButton();
}

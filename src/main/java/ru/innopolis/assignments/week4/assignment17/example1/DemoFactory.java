/*
 * Copyright (c) 2019. Alexander Tsupko (alexander.tsupko@outlook.com). All rights reserved.
 */

package ru.innopolis.assignments.week4.assignment17.example1;

import ru.innopolis.assignments.week4.assignment17.example1.factory.Dialog;
import ru.innopolis.assignments.week4.assignment17.example1.factory.HtmlDialog;
import ru.innopolis.assignments.week4.assignment17.example1.factory.WindowsDialog;

/**
 * Клиентский код.
 */
public class DemoFactory {
    private Dialog dialog; // экземляр абстрактного создателя кнопок

    /**
     * Клиент выбирает, какую кнопку создать, в зависимости от системы.
     */
    public Dialog createDialog(String osType) {
        if (osType.equals("Windows")) {
            dialog = new WindowsDialog();
        } else {
            dialog = new HtmlDialog();
        }
        return dialog;
    }

    /**
     * Выбранный диалог исполняет свою специфическую бизнес-логику.
     */
    public void runBusinessLogic() {
        dialog.renderWindow();
    }
}

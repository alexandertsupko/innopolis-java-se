/*
 * Copyright (c) 2019. Alexander Tsupko (alexander.tsupko@outlook.com). All rights reserved.
 */

package ru.innopolis.homeworks.week4.homework17.example1.buttons;

import javax.swing.*;
import java.awt.*;

/**
 * Реализация Windows-кнопки.
 */
public class WindowsButton implements Button {
    private JFrame frame = new JFrame(); // окно
    private JPanel panel = new JPanel(); // панель
    private JButton button = null;       // кнопка

    @Override
    public void render() {
        // Создать и настроить метку
        JLabel label = new JLabel("Hello World!");
        label.setOpaque(true);
        label.setBackground(new Color(235, 233, 126));
        label.setFont(new Font("Dialog", Font.BOLD, 44));
        label.setHorizontalAlignment(SwingConstants.CENTER);

        // Добавить метку и кнопку на панель
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel.add(label);
        onClick();
        panel.add(button);

        // Добавить панель в окно и настроить окно
        frame.add(panel);
        frame.setSize(320, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    @Override
    public void onClick() {
        // Создать и настроить кнопку
        button = new JButton("Exit");
        button.addActionListener(event -> {
            frame.setVisible(false);
            System.exit(0);
        });
    }
}

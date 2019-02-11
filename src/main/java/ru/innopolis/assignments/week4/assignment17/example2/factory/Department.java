/*
 * Copyright (c) 2019. Alexander Tsupko (alexander.tsupko@outlook.com). All rights reserved.
 */

package ru.innopolis.assignments.week4.assignment17.example2.factory;

import ru.innopolis.assignments.week4.assignment17.example2.employees.Employee;

/**
 * Базовый абстрактный класс с фабричным методом {@code createEmployee(String)}.
 */
public abstract class Department {
    protected abstract Employee createEmployee(String name);

    public void fire(String name) {
        Employee employee = this.createEmployee(name);
        employee.paySalary();
        employee.dismiss();
    }
}

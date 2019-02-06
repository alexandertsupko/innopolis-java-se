/*
 * Copyright (c) 2019. Alexander Tsupko (alexander.tsupko@outlook.com). All rights reserved.
 */

package ru.innopolis.homeworks.week4.homework17.example2.factory;

import ru.innopolis.homeworks.week4.homework17.example2.employees.Accountant;
import ru.innopolis.homeworks.week4.homework17.example2.employees.Employee;

public class AccountantDepartment extends Department {
    @Override
    protected Employee createEmployee(String name) {
        return new Accountant(name);
    }
}

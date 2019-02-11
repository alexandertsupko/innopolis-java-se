/*
 * Copyright (c) 2019. Alexander Tsupko (alexander.tsupko@outlook.com). All rights reserved.
 */

package ru.innopolis.assignments.week4.assignment17.example2.factory;

import ru.innopolis.assignments.week4.assignment17.example2.employees.Employee;
import ru.innopolis.assignments.week4.assignment17.example2.employees.Programmer;

public class ITDepartment extends Department {
    @Override
    protected Employee createEmployee(String name) {
        return new Programmer(name);
    }
}

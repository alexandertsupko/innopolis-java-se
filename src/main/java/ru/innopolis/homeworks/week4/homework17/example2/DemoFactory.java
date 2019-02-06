/*
 * Copyright (c) 2019. Alexander Tsupko (alexander.tsupko@outlook.com). All rights reserved.
 */

package ru.innopolis.homeworks.week4.homework17.example2;

import ru.innopolis.homeworks.week4.homework17.example2.employees.Accountant;
import ru.innopolis.homeworks.week4.homework17.example2.employees.Employee;
import ru.innopolis.homeworks.week4.homework17.example2.employees.Programmer;

import java.util.ArrayList;
import java.util.List;

/**
 * Клиентский код.
 */
public class DemoFactory {
    private static List<Employee> employees = new ArrayList<>();

    public static void main(String[] args) {
        hireAccountants(3);
        hireProgrammers(2);
        employees.parallelStream().forEach(Employee::paySalary);
        System.out.println(employees);
    }

    private static void hireAccountants(int n) {
        while (n-- > 0) {
            employees.add(new Accountant("Accountant #" + n));
        }
    }

    private static void hireProgrammers(int n) {
        while (n-- > 0) {
            employees.add(new Programmer("Programmer #" + n));
        }
    }
}

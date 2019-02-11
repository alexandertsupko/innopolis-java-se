/*
 * Copyright (c) 2019. Alexander Tsupko (alexander.tsupko@outlook.com). All rights reserved.
 */

package ru.innopolis.assignments.week4.assignment17.example2.employees;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Программист является работником и реализует методы {@code paySalary()} и {@code dismiss()}.
 */
public class Programmer implements Employee {
    private final int id;
    private final String name;

    private final LocalDate dateOfHiring;
    private LocalDate time;

    private BigDecimal income = BigDecimal.ZERO;
    private BigDecimal salary = new BigDecimal(50_000);

    public Programmer(String name) {
        this.id = Integer.parseInt(name.substring(name.indexOf('#') + 1));
        this.name = name;
        this.dateOfHiring = LocalDate.now();
        this.time = this.dateOfHiring;
    }

    @Override
    public void paySalary() {
        while (time.isBefore(dateOfHiring.plusYears(1))) {
            income = income.add(salary);
            System.out.printf("%s received another %.2f\u20bd and his income is %.2f\u20bd on %s\n",
                    name, salary, income, time);
            try {
                Thread.sleep(1_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            time = time.plusMonths(1);
        }
    }

    @Override
    public void dismiss() {
        System.out.printf("Programmer %s was dismissed on %s\n", name, time);
    }

    @Override
    public String toString() {
        return name;
    }
}

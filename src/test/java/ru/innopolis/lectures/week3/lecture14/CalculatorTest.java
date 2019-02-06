package ru.innopolis.lectures.week3.lecture14;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CalculatorTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(CalculatorTest.class);

    private Calculator calculator;

    @BeforeAll
    static void init() {
        LOGGER.info("init ALL");
    }

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
        LOGGER.info("setup");
    }

    @Test
    void sum() {
        LOGGER.info("sum...");
        assertEquals(7, calculator.sum(3, 4), "3 + 4 = 7");
        assertEquals(-1, calculator.sum(3, -4), "3 - 4 = -1");
    }

    @Test
    void divide() throws DivideByZeroException {
        LOGGER.info("divide...");
        assertEquals(2, calculator.divide(4, 2), "4 / 2 = 2");
        assertEquals(1, calculator.divide(3, 2), "3 / 2 = 1 (int)");
        assertEquals(1.5, calculator.divide(3.0, 2), "3 / 2 = 1 (double)");
    }

    @Test
    void divideNegativeTest() {
        LOGGER.info("throw...");
        assertThrows(DivideByZeroException.class, () -> calculator.divide(1, 0), "division by zero is prohibited");
    }
}
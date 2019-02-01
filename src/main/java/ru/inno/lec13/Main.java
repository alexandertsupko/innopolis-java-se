package ru.inno.lec13;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import ru.inno.lec13.auth.User;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Запуск программы с аргументами: " + Arrays.toString(args));
        }

        if (args.length < 1) {
            System.out.println("java... <login>");
            LOGGER.error("Отстутствует параметр <login>");
            return;
        }

        User user = new User();
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Введите имя пользователя: ");
            while (scanner.hasNextLine()) {
                String login = scanner.nextLine();
                if ("quit".equals(login)) {
                    LOGGER.info("Пользователь {} успешно вышел", MDC.get("login"));
                    return;
                }
                try {
                    user.doLogin(login);
                } catch (Exception e) {
                    LOGGER.error("Произошла ошибка авторизации");
                    e.printStackTrace();
                }
            }
        }
    }
}

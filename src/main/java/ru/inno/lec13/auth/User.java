package ru.inno.lec13.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

public class User {
    private static final Logger LOGGER = LoggerFactory.getLogger(User.class);

    public boolean doLogin(String login) {
        LOGGER.info("попытка зайти под пользователем {}", login);
        if ("user".equals(login)) {
            LOGGER.info("пользователь успешно авторизован");
            MDC.put("login", login);
            return true;
        }
        if ("root".equals(login)) {
            LOGGER.warn("Запрещено логиниться супер-пользователем: {}", login);
            throw new UnsupportedOperationException();
        }
        LOGGER.info("Пользователь не авторизован");
        return false;
    }
}

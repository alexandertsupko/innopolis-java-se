package ru.inno.lec06.practice;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class SomeHandler implements InvocationHandler {
    private final SomeDoer someDoer;
    private final Logging annotation;

    public SomeHandler(SomeDoer someDoer) {
        this.someDoer = someDoer;
        this.annotation = someDoer.getClass().getAnnotation(Logging.class);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long start = System.currentTimeMillis();
        Object o = method.invoke(someDoer, args);
        if (annotation != null) {
            System.out.println("Method call");
            System.out.println(System.currentTimeMillis() - start);
        }
        return o;
    }
}

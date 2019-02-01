package ru.inno.lec06.practice;

import java.lang.reflect.Proxy;

public class Main {
    public static void main(String[] args) {
        SomeDoer a = new A();
        SomeDoer b = new B();

        SomeHandler handlerA = new SomeHandler(a);
        SomeHandler handlerB = new SomeHandler(b);

        ClassLoader loader = A.class.getClassLoader();
        Class<?>[] interfaces = A.class.getInterfaces();

        SomeDoer someDoerA = (SomeDoer) Proxy.newProxyInstance(loader, interfaces, handlerA);
        SomeDoer someDoerB = (SomeDoer) Proxy.newProxyInstance(loader, interfaces, handlerB);

        someDoerA.doSome();
        someDoerB.doSome();
    }
}

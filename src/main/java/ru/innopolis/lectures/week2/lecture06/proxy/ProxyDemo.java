package ru.innopolis.lectures.week2.lecture06.proxy;

import java.lang.reflect.Proxy;

public class ProxyDemo {
    public static void main(String[] args) {
        Trainer trainer = new TrainerImpl();
        trainer.eat();
        trainer.talk();
        trainer.teach();

        System.out.println("Создание супер-тренера:");
        TrainerMan handle = new TrainerMan(trainer);

        Trainer man = (Trainer) Proxy.newProxyInstance(
                ProxyDemo.class.getClassLoader(),
                new Class[]{Trainer.class},
                handle
        );
        System.out.println("съедено: " + man.eat());
        System.out.println("сказано: " + man.talk());
        man.teach();
    }
}

package ru.inno.lec06.proxy;

public class TrainerImpl implements Trainer {
    @Override
    public int eat() {
        System.out.println("ем");
        return 2;
    }

    @Override
    public String talk() {
        System.out.println("говорю");
        return "Говорю";
    }

    @Override
    public void teach() {
        System.out.println("учу");
    }
}

package ru.innopolis.lectures.week2.lecture07;

public class KindMagic implements Magic {
    static {
        System.out.println("Хоттабыч2");
    }

    @Override
    public void doMagic() {
        System.out.println("Абра-кадабра!!!");
    }
}

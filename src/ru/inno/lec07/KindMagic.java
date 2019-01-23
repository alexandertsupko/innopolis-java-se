package ru.inno.lec07;

public class KindMagic implements Magic {
    static {
        System.out.println("Хоттабыч2");
    }

    @Override
    public void doMagic() {
        System.out.println("Абра-кадабра!!!");
    }
}

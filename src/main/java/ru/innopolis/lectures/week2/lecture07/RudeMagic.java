package ru.innopolis.lectures.week2.lecture07;

public class RudeMagic implements Magic {
    static {
        System.out.println("Злой волшебник");
    }

    @Override
    public void doMagic() {
        System.out.println("ахалай-махалай");
    }
}

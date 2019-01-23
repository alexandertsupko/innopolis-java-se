package ru.inno.lec07;

public class RudeMagic implements Magic {
    static {
        System.out.println("Злой волшебник");
    }

    @Override
    public void doMagic() {
        System.out.println("ахалай-махалай");
    }
}

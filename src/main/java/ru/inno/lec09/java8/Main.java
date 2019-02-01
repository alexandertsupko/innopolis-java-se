package ru.inno.lec09.java8;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        InterfaceImpl impl = new InterfaceImpl();
        impl.doSome();

        int k = 0;
        compare(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return k;
            }
        });

        compare((Object o1, Object o2) -> { return 0; });
        compare((o1, o2) -> { return 0; });
        compare((o1, o2) ->  k);
        Thread thread = new Thread(() -> {
            System.out.println("hello");
            System.out.println("hello");
            System.out.println("hello");
            System.out.println("hello");
        });

        // demo for PrinterInterface
        doPrint(impl::doSome);

        int[] a = new int[6];
        for (int i = 0; i < a.length; ++i) {
            a[i] = i + 1;
        }
        int sum = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] % 2 != 0) {
                sum += a[i];
            }
        }
        System.out.println(sum);

        IntStream stream = Arrays.stream(a);
        sum = stream
                .filter(integer -> integer % 2 != 0)
                .reduce((o1, o2) -> o1 + o2)
                .orElse(0);
        System.out.println(sum);
    }

    private static void doPrint(PrinterInterface printer) {
        printer.print();
    }

    public static void compare(Comparator comparator) {
    }
}

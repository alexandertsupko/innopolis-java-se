package ru.inno.lec02;

import java.util.Iterator;
import java.util.Random;

public class ArrayDemo {
    public static void main(String[] args) {
        int[] arr = new int[10];
        Integer arrB[] = new Integer[15];

        Random random = new Random();
        for (int i = 0; i < 15; i++) {
            arrB[i] = random.nextInt(1000);
        }

        for (int i : arr) {
//            System.out.println(i);
        }

        for (Integer integer : arrB) {
//            System.out.println(integer);
        }

        MyArr myArr = new MyArr();
        for (Object o : myArr) {

        }

        myLabel:
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10; j++) {
                if (j == 5) {
                    continue myLabel;
                } else {
                    System.out.println(i + " - " + j);
                }
            }
        }
    }
}

class MyArr implements Iterable {

    @Override
    public Iterator iterator() {
        return new Iterator() {
            @Override
            public boolean hasNext() {
                System.out.println("hasNext");
                return false;
            }

            @Override
            public Object next() {
                System.out.println("next");
                return null;
            }
        };
    }
}

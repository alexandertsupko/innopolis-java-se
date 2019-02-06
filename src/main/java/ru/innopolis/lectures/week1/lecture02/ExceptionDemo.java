package ru.innopolis.lectures.week1.lecture02;

public class ExceptionDemo {
    public static void main(String[] args) {
        A a = new A(new C());
        try {
            System.out.println(a.doSome());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class A {
    B b;

    public A(B b) {
        this.b = b;
    }

    String doSome() throws Exception {
        return b.doSomeElse().toLowerCase();
    }
}

class B {
    String doSomeElse() {
        return "B.doSomeElse";
    }
}

class C extends B {
    @Override
    String doSomeElse() {
        return "C.doSomeElse";
    }
}
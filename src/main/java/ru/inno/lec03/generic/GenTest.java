package ru.inno.lec03.generic;

import ru.inno.lec03.generic.entities.Cat;
import ru.inno.lec03.generic.entities.Dog;
import ru.inno.lec03.generic.entities.Pet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GenTest {
    public static void main(String[] args) {
        List<Cat> cats = getPets();
        List<Pet> pets = new ArrayList<>();
        pets.add(new Dog());
        Collections.copy(pets, cats);
        callPets(pets);
    }

    private static <T> void callPets(List<T> pets) {
        for (T pet : pets) {
            System.out.println(pet);
        }
    }

    private static List<Cat> getPets() {
        List<Cat> result = new ArrayList<>();
        result.add(new Cat());
        return result;
    }
}

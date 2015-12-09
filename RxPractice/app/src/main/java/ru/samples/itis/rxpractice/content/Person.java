package ru.samples.itis.rxpractice.content;

import android.support.annotation.NonNull;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Artur Vasilov
 */
public class Person {

    private final String mName;
    private final int mAge;

    public Person(@NonNull String name, int age) {
        mName = name;
        mAge = age;
    }

    @NonNull
    public String getName() {
        return mName;
    }

    public int getAge() {
        return mAge;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Person person = (Person) o;
        return getAge() == person.getAge() && getName().equals(person.getName());

    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + getAge();
        return result;
    }

    @Override
    public String toString() {
        return "Person{" +
                "mName='" + mName + '\'' +
                ", mAge=" + mAge +
                '}';
    }

    public int intValue() {
        return getName().length() + getAge();
    }

    @NonNull
    public Person makeChild(@NonNull Person person) {
        String name = getName() + " : " + person.getName();
        return new Person(name, 0);
    }

    @NonNull
    public static Person randomPerson() {
        Random random = new SecureRandom();
        final String template = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
                "abcdefghijklmnopqrstuvwxyz";

        String name = randomString(random, template, random.nextInt(15) + 3);
        int age = random.nextInt(100);
        return new Person(name, age);
    }

    @NonNull
    public static List<Person> randomPersonsList(int size) {
        List<Person> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(randomPerson());
        }
        return list;
    }

    @NonNull
    private static String randomString(Random random, String characters, int length) {
        char[] text = new char[length];
        for (int i = 0; i < length; i++) {
            text[i] = characters.charAt(random.nextInt(characters.length()));
        }
        return new String(text);
    }
}

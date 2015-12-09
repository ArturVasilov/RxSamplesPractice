package ru.samples.itis.rxpractice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;

import ru.samples.itis.rxpractice.content.Person;
import ru.samples.itis.rxpractice.tasks.RxJavaTask3;
import rx.Observable;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/**
 * @author Artur Vasilov
 */
@RunWith(JUnit4.class)
public class Task3Test {

    @Test
    public void testInitialized() throws Exception {
        Observable<Integer> observable = RxJavaTask3.task3Observable(Observable.just(new Person("a", 10)).toList());
        assertNotNull(observable);
    }

    @Test
    public void testInput1() throws Exception {
        List<Person> persons = new ArrayList<Person>() {{
            add(new Person("Ivan", 15));
            add(new Person("a", 8));
            add(new Person("abcde", 7));
            add(new Person("", 70));
        }};

        Observable<Integer> observable = RxJavaTask3.task3Observable(Observable.just(persons));
        observable.subscribe(integer -> {
            assertEquals(120, integer.intValue());
        });
    }

    @Test
    public void testInput2() throws Exception {
        List<Person> persons = new ArrayList<Person>() {{
            add(new Person("Ivan", 15));
            add(new Person("a", 8));
            add(new Person("abcde", 7));
            add(new Person("", 70));
        }};

        List<Person> personsEqual = new ArrayList<Person>() {{
            add(new Person("Ivan", 15));
            add(new Person("a", 8));
            add(new Person("abcde", 7));
            add(new Person("", 70));
        }};

        Observable<Integer> observable = RxJavaTask3.task3Observable(Observable.just(persons, personsEqual));
        observable.subscribe(integer -> {
            assertEquals(120, integer.intValue());
        });
    }

    @Test
    public void testInput3() throws Exception {
        List<Person> persons = new ArrayList<Person>() {{
            add(new Person("Ivan", 15));
            add(new Person("a", 8));
            add(new Person("abcde", 7));
            add(new Person("", 70));
        }};

        List<Person> persons2 = new ArrayList<Person>() {{
            add(new Person("Ivan", 15));
            add(new Person("a", 8));
            add(new Person("abcde", 10));
            add(new Person("", 70));
        }};

        List<Person> persons3 = new ArrayList<Person>() {{
            add(new Person("Ivan1", 15));
            add(new Person("a1", 8));
            add(new Person("abcde2", 10));
            add(new Person("22", 70));
        }};

        Observable<Integer> observable = RxJavaTask3.task3Observable(Observable.just(persons, persons2, persons3));
        observable.subscribe(integer -> {
            assertEquals(273, integer.intValue());
        });
    }

    @Test
    public void testInputRandom() throws Exception {
        List<Person> persons = Person.randomPersonsList(16);
        List<Person> persons1 = Person.randomPersonsList(32);
        List<Person> persons2 = Person.randomPersonsList(78);
        List<Person> persons3 = Person.randomPersonsList(3);

        int result = 0;
        //suppose we don't have the same persons, ok
        for (Person person : persons) {
            result += person.getName().length() + person.intValue();
        }
        for (Person person : persons1) {
            result += person.getName().length() + person.intValue();
        }
        for (Person person : persons2) {
            result += person.getName().length() + person.intValue();
        }
        for (Person person : persons3) {
            result += person.getName().length() + person.intValue();
        }
        final int expected = result;

        Observable<Integer> observable = RxJavaTask3.task3Observable(Observable.just(persons, persons1, persons2, persons3));
        observable.subscribe(integer -> {
            assertEquals(expected, integer.intValue());
        });
    }
}

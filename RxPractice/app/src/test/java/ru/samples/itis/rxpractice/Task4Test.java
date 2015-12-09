package ru.samples.itis.rxpractice;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ru.samples.itis.rxpractice.content.Person;
import ru.samples.itis.rxpractice.tasks.RxJavaTask4;
import rx.Observable;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/**
 * @author Artur Vasilov
 */
@RunWith(JUnit4.class)
public class Task4Test {

    @Test
    public void testInitialized() throws Exception {
        List<Person> fathers1 = Person.randomPersonsList(1);
        List<Person> fathers2 = Person.randomPersonsList(1);
        List<Person> mothers1 = Person.randomPersonsList(1);
        List<Person> mothers2 = Person.randomPersonsList(1);

        Observable<Person> observable = RxJavaTask4.task4Observable(fathers1, fathers2,
                mothers1, mothers2);
        assertNotNull(observable);
    }

    @Test
    public void testInput1() throws Exception {
        List<Person> fathers1 = new ArrayList<>();
        fathers1.add(new Person("Father1", 25));

        List<Person> fathers2 = new ArrayList<>();
        fathers2.add(new Person("Father2", 27));

        List<Person> mothers1 = new ArrayList<>();
        mothers1.add(new Person("Mother1", 23));

        List<Person> mothers2 = new ArrayList<>();
        mothers2.add(new Person("Mother2", 26));

        Observable<Person> observable = RxJavaTask4.task4Observable(fathers1, fathers2,
                mothers1, mothers2);

        observable.count().subscribe(integer -> assertEquals(integer.intValue(), 2));

        Person child1 = new Person("Father1 : Mother1", 0);
        Person child2 = new Person("Father2 : Mother2", 0);
        Observable<Person> expected = Observable.just(child1, child2);

        Observable.zip(observable, expected, Person::equals)
                .subscribe(Assert::assertTrue);
    }

    @Test
    public void testInputRandom() throws Exception {
        Random random = new SecureRandom();
        int first = random.nextInt(100) + 50;
        int second = random.nextInt(100) + 50;
        List<Person> fathers1 = Person.randomPersonsList(first);
        List<Person> fathers2 = Person.randomPersonsList(second);
        List<Person> mothers1 = Person.randomPersonsList(first);
        List<Person> mothers2 = Person.randomPersonsList(second);

        Observable<Person> observable = RxJavaTask4.task4Observable(fathers1, fathers2,
                mothers1, mothers2);

        final int expectedCount = first + second;
        observable.count().subscribe(integer -> assertEquals(integer.intValue(), expectedCount));

        List<Person> children = new ArrayList<>();
        for (int i = 0; i < first; i++) {
            Person father = fathers1.get(i);
            Person mother = mothers1.get(i);
            Person child = father.makeChild(mother);
            children.add(child);
        }

        for (int i = 0; i < second; i++) {
            Person father = fathers2.get(i);
            Person mother = mothers2.get(i);
            Person child = father.makeChild(mother);
            children.add(child);
        }

        Observable.zip(observable, Observable.from(children), Person::equals)
                .subscribe(Assert::assertTrue);
    }
}

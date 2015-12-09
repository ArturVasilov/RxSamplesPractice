package ru.samples.itis.rxpractice;

import android.support.annotation.NonNull;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import ru.samples.itis.rxpractice.tasks.RxJavaTask1;
import rx.Observable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

/**
 * @author Artur Vasilov
 */
@RunWith(JUnit4.class)
public class Task1Test {

    @Test
    public void testInput1() throws Exception {
        List<String> list = createList("Hello", "Reactive", "World");

        Observable<Integer> observable = RxJavaTask1.task1Observable(list);
        testObservable(observable, 2, createList(8, 5));
    }

    @Test
    public void testInput2() throws Exception {
        List<String> list = createList("Rrrr", "r", "R", "aRRRR", "aA", "");

        Observable<Integer> observable = RxJavaTask1.task1Observable(list);
        testObservable(observable, 4, createList(4, 1, 1, 5));
    }

    @Test
    public void testInput3() throws Exception {
        List<String> list = createList("Hele is nothing", "This is input with no such lettel lol",
                "AAAaa", "Enough", "Stop it", "done",
                "If youl obselvable will emit some items, you should know - you've failed");

        Observable<Integer> observable = RxJavaTask1.task1Observable(list);
        testObservable(observable, 0, new ArrayList<>());
    }

    @Test
    public void testInputRandom() throws Exception {
        List<String> list = randomStringList();
        List<Integer> expected = new ArrayList<>();
        for (String s : list) {
            if (s.contains("r") || s.contains("R")) {
                expected.add(s.length());
            }
        }

        Observable<Integer> observable = RxJavaTask1.task1Observable(list);
        testObservable(observable, expected.size(), expected);
    }

    private void testObservable(@NonNull Observable<Integer> observable, final int expectedSize,
                                @NonNull List<Integer> expectedValues) {
        Observable<Integer> count = observable.count().first();
        assertNotNull(count);
        count.subscribe(integer -> {
            assertEquals(expectedSize, integer.intValue());
        });

        Observable.zip(observable, Observable.from(expectedValues),
                (integer, integer2) -> integer.intValue() == integer2.intValue())
                .doOnError(throwable -> fail())
                .subscribe(Assert::assertTrue);
    }

    @SafeVarargs
    @NonNull
    private final <T> List<T> createList(T... ts) {
        List<T> list = new ArrayList<>();
        Collections.addAll(list, ts);
        return list;
    }

    @NonNull
    private String randomString(Random random, String characters, int length) {
        char[] text = new char[length];
        for (int i = 0; i < length; i++) {
            text[i] = characters.charAt(random.nextInt(characters.length()));
        }
        return new String(text);
    }

    @NonNull
    private List<String> randomStringList() {
        final String template = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
                "abcdefghijklmnopqrstuvwxyz"
                + "0123456789";
        Random random = new SecureRandom();

        int size = random.nextInt(10_000) + 500;
        List<String> stringList = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            int length = random.nextInt(10) + 5;
            stringList.add(randomString(random, template, length));
        }
        return stringList;
    }

}

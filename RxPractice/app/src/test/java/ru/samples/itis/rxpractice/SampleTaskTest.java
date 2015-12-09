package ru.samples.itis.rxpractice;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import ru.samples.itis.rxpractice.tasks.RxJavaSampleTask;
import rx.Observable;

import static junit.framework.Assert.assertNotNull;

/**
 * @author Artur Vasilov
 */
@RunWith(JUnit4.class)
public class SampleTaskTest {

    @Test
    public void testNotNull() throws Exception {
        Observable<Boolean> observable = RxJavaSampleTask.isLollipopOrHigher(10);
        assertNotNull(observable);

        observable = RxJavaSampleTask.isLollipopOrHigher(29);
        assertNotNull(observable);

        observable = RxJavaSampleTask.isLollipopOrHigher(-100);
        assertNotNull(observable);

        observable = RxJavaSampleTask.isLollipopOrHigher(0);
        assertNotNull(observable);
    }

    @Test
    public void testSampleTask() throws Exception {
        Observable<Boolean> observable = RxJavaSampleTask.isLollipopOrHigher(10);
        observable.subscribe(Assert::assertFalse);

        observable = RxJavaSampleTask.isLollipopOrHigher(0);
        observable.subscribe(Assert::assertFalse);

        observable = RxJavaSampleTask.isLollipopOrHigher(21);
        observable.subscribe(Assert::assertTrue);

        observable = RxJavaSampleTask.isLollipopOrHigher(23);
        observable.subscribe(Assert::assertTrue);

    }
}

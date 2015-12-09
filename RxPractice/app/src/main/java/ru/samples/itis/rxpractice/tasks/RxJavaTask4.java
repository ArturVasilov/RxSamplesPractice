package ru.samples.itis.rxpractice.tasks;

import android.support.annotation.NonNull;

import java.util.List;

import ru.samples.itis.rxpractice.content.Person;
import rx.Observable;

/**
 * @author Artur Vasilov
 */
public class RxJavaTask4 {

    /**
     * TODO : implement this method
     *
     * This method takes 4 list of persons - you should return one single observable of persons
     * 1) Merge (concat) two fathers list into single fathers observable
     * 2) Repeat step 1 for mothers lists
     * 3) Using these 2 observables create observable of children using Person#makeChild method
     *
     * @param fathers1 - first fathers list
     * @param fathers2 - second fathers list
     * @param mothers1 - first mothers list
     * @param mothers2 - second mothers list
     * @return children list from merged fathers and mothers
     */
    @NonNull
    public static Observable<Person> task4Observable(@NonNull List<Person> fathers1,
                                                     @NonNull List<Person> fathers2,
                                                     @NonNull List<Person> mothers1,
                                                     @NonNull List<Person> mothers2) {
        throw new RuntimeException("Observable not implemented exception");
    }

}























































































/*
return Observable.zip(
                Observable.from(fathers1).concatWith(Observable.from(fathers2)),
                Observable.from(mothers1).concatWith(Observable.from(mothers2)),
                new Func2<Person, Person, Person>() {
                    @Override
                    public Person call(Person person, Person person2) {
                        return person.makeChild(person2);
                    }
                }
        );

          return Observable.zip(
                Observable.from(fathers1).concatWith(Observable.from(fathers2)),
                Observable.from(mothers1).concatWith(Observable.from(mothers2)),
                Person::makeChild);
 */

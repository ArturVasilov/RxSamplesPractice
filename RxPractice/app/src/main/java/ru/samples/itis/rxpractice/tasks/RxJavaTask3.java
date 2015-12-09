package ru.samples.itis.rxpractice.tasks;

import android.support.annotation.NonNull;

import java.util.List;

import ru.samples.itis.rxpractice.content.Person;
import rx.Observable;

/**
 * @author Artur Vasilov
 */
public class RxJavaTask3 {

    /**
     * TODO : implement this method
     *
     * This method takes observable of list of persons as a parameter.
     * Note: observable may have more than one persons list
     *
     * 1) Transform observable of list of persons into single observable of all persons
     * 2) For each person in new observable double person name
     * 3) Remove same persons from observable
     * 4) Create observable of single integer
     *    which represents sum of calls Person#intValue for every persons
     * 5) Return observable from step 4
     *
     * @param observable - observable of persons list
     * @return observable with single integer values
     */
    @NonNull
    public static Observable<Integer> task3Observable(
            @NonNull Observable<List<Person>> observable) {
        throw new RuntimeException("Observable not implemented exception");
    }

}



































































































































/*


        return observable
                .flatMap(new Func1<List<Person>, Observable<Person>>() {
                    @Override
                    public Observable<Person> call(List<Person> persons) {
                        return Observable.from(persons);
                    }
                })
                .map(new Func1<Person, Person>() {
                    @Override
                    public Person call(Person person) {
                        String newName = person.getName() + person.getName();
                        return new Person(newName, person.getAge());
                    }
                })
                .distinct()
                .map(new Func1<Person, Integer>() {
                    @Override
                    public Integer call(Person person) {
                        return person.intValue();
                    }
                })
                .reduce(0, new Func2<Integer, Integer, Integer>() {
                    @Override
                    public Integer call(Integer integer, Integer integer2) {
                        return integer + integer2;
                    }
                });





         return observable
                .flatMap(Observable::from)
                .map(person -> {
                    String newName = person.getName() + person.getName();
                    return new Person(newName, person.getAge());
                })
                .distinct()
                .map(Person::intValue)
                .reduce(0, (integer, integer2) -> integer + integer2);

 */



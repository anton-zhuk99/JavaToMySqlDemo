package org.example.mysqldemo;

import org.example.mysqldemo.config.AppConfiguration;
import org.example.mysqldemo.model.Person;
import org.example.mysqldemo.repository.PersonRepository;

import java.util.List;

public class Main {

    private static final PersonRepository repository;

    static {
        repository = AppConfiguration.getPersonRepository();
    }

    public static void main(String[] args) {
        Person person1 = new Person("John", "Doe");
        Person person2 = new Person("Jack", "Smith");
        Person person3 = new Person("Mary", "Williams");

        repository.save(person1);
        repository.save(person2);
        repository.save(person3);

        Person personFromDatabase = repository.get(2);
        System.out.println("Person from database with id=2:");
        System.out.println(personFromDatabase.toString());

        System.out.println("-------------------------------------");

        List<Person> list = repository.list();
        System.out.println("All persons from DB:");
        System.out.println(list.toString());
    }

}

package services;

import org.jetbrains.annotations.NotNull;
import services.contacts.Contact;
import services.contacts.ContactType;
import services.entities.Entry;
import services.entities.Person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PhoneBook {

    private final Map<Person, Entry> data;

    private static final String ENTRY_IS_ALREADY_ADDED_MESSAGE = "The entry with these parameters already exists";

    private static final String ENTRY_DOES_NOT_EXIST_MESSAGE = "The entry with these parameters doesn't exist";

    public PhoneBook() {
        data = new HashMap<>();
    }

    public void addEntry(@NotNull String name, @NotNull String surname,
                         @NotNull ContactType type, @NotNull String info) {

        Person person = new Person(name, surname);

        if (data.containsKey(person))
            throw new IllegalArgumentException(ENTRY_IS_ALREADY_ADDED_MESSAGE);

        Entry entry = new Entry(new Contact(type, info));
        data.put(person, entry);
    }

    public void addContact(@NotNull String name, @NotNull String surname,
                           @NotNull ContactType type, @NotNull String info) {

        Person person = new Person(name, surname);

        if (!data.containsKey(person))
            throw new IllegalArgumentException(ENTRY_DOES_NOT_EXIST_MESSAGE);

        data.get(person).addContact(new Contact(type, info));
    }

    public void editContact(@NotNull String name, @NotNull String surname,
                            @NotNull ContactType oldType, @NotNull String oldInfo,
                            @NotNull ContactType newType, @NotNull String newInfo) {

        Person person = new Person(name, surname);

        if (!data.containsKey(person))
            throw new IllegalArgumentException(ENTRY_DOES_NOT_EXIST_MESSAGE);

        data.get(person).editContact(new Contact(oldType, oldInfo), new Contact(newType, newInfo));
    }

    public void deleteEntry(@NotNull String name, @NotNull String surname) {
        Person person = new Person(name, surname);

        if (!data.containsKey(person))
            throw new IllegalArgumentException(ENTRY_DOES_NOT_EXIST_MESSAGE);

        data.remove(person);
    }

    public void deleteContact(@NotNull String name, @NotNull String surname,
                              @NotNull ContactType type, @NotNull String info) {

        Person person = new Person(name, surname);

        if (!data.containsKey(person))
            throw new IllegalArgumentException(ENTRY_DOES_NOT_EXIST_MESSAGE);

        data.get(person).deleteContact(new Contact(type, info));
    }

    public String get(@NotNull String name, @NotNull String surname) {
        Person person = new Person(name, surname);

        if (!data.containsKey(person))
            throw new IllegalArgumentException(ENTRY_DOES_NOT_EXIST_MESSAGE);

        return person.toString() + "\n" + data.get(person).getInfo();
    }

    public List<String> find(@NotNull String sequence) {
        List<String> contacts = new ArrayList<>();

        for (Person person : data.keySet()) {
            if (person.toString().toLowerCase().contains(sequence.toLowerCase())
            || data.get(person).toString().toLowerCase().contains(sequence.toLowerCase()))
                contacts.add(person.toString() + "\n" + data.get(person).getInfo());
        }

        return contacts;
    }

    public List<String> getAll() {
        List<String> entries = new ArrayList<>();
        data.keySet().forEach(person -> entries.add(person.toString() + "\n"
                + data.get(person).getInfo()));

        return entries;
    }
}

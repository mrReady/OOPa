package application;

import services.PhoneBook;

import java.util.List;

import static services.contacts.ContactType.MOBILE_PHONE;
import static services.contacts.ContactType.WORK_PHONE;
import static services.contacts.ContactType.HOME_PHONE;
import static services.contacts.ContactType.EMAIL;

public class Application {
    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();

        phoneBook.addEntry("Jordon", "Jocker", EMAIL, "lalulelilo@gmail.com");
        phoneBook.addEntry("Joj", "coc", MOBILE_PHONE, "+78005553535");
        phoneBook.addEntry("Adam", "Eva", HOME_PHONE,"148837");
        phoneBook.addEntry("David", "Hater", EMAIL, "ggwppointcum@gmail.com");

        phoneBook.addContact("Jordon", "Jocker", MOBILE_PHONE, "+43282970517");
        phoneBook.editContact("Adam", "Eva",
                HOME_PHONE, "148837", WORK_PHONE, "+79998887654");

        System.out.println("Get one entry using name and surname as the key");
        System.out.println(phoneBook.get("David", "Hater"));

        phoneBook.deleteContact("David", "Hater", EMAIL, "ggwppointcum@gmail.com");
        phoneBook.deleteEntry("David", "Hater");

        System.out.println("Get entries using char sequence");
        System.out.println("Search for \"jo\":");
        List<String> list = phoneBook.find("jo");
        list.forEach(System.out::println);

        System.out.println("Search for \"55\":");
        list = phoneBook.find("55");
        list.forEach(System.out::println);

        System.out.println("Get all of the entries:");
        list = phoneBook.getAll();
        list.forEach(System.out::println);
    }
}

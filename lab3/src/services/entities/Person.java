package services.entities;

public class Person {
    private String name;
    private String surname;

    public Person (String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    @Override
    public String toString() {
        return name + " " + surname;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (this.getClass() != obj.getClass()) return false;

        Person that = (Person) obj;
        return name.equals(that.name) && surname.equals(that.surname);
    }

    @Override
    public int hashCode() {
        int prime = 27;
        int result = 1;

        result = result * prime + name.hashCode();
        result = result * prime + surname.hashCode();

        return result;
    }
}

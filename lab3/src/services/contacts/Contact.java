package services.contacts;

public class Contact {

    private final ContactType type;
    private final String info;

    private static final String INCORRECT_FORMAT_MESSAGE = "Incorrect format of contact information";

    public Contact(ContactType type, String info) {

        if (type == ContactType.EMAIL && !info.contains("@"))
            throw new IllegalArgumentException(INCORRECT_FORMAT_MESSAGE);
        else if (type != ContactType.EMAIL && info.length() < 6)
            throw new IllegalArgumentException(INCORRECT_FORMAT_MESSAGE);

        this.type = type;
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public ContactType getType() {
        return type;
    }
}

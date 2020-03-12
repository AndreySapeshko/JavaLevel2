import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PhoneBook {
    private HashMap<Person, ArrayList[]> phoneBook;
    private String name;
    private String owner;

    public PhoneBook(String name, String owner) {
        this.name = name;
        this.owner = owner;
        phoneBook = new HashMap<>();
    }

    public PhoneBook(String owner) {
        this.owner = owner;
        phoneBook = new HashMap<>();
    }

    public PhoneBook() {
        phoneBook = new HashMap<>();
    }

    public void setContact(String lastName) {
        Person person = new Person(lastName);
        phoneBook.put(person, person.getContacts());

    }

    public void setContact(String lastName, int numPhone) {
        Person person = new Person(lastName);
        phoneBook.put(person, person.getContacts());
        person.setPhone(numPhone);

    }

    public void setContact(String lastName, String email) {
        Person person = new Person(lastName);
        phoneBook.put(person, person.getContacts());
        person.setEmail(email);

    }

    public void setContact(String lastName, int numPhone, String email) {
        Person person = new Person(lastName);
        phoneBook.put(person, person.getContacts());
        person.setPhone(numPhone);
        person.setEmail(email);

    }

    public void setContact(String lastName, String name, String email) {
        Person person = new Person(lastName);
        person.setName(name);
        phoneBook.put(person, person.getContacts());
        person.setEmail(email);
    }

    public void setContact(String lastName, String name, int nPhone) {
        Person person = new Person(lastName);
        person.setName(name);
        phoneBook.put(person, person.getContacts());
        person.setPhone(nPhone);
    }

    public void setContact(String lastName, String name, int nPhone, String email) {
        Person person = new Person(lastName);
        person.setName(name);
        phoneBook.put(person, person.getContacts());
        person.setPhone(nPhone);
        person.setEmail(email);
    }

    public void getContact(String lastName) {
        for (Map.Entry<Person, ArrayList[]> pair : phoneBook.entrySet()) {
            if (pair.getKey().getLastName().equalsIgnoreCase(lastName)) {
                System.out.println(pair.getKey().toString());
            }
        }
    }

    @Override
    public String toString() {
        String strPhoneBook = "{ PhoneBook name: " + name + ", owner: " + owner ;
            for (Map.Entry<Person, ArrayList[]> pair : phoneBook.entrySet()) {
                strPhoneBook += "\n" + pair.getKey().toString();
            }
            strPhoneBook += " }";
        return strPhoneBook;
    }
}

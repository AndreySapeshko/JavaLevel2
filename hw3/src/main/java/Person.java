import java.util.ArrayList;

public class Person implements Comparable {
    private String name;
    private String lastName;
    private static int countPerson;
    private int id;
    private ArrayList<String> email;
    private ArrayList<Integer> phone;
    private ArrayList[] contacts;

    public Person(String lastName) {
        this.lastName = lastName;
        id = ++countPerson;
        email = new ArrayList<>();
        phone = new ArrayList<>();
        contacts = new ArrayList[2];
        contacts[0] = phone;
        contacts[1] = email;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        String strEmail = "[ ";
        for (int i = 0; i < email.size(); i++) {
            strEmail += email.get(i) + " ";
        }
        return strEmail + "]";
    }

    public String getPhone() {
        String strPhone = "[ ";
        for (int i = 0; i < phone.size(); i++) {
            strPhone += phone.get(i) + " ";
        }
        return strPhone + "]";
    }

    public ArrayList[] getContacts() {
        return contacts;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String strEmail) {
        if (email.contains(strEmail)) {
            System.out.println("Такой e-mail уже записан");
        } else {
            email.add(strEmail);
        }
    }

    public void setPhone(int nPhone) {
        if (phone.contains(nPhone)) {
            System.out.println("Такой номер уже записан");
        } else {
            phone.add(nPhone);
        }
    }

    @Override
    public String toString() {
        return "{ id: " + getId() + " Фаьилия: " + getLastName() + ", имя: " + getName() +
                "\nномера телефонов: " + getPhone() + "\ne-mail: " + getEmail() + " }";
    }

    @Override
    public int compareTo(Object o) {
        int result = 0;
        if (o instanceof Person) {
            if (id == ((Person) o).id) {
                return 0;
            }
            if (!getLastName().equalsIgnoreCase(((Person) o).getLastName())) {
                return 1;
            }
            if (!getName().equalsIgnoreCase(((Person) o).getName())) {
                return 1;
            }
            if (phone.size() > ((Person) o).phone.size() || phone.size() < ((Person) o).phone.size()) {
                return 1;
            } else {
                for (int i = 0; i < phone.size(); i++) {
                    if (phone.get(i) != ((Person) o).phone.get(i)) {
                        return 1;
                    }
                }
            }
            if (email.size() > ((Person) o).email.size() || email.size() < ((Person) o).email.size()) {
                return 1;
            } else {
                for (int i = 0; i < email.size(); i++) {
                    if (!email.get(i).equals(((Person) o).email.get(i))) {
                        return 1;
                    }
                }
            }
        } else {
            return 1;
        }
        return 0;
    }
}

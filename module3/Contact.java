package Module3;

public class Contact {
    String name;
    String phone;

    public Contact(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public String toString() {
        return name + " | " + phone;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }
}

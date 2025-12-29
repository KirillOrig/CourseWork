package model;

public class User {

    private String lastName;
    private String firstName;
    private String middleName;
    private String address;
    private String phone;

    public User(String lastName, String firstName, String middleName,
                String address, String phone) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.address = address;
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public String getLastName() {
        return lastName;
    }

    public String toString() {
        return lastName + ";" + firstName + ";" + middleName + ";" +
                address + ";" + phone;
    }
}

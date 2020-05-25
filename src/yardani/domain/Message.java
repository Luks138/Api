package yardani.domain;

public class Message {

    private String id;
    private String firstName;
    private String lastName;
    private String country;
    private String city;
    private String street;
    private String houseNum;
    private String email;
    private Message address;

    public Message(String id, String firstName, String lastName, String country, String email, Message address) {
        setId(id);
        setFirstName(firstName);
        setLastName(lastName);
        setCountry(country);
        setEmail(email);
        setAddress(address);
    }

    public Message(String street, String houseNum, String city) {
        setStreet(street);
        setHouseNum(houseNum);
        setCity(city);
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setHouseNum(String houseNum) {
        this.houseNum = houseNum;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(Message address) {
        this.address = address;
    }
}

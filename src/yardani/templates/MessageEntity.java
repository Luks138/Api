package yardani.templates;

public class MessageEntity {

    private String id;
    private String firstName;
    private String lastName;
    private String country;
    private String city;
    private String street;
    private String houseNum;
    private String email;
    private MessageEntity address;

    public MessageEntity(String id, String firstName, String lastName, String country, String email, MessageEntity address) {
        setId(id);
        setFirstName(firstName);
        setLastName(lastName);
        setCountry(country);
        setEmail(email);
        setAddress(address);
    }

    public MessageEntity(String street, String houseNum, String city) {
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

    public void setAddress(MessageEntity address) {
        this.address = address;
    }
}

package yardani.templates;

public class MessageEntity {

    private String id;
    private String firstName;
    private String lastName;
    private String country;
    private String city;

    public MessageEntity(String id, String firstName, String lastName, String country, String city) {
        setId(id);
        setFirstName(firstName);
        setLastName(lastName);
        setCountry(country);
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
}

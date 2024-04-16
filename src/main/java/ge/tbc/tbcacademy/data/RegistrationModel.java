package ge.tbc.tbcacademy.data;

public class RegistrationModel {
    private final Integer id;
    private final String firstName;
    private final String lastName;
    private final String gender;

    public RegistrationModel(Integer id, String firstName, String lastName, String gender) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGender() {
        return gender;
    }

    @Override
    public String toString() {
        return "RegistrationModel{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}

package model;

import com.google.common.base.Preconditions;

public class PersonalData{

    private final String name;
    private final String surname;
    private final String email;

    public PersonalData (String name, String surname, String email) {

        Preconditions.checkArgument(surname.matches("[a-zA-Z]{2,}"),"Wrong surname: " + surname);
        Preconditions.checkArgument(name.matches("[a-zA-Z]+"),"Wrong name: " + name);
        Preconditions.checkArgument(email.matches("^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+" +
                "(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$"),"Wrong email: " + email);

        this.name = name;
        this.surname = surname;
        this.email=email;
    }

    @Override
    public String toString() {
        return name + " " + surname + "\n" + email;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }
}
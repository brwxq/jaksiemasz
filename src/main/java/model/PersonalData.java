package model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PersonalData {

    private final String name;
    private final String surname;
    private final String email;
    private final String nationality;
    private final Gender gender;
    private final String academy;
    private final String phone;

    @Builder
    private PersonalData(String name, String surname, String email, String nationality, Gender gender, String academy, String phone) {

        //Preconditions.checkArgument(surname.matches("[a-zA-Z]{2,}"), "Wrong surname: " + surname);
        //Preconditions.checkArgument(name.matches("[a-zA-Z]+"), "Wrong name: " + name);
        //Preconditions.checkArgument(email.matches("^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+" + "(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$"), "Wrong email: " + email);

        this.name = name;
        this.surname = surname;
        this.email = email;
        this.nationality = nationality;
        this.academy = academy;
        this.gender = gender;
        this.phone = phone;
    }

    public enum Gender{
        MALE,FEMALE
    }
}
package model;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public abstract class AbstractEmployee implements IEmployee {
    private final PersonalData personalData;
    private Role role;

    public AbstractEmployee(PersonalData personalData, Role role) {
        this.personalData = personalData;
        this.role = role;
    }
}

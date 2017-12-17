package model;

import lombok.Getter;

@Getter
public abstract class AbstractEmployee implements IEmployee {
    private final PersonalData personalData;
    private Role role;

    public AbstractEmployee(PersonalData personalData, Role role) {
        this.personalData = personalData;
        this.role = role;
    }

    @Override
    public String toString() {
        return personalData.getName() + " " + personalData.getSurname() + " " + getRole();
    }
}

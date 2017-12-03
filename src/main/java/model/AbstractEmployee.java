package model;

public abstract class AbstractEmployee implements IEmployee {
    private final PersonalData personalData;
    private Role role;

    public AbstractEmployee(PersonalData personalData, Role role) {
        this.personalData = personalData;
        this.role = role;
    }

    @Override
    public PersonalData getPersonalData() {
        return personalData;
    }

    @Override
    public Role getRole() {
        return role;
    }

    @Override
    public String toString() {
        return personalData + "\n" + role;
    }
}

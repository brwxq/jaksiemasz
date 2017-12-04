package model;

public interface IEmployee {
    Role getRole();
    PersonalData getPersonalData();
    void assign(Task task);
    Report reportWork();
}

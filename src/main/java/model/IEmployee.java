package model;

import reports.IReport;

public interface IEmployee {
    Role getRole();
    PersonalData getPersonalData();
    void assign(Task task);
    IReport reportWork();
}

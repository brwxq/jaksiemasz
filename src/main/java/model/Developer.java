package model;

import reports.DeveloperReport;
import reports.IReport;

import java.util.LinkedList;
import java.util.List;

public class Developer extends AbstractEmployee {

    private List<Task> tasks = new LinkedList<>();

    public Developer(PersonalData personalData, Role role) {
        super(personalData, role);
    }

    @Override
    public void assign(Task task) {
        tasks.add(task);
    }

    @Override
    public IReport reportWork() {
        return new DeveloperReport(this);
    }

    public List<Task> getTasks() {
        return tasks;
    }
}

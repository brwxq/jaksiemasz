package model;

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
    public Report reportWork() {
        return new Report(this,tasks);
    }

    public List<Task> getTasks() {
        return tasks;
    }
}

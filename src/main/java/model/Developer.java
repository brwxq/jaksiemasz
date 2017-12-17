package model;

import lombok.Builder;
import lombok.Getter;

import java.util.LinkedList;
import java.util.List;

@Getter
public class Developer extends AbstractEmployee {

    private List<Task> tasks = new LinkedList<>();

    @Builder
    private Developer(PersonalData personalData, Role role) {
        super(personalData, role);
    }

    @Override
    public void assign(Task task) {
        tasks.add(task);
    }

    @Override
    public Report reportWork() {
        return new Report(this);
    }

    @Override
    public int getUnitsOfWork() {
        return tasks.stream().mapToInt(Task::getUnitsOfWork).sum();
    }

    @Override
    public void presentWork() {
        System.out.println(this);
        System.out.println("My tasks: ");
        tasks.forEach(e -> System.out.println(e));
    }
}

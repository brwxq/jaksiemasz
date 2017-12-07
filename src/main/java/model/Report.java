package model;

import java.util.LinkedList;
import java.util.List;

public class Report {
    private final IEmployee employee;
    private final List<Task> tasks;

    public Report(IEmployee employee, List<Task> tasks){
        this.employee=employee;
        this.tasks=new LinkedList<>(tasks);
    }

    public void getReport() {
        System.out.println(employee);

        int numberOfAllUnits = tasks.stream()
                .map(Task::getUnitsOfWork)
                .reduce(0,(a, b)->a+b);
        System.out.println("Number of all units of work: " + numberOfAllUnits);
    }

    public void printAllTasks(){
        System.out.println(employee);
        System.out.println("Tasks:");
        tasks.forEach(System.out::println);
    }


}

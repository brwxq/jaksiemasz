package model;

import java.util.LinkedList;
import java.util.List;

public class Report {
    private IEmployee employee;
    private List<Task> tasks = new LinkedList<>();

    public Report(IEmployee employee, List<Task> tasks){
        this.employee=employee;
        this.tasks=tasks;
    }

    public void getReport() {
        System.out.println(employee);

        int numberOfAllUnits = tasks.stream()
                .map(Task::getUnitsOfWork)
                .reduce(0,(a, b)->a+b);
        System.out.println("Number of all units of work: " + numberOfAllUnits);
    }

    public void getAllTasks(){
        System.out.println(employee);
        System.out.println("Tasks:");
        tasks.forEach(System.out::println);
    }


}

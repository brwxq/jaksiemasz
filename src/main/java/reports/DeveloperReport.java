package reports;

import model.Developer;
import model.Task;

public class DeveloperReport implements IReport{

    private Developer developer;

    public DeveloperReport(Developer developer) {
        this.developer = developer;
    }

    @Override
    public void getReport() {
        System.out.println(developer);
        System.out.println("Tasks:");
        int unitsPerDeveloper = 0;
        for(Task task : developer.getTasks()){
            unitsPerDeveloper+=task.getUnitsOfWork();
            System.out.println(task);
        }

        System.out.println("Number of all units: " + unitsPerDeveloper);
        System.out.println("-------------------------");
    }
}

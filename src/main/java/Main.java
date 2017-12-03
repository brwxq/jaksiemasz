import factory.*;
import model.*;

import java.io.IOException;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws IOException{
        PersonalDataFactory factory = new PersonalDataFactory();
        PersonalData data = factory.generate(1).get(0);
        Developer developer = new Developer(data, Role.DEVELOPER);
        Task task1 = new Task(2,"task 1", new Date());
        Task task2 = new Task(2,"task 2", new Date());
        developer.assign(task1);
        developer.assign(task2);
        developer.reportWork().getReport();

        Developer developer2 = new Developer(data, Role.DEVELOPER);
        developer2.assign(task1);
        developer2.assign(task2);
        developer.reportWork().getReport();
    }
}

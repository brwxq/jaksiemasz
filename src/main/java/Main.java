import factory.CompanyFactory;
import model.IEmployee;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException{
        CompanyFactory factory = new CompanyFactory();
        List<IEmployee> employees = factory.generate(100);
        employees.get(0).reportWork().printAllTasks();
    }
}

import factory.CompanyFactory;
import model.IEmployee;
import model.PersonalData;

import java.io.IOException;
import java.util.List;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) throws IOException{
        CompanyFactory factory = new CompanyFactory();
        List<IEmployee> employees = factory.generate(100);
        employees.get(96).reportWork().printReport();


        Predicate<IEmployee> studyOnAGH = e -> e.getPersonalData().getAcademy().equalsIgnoreCase("AGH");
        Predicate<IEmployee> isMale = e -> e.getPersonalData().getGender().equals(PersonalData.Gender.MALE);

    }
}

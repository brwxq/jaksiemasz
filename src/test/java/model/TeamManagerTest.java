package model;

import factory.PersonalDataFactory;
import org.junit.Test;

public class TeamManagerTest {

    @Test(expected = UnsupportedOperationException.class)
    public void cantHireMoreEmployees() throws Exception {
        PersonalDataFactory factory = new PersonalDataFactory();
        PersonalData data = factory.generate(1).get(0);

        Developer developer1 = new Developer(data, Role.DEVELOPER);
        Developer developer2 = new Developer(data, Role.DEVELOPER);
        Developer developer3 = new Developer(data, Role.DEVELOPER);
        TeamManager manager = new TeamManager(data, Role.DEVELOPMENT_MANAGER,2);

        manager.hire(developer1);
        manager.hire(developer2);
        manager.hire(developer3);
    }

    public void hire2() throws Exception {
        PersonalDataFactory factory = new PersonalDataFactory();
        PersonalData data = factory.generate(1).get(0);

        Developer developer1 = new Developer(data, Role.DEVELOPER);
        Developer developer2 = new Developer(data, Role.DEVELOPER);
        Developer developer3 = new Developer(data, Role.DEVELOPER);


    }

}
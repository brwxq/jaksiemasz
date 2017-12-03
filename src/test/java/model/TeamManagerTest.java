package model;

import factory.PersonalDataFactory;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class TeamManagerTest {
    @Test
    public void hire() throws Exception {
        PersonalDataFactory factory = new PersonalDataFactory();
        PersonalData data = factory.generate(1).get(0);

        Developer developer = new Developer(data, Role.DEVELOPER);
        TeamManager manager = new TeamManager(data, Role.DEVELOPMENT_MANAGER,10);
        manager.hire(developer);

        assertTrue(manager.getEmployees().get(0)==developer);

        developer.reportWork().getReport();
    }

}
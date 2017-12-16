package model;

import factory.PersonalDataFactory;
import org.junit.Test;

public class TeamManagerTest {

    @Test(expected = UnsupportedOperationException.class)
    public void cantHireMoreEmployees() throws Exception {
        PersonalDataFactory factory = new PersonalDataFactory();
        PersonalData data = factory.generate(1).get(0);

        Developer developer1 = Developer.builder()
                .personalData(data)
                .role(Role.DEVELOPER)
                .build();

        Developer developer2 = Developer.builder()
                .personalData(data)
                .role(Role.DEVELOPER)
                .build();

        Developer developer3 = Developer.builder()
                .personalData(data)
                .role(Role.DEVELOPER)
                .build();

        TeamManager manager = TeamManager.builder()
                .personalData(data)
                .role(Role.DEVELOPMENT_MANAGER)
                .limit(2)
                .build();

        manager.hire(developer1);
        manager.hire(developer2);
        manager.hire(developer3);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void hire2() throws Exception {
        PersonalDataFactory factory = new PersonalDataFactory();
        PersonalData data = factory.generate(1).get(0);

        Developer developer1 = Developer.builder()
                .personalData(data)
                .role(Role.DEVELOPER)
                .build();

        Developer developer2 = Developer.builder()
                .personalData(data)
                .role(Role.DEVELOPER)
                .build();

        TeamManager manager = TeamManager.builder()
                .personalData(data)
                .role(Role.DEVELOPMENT_MANAGER)
                .limit(2)
                .build();

        manager.hire(developer1);
        manager.hire(developer1);
        manager.hire(developer2);
    }

}
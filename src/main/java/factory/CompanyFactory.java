package factory;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import model.*;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class CompanyFactory {
    private final String PATH_TO_TASKS_FILE = "./src/main/java/resources/tasks";
    private Iterator<PersonalData> it;
    private Random random = new Random();

    public List<IEmployee> generate(int limit) throws IOException{
        PersonalDataFactory personalDataFactory = new PersonalDataFactory();
        List<PersonalData> personalDatas = personalDataFactory.generate(limit);
        it = personalDatas.iterator();

        List<IEmployee> randomEmployees = new LinkedList<>();
        int size = limit/6; //assume 5:1 managers:developers
        TeamManager ceo = new TeamManager(it.next(), Role.CEO, limit);
        List<TeamManager> managers = generateManagers(size);
        List<Developer> developers = generateDevelopers(limit-size-1);

        managers.forEach(ceo::hire);

        TeamManager randomManager;
        for(Developer developer : developers){
             randomManager = managers.get(random.nextInt(managers.size()));
             randomManager.hire(developer);
        }

        List<Task> tasks = generateTasks(limit);
        for(Task task : tasks){
            randomManager = managers.get(random.nextInt(managers.size()));
            randomManager.assign(task);
        }

        randomEmployees.addAll(managers);
        randomEmployees.addAll(developers);
        randomEmployees.add(ceo);

        managers.get(0).reportWork().getReport();

        return randomEmployees;
    }

    private List<Task> generateTasks(int limit) throws IOException {
        List<Task> tasks = new LinkedList<>();
        List<String> taskStrings = Files.readLines(new File(PATH_TO_TASKS_FILE), Charsets.UTF_8);
        long DAY_IN_MS = 1000 * 60 * 60 * 24;

        for(int i=0; i<limit*5; i++){
            Date randomDeadlineDate = new Date(System.currentTimeMillis()+random.nextInt(14)*DAY_IN_MS);

            tasks.add(new Task(random.nextInt(10),
                    taskStrings.get(random.nextInt(taskStrings.size())),
                    randomDeadlineDate));
        }
        return tasks;
    }

    private List<TeamManager> generateManagers(int limit){
        List<TeamManager> managers = new LinkedList<>();

        for(int i=0; i<limit-1; i++) {
            managers.add(new TeamManager(it.next(), Role.DEVELOPMENT_MANAGER, limit));
        }
        return managers;
    }

    private List<Developer> generateDevelopers(int limit){
        List<Developer> developers = new LinkedList<>();
        List<Role> roles = new LinkedList<>();
        roles.add(Role.DEVELOPER);
        roles.add(Role.CONTRIBUTOR);
        roles.add(Role.TEAM_LEADER);
        roles.add(Role.TESTER);

        for(int i=0; i<limit; i++) {
            developers.add(new Developer(it.next(),roles.get(random.nextInt(roles.size()))));
        }
        return developers;
    }
}

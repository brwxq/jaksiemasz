package model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;


@Getter
public class TeamManager extends AbstractEmployee implements IManager {

    private List<IEmployee> employees;
    private int limit;

    @Getter(AccessLevel.NONE)
    private final Random random = new Random();

    @Builder
    private TeamManager(PersonalData personalData, Role role, int limit) {
        super(personalData, role);
        this.limit = limit;
        this.employees = new LinkedList<>();
    }

    @Override
    public void hire(IEmployee employee) {
        if(canHire()) {
            if(!employees.contains(employee)) {
                employees.add(employee);
            } else {
                throw new UnsupportedOperationException(getNameAndSurname(employee) + " is already employed.");
            }
        } else {
            throw new UnsupportedOperationException(getNameAndSurname(this) + " can't hire more employees!");
        }
    }

    @Override
    public void fire(IEmployee employee) {
        if(employees.contains(employee)){
            employees.remove(employee);
        } else throw new NoSuchElementException(
                getNameAndSurname(this) + " doesn't employ " + getNameAndSurname(employee));
    }

    @Override
    public boolean canHire() {
        return limit > employees.size();
    }

    @Override
    public void assign(Task task) {
        if(!employees.isEmpty()) {
            IEmployee employee = employees.get(random.nextInt(employees.size()));
            employee.assign(task);
        } else throw new UnsupportedOperationException(getNameAndSurname(this) + " don't have employees.");
    }

    @Override
    public Report reportWork() {
        List<Task> tasks = new LinkedList<>();

        for(IEmployee e : employees){
            if(e instanceof Developer) {
                Developer developer = (Developer) e;
                tasks.addAll(developer.getTasks());
            }
        }
        return new Report(this,tasks);
    }

    private String getNameAndSurname(IEmployee e){
        return e.getPersonalData().getName() + " " + e.getPersonalData().getSurname();
    }
}

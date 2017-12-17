package model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.function.Predicate;


@Getter
public class TeamManager extends AbstractEmployee implements IManager {

    private List<IEmployee> employees;
    private int limit;
    List<Predicate<IEmployee>> preferences;

    @Getter(AccessLevel.NONE)
    private final Random random = new Random();

    @Builder
    protected TeamManager(PersonalData personalData, Role role, int limit) {
        super(personalData, role);
        this.limit = limit;
        this.employees = new LinkedList<>();
        this.preferences = new LinkedList<>();
    }

    @Override
    public void hire(IEmployee employee) {
        if(canHire(employee)) {
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
    public boolean canHire(IEmployee employee) {
        return (limit==0 || limit > employees.size())
                && (preferences.isEmpty() || preferences.stream().anyMatch(p -> p.test(employee)));
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
        return new Report(this,employees);
    }

    private String getNameAndSurname(IEmployee e){
        return e.getPersonalData().getName() + " " + e.getPersonalData().getSurname();
    }

    @Override
    public int getUnitsOfWork() {
        return employees.stream().mapToInt(IEmployee::getUnitsOfWork).sum();
    }

    @Override
    public void addPreference(Predicate<IEmployee> p) {
        preferences.add(p);
    }

    @Override
    public void presentWork() {
        System.out.println(this);
        System.out.format("My workers: \n");
        employees.forEach(e -> System.out.format("%-15s\n",e));
    }
}
